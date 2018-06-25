package com.apollo.inbox.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.step.service.StepListService;

@Controller
public class TestController {
	


	

/*	@RequestMapping("/table.htm")
	public String projectTable() {
		return "project/table";
	}*/
	

	@RequestMapping("/report.htm")
	public String report() {
		return "header/report";
	}
	
	@RequestMapping("/starredTask.htm")
	public String starredTask() {
		return "header/starredTask";
	}
    

    
    @RequestMapping("/workload.htm")
    public String workLoad() {
        
        return "step/workload";
    }
	
}
