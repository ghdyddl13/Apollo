package com.apollo.step.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.step.service.StepBoardService;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;

@Controller
public class StepBoardController {

	@Autowired
	private StepBoardService boardservice;
	
	
	
	@Autowired
	private View jsonview;
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : board에서 task 생성 
	 작성자명 : 이 창 훈
	 */
	@RequestMapping("/boardInsertTask.htm")
	public View createTask(TaskDTO taskdto, HttpServletRequest request) {
		System.out.println("boardInsertTask : " + taskdto.getTname() + "/" + request.getSession().getAttribute("pid") + "/" + taskdto.getTstatusid());
		
		try {
			boardservice.insertBoardTask(taskdto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return jsonview;
	}
	
	public String showBoardStepList(String s1, Model model) {
		return null;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : board에서 task를 드래그를 통해 이동하여 update함. 
	 작성자명 : 이 창 훈
	 */
	@RequestMapping("/boardTaskStatusUpdate.htm")
	public View changeBoardStepStatus(TaskDTO taskdto) {
		try {
			boardservice.updateBoardTaskByTid(taskdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : 해당 step의 task들을 board page에 각각의 상태와 task를 가져온다. 
	 작성자명 : 이 창 훈
	 */
	@RequestMapping("/board.htm")
    public String selectBoard(Model model,HttpServletRequest request) {
        int sid = (Integer) request.getSession().getAttribute("sid");
        System.out.println("sid : " + sid);
        try {
        	  ArrayList<TstatusDTO> tstatusdto = boardservice.selectTstatusBySid(sid);
              model.addAttribute("b", tstatusdto);
              
              ArrayList<TaskDTO> taskdto = boardservice.getTasksByStepId(sid);
              model.addAttribute("t", taskdto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
               
        
        return "step/board";
    }
	
}
