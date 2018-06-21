package com.apollo.task.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.task.service.TaskService;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;

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
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : task modal에 필요한 정보 가져오기
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getTask.htm")
	public View getTask(String pid, String tid, Model model) {

	TaskDTO taskdto = new TaskDTO();
	taskdto = service.getTask(tid);
	model.addAttribute("task", taskdto);
	
	System.out.println("task : " + taskdto.getTname());
	
	ArrayList<StepDTO> steplist = new ArrayList();
	steplist = service.getStepid(tid);
	model.addAttribute("steps", steplist);

	System.out.println("steplist size : " + steplist.size());
	
	ArrayList<TstatusDTO> tstatuslist = new ArrayList();
	tstatuslist = service.gettstatuslist(pid);
	model.addAttribute("tstatuslist", tstatuslist);
	
	for(TstatusDTO dto : tstatuslist) {
		System.out.println(dto.getTstatus());
	}
	
	return jsonview;
	}
	
	@RequestMapping("/insertcomment.htm")
	public String insertComment(CommentDTO commentdto, HttpSession session) {
		System.out.println(commentdto.getComments());
		System.out.println(commentdto.getTid());
		System.out.println("!코멘트 인서트 컨트롤러!");
		String mid=(String)session.getAttribute("mid");
		mid="testid1";//테스트아이디
		commentdto.setMid(mid);
		commentdto.setCmtkind(0);
		service.insertComment(commentdto);
		
		return "redirect:/inbox.htm";
	}
	
	
}
