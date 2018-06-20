package com.apollo.step.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.step.service.StepListService;
import com.apollo.vo.TaskDTO;
@Controller
public class StepListController {
	
	@Autowired
	private StepListService service;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 리스트페이지 비동기 화면전환 
	 작성자명 : 박 민 식
	 */
    @RequestMapping("/list.htm")
    public String list(int sid, HttpServletRequest request) {
       
    	System.out.println(sid);
    	
      // 스텝 id 와 프로젝트 id 세션값 갱신ㄴ
        int pid = service.getProjectIdByStepId(sid);
        request.getSession().setAttribute("sid", sid);
        request.getSession().setAttribute("pid", pid);
        
        return "step/list";
    }
	
	public View createTask(TaskDTO taskdto) {
		return null;
	}
	
	public String showTaskList(String s1, Model model) {
		return null;
	}

	public String changeTasks(String[] s1, String s2, String s3, Model model) {
		return null;
	}

	public String deleteTasks(String[] s1, Model model) {
		return null;
	}
	
}
