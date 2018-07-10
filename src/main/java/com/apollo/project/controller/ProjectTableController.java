package com.apollo.project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.member.service.MemberService;
import com.apollo.project.service.ProjectInfoService;
import com.apollo.project.service.ProjectTableService;
import com.apollo.vo.FolderDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;
/**
 * 
  클래스명 : ProjectTableController
  날      짜 : 2018. 6. 22.
  작성자명 : 김 래 영
 */
@Controller
public class ProjectTableController {
	
	@Autowired 
	private ProjectTableService tableservice;
	
	@Autowired 
	private ProjectInfoService projectinfoservice;
	
	@Autowired 
	private MemberService memberservice;

	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : 프로젝트 테이블 페이지 실행되는 함수
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/table.htm")
	public String projectTable(HttpServletRequest request, Model model, HttpSession session) {	
		System.out.println("aa");
		session.setAttribute("location", "/table.htm");
		
		int pid = (Integer) request.getSession().getAttribute("pid");
		//String mid = (String) request.getSession().getAttribute("mid");
		
		ArrayList<Integer> pids = new ArrayList<Integer>();
		pids.add(pid);
		
		model.addAttribute("pid", pid);
		
		try {
			ArrayList<StepDTO> steplist = null; // pid 에 속한 step 가져오기
			steplist = tableservice.getStepInProject(pid);
			model.addAttribute("steplist", steplist);
			

			ArrayList<FolderDTO> folderlist = null; // pid 에 속한 folder 가져오기
			folderlist = tableservice.selectFolderList(pids);
			model.addAttribute("folderlist", folderlist);
			
			if(steplist.size() != 0) {
				System.out.println(steplist.size());
				ArrayList<TaskDTO> tasklist = null; //sid 에 속한 task 및 tstatus 가져오기
				tasklist = tableservice.getTasksInStep(steplist);
				model.addAttribute("tasklist", tasklist);
				
				ArrayList<MemberDTO> memberlist = null; //step 에 담당자 가져오기
				memberlist = tableservice.selectStepAssignees(steplist);
				model.addAttribute("memberlist", memberlist);
				
			
				
				ArrayList<TstatusDTO> tstatuslist = null; //tstatus 상태 가져오기
				tstatuslist = tableservice.getTstatuslistPid(pid);
				model.addAttribute("tstatuslist", tstatuslist);
			}


			ProjectDTO projectinfo = projectinfoservice.getProjectInfo(pid);
			model.addAttribute("projectinfo", projectinfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "project/table";
	}
	
}
