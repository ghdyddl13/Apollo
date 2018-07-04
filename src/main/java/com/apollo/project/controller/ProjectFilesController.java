package com.apollo.project.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.project.service.ProjectFilesService;
import com.apollo.project.service.ProjectInfoService;
import com.apollo.vo.FileDTO;
import com.apollo.vo.ProjectDTO;

@Controller
public class ProjectFilesController {

	@Autowired
	private ProjectFilesService fileservice;
	@Autowired 
	private ProjectInfoService projectinfoservice;
	
	
	@RequestMapping("/files.htm")
	public String projectFilelist(Model model, HttpServletRequest request, HttpSession session) {
		session.setAttribute("location", "/files.htm");
		int pid = (Integer) request.getSession().getAttribute("pid");
		try {
			ArrayList<FileDTO> filedto = fileservice.selectFileListByProjectId(pid);
			model.addAttribute("f", filedto);
			
			ProjectDTO projectinfo = projectinfoservice.getProjectInfo(pid);
			model.addAttribute("projectinfo", projectinfo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "project/files";
	}
	
	@RequestMapping("/filesDeleteByFileId.htm")
	public String filesDeleteByFileId(int fileid) {
		System.out.println("파일 컨트롤러 들어왔어요 : " + fileid);
		try {
			fileservice.filesDeleteByFileId(fileid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/files.htm";
	}
	
}
