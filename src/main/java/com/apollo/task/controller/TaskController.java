package com.apollo.task.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.project.service.ProjectInfoService;
import com.apollo.task.dao.SubtaskDAO;
import com.apollo.task.service.TaskService;
import com.apollo.vo.CommentAndMemberDTO;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidtidDTO;
import com.apollo.vo.StarredTaskDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.SubtaskDTO;
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
	tstatuslist = service.gettstatuslist(tid);
	model.addAttribute("tstatuslist", tstatuslist);
	
	// 해당 테스크의 담당자들
	ArrayList<MemberDTO> sametaskmemberlist = new ArrayList<MemberDTO>();
	sametaskmemberlist = service.getSameTaskAssignee(tid);
	model.addAttribute("sametaskmemberlist", sametaskmemberlist);
	
	// 해당 테스크의 서브테스크들
	ArrayList<SubtaskDTO> subtasklist = new ArrayList<SubtaskDTO>();
	subtasklist = service.getSubTasks(tid);
	model.addAttribute("subtasklist", subtasklist);
	
	// 해당 테스크의 코멘트들과 행위자에 대한 member 테이블 정보
	ArrayList<CommentAndMemberDTO> commentandmemberlist =  new  ArrayList<CommentAndMemberDTO>();
	commentandmemberlist = service.getCommentsAndMember(tid);
	model.addAttribute("commentandmemberlist", commentandmemberlist);

	// 유저의 mid
	model.addAttribute("userid", mid);
	
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
		
		int result = service.deleteTask(tid);
		System.out.println("delete Task 결과 : " + result);
		
	    if(location.equals("/information.htm")) {
	    	return "redirect:/information.htm?pid=" + pid;
	    } else if(location.equals("/board.htm")) {
	    	return "redirect:/board.htm";
	    }else if(location.equals("/list.htm")) {
	    	int sid = (Integer) session.getAttribute("sid");
	    	return "redirect:/list.htm?sid=" + sid;
	    }else if(location.equals("step/timeline.htm")) {
	    	return "redirect:step/timeline.htm";
	    }else if(location.equals("/table.htm")) {
	    	return "redirect:/table.htm";
	    }else {
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
	public String changeTstatus(int tid, int value, String tname, Model model, HttpSession session) {
		
		System.out.println("changetstatus 컨트롤러 실행됨");
		
		TidvalueDTO dto = new TidvalueDTO();
		dto.setTid(tid);
		dto.setValue(value);
		
		int result = service.changeTstatus(dto);
		
		// 상태 변경 성공시 코멘트 입력
		if(result == 1) {
			
			System.out.println("상태변경 성공! 상태변경 코멘트 입력 시작!");
			String comment = "";
			String mid = (String) session.getAttribute("mid");
			ArrayList<TstatusDTO> tstatuslist = new ArrayList();
			tstatuslist = service.gettstatuslist(tid);

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
			
			System.out.println("컨트롤러에서 검증");
			System.out.println(commentdto.getCmtid());
			System.out.println(commentdto.getComments());
			System.out.println(commentdto.getTid());
			System.out.println(commentdto.getMid());
			System.out.println(commentdto.getCmtkind());
			System.out.println("=================");
			
			int insert_comment_result = service.insertComment(commentdto);
			System.out.println("코멘트 입력 여부 : " + insert_comment_result);
			
		} // end - 상태 변경 성공시 발동 조건문 
		
		model.addAttribute("result", result);
		
		String location = (String) session.getAttribute("location");
		int pid = (Integer) session.getAttribute("pid");
		
	    if(location.equals("/information.htm")) {
	    	return "redirect:/information.htm?pid=" + pid;
	    } else if(location.equals("/board.htm")) {
	    	return "redirect:/board.htm";
	    }else if(location.equals("/list.htm")) {
	    	int sid = (Integer) session.getAttribute("sid");
	    	return "redirect:/list.htm?sid=" + sid;
	    }else if(location.equals("step/timeline.htm")) {
	    	System.out.println("timeline반환");
	    	return "redirect:step/timeline.htm";
	    }else if(location.equals("/table.htm")) {
	    	return "redirect:/table.htm";
	    }else {
	    	System.out.println("null반환");
	    	return null;
	    }
		
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 일반 채팅시 코멘트 insert + receiver에 할당자 고려하여 insert
	 작성자명 : 신 호 용
	 */
	@RequestMapping("/insertcomment.htm")
	public String insertComment(CommentDTO commentdto, HttpSession session){
		System.out.println(commentdto.getComments());
		System.out.println(commentdto.getTid());
		System.out.println("!코멘트 인서트 컨트롤러!");
		String mid=(String)session.getAttribute("mid");
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
		
		String myname = (String) session.getAttribute("mid");
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
				modifier = service.getTaskModifierName(myname);
				String assignee_name = service.getTaskModifierName(mid);

				String comment = "";
				comment = modifier + "님이 " + tname +" 업무를 " + assignee_name +"님에게 할당하였습니다";
				
				// comments 테이블에 insert
				CommentDTO commentdto = new CommentDTO();
				commentdto.setComments(comment);
				commentdto.setTid(tid);
				
				String mymid = (String) session.getAttribute("mid");
				commentdto.setMid(mymid);
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
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 테스크 모달에서 sday 를 데이트 피커에서 누르면 sday를 변경
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/changesdayoftask.htm")
	public View changeSdayOfTask(int tid, String sday, HttpSession session, Model model){
		
		System.out.println("sday 컨트롤러 실행");
		
		System.out.println("tid : " + tid);
		System.out.println("sday : " + sday);
		
		TaskDTO dto = new TaskDTO();
		dto.setSday(sday);
		dto.setTid(tid);
		
		int result = service.changeSdayOfTask(dto);
		System.out.println("sday 변경 결과 : " + result);
		
		
		// sday 변경 제대로 되었을 시 stream 저장
		if(result == 1) {
			
			System.out.println("sday 변경 성공! 코멘트 입력 시작!");
			
			String modifier = (String) session.getAttribute("mid");
			String modifier_name = service.getTaskModifierName(modifier);
			String tname = service.getTname(tid);
			String comment = "";
			comment = modifier_name + "님이 " + tname +" 업무의 시작일을 " + sday +"로 변경하였습니다";
			
			// comments 테이블에 insert
			CommentDTO commentdto = new CommentDTO();
			commentdto.setComments(comment);
			commentdto.setTid(tid);
			commentdto.setMid(modifier);
			commentdto.setCmtkind(1);
			int final_result = service.insertComment(commentdto);
			
			System.out.println("sday 변경 코멘트 입력 성공 여부 : " + final_result);
			
		}
		
		return jsonview;
		 		
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 테스크 모달에서 eday 를 데이트 피커에서 누르면 eday를 변경
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/changeedayoftask.htm")
	public View changeEdayOfTask(int tid, String eday, HttpSession session, Model model){
		
		System.out.println("eday 컨트롤러 실행");
		
		System.out.println("tid : " + tid);
		System.out.println("eday : " + eday);
		
		TaskDTO dto = new TaskDTO();
		dto.setEday(eday);
		dto.setTid(tid);
		
		int result = service.changeEdayOfTask(dto);
		System.out.println("eday 변경 결과 : " + result);
		
		
		// eday 변경 제대로 되었을 시 stream 저장
		if(result == 1) {
			
			System.out.println("sday 변경 성공! 코멘트 입력 시작!");
			
			String modifier = (String) session.getAttribute("mid");
			String modifier_name = service.getTaskModifierName(modifier);
			String tname = service.getTname(tid);
			String comment = "";
			comment = modifier_name + "님이 " + tname +" 업무의 종료일을 " + eday +"로 변경하였습니다";
			
			// comments 테이블에 insert
			CommentDTO commentdto = new CommentDTO();
			commentdto.setComments(comment);
			commentdto.setTid(tid);
			commentdto.setMid(modifier);
			commentdto.setCmtkind(1);
			int final_result = service.insertComment(commentdto);
			
			System.out.println("eday 변경 코멘트 입력 성공 여부 : " + final_result);
			
		}
		
		return jsonview;
	}
	

	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 테스크 모달에서 eday 를 데이트 피커에서 누르면 eday를 변경
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/addsubtask.htm")
	public View addSubTask(int tid, String subtaskstr, Model model){
		
		System.out.println("addSubTask 컨트롤러 실행");
		
		SubtaskDTO dto = new SubtaskDTO();
		dto.setTid(tid);
		dto.setSubtask(subtaskstr);
		dto.setIschecked(0);

		int result = service.addSubTask(dto);
		
		System.out.println("addSubTask 실행 결과 : " + result);
		model.addAttribute("result", result);
		
		// 해당 테스크의 서브테스크들
		ArrayList<SubtaskDTO> subtasklist = new ArrayList<SubtaskDTO>();
		subtasklist = service.getSubTasks(tid);
		model.addAttribute("subtasklist", subtasklist);
		
		return jsonview;
	}
		
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 서브테스크의 완료/미완료 여부를 변경한다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/changesubtask.htm")
	public View changeSubtask(int subtaskid, int tid, int subtask_fini_or_unfini, Model model) {
		
		System.out.println("changeSubtask 컨트롤러 실행됨");

		SubtaskDTO dto = new SubtaskDTO();
		dto.setSubtaskid(subtaskid);
		dto.setTid(tid);
		dto.setIschecked(subtask_fini_or_unfini);
		
		int result = service.changeSubtask(dto);
		
		System.out.println("changeSubtask 실행 결과 : " + result);
		model.addAttribute("result", result);
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : subtask 를 삭제
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/deletesubtask.htm")
	public View deleteSubtask(int tid, int subtaskid, Model model) {
		
		System.out.println("deleteSubtask 컨트롤러 실행됨");

		int result = service.deleteSubtask(subtaskid);
		
		System.out.println("deleteSubtask 실행 결과 : " + result);
		model.addAttribute("result", result);
		
		// 해당 테스크의 서브테스크들
		ArrayList<SubtaskDTO> subtasklist = new ArrayList<SubtaskDTO>();
		subtasklist = service.getSubTasks(tid);
		model.addAttribute("subtasklist", subtasklist);
		
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : task의 상세설명 부분을 update
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/updateTaskDetail.htm")
	public View updateTaskDetail(int tid, String content, Model model) {
		
		System.out.println("updateTaskDetail 컨트롤러 실행됨");

		TaskDTO dto = new TaskDTO();
		dto.setTid(tid);
		dto.setDetail(content);
		
		int result = service.updateTask(dto);
		model.addAttribute("result", result);
		
		if(result == 1) {
			System.out.println("Task Detail update 완료!");
		}
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 해당 테스크에 관련한 코멘트 모든 정보와 mid에 관한 member 테이블 모두 가져옴
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getcommentsandmember.htm")
	public View getCommentsAndMember(int tid, Model model){
	
		System.out.println("getCommentsAndMember 컨트롤러 실행됨");
		
		ArrayList<CommentAndMemberDTO> commentandmemberlist =  new  ArrayList<CommentAndMemberDTO>();
		commentandmemberlist = service.getCommentsAndMember(tid);
		model.addAttribute("commentandmemberlist", commentandmemberlist);
		
		System.out.println("getCommentsAndMember 컨트롤러->서비스 모두 거치고 돌아옴");
		
		return jsonview;
	
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 테스크 모달 내부에서 @ 로 같은 프로젝트 사람들 popup div에 띄워야 할 때 쓰는 함수
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/getsameprojectmembersintaskmodal.htm")
	public View getSameprojectMembersInTaskmodal(int pid, Model map){
	
		System.out.println("getSameprojectMembersInTaskmodal 컨트롤러 실행됨");
		
		ArrayList<MemberDTO> sameprojectmembers = new ArrayList<MemberDTO>();
		sameprojectmembers = projectinfoservice.getProjectMembers(pid);
        map.addAttribute("sameprojectmembers", sameprojectmembers);
		
		return jsonview;
	
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 일반 채팅 입력시 comment insert + receiver insert (할당자 고려하여) 
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/insertcommentandreceiver.htm")
	public View insertCommentAndReceiver(int tid, String comments, HttpSession session, Model map){
		
		System.out.println("insertCommentAndReceiver 컨트롤러 실행");
		String mid=(String)session.getAttribute("mid");
		
		CommentDTO dto = new CommentDTO();
		dto.setMid(mid);
		dto.setTid(tid);
		dto.setComments(comments);
		dto.setCmtkind(0);
		
		int result = service.insertInboxComment(dto);
		map.addAttribute("result", result);
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 이름을 찾아준다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/findmname.htm")
	public View findMname(String mid, Model map){
		
		System.out.println("findMname 컨트롤러 실행");
		
		String speaker = service.getTaskModifierName(mid);
		map.addAttribute("speaker", speaker);
		
		return jsonview;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 테스크 모달 내에서 @ 붙여서 지목하여 보내기 
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/selectandchatintaskmodal.htm")
	public View selectAndChatInTaskmodal(int tid, String comments, String receiver, HttpSession session, Model map){
		
		System.out.println("리시버 들어오냐? : " + receiver);
		
		System.out.println("selectAndChatInTaskmodal 컨트롤러 실행");
		String mid=(String)session.getAttribute("mid");
		
		CommentDTO dto = new CommentDTO();
		dto.setMid(mid);
		dto.setTid(tid);
		dto.setComments(comments);
		dto.setCmtkind(0);
		
		int result = service.insertInboxComment2(dto, receiver);
		map.addAttribute("result", result);
		
		return jsonview;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 테스크 모달 내에서 @ 붙여서 지목하여 보내기 
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/changetname.htm")
	public String changeTname(int tid, String tname, HttpSession session, Model map){
		
		System.out.println("changeTname 컨트롤러 실행");
		
		TaskDTO dto = new TaskDTO();
		dto.setTid(tid);
		dto.setTname(tname);
		
		int result = service.changeTname(dto);
		map.addAttribute("result", result);
		
		String location = (String) session.getAttribute("location");
		int pid = (Integer) session.getAttribute("pid");
		
	    if(location.equals("/information.htm")) {
	    	return "redirect:/information.htm?pid=" + pid;
	    } else if(location.equals("/board.htm")) {
	    	return "redirect:/board.htm";
	    }else if(location.equals("/list.htm")) {
	    	int sid = (Integer) session.getAttribute("sid");
	    	return "redirect:/list.htm?sid=" + sid;
	    }else if(location.equals("step/timeline.htm")) {
	    	return "redirect:step/timeline.htm";
	    }else if(location.equals("/table.htm")) {
	    	return "redirect:/table.htm";
	    }else {
	    	return null;
	    }
		
		
		
		
	}
	
	
	
	
	
}
