package kr.or.apollo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

@Controller
public class ProjectInfoController {
	
	@RequestMapping("/information.htm")
	public String projectInfoShow(String s1) {
		
		System.out.println("dd");
		
		return "project/information";
	}
	
	public View projectMemberSearch(String s1) {
		return null;
	}
	
	public View projectMemberAdd(String s1) {
		
		return null;
	}
	
}
