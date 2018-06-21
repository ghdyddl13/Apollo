package com.apollo.sidebar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

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

	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 프로젝트 변경
	 작성자명 : 박 민 식
	 */
	@RequestMapping(value="updateProject.htm", method=RequestMethod.POST)
	public View updateProject(ProjectDTO projectdto, Model model) {
		System.out.println(projectdto.toString());
		int result = 0;
		try {
			result=sidebarservice.updateProject(projectdto);
			model.addAttribute("result",result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonview;
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
	@RequestMapping(value="/selectProjectList.htm", method=RequestMethod.POST)
	public View selectProjectList(Model model,HttpServletRequest request) {
		String mid =  (String) request.getSession().getAttribute("mid");
		System.out.println(mid);
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
	
	@RequestMapping(value="/selectFolderList.htm", method=RequestMethod.POST)
	public View selectFolderList(@RequestParam(value="pids") List<Integer> pids, Model model) {
		/*ArrayList<String> arraypids=  (ArrayList<String>) pids;*/
		ArrayList<FolderDTO> folderlist = null;
		
		try {
			folderlist =sidebarservice.selectFolderList(pids);
			model.addAttribute("folderlist", folderlist);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectFolderList service cotroller");
		}
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : 참여중인 모든 프로젝트의 스텝리스트를 가져오는 함수
	 작성자명 : 박 민 식
	 */
	@RequestMapping(value="/selectStepList.htm", method=RequestMethod.POST)
	public View selectStepList(@RequestParam(value="pids") List<Integer> pids, Model model) {
		/*ArrayList<String> arraypids=  (ArrayList<String>) pids;*/
		ArrayList<StepDTO> steplist = null;
		
		try {
			steplist =sidebarservice.selectStepList(pids);
			model.addAttribute("steplist", steplist);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectFolderList service cotroller");
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
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 프로젝트가 없을 때 실행되는 함수
	 작성자명 : 김 래 영
	 */
	@RequestMapping("/noproject.htm")
	public String noproject() {
		return "project/noproject";
		
	}
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : fid 로 폴더 정보 가져오기
	 작성자명 : 김 래 영
	 */
	@RequestMapping(value="/selectfolder.htm")
	public View selectFolder(String fid, Model model) {
		System.out.println("selectfolder 들어옴");
		FolderDTO selectfolder = null;
		try {
			selectfolder = sidebarservice.selectFolder(fid);
			model.addAttribute("selectfolder", selectfolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
		
	}	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 폴더 수정
	 작성자명 : 김 래 영
	 */
	@RequestMapping(value="/updatefolder.htm", method=RequestMethod.POST)
	public View updateFolder(FolderDTO folderdto, Model model) {
		System.out.println("update folder");
		System.out.println(folderdto.toString());
		int updatefolder = 0;
		try {
			updatefolder = sidebarservice.updateFolder(folderdto);
			model.addAttribute("updatefolder", updatefolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
		
	}
	
	@RequestMapping("/deletefolder.htm")
	public View deleteFolder(String fid, Model model) {
		System.out.println("delete folder");
		int deletefolder = 0;
		try {
			deletefolder = sidebarservice.deleteFolder(fid);
			model.addAttribute("deletefolder", deletefolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonview;
	}

}













