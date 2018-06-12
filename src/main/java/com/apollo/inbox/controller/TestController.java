package com.apollo.inbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/index.htm")
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
	}
	
	@RequestMapping("/file.htm")
	public String projectFilelist() {
		return "project/files";
	}
	
	@RequestMapping("/information.htm")
	public String projectInformation() {
		return "project/information";
	}
	
	@RequestMapping("/table.htm")
	public String projectTable() {
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
        System.out.println("list"); 
        
        return "step/list";
    }
    
    
    @RequestMapping("/workload.htm")
    public String workLoad() {
        
        return "step/workload";
    }
	
}
