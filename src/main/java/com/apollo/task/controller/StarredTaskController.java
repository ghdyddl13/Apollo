package com.apollo.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apollo.task.service.StarredTaskService;
import com.apollo.vo.MyWorkTaskDTO;

@Controller
public class StarredTaskController {
	
	@Autowired
	private StarredTaskService service;
	
	@RequestMapping(value="/starredTask.htm",method=RequestMethod.GET)
	public String showStarredTasks(HttpSession session,HttpServletRequest request,ModelMap map) {
    	session.setAttribute("location", "/starredTask.htm");
		String mid = (String)request.getSession().getAttribute("mid");
		List<MyWorkTaskDTO> tasklist = service.getListTask(mid);
		map.addAttribute("tasklist", tasklist);
		return "header/starredTask";
	}
}
