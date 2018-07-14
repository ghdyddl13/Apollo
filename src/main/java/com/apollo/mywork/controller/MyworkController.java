package com.apollo.mywork.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apollo.mywork.service.MyworkService;
import com.apollo.vo.MyWorkMemberDTO;
import com.apollo.vo.MyWorkTaskDTO;
@Controller
public class MyworkController {

	@Autowired
	private MyworkService service;
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : Mywork 첫 페이지 로드
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/myWork.htm",method=RequestMethod.GET)
	public String showMyworkPage(HttpSession session,HttpServletRequest request,ModelMap map) {
    	session.setAttribute("location", "/myWork.htm");
		String mid = (String)request.getSession().getAttribute("mid");
		System.out.println(mid);
		
		Map<String, List<MyWorkTaskDTO>> taskmap = null;
		
		
		try {
			taskmap = service.getMyWork(mid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		map.addAttribute("todaylist", taskmap.get("todaylist"));
		map.addAttribute("counttodaylist", taskmap.get("todaylist").size());
		map.addAttribute("today",service.dateToString(service.getdate("today")));
		map.addAttribute("thisweeklist", taskmap.get("thisweeklist"));
		map.addAttribute("countthisweeklist", taskmap.get("thisweeklist").size());
		map.addAttribute("thisweek",service.dateToString(service.getdate("thisweekfirst"))+" - "+service.dateToString(service.getdate("thisweeklast")));
		map.addAttribute("nextweeklist", taskmap.get("nextweeklist"));
		map.addAttribute("countnextweeklist", taskmap.get("nextweeklist").size());
		map.addAttribute("nextweek",service.dateToString(service.getdate("nextweekfirst"))+" - "+service.dateToString(service.getdate("nextweeklast")));
		map.addAttribute("laterlist", taskmap.get("laterlist"));
		map.addAttribute("countlaterlist", taskmap.get("laterlist").size());
		map.addAttribute("later", "After "+service.dateToString(service.getdate("laterfirst")));

		return "header/myWork";
	}
}
