package com.apollo.task.controller;

import java.awt.Dialog.ModalExclusionType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apollo.task.service.StarredTaskService;
import com.apollo.vo.MyWorkTaskDTO;
import com.apollo.vo.StarredTaskDTO;
/**
 * 
  클래스명 : StarredTaskController
  날      짜 : 2018. 7. 3.
  작성자명 : 이 진 우
 */
@Controller
public class StarredTaskController {
	
	@Autowired
	private StarredTaskService service;
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : Starred Task 페이지에 들어갈때 발동되는 컨트롤러
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/starredTask.htm",method=RequestMethod.GET)
	public String showStarredTasks(HttpSession session,HttpServletRequest request,ModelMap map,Model model) {
    	session.setAttribute("location", "/starredTask.htm");
		String mid = (String)request.getSession().getAttribute("mid");
		List<MyWorkTaskDTO> tasklist = service.getListTask(mid);
		map.addAttribute("tasklist", tasklist);
		model.addAttribute("inbox", "starredtask");
		return "header/starredTask";
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : 즐겨찾기 삭제 기능 
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/deleteStarredTask.htm",method=RequestMethod.POST)
	public String deleteStarredTask(int tid, HttpSession session, ModelMap map) {
		StarredTaskDTO dto = new StarredTaskDTO();
		dto.setMid((String)session.getAttribute("mid"));
		dto.setTid(tid);
		service.deleteStarredTask(dto);
		
		return "redirect:/starredTask.htm";
	}
}
