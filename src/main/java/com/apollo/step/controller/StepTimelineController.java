package com.apollo.step.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.step.service.StepTimelineService;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;

/**
 * 
  클래스명 : StepTimelineController
  날      짜 : 2018. 6. 15.
  작성자명 : 박 민 식
 */
@Controller
@RequestMapping("/step")
public class StepTimelineController {
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private StepTimelineService steptimelineservice; 
	
	@RequestMapping("/timeline.htm")
	public String getTimelineView(Model model,HttpServletRequest request) {
		ArrayList<TstatusDTO> tstatuses = null;
		int sid = (Integer) request.getSession().getAttribute("sid");
		try {
			tstatuses = steptimelineservice.selectTstatusBySid(sid);
			model.addAttribute("tstatuses", tstatuses);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "step/timeline";
	}
	
	@RequestMapping("/getTimelineTasks.htm")
	public View getTimelineTasks(String sid, Model model) {
		ArrayList<TaskDTO> tasks = null;
		
		try {
			tasks = steptimelineservice.getTasksByStepId(sid);
			model.addAttribute("tasks", tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return jsonview;
	}
}
