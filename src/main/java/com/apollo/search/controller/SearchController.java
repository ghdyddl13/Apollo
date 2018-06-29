package com.apollo.search.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.apollo.search.service.SearchService;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchservice;
	@Autowired
	private View jsonview;
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 최근 생성된 태스크 10개를 뽑아주는 함수
	 작성자명 : 박 민 식
	 */
	@RequestMapping(value="getRecentTasks.htm", method=RequestMethod.POST)
	public View getRecentTasks(Model model,HttpServletRequest request) {
		String mid = (String) request.getSession().getAttribute("mid");
		ArrayList<TaskDTO> rcttasks= null;
		try {
			rcttasks = searchservice.getRecentTasks(mid);
			model.addAttribute("rcttasks", rcttasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}
	
	@RequestMapping(value="getSearchResult.htm", method=RequestMethod.POST)
	public View getSearchResult(String input, Model model, HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("input", input);
		map.put("mid", (String) request.getSession().getAttribute("mid"));
		ArrayList<TaskDTO> searchtasks = null;
		ArrayList<ProjectDTO> searchprojects =null;
		ArrayList<StepDTO> searchsteps = null;
		ArrayList<MemberDTO> searchmembers =null;
		
		try {
			searchmembers = searchservice.getSearchMembers(map);
			searchprojects = searchservice.getSearchProjects(map);
			searchsteps = searchservice.getSearchSteps(map);
			searchtasks = searchservice.getSearchTasks(map);
			model.addAttribute("searchmembers",searchmembers);
			model.addAttribute("searchprojects",searchprojects);
			model.addAttribute("searchsteps",searchsteps);
			model.addAttribute("searchtasks",searchtasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonview;
	}
}


/**
 프로젝트 : Apollo
 파일이름 : SearchController.java 
 날      짜 : 2018. 6. 25.
 작 성  자 : 박 민 식
*/