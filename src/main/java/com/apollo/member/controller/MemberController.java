package com.apollo.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.apollo.member.service.MemberService;
import com.apollo.vo.AuthkeyDTO;
import com.apollo.vo.MemberDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
	 날      짜 : 2018. 6. 14.
	 기      능 : 로그인 페이지로가는 index컨트롤러 
	 작성자명 : 신 호 용
	 */
	@RequestMapping("/index.htm")
	public String index() {
		System.out.println("login");
		return "login";
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입에서 아이디 중복체크 확인 
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value = "/midcheck.htm", method = RequestMethod.POST)
	public View midCheck(String mid, Model model) {
		System.out.println("아이디 체크");
		int result = service.midcheck(mid);
		
		if (result > 0) {
			System.out.println("아이디 중복");
			model.addAttribute("result", "fail");
		} else {
			System.out.println("삽입 실패");
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
		System.out.println("키 체크");
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
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입 페이지로 이동
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/join.htm",method=RequestMethod.GET)
	public String memberInsert() {
		System.out.println("join(get)");
		return "join";
	}
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 회원가입 
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/join.htm",method=RequestMethod.POST)
	public String memberInsert(MemberDTO memberdto, Model model) {
		int result = 0;
		String viewpage="";
		System.out.println("join(post)");
		memberdto.setPwd(this.bCryptPasswordEncoder.encode(memberdto.getPwd()));
		result = service.insertMember(memberdto);
		
		if(result > 0) {
			System.out.println("가입성공");
			viewpage = "login";
		}else {
			System.out.println("가입실패");
			viewpage = "join.htm";
		}
		
		return viewpage; //주의 (website/index.htm
	}	
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 로그인 페이지로 이동 (현재는 index.htm와 같다.. main페이지 생기면  index.htm수정)
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/login.htm",method=RequestMethod.GET)
	public String login() {
		System.out.println("login");
		return "login";
	}
	
	/**
	 날      짜 : 2018. 6. 14.
	 기      능 : 로그인  
	 작성자명 : 신 호 용
	 */
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	public String login(String mid, String pwd, Model model,HttpSession session) {
		System.out.println("로그인처리");
		String result="";
		String msg = "";
		String securitypwd = service.getlogin(mid);
		System.out.println(mid);
		if(securitypwd == null) {
			System.out.println("아이디 존재 안함");
			msg = "아이디가 존재하지 않습니다.";
			result = "login";
		}else {
			if(bCryptPasswordEncoder.matches(pwd, securitypwd)) {
			    System.out.println("비밀번호 일치");
			    session.setAttribute("mid", mid);
			    result = "main";
			}else { 
			    System.out.println("비밀번호 불일치");
			    msg="비밀번호가 일치하지 않습니다.";
			    result = "login";
			}
		}
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
	public String createApollokey(AuthkeyDTO authkeydto) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper1 = new MimeMessageHelper(message, true, "utf-8"); // true로 해야 첨부파일 추가 가능
			messageHelper1.setSubject("안녕하세요, Apollo 입니다.");

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
			
			service.createApollokey(authkeydto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/index.htm";
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
		System.out.println(memberdto.getMid()+"-----------");
		String result = service.findpwdidcheck(memberdto.getMid());
		System.out.println(memberdto.getMname());
		System.out.println(memberdto.getMid());
		System.out.println(result);
		
		if(result == null) {
			model.addAttribute("result","완전실패");
			return jsonview;
		}else {
			
			if(result.equals(memberdto.getMname())) {
				MimeMessage message = javaMailSender.createMimeMessage();
				
				try {
					MimeMessageHelper messageHelper1 = new MimeMessageHelper(message, true, "utf-8"); // true로 해야 첨부파일 추가 가능
					messageHelper1.setSubject("안녕하세요, Apollo 입니다.");
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
		System.out.println("프로필에 들어갈 멤버 정보 컨트롤러 들어옴");
		System.out.println("mid : " + mid);
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
}