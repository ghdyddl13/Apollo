package com.apollo.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.report.service.ReportService;

@Controller
public class ReportController {

	
	@Autowired 
	private ReportService reportservice;
	
	@Autowired
	private View jsonview;
	
	
	@RequestMapping("/report.htm")
	public String report() {
		return "header/report";
	}
	
	@RequestMapping("/testprint.htm")
	public View getfiles() {
		
		try {
		   	reportservice.writeData();

			} catch (Exception e) {

				System.out.println("error!!");
				e.printStackTrace();
			}
		
		
		return jsonview;
	}
	
}
