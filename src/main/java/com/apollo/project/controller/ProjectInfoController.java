package com.apollo.project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;
import com.apollo.project.service.ProjectInfoService;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

@Controller
public class ProjectInfoController {
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private ProjectInfoService projectinfoservice;
	
	@RequestMapping("/information.htm")
	public String projectInfoShow(String s1) {
		
		
		
		return "project/information";
	}
	
	@RequestMapping("/donutChart.htm")
	public View donutChart(String pid, ModelMap map) {
		
		ArrayList<TaskDTO> tasklist = new ArrayList<TaskDTO>();
		tasklist = projectinfoservice.getTasks(pid);
        map.addAttribute("tastlist", tasklist);
		
		return jsonview;
	}
	
	@RequestMapping("/getSteps.htm")
	public View getSteps(String pid, ModelMap map) {

		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		steplist = projectinfoservice.getSteps(pid);
        map.addAttribute("Steplist", steplist);
		
		return jsonview;
	}
	
	@RequestMapping("/getTasksInSteps.htm")
	public View getTasksInSteps(String sid, ModelMap map) {

		ArrayList<TaskDTO> taskinsteplist = new ArrayList<TaskDTO>();
		taskinsteplist = projectinfoservice.getTasksInSteps(sid);
        map.addAttribute("taskinsteplist", taskinsteplist);
		
		return jsonview;
	}
	
	@RequestMapping("/getProjectMembers.htm")
	public View getProjectMembers(String pid, ModelMap map) {

		ArrayList<MemberDTO> getProjectMemberlist = new ArrayList<MemberDTO>();
		getProjectMemberlist = projectinfoservice.getProjectMembers(pid);

		for(MemberDTO dto : getProjectMemberlist) {
			//int mid = dto.getMid();
			
		}
		return jsonview;
	}
	
	public View projectMemberSearch(String s1) {
		return null;
	}
	
	public View projectMemberAdd(String s1) {
		
		return null;
	}
	
}
