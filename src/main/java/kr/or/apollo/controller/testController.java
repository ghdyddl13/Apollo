package kr.or.apollo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/index.htm")
	public String Main() {
		
		return "home.login";
	}
	
	@RequestMapping("/board.htm")
	public String board() {
		
		return "step.board";
	}
	
	@RequestMapping("/list.htm")
	public String list() {
		
		return "step.list";
	}
	
	@RequestMapping("/timeline.htm")
	public String timeLine() {
		
		return "step.timeline";
	}
	
	@RequestMapping("/workload.htm")
	public String workLoad() {
		
		return "step.workload";
	}
}
