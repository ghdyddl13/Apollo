package com.apollo.project.controller;
import org.springframework.web.servlet.View;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.project.service.ProjectInfoService;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;

@Controller
public class ProjectInfoController {
	
	@Autowired
	private View jsonview;
	
	@Autowired 
	private ProjectInfoService projectinfoservice;
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : 프로젝트 인포메이션 페이지 로드 
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/information.htm")
	public String projectInfoShow(String pid, Model map) {
		
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
		map.addAttribute("steplist", steplist);
		
		return "project/information";
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : 프로젝트 인포메이션 내 도넛 차트 데이터 불러옴 
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/donutChart.htm")
	public View donutChart(String pid, ModelMap map) {
		ArrayList<TaskDTO> assignedtasklist = new ArrayList<TaskDTO>();
		ArrayList<TaskDTO> notassignedtasklist = new ArrayList<TaskDTO>();
		
		assignedtasklist = projectinfoservice.getAssignedTasks(pid);
		notassignedtasklist = projectinfoservice.getNotAssignedTasks(pid);
		
        map.addAttribute("assignedtasklist", assignedtasklist);
        map.addAttribute("notassignedtasklist", notassignedtasklist);
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : pid로 해당 프로젝트를 바라보는 스텝을 가져옴
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getSteps.htm")
	public View getSteps(String pid, ModelMap map) {

		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
        map.addAttribute("Steplist", steplist);
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : sid로 해당 스텝에 속한 task 들을 가져옴
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getTasksInSteps.htm")
	public View getTasksInSteps(String sid, ModelMap map) {

		ArrayList<TaskDTO> taskinsteplist = new ArrayList<TaskDTO>();
		taskinsteplist = projectinfoservice.getTasksInSteps(sid);
        map.addAttribute("taskinsteplist", taskinsteplist);
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : 같은 프로젝트에 속한 멤버들을 가져옴
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getProjectMembers.htm")
	public View getProjectMembers(String pid, ModelMap map) {

		ArrayList<MemberDTO> getProjectMemberlist = new ArrayList<MemberDTO>();
		getProjectMemberlist = projectinfoservice.getProjectMembers(pid);
        map.addAttribute("getProjectMemberlist", getProjectMemberlist);
		return jsonview;
	}
	
	public View projectMemberSearch(String s1) {
		return null;
	}
	
	public View projectMemberAdd(String s1) {
		
		return null;
	}
	
}
