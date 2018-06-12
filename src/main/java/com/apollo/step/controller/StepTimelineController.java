package com.apollo.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
  클래스명 : StepTimelineController
  날      짜 : 2018. 6. 12.
  작성자명 : 박 민 식
 */

@Controller
@RequestMapping("/step")
public class StepTimelineController {

	
	@RequestMapping("/timeline.htm")
	public String getTimelineView(String pid) {
		
		return "step/timeline";
	}
}
