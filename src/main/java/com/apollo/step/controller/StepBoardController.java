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
	
	public View createTask(TaskDTO tastdto) {
		return null;
	}
	
	public String showBoardStepList(String s1, Model model) {
		return null;
	}
	
	public View changeBoardStepStatus(TaskDTO taskdto) {
		return null;
	}
	
	@RequestMapping("/board.htm")
    public String selectBoard(Model model) {
		System.out.println("board 컨트롤러 들어왔따");
        int sid = 3;
        ArrayList<TstatusDTO> tstatusdto = boardservice.selectTstatusBySid(sid);
        System.out.println("tstatusdto : " + tstatusdto);
        model.addAttribute("b", tstatusdto);
        
        ArrayList<TaskDTO> taskdto = boardservice.getTasksByStepId(sid);
        model.addAttribute("t", taskdto);
        
        
        return "step/board";
    }
	
}
