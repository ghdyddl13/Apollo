package kr.or.apollo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.apollo.service.MemberService;
import kr.or.apollo.vo.MemberDTO;

@Controller
public class MemberController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private View jsonview;
	
	
	@RequestMapping(value = "midcheck.htm", method = RequestMethod.POST)
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
	
	
	public View apollokeyCheck(String s2) {
		return null;
	}

	@RequestMapping(value="join.htm",method=RequestMethod.GET)
	public String memberInsert() {
		System.out.println("join(get)");
		return "join";
	}
	
	@RequestMapping(value="join.htm",method=RequestMethod.POST)
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
	@RequestMapping(value="login.htm",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="login.htm",method=RequestMethod.POST)
	public String login2(String mid, String pwd, Model model) {
		System.out.println("로그인처리");
		String result="";
		String msg = "";
		String securitypwd = service.getlogin(mid);
		if(securitypwd == null) {
			System.out.println("아이디 존재 안함");
			msg = "아이디가 존재하지 않습니다.";
			result = "login";
		}else {
			if(bCryptPasswordEncoder.matches(pwd, securitypwd)) {
			    System.out.println("비밀번호 일치");
			    result = "redirect:/information.htm";
			}else { 
			    System.out.println("비밀번호 불일치");
			    msg="비밀번호가 일치하지 않습니다.";
			    result = "login";
			}
		}
		model.addAttribute("msg", msg);
		  
		return result;
	}
	
	public String createApollokey(String s1, String s2, Model model) {
		
		return null;
		}
	
	public String changeProfile(MemberDTO memberdto, Model model) {
		
		return null;
	}
	
	public String showMember(String s1, Model model) {
		return null;
	
	}
}