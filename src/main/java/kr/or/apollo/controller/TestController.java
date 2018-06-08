package kr.or.apollo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/index.htm")
	public String main() {
		System.out.println("test");
		return "home.login";
	}
	
	@RequestMapping("/login.htm")
	public String login() {
		System.out.println("dd");
		return "project.information";
	}
	
	@RequestMapping("/file.htm")
	public String projectFilelist() {
		return "project.files";
	}
	
	@RequestMapping("/information.htm")
	public String projectInformation() {
		return "project.information";
	}
	
	@RequestMapping("/table.htm")
	public String projectTable() {
		return "project.table";
	}
	
	@RequestMapping("/inbox.htm")
	public String inbox() {
		return "header.inbox";
	}
	
	@RequestMapping("/myWork.htm")
	public String myWork() {
		return "header.myWork";
	}
	
	@RequestMapping("/report.htm")
	public String report() {
		return "header.report";
	}
	
	@RequestMapping("/starredTask.htm")
	public String starredTask() {
		return "header.starredTask";
	}
	
	@RequestMapping("/stream.htm")
	public String stream() {
		return "header.stream";
	}
	
}
