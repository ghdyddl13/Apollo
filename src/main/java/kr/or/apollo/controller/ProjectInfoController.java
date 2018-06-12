package kr.or.apollo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.apollo.service.ProjectInfoService;
import kr.or.apollo.vo.StepDTO;
import kr.or.apollo.vo.TaskDTO;

@Controller
public class ProjectInfoController {
	
	/*
	 * 1. 도넛 그래프
	 * dao에서 해당 project의 pid를 가진 task를 가지고 와서 
	 * 마감기한 기준으로 계산하여 도넛 그래프 형성
	 * 호버시는 아직 미정
	 *
	 * 2. task 완료/미완료
	 * dao에서 해당 project의 pid를 가진 task를 가지고 와서 
	 * task의 상태를 파악하여 완료/미완료 분류하여 테이블 뿌리기
	 * 검색 되어야 하므로 sb admin 테이블 그대로 가져와서 css 변경
	 * 
	 * 3. Step 진행률
	 * 같은 pid를 가진 step들 가져오고 step들의 task들을 가져와서
	 * 완료 미완료 구분하여 그래프 형성한다
	 * 
	 * 4. 같은 pid 가지고 있는 멤버들 가져왓
	 * 테이블로 뿌려주고 같은 형식을 위해서 task 완료 미완료에서 쓴
	 * 테이블을 그대로 가져와도 무방하다.
	 * 검색을 넣을지 말지는 회의를 통해서 결정
	 */
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private ProjectInfoService projectinfoservice;
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 info 페이지를 올려준다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/information.htm")
	public String projectInfoShow(String s1) {
		System.out.println("projectInfoShow 메소드 탔음");
		return "project/information";
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 마감기한 기준 Task 현황 그래프
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/donutChart.htm")
	public View donutChart(String pid, ModelMap map) {
		System.out.println("DonutChart 메소드 탔음");
		
		ArrayList<TaskDTO> tasklist = new ArrayList<TaskDTO>();
		tasklist = projectinfoservice.getTasks(pid);
        map.addAttribute("tastlist", tasklist);
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : Step별 Task 완료/미완료 현황 select bar에 들어갈 step 명
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getSteps.htm")
	public View getSteps(String pid, ModelMap map) {

		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
        map.addAttribute("Steplist", steplist);
		
		return jsonview;
	}
	
	@RequestMapping("/getTasksInSteps.htm")
	public View getTasksInSteps(String sid, ModelMap map) {

		ArrayList<String> taskinsteplist = new ArrayList<String>();
		/*
		taskinsteplist = 
        map.addAttribute("taskinsteplist", taskinsteplist);
		*/
		
		return jsonview;
	}
	
	
	
	
	
	
	public View projectMemberSearch(String s1) {
		return null;
	}
	
	public View projectMemberAdd(String s1) {
		
		return null;
	}
	
}
