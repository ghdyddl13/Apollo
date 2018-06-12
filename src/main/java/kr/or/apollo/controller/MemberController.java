package kr.or.apollo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.apollo.service.MemberService;
import kr.or.apollo.vo.AuthkeyDTO;
import kr.or.apollo.vo.MemberDTO;



@Controller
public class MemberController {


	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	 
	@Autowired
	private MemberService service;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private View jsonview;
	public View midCheck(String s1) {
		
		return null;
	}
	
	public View apollokeyCheck(String s2) {
		
		return null;
	}
	
	
	public String memberInsert(MemberDTO memberdto, Model model) {
		
		return null;
	}
	

	public String login(MemberDTO memberdto, Model model) {
		return null;
	}
	

	@RequestMapping(value = "apollokey.htm", method = RequestMethod.POST)
	public String createApollokey(AuthkeyDTO authkeydto, HttpServletRequest request) {
		MimeMessage message = javaMailSender.createMimeMessage();
		// String baseUrl = request.getServletContext().getRealPath("/");

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
	@RequestMapping(value = "findpwd.htm", method = RequestMethod.POST)
	public View findpwd(MemberDTO memberdto, HttpServletRequest request, Model model){
		String result = service.findpwdidcheck(memberdto.getMid());
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
				
			}
			else {
				model.addAttribute("result","실패");
				return jsonview;
			}
		}
	
		
	}
}