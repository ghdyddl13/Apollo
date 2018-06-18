package com.apollo.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.task.service.TaskService;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.TaskDTO;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@Autowired
	private View jsonview;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : 태스크 정보변경 >> 동적쿼리
	 작성자명 : 박 민 식
	 */
	@RequestMapping("/updateTask.htm")
	public View updateTask(TaskDTO taskdto, Model model) {
		int result = 0;
		try {
			result=service.updateTask(taskdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("result", result);
		return jsonview;
	}
	
	public View callMemberComment(CommentDTO commentdto) {
		
		return null;
	}
	
	public View doComment(CommentDTO commentdto) {
		return null;
	}
	
	
	
}
