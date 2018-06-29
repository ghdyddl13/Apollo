package com.apollo.inbox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {


	@RequestMapping("/report.htm")
	public String report() {
		return "header/report";
	}
	

    
    @RequestMapping("/workload.htm")
    public String workLoad() {
        
        return "step/workload";
    }
	
}
