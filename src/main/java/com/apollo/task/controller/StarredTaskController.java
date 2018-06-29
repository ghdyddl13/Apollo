package com.apollo.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.apollo.task.service.StarredTaskService;
import com.apollo.vo.MyWorkTaskDTO;

@Controller
public class StarredTaskController {
	@Autowired
	private StarredTaskService service;
	@Autowired
	private View jsonview;
	
	
	@RequestMapping(value="/starredTask.htm",method=RequestMethod.GET)
	public View showStarredTasks(HttpServletRequest request,ModelMap map) {
		String mid = (String)request.getSession().getAttribute("mid");
		List<MyWorkTaskDTO> tasklist = service.getListTask(mid);
		map.addAttribute("tasklist", tasklist);
		return jsonview;
	}
}
