package com.apollo.project.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.project.service.ProjectFilesService;
import com.apollo.vo.FileDTO;

@Controller
public class ProjectFilesController {

	@Autowired
	private ProjectFilesService fileservice;
	
	@RequestMapping("/files.htm")
	public String projectFilelist(Model model, HttpServletRequest request, HttpSession session) {
		session.setAttribute("location", "/files.htm");
		int pid = (Integer) request.getSession().getAttribute("pid");
		System.out.println("pid : " + pid);
		try {
			ArrayList<FileDTO> filedto = fileservice.selectFileListByProjectId(pid);
			System.out.println("filedto : " + filedto);
			model.addAttribute("f", filedto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "project/files";
	}
	
}
