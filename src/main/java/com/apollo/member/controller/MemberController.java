package com.apollo.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.apollo.inbox.dao.InboxDAO;
import com.apollo.inbox.service.InboxService;
import com.apollo.member.service.MemberService;
import com.apollo.vo.AuthkeyDTO;
import com.apollo.vo.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.mail.iap.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.ui.velocity.VelocityEngineUtils;




@Controller
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Autowired
	private InboxService inboxservice;
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;
	
	HttpSession session;

	/**
	 * 
	 날      짜 : 2018. 7. 2.
	 기      능 : 소개페이지
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/index.htm")
	public String intro() {
		return "intro";
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : 구글로그인 후 main 페이지 이동하는 컨트롤러
	 작성자명 : 이 창 훈
	 */
	@RequestMapping("/main.htm")
	public String main(Model model,  HttpServletRequest request) {
		
		String mid = (String) request.getSession().getAttribute("mid");
		 MemberDTO memberdto = service.getProfileInfoMember(mid);
		 model.addAttribute("memberdto", memberdto);
		int newcount = inboxservice.newCount(mid);
		model.addAttribute("newcount", newcount);
		return "main";
	}
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입에서 아이디 중복체크 확인 
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value = "/midcheck.htm", method = RequestMethod.POST)
	public View midCheck(String mid, Model model) {
		int result = service.midcheck(mid);
		if (result > 0) {
			model.addAttribute("result", "fail");
		} else {
			model.addAttribute("result", "success");
		}
		return jsonview;

	}
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입에서 인증키 확인 
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value = "/keycheck.htm", method = RequestMethod.POST)
	public View keyCheck(String apollokey, Model model) {
		int result = service.keycheck(apollokey);
		if (result > 0) {
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : 메일로 보낸 인증코드로 메일 인증하는 컨트롤러 
	 작성자명 : 이 창 훈
	 */
	@RequestMapping(value="/emailcheck.htm",method=RequestMethod.GET)
	public String emailcheck(String emailcheckkey, String mid) {
		System.out.println("emailcheckkey : " + emailcheckkey);
		System.out.println("mid : " + mid);
		String emailcheckbymid = service.emailcheckbymid(mid);
		if(emailcheckkey.equals(emailcheckbymid)) {
			service.emailcheck(mid);
		}
		return "login";
	}
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입 페이지로 이동
	 작성자명 : 신 호 용
	 */
	
	@RequestMapping(value="/join.htm",method=RequestMethod.GET)
	public String memberInsert() {
		return "join";
	}
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입 
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/joinform.htm", method=RequestMethod.POST)
	public String memberInsert(MemberDTO memberdto, Model model) {
		int result = 0;
		String viewpage="";
		String emailcheckkey = "";
		String msg="";
		for (int i = 0; i < 8; i++) {
			char lowerStr = (char) (Math.random() * 26 + 97);
			if (i % 2 == 0) {
				emailcheckkey += (int) (Math.random() * 10);
			} else {
				emailcheckkey += lowerStr;
			}
		}
		memberdto.setEmailcheck(emailcheckkey);
		memberdto.setPwd(this.bCryptPasswordEncoder.encode(memberdto.getPwd()));		
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper1 = new MimeMessageHelper(message, true, "utf-8"); // true로 해야 첨부파일 추가 가능
			messageHelper1.setSubject("안녕하세요, Apollo 입니다. 인증 메일 보내드립니다.");

			String templateLocation1 = "emailcheck.vm";


			Map<String, Object> vmmodel = new HashMap<String, Object>();
			vmmodel.put("mid", memberdto.getMid());
			vmmodel.put("emailcheckkey", memberdto.getEmailcheck());
			vmmodel.put("mname", memberdto.getMname());

			String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation1, "UTF-8",
					vmmodel);

			messageHelper1.setText(content, true);
			

			messageHelper1.setFrom("apolloyy104@gmail.com", "Apollo");
			messageHelper1.setTo(new InternetAddress(memberdto.getMid()));
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = service.insertMember(memberdto);
		if(result > 0) {
			msg="입력하신 E-Mail로 인증메일을 전송했습니다. 메일 인증을 하지 않으면 로그인을 할 수 없습니다.";
			viewpage = "login";
		}else {
			viewpage = "join.htm";
		}
		model.addAttribute("msg", msg);
		return viewpage; //주의 (website/index.htm
	}	
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 로그인 페이지로 이동 (현재는 index.htm와 같다.. main페이지 생기면  index.htm수정)
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/login.htm",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 로그인  
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	public String login(String mid, String pwd, Model model,HttpSession session) throws Exception {
		String ischecked = service.ischecked(mid);
		String result="";
		String msg = "";
		int newcount = 0;
		String securitypwd = service.getlogin(mid);
		if(securitypwd == null) {
			System.out.println("아이디 존재 안함");
			msg = "아이디가 존재하지 않습니다.";
			result = "login";
		}else {
			if (bCryptPasswordEncoder.matches(pwd, securitypwd)) {
				if (ischecked.equals("y")) {
					System.out.println("비밀번호 일치");

					MemberDTO memberdto = service.getProfileInfoMember(mid);
					session.setAttribute("mid", mid);
					model.addAttribute("memberdto", memberdto);
					result = "main";
					int check = service.freeTrialCheck(mid);
					if (check == 1) {
						result = "login";
						msg = "2주의 무료체험기간이 만료되었습니다.";

					} else {
						///////////////이거 main.htm에 넣으면 아마 끝?
						newcount = inboxservice.newCount(mid);
						session.setAttribute("mid", mid);
						result = "main";

					}
				}
				else {
					 msg = "인증되지 않은 이메일입니다. 가입 당시 입력하신 E-Mail을 통해 인증해주세요";
			         result = "login";
				}
			} else {
				System.out.println("비밀번호 불일치");
				msg = "비밀번호가 일치하지 않습니다.";
				result = "login";
			}
		}
		model.addAttribute("newcount", newcount);
		model.addAttribute("msg", msg);
		
		  
		return result;
	}


	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 로그아웃
	 작성자명 : 신 호 용
	 */
	@RequestMapping("/logout.htm")
	public String logout(HttpSession session) {
		 session.invalidate();
	     return "login";
	
	}
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 구매하기
	 작성자명 : 이 창 훈
	 */
	@RequestMapping(value = "/apollokey.htm", method = RequestMethod.POST)
	public View createApollokey(AuthkeyDTO authkeydto, Model model)throws Exception {
		int result = 0;
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper1 = new MimeMessageHelper(message, true, "utf-8"); // true로 해야 첨부파일 추가 가능
			messageHelper1.setSubject("안녕하세요, Apollo 입니다. 인증키 보내드립니다.");

			String templateLocation1 = "officeKey.vm";

			String apollokey = "";
			for (int i = 1; i <= 16; i++) {
				 char upperStr = (char)(Math.random() * 26 + 65);
				if (i % 2 == 0) {
					apollokey += (int) (Math.random() * 10);
				} else {
					apollokey += upperStr;
				}
				if(i==16) {
					apollokey += "";
				}else if(i%4==0) {
					apollokey += "-";
				}
			}

			Map<String, Object> vmmodel = new HashMap<String, Object>();
			vmmodel.put("email", authkeydto.getEmail());
			vmmodel.put("apollokey", apollokey);
			vmmodel.put("cname", authkeydto.getCname());

			String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation1, "UTF-8",
					vmmodel);

			messageHelper1.setText(content, true);
			

			messageHelper1.setFrom("apolloyy104@gmail.com", "Apollo");
			messageHelper1.setTo(new InternetAddress(authkeydto.getEmail()));
			javaMailSender.send(message);
			
			//AuthkeyDTO에 임의의 난수 16자리 apollokey값 저장하기
			authkeydto.setApollokey(apollokey);
			
			 result = service.createApollokey(authkeydto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(result > 0 ) {
			model.addAttribute("result","성공");
			return jsonview;
		}
		return jsonview;
	}
	
	public String changeProfile(MemberDTO memberdto, Model model) {
		
		return null;
	}
	
	public String showMember(String s1, Model model) {
		return null;
	
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : Password 찾는 controller  
	 작성자명 : 이 창 훈
	 * 
	 */
	@RequestMapping(value = "/findpwd.htm", method = RequestMethod.POST)
	public View findpwd(MemberDTO memberdto, Model model){
		String result = service.findpwdidcheck(memberdto.getMid());
		if(result == null) {
			model.addAttribute("result","완전실패");
			return jsonview;
		}else {
			if(result.equals(memberdto.getMname())) {
				MimeMessage message = javaMailSender.createMimeMessage();
				try {
					MimeMessageHelper messageHelper1 = new MimeMessageHelper(message, true, "utf-8"); // true로 해야 첨부파일 추가 가능
					messageHelper1.setSubject("안녕하세요, Apollo 입니다. 임시 비밀번호 보내드립니다.");
					String templateLocation = "findPwd.vm";

					String pwd = "";
					for (int i = 0; i < 8; i++) {
						char lowerStr = (char) (Math.random() * 26 + 97);
						if (i % 2 == 0) {
							pwd += (int) (Math.random() * 10);
						} else {
							pwd += lowerStr;
						}
					}
					//MemberDTO에 임의의 난수 8자리 pwd값 저장하기
					memberdto.setPwd(this.bCryptPasswordEncoder.encode(pwd));
					
					Map<String, Object> vmmodel = new HashMap<String, Object>();
					vmmodel.put("mid", memberdto.getMid());
					vmmodel.put("pwd", pwd);

					
					String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8",
							vmmodel);

					messageHelper1.setText(content, true);

					messageHelper1.setFrom("apolloyy104@gmail.com", "Apollo");
					messageHelper1.setTo(new InternetAddress(memberdto.getMid()));
					javaMailSender.send(message);
					
					service.findpwd(memberdto);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model.addAttribute("result","성공");
				return jsonview;
				
			}else {
				model.addAttribute("result","실패");
				return jsonview;
			}
		}
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 태스크  담당자 명단 구하기 
	 작성자명 : 박 민 식
	 */
	@RequestMapping("/getTaskAssignees.htm")
	public View getTaskAssignees(String tid, Model model){
		ArrayList<MemberDTO> taskassignees = null;;
		try {
			taskassignees= service.getTaskAssignees(tid);
			model.addAttribute("taskassignees", taskassignees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}
	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 프로필 사진 클릭시 프로필모달에 들어갈 멤버정보 불러오기
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/profilemember.htm")
	public View getProfileInfoMember(String mid, Model model) {
		MemberDTO profileinfo = null;
		try {
			profileinfo = service.getProfileInfoMember(mid);
			model.addAttribute("profileinfo", profileinfo);
			System.out.println("profileinfo : " + profileinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}

	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 개인정보수정을 위한 정보 불러오기
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/updatememberinfo.htm")
	public View updateMemberInfo(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		String mid = (String) request.getSession().getAttribute("mid");
		model.addAttribute("mid", mid);
		MemberDTO updatememberinfo = null;
		
		try {
			updatememberinfo = service.updateMemberInfo(mid);
			model.addAttribute("updatememberinfo", updatememberinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 개인정보수정
	 작성자명 : 김 래 영
	 */
	@RequestMapping(value="/updatemember.htm", method=RequestMethod.POST)
	public View updateMemberInfo(MemberDTO memberdto, Model model, MultipartHttpServletRequest mrequest) {
		int updatemember = 0;
		System.out.println(memberdto);
		
		//경로 설정
		String path = "C:\\bitcamp104\\Final\\Apollo\\src\\main\\webapp\\profile";
		
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		} //경로가 지정되어 있지 않은 경우 자동 폴더 생성
		
		try {
			Iterator<String> files = mrequest.getFileNames(); //업로드된 파일들의 이름 목록 가져오기
			while(files.hasNext()) {
				String image = files.next();
				MultipartFile mfile = mrequest.getFile(image); //param 이 image 인 파일 정보 
				String filename = mfile.getOriginalFilename();
				System.out.println(filename);
				
				mfile.transferTo(new File(path + filename)); //원하는 위치에 해당 파일명으로 저장됨
				memberdto.setImage(memberdto.getImage());
				
				updatemember = service.updateMemberInfo(memberdto);
				model.addAttribute("updatemember", updatemember);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 개인정보수정 modal 에서 인증키 확인
	 작성자명 : 김 래 영
	 */
	@RequestMapping(value = "/updatekeycheck.htm", method = RequestMethod.POST)
	public View updatekeycheck(String apollokey, Model model) {
		int result = service.keycheck(apollokey);
		if (result > 0) {
			System.out.println("키 인증 성공");
			model.addAttribute("result", "success");
		} else {
			System.out.println("키 인증 실패");
			model.addAttribute("result", "fail");
		}
		return jsonview;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 개인정보수정 modal 에서 비밀번호 변경
	 작성자명 : 김 래 영
	 */
	@RequestMapping(value="/updatepwd.htm", method=RequestMethod.POST)
	public View updatePwd(String cpwd, String upwd, HttpServletRequest request, Model model, HttpSession session) {
		String mid = (String) request.getSession().getAttribute("mid");
		model.addAttribute("mid", mid);
		
		MemberDTO memberdto = null;
		int result = 0;
		int count =0;
		try {
			//member 정보 불러오기
			memberdto = service.updateMemberInfo(mid);
			
			//암호화된 비밀번호를 DB에서 불러오기
			if(bCryptPasswordEncoder.matches(cpwd, memberdto.getPwd())) {
				result = 1; //현재 비밀번호와 DB 비변이 일치할 경우 1을 리턴
			}else {
				result = 0; //불일치할 경우 0 리턴
			}
			model.addAttribute("result", result);
	
			//비밀번호 업데이트하기 
			if(result == 1) { // 현재 비밀번호와 DB에 저장된 비밀번호가 일치할 경우 
				memberdto.setPwd(this.bCryptPasswordEncoder.encode(upwd));
				count = service.updatePwd(memberdto);
				System.out.println("비밀번호 변경 완료");
			}
			model.addAttribute("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 같은 인증키를 가진 사원목록 가져오기
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/selectmemberlist.htm")
	public String selectMemberList(HttpServletRequest request, Model model, HttpSession session) {
		String mid = (String) request.getSession().getAttribute("mid");
		model.addAttribute("mid", mid);
		
		ArrayList<MemberDTO> memberlist = null;
		try {
			memberlist = service.selectMemberList(mid);
			model.addAttribute("memberlist", memberlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "memberlist";
		
	}

}