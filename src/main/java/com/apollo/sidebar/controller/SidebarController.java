package com.apollo.sidebar.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.apollo.sidebar.dao.FolderDAO;
import com.apollo.sidebar.service.SidebarService;
import com.apollo.vo.FolderDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;

@Controller
public class SidebarController {
	
	@Autowired 
	private SidebarService sidebarservice;
	
	HttpSession session;
	
	@Autowired 
	private View jsonview;
	
	@RequestMapping(value="/insertproject.htm", method=RequestMethod.POST)
	public View insertProject(ProjectDTO projectdto, Model model) {
		System.out.println("프로젝트 생성");
		try {
			int result = sidebarservice.insertProject(projectdto);
			System.out.println(projectdto.toString());
			
			model.addAttribute("result", result); // service에 return 타입인 result 를 result 에 저장 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview; //result 가 json 형태로 변환되어 저장
	}

	public View changeProjectStatus(int i1, Model model) {
		return null;
	}
	
	public View changeProject(ProjectDTO projectdto) {
		return null;
	}
	
	@RequestMapping(value="/insertstep.htm", method=RequestMethod.POST)
	public View insertStep(StepDTO stepdto, Model model) {
		System.out.println("insert step");
		try {
			int stepresult = sidebarservice.insertStep(stepdto);
			System.out.println(stepdto.toString());
			
			model.addAttribute("stepresult", stepresult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview; 
	}
	public String changeStep(StepDTO stepdto, Model model) {
		return null;
	}
	
	public String deleteStep(String s1) {
		return null;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : 프로젝트 리스트 가져오기 
	 작성자명 : 박 민 식
	 */
	@RequestMapping("/selectProjectList.htm")
	public View selectProjectList(Model model,HttpServletRequest request) {
		String mid =  (String) request.getSession().getAttribute("mid");
		ArrayList<ProjectDTO> projectlist = null;
		try {
			projectlist = sidebarservice.selectProjectList(mid);
			model.addAttribute("projectlist",projectlist);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectProjectList Controller 에러");
		}
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : 프로젝트 리스트 가져오기 
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/getprojectmembers.htm")
	public View getProjectMembers(int pid, Model model) {
		
		System.out.println("pid : " + pid);
		
		ArrayList<MemberDTO> memberlist = new ArrayList();
		memberlist = sidebarservice.getMemberList(pid);
		model.addAttribute("memberlist", memberlist);
		
		System.out.println("memberlist size : " + memberlist.size());
		
		return jsonview;
	}
	
	@RequestMapping(value="/insertfolder.htm", method=RequestMethod.POST)
	public View insertFolder(FolderDTO folderdto, Model model) {
		System.out.println("isnert folder");
		try {
			int folderresult = sidebarservice.insertfolder(folderdto);
			System.out.println(folderdto.toString());
			
			model.addAttribute("folderresult", folderresult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview; 
		
	}
	
	
}
