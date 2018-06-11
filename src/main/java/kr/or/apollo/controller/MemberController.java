package kr.or.apollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.apollo.service.MemberService;
import kr.or.apollo.vo.MemberDTO;

@Controller
public class MemberController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private MemberService service;
	
	public View midCheck(String s1) {
		return null;
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
			viewpage = "redirect:/information.htm";
		}else {
			System.out.println("가입실패");
			viewpage = "join.htm";
		}
		
		return viewpage; //주의 (website/index.htm
	}
	
	public String login(MemberDTO memberdto, Model model) {
		return null;
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