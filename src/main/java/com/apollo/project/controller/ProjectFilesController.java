package com.apollo.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectFilesController {

	
	@RequestMapping("/files.htm")
	public String projectFilelist(HttpServletRequest request) {

	
		
		return "project/files";
	}
	
}
