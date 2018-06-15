package com.apollo.step.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.step.service.StepTimelineService;
import com.apollo.vo.TaskDTO;

/**
 * 
  Ŭ������ : StepTimelineController
  ��      ¥ : 2018. 6. 12.
  �ۼ��ڸ� : �� �� ��
 */
@Controller
@RequestMapping("/step")
public class StepTimelineController {
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private StepTimelineService steptimelineservice; 
	
	@RequestMapping("/timeline.htm")
	public String getTimelineView(String pid) {
		
		return "step/timeline";
	}
	
	@RequestMapping("/getTimelineTasks.htm")
	public View getTimelineTasks(String sid, Model model) {
		ArrayList<TaskDTO> tasks = null;
		
		try {
			tasks = steptimelineservice.getTasksByStepId(sid);
			model.addAttribute("tasks", tasks);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
		
		
		return jsonview;
	}
}
