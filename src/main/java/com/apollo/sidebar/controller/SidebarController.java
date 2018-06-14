package com.apollo.sidebar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.apollo.sidebar.service.SidebarService;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;

@Controller
public class SidebarController {
	
	@Autowired 
	private SidebarService sidebarservice;
	
	@RequestMapping(value="/raeyoung.htm", method=RequestMethod.POST)
	public String insertProject(ProjectDTO projectdto) {
		try {
			sidebarservice.insertProject(projectdto);
			System.out.println(projectdto.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/login.htm";
	}

	public View changeProjectStatus(int i1, Model model) {
		return null;
	}
	
	public View changeProject(ProjectDTO projectdto) {
		return null;
	}
	
	public String createStep(StepDTO stepdto, Model model) {
		return null;
	}
	
	public String changeStep(StepDTO stepdto, Model model) {
		return null;
	}
	
	public String deleteStep(String s1) {
		return null;
	}
	
	
}
