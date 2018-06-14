package com.apollo.inbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	/*@RequestMapping("/index.htm")
	public String main() {
		System.out.println("login");
		return "login";
	}
	
	@RequestMapping("/join.htm")
	public String join() {
		return "join";
	}

	@RequestMapping("/login.htm")
	public String login() {
		return "main";
	}*/
	
	@RequestMapping("/files.htm")
	public String projectFilelist() {
		System.out.println("files");
		return "project/files";
	}
	
	
	@RequestMapping("/table.htm")
	public String projectTable() {
		System.out.println("table");
		return "project/table";
	}
	
	@RequestMapping("/inbox.htm")
	public String inbox() {
		return "header/inbox";
	}
	
	
	@RequestMapping("/report.htm")
	public String report() {
		return "header/report";
	}
	
	@RequestMapping("/starredTask.htm")
	public String starredTask() {
		return "header/starredTask";
	}
	
	@RequestMapping("/stream.htm")
	public String stream() {
		return "header/stream";
	}
	@RequestMapping("/board.htm")
    public String board() {
        
        return "step/board";
    }
    
    @RequestMapping("/list.htm")
    public String list() {
        
        return "step/list";
    }
    
    @RequestMapping("/timeline.htm")
    public String timeLine() {
        
        return "step/timeline";
    }
    
    @RequestMapping("/workload.htm")
    public String workLoad() {
        
        return "step/workload";
    }
}
