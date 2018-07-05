package com.apollo.project.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.project.service.ProjectInfoService;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;
import com.apollo.vo.ProjectDTO;
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
	 			  4. 프로젝트 이름과 아이디
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/information.htm")
	public String projectInfoShow(int pid, HttpSession session, Model map) {
		session.setAttribute("location", "/information.htm");
		session.setAttribute("pid", pid);
		
		String mid = (String) session.getAttribute("mid");
		//map.addAttribute("pid", pid);
		ProjectDTO projectinfo = projectinfoservice.getProjectInfo(pid);
		map.addAttribute("projectinfo", projectinfo);
		
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
		
		for(StepDTO dto : steplist) {
			
			if(dto.getSname().length() > 15) {
					String temp = dto.getSname();
					temp = temp.substring(0, 10);
					String newsname = temp + "...";
					dto.setSname(newsname);
			}
			
		}
		
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
	public View donutChart(int pid, ModelMap map) {
		
		ArrayList<TaskDTO> tasklist = new ArrayList<TaskDTO>();
		tasklist = projectinfoservice.getTasks(pid);
		
        map.addAttribute("tasklist", tasklist);
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : pid로 해당 프로젝트를 바라보는 스텝을 가져옴
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getSteps.htm")
	public View getSteps(int pid, ModelMap map) {

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
	public View getTasksInSteps(int sid, ModelMap map) {

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
	public View getTasksByStep(int sid, ModelMap map) {

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
	public View getProgressData(int pid, ModelMap map) {

		ArrayList<ArrayList<TaskDTO>> tasklistbysteps = new ArrayList<ArrayList<TaskDTO>>();
		
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
		
		for(StepDTO stepdto : steplist) {
			
			ArrayList<TaskDTO> taskinsteplist = new ArrayList<TaskDTO>();
			taskinsteplist = projectinfoservice.getTasksInSteps(stepdto.getSid());
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
	public String projectMemberAdd(String mid, HttpSession session) {
		
		System.out.println("projectMemberAdd 컨트롤러 실행");
		String location = (String) session.getAttribute("location");
		
		int pid = (Integer) session.getAttribute("pid");
		
		System.out.println("pid : " + pid);
		System.out.println("mid : " + mid);
		
        MidpidDTO midpiddto = new MidpidDTO();
        midpiddto.setMid(mid);
        midpiddto.setPid(pid);
		
		int result = 0;
		result = projectinfoservice.insertPmember(midpiddto);

	    if(location.equals("/information.htm")) {
	    	return "redirect:/information.htm?pid=" + pid;
	    }else {
	    	return null;
	    }
		
	}
	
}
