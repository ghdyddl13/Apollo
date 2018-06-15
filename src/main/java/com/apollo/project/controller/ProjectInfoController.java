package com.apollo.project.controller;
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
	 날      짜 : 2018. 6. 15.
	 기      능 : 프로젝트 인포메이션 페이지 로드
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/information.htm")
	public String projectInfoShow(String pid, Model map) {
		
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
		map.addAttribute("steplist", steplist);
		
		ArrayList<MemberDTO> getProjectMemberlist = new ArrayList<MemberDTO>();
		getProjectMemberlist = projectinfoservice.getProjectMembers(pid);
        map.addAttribute("memberlist", getProjectMemberlist);

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
		ArrayList<TaskDTO> unassignedtasklist = new ArrayList<TaskDTO>();
		
		assignedtasklist = projectinfoservice.getAssignedTasks(pid);
		unassignedtasklist = projectinfoservice.getNotAssignedTasks(pid);
		
        map.addAttribute("assignedtasklist", assignedtasklist);
        map.addAttribute("unassignedtasklist", unassignedtasklist);
		
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
	 날      짜 : 2018. 6. 14.
	 기      능 : Step별 Task 완료/ 미완료에서 Step 셀렉트바에 따른 Task 가져오기
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getTasksByStepForSituation.htm")
	public View getTasksByStep(String sid, ModelMap map) {

		ArrayList<TaskDTO> tasklist = null;
		
		try {
			tasklist = projectinfoservice.getTasksByStepId(sid);
			map.addAttribute("tasklist", tasklist);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : Step 진행률 bar 데이터 가져오기
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getProgressData.htm")
	public View getProgressData(String pid, ModelMap map) {

		ArrayList<ArrayList<TaskDTO>> tasklistbysteps = new ArrayList<ArrayList<TaskDTO>>();
		
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
		
		for(StepDTO stepdto : steplist) {
			
			ArrayList<TaskDTO> taskinsteplist = new ArrayList<TaskDTO>();
			taskinsteplist = projectinfoservice.getTasksInSteps(Integer.toString(stepdto.getSid()));
			tasklistbysteps.add(taskinsteplist);
			
		}
		
		map.addAttribute("steplist", steplist);
        map.addAttribute("tasklistbysteps", tasklistbysteps);
		
		return jsonview;
	}
	
	
	
	
	public View projectMemberSearch(String s1) {
		return null;
	}
	
	public View projectMemberAdd(String s1) {
		
		return null;
	}
	
}
