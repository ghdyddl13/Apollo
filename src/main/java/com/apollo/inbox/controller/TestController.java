package com.apollo.inbox.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.step.service.StepListService;

@Controller
public class TestController {
	
	@RequestMapping("/file.htm")
	public String projectFilelist() {
		return "project/files";
	}
	

	@RequestMapping("/table.htm")
	public String projectTable() {
		return "project/table";
	}
	

	@RequestMapping("/report.htm")
	public String report() {
		return "header/report";
	}
	
	@RequestMapping("/starredTask.htm")
	public String starredTask() {
		return "header/starredTask";
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
