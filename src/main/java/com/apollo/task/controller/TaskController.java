package com.apollo.task.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.project.service.ProjectInfoService;
import com.apollo.task.service.TaskService;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidtidDTO;
import com.apollo.vo.StarredTaskDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TaskInStepDTO;
import com.apollo.vo.TidpidDTO;
import com.apollo.vo.TidvalueDTO;
import com.apollo.vo.TstatusDTO;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@Autowired 
	private ProjectInfoService projectinfoservice;
	
	@Autowired
	private View jsonview;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : 태스크 정보변경 >> 동적쿼리
	 작성자명 : 박 민 식
	 */
	@RequestMapping("/updateTask.htm")
	public View updateTask(TaskDTO taskdto, Model model) {
		int result = 0;
		try {
			result=service.updateTask(taskdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("result", result);
		return jsonview;
	}
	
	public View callMemberComment(CommentDTO commentdto) {
		
		return null;
	}
	
	public View doComment(CommentDTO commentdto) {
		return null;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : task modal에 필요한 정보 가져오기
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getTask.htm")
	public View getTask(int tid, HttpSession session, Model model) {

	System.out.println("getTask 작동");
		
	int pid = (Integer) session.getAttribute("pid");
    String mid = (String) session.getAttribute("mid");

    
    ArrayList<StarredTaskDTO> starredtasklist = new ArrayList();
    starredtasklist = service.getStarredTaskList(mid);
    model.addAttribute("starredtasklist", starredtasklist);
    
	TaskDTO taskdto = new TaskDTO();
	taskdto = service.getTask(tid);
	model.addAttribute("task", taskdto);
	
	ArrayList<StepDTO> steplist = new ArrayList();
	steplist = service.getStepid(tid);
	model.addAttribute("steps", steplist);
	
	ArrayList<TstatusDTO> tstatuslist = new ArrayList();
	tstatuslist = service.gettstatuslist(pid);
	model.addAttribute("tstatuslist", tstatuslist);
	
	// 해당 테스크의 담당자들
	ArrayList<MemberDTO> sametaskmemberlist = new ArrayList<MemberDTO>();
	sametaskmemberlist = service.getSameTaskAssignee(tid);
	model.addAttribute("sametaskmemberlist", sametaskmemberlist);
	
	return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 즐겨찾기 추가 혹은 삭제
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/addordeletestar.htm")
	public View addordeletestar(int tid, int starAddOrDel, HttpSession session, Model model) {

		String mid = (String) session.getAttribute("mid");
		StarredTaskDTO dto = new StarredTaskDTO();
		dto.setMid(mid);
		dto.setTid(tid);
		
		int result;
		// 현재 즐겨찾기에 되어있지 않다 -> 따라서 즐겨찾기에 추가
		if(starAddOrDel == 1) {
			result = service.addstar(dto);
			model.addAttribute("result", "added");
		}
		
		// 현재 즐겨찾기에 되어있다 -> 따라서 즐겨찾기에서 삭제
		else {
			result = service.deletestar(dto);
			model.addAttribute("result", "deleted");
		}
		
	return jsonview;
	
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : task 삭제
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/deletetask.htm")
	public String deleteTask(int tid, Model model, HttpSession session) {
		
		String location = (String) session.getAttribute("location");
		int pid = (Integer) session.getAttribute("pid");
		int sid = (Integer) session.getAttribute("sid");
		
		System.out.println("테스트 출력 pid/sid/tid");
		System.out.println("pid : " + pid);
		System.out.println("sid : " + sid);
		System.out.println("tid : " + tid);
		
		int result = service.deleteTask(tid);
		System.out.println("delete Task 결과 : " + result);
		
	    if(location.equals("/information.htm")) {
	    	return "redirect:/information.htm?pid=" + pid;
	    } else if(location.equals("/board.htm")) {
	    	return "redirect:/board.htm";
	    }else if(location.equals("/list.htm")) {
	    	return "redirect:/list.htm?sid=" + sid;
	    }
//	    else if(location.equals("/.htm")) {
//	    	return "redirect:/.htm";
//	    }else if(location.equals("/.htm")) {
//	    	return "redirect:/.htm";
//	    }
	    else {
	    	return null;
	    }
	    
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : step 삭제
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/deletestepintaskmodal.htm")
	public View deleteStepInTaskModal(int tid, int sid, Model model) {
	
		TaskInStepDTO dto = new TaskInStepDTO();
		dto.setSid(sid);
		dto.setTid(tid);
		
		int result = service.deleteStepInTaskModal(dto);
		
		ArrayList<StepDTO> steplist = new ArrayList();
		steplist = service.getStepid(tid);
		model.addAttribute("steplist_after_delete_step", steplist);
		
		return jsonview;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 해당 task가 몇 개의 step에 속해 있는지 확인
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/counttaskinstep.htm")
	public View countTaskInStep(int tid, Model model) {
	
		int result = service.countTaskInStep(tid);
		model.addAttribute("countresult", result);
		
		return jsonview;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : 해당 task 상태 변경하기
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/changetstatus.htm")
	public View changeTstatus(int tid, int value, String tname, Model model, HttpSession session) {
	
		TidvalueDTO dto = new TidvalueDTO();
		dto.setTid(tid);
		dto.setValue(value);
		
		int result = service.changeTstatus(dto);
		
		// 상태 변경 성공시 코멘트 입력
		if(result == 1) {
			
			System.out.println("상태변경 성공! 상태변경 코멘트 입력 시작!");
			String comment = "";
			String mid = (String) session.getAttribute("mid");
			int pid = (Integer) session.getAttribute("pid");
			ArrayList<TstatusDTO> tstatuslist = new ArrayList();
			tstatuslist = service.gettstatuslist(pid);

			for(TstatusDTO tstatusdto : tstatuslist) {
				int tstatusid = tstatusdto.getTstatusid();
					if(tstatusid == value) {
						
						String modifier = service.getTaskModifierName(mid);
						comment = modifier + "님이 " + tname +"의 상태를 " + tstatusdto.getTstatus() + "로 변경하였습니다";
					}
			}
			
			CommentDTO commentdto = new CommentDTO();
			commentdto.setComments(comment);
			commentdto.setTid(tid);
			commentdto.setMid(mid);
			commentdto.setCmtkind(1);
			
			int insert_comment_result = service.insertComment(commentdto);
			System.out.println("코멘트 입력 여부 : " + insert_comment_result);
			
		} // end - 상태 변경 성공시 발동 조건문 
		
		model.addAttribute("result", result);
		
		return jsonview;
	}
	
	@RequestMapping("/insertcomment.htm")
	public String insertComment(CommentDTO commentdto, HttpSession session){
		System.out.println(commentdto.getComments());
		System.out.println(commentdto.getTid());
		System.out.println("!코멘트 인서트 컨트롤러!");
		String mid=(String)session.getAttribute("mid");
		mid="testid1";//테스트아이디
		commentdto.setMid(mid);
		commentdto.setCmtkind(0);
		service.insertInboxComment(commentdto);
		
		return "redirect:/inbox.htm";
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : 해당 task가 속한 프로젝트의 모든 스텝들을 가져온다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getStepListByTid.htm")
	public View getStepListByTid(int tid, Model model) {
		
		ArrayList<StepDTO> steplist = service.getStepListByTid(tid);
		model.addAttribute("steplist", steplist);
		return jsonview;
	}

	
	/**
	 * 
	 날      짜 : 2018. 6. 23.
	 기      능 : 테스크 모달 창에서 스텝 추가를 위한 모달을 불러오는 기능
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/addTaskInStepInTaskModal.htm")
	public View addTaskInStepInTaskModal(int sid, int tid, Model model){
		
		System.out.println("테스크 모달 내 스텝 추가 컨트롤러 탔음");

		TaskInStepDTO taskinstepdto = new TaskInStepDTO();
		taskinstepdto.setSid(sid);
		taskinstepdto.setTid(tid);
		
		int result = service.addTaskInStepInTaskModal(taskinstepdto);
		model.addAttribute("result", result);
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 23.
	 기      능 : 테스크 모달 창에서 스텝 추가(이중모달) 모달 내에 추가 버튼을 누르면 실행
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/addTaskInStepInTaskModal_2.htm")
	public View getStepNamesbytid(int tid, Model model){

		ArrayList<StepDTO> steplist = service.getStepNamesbytid(tid);
		model.addAttribute("steplist", steplist);
		
		return jsonview;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : 테스크 모달 창에서 업무 담당자 삭제 버튼을 누르면 실행
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/deleteassignee.htm")
	public View deleteAssignee(String mid, int tid, Model model){
		
		System.out.println("테스크 모달 내 업무 담당자 삭제 컨트롤러 메소드 실행");
		
		MidtidDTO dto = new MidtidDTO();
		dto.setMid(mid);
		dto.setTid(tid);
		int deleteresult = service.deleteAssignee(dto);
		if(deleteresult == 1) {
			System.out.println("업무 담당자 1명 삭제 완료");
		}
		model.addAttribute("deleteresult", deleteresult);
		
		// 해당 테스크 담당자들
		ArrayList<MemberDTO> sametaskmemberlist = new ArrayList<MemberDTO>();
		sametaskmemberlist = service.getSameTaskAssignee(tid);
		model.addAttribute("sametaskmemberlist", sametaskmemberlist);
		
		return jsonview;
	}

	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 테스크 모달에서 업무 담당자 추가하는 이중 모달시 데이터 불러온다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/addtaskassigneemodalinfo.htm")
	public View addTaskAssigneeModalInfo(int tid, HttpSession session, Model model){
		
		System.out.println("assignee 추가를 위한 이중 모달 데이터 컨트롤러 실행");
		int pid = (Integer) session.getAttribute("pid");
		
		// 같은 프로젝트이지만 해당 테스크의 담당자가 아닌 사람들
		ArrayList<MemberDTO> getSameProjectButNotSameTaskMemberList = new ArrayList<MemberDTO>();
		
		TidpidDTO tidpiddto = new TidpidDTO();
		tidpiddto.setPid(pid);
		tidpiddto.setTid(tid);
		
		getSameProjectButNotSameTaskMemberList = service.getSameProjectButNotSameTaskMemberList(tidpiddto);
		model.addAttribute("getSameProjectButNotSameTaskMemberList", getSameProjectButNotSameTaskMemberList);

		return jsonview;
		 		
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 테스크 모달에서 assignee 추가하는 이중모달 내에서 플러스 버튼 누르면 실행
	 			  테스크에 업무 담당자를 할당하고 코멘트 입력한다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/addassigneeintaskmodal.htm")
	public View addAssigneeInTaskModal(String mid, int tid, String tname, HttpSession session, Model model){
		
		System.out.println("mid : " + mid);
		System.out.println("tid : " + tid);
		System.out.println("tname : " + tname);
		
		System.out.println("이중 모달 내에서 assignee 할당과 코멘트 입력을 위한 컨트롤러 실행");
		MidtidDTO midtiddto = new MidtidDTO();
		midtiddto.setMid(mid);
		midtiddto.setTid(tid);
		int result = service.addAssigneeInTaskModal(midtiddto);
		
		// assignee 추가 성공시 코멘트 입력
			if(result == 1) {

				System.out.println("assignee 할당 성공! 코멘트 입력 시작!");
				
				String modifier = (String) session.getAttribute("mid");
				modifier = service.getTaskModifierName(mid);
				String assignee_name = service.getTaskModifierName(mid);

				String comment = "";
				comment = modifier + "님이 " + tname +" 업무를 " + assignee_name +"님에게 할당하였습니다";
				
				// comments 테이블에 insert
				CommentDTO commentdto = new CommentDTO();
				commentdto.setComments(comment);
				commentdto.setTid(tid);
				
				String myname = (String) session.getAttribute("mid");
				commentdto.setMid(myname);
				commentdto.setCmtkind(2);
				int final_result = service.insertAssignComment(commentdto);
				System.out.println("receiver insert(기 할당자 몇 명?): " + final_result);
			}
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 테스크 모달에서 assignee 추가하는 이중모달 내에서 플러스 버튼 누르면 assignee 갱신
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/reappendassignee.htm")
	public View reAppendAssignee(int tid, HttpSession session, Model model){
		
		// 해당 테스크의 담당자들
		ArrayList<MemberDTO> sametaskmemberlist = new ArrayList<MemberDTO>();
		sametaskmemberlist = service.getSameTaskAssignee(tid);
		model.addAttribute("sametaskmemberlist", sametaskmemberlist);
		
		return jsonview;
		 		
	}
	
	
	
}
