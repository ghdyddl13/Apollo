package com.apollo.inbox.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.step.service.StepListService;

@Controller
public class TestController {
	
	/*@RequestMapping("/index.htm")
	public String main() {
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
	
	@RequestMapping("/file.htm")
	public String projectFilelist() {
		return "project/files";
	}
	

	@RequestMapping("/table.htm")
	public String projectTable() {
		return "project/table";
	}
	
	@RequestMapping("/inbox.htm")
	public String inbox() {
		System.out.println("inbox 컨트롤러 탓다");
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
    
    @RequestMapping("/list.htm")
    public String list(int sid, HttpServletRequest request) {
       
    	System.out.println(sid);
    	
        StepListService service = new StepListService();
        int pid = service.getProjectIdByStepId(sid);
        request.getSession().setAttribute("sid", sid);
        request.getSession().setAttribute("pid", pid);
        
        return "step/list";
    }
    
    
    @RequestMapping("/workload.htm")
    public String workLoad() {
        
        return "step/workload";
    }
	
}
