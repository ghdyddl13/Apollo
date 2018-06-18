package com.apollo.project.controller;
import org.springframework.web.servlet.View;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.project.service.ProjectInfoService;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;
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
	 			  로드되자마자 뿌려주는 정보는 다음과 같다
	 			  1. 해당 프로젝트에 속한 스텝명(셀렉트 박스)
	 			  	 (테이블은 비동기로 onload되자마자 trigger change)
	 			  2. 프로젝트에 참여한 멤버 명단
	 			  3. 모달 페이지에 띄워 줄 회원 명단
	 			  	 (같은 회사키를 공유하는 회원들을 찾아서 뿌려준다)
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/information.htm")
	public String projectInfoShow(String[] data, Model map) {
		String tempstr = data[0];
		String[] data_arr = tempstr.split(",");
		
		String pid = data_arr[0];
		String mid = data_arr[1];
	
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
		map.addAttribute("steplist", steplist);

		
		ArrayList<MemberDTO> getProjectMemberlist = new ArrayList<MemberDTO>();
		getProjectMemberlist = projectinfoservice.getProjectMembers(pid);
        map.addAttribute("memberlist", getProjectMemberlist);

        MidpidDTO midpiddto = new MidpidDTO();
        midpiddto.setMid(mid);
        midpiddto.setPid(pid);
        
        // 같은 회사키를 사용하면서도 아직 해당 프로젝트에 참여중이지 않은 멤버들만 가져온다
        // 가져와서 모달에 넣어준다
        ArrayList<MemberDTO> invitememberlist = new ArrayList<MemberDTO>();
        invitememberlist = projectinfoservice.getInviteMemberList(midpiddto);
        map.addAttribute("invitememberlist", invitememberlist);

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
	
	/**
	 * 
	 날      짜 : 2018. 6. 16.
	 기      능 : 프로젝트 맴버 초대 모달에서 초대 아이콘을 누르면 프로젝트에 맴버가 추가됨
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/insertMidToPmember.htm")
	public View projectMemberAdd(String[] data, Model map) {
		
		String tempstr = data[0];
		String[] data_arr = tempstr.split(",");
		
		String pid = data_arr[0];
		String mid = data_arr[1];

		System.out.println("테스트출력");
		System.out.println("mid : " + mid);
		System.out.println("pid : " + pid);
		
        MidpidDTO midpiddto = new MidpidDTO();
        midpiddto.setMid(mid);
        midpiddto.setPid(pid);
		
		int result = 0;
		result = projectinfoservice.insertPmember(midpiddto);
        map.addAttribute("result", result);
		return jsonview;
		
	}
	
}
