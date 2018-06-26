package com.apollo.task.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.member.dao.MemberDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.AssigneeDAO;
import com.apollo.task.dao.CommentDAO;
import com.apollo.task.dao.StarredTaskDAO;
import com.apollo.task.dao.SubtaskDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.task.dao.TstatusDAO;
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

@Service
public class TaskService {


	@Autowired	
	private SqlSession session;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : 테스크 정보 변경
	 작성자명 : 박 민 식
	 */
	public int updateTask(TaskDTO taskdto) {
		int result = 0;
		
		try {
			TaskDAO dao = session.getMapper(TaskDAO.class);
			result = dao.updateTask(taskdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : tid로 task 가져오기
	 작성자명 : 김 정 권
	 */
	public TaskDTO getTask(int tid) {
		
		TaskDTO taskdto = new TaskDTO();
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		taskdto = taskdao.getTask(tid);
		
		return taskdto;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : tid로 해당 task가 속한 step들 가져오기
	 작성자명 : 김 정 권
	 */
	public ArrayList<StepDTO> getStepid(int tid) {
		
		ArrayList<StepDTO> steplist = new ArrayList();
		StepDAO stepdao = session.getMapper(StepDAO.class);
		steplist = stepdao.getStepsid(tid);
		
		return steplist;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : pid로 tstatus 가져오기
	 작성자명 : 김 정 권
	 */
	public ArrayList<TstatusDTO> gettstatuslist(int pid) {
		
		ArrayList<TstatusDTO> tstatuslist = new ArrayList();
		TstatusDAO tstatusdao = session.getMapper(TstatusDAO.class);
		tstatuslist = tstatusdao.getTstatuslist(pid);
		return tstatuslist;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : mid를 이용해서 즐겨찾기 한 tid들을 가져온다
	 작성자명 : 김 정 권
	 */
	public ArrayList<StarredTaskDTO> getStarredTaskList(String mid){

		ArrayList<StarredTaskDTO> starredtasklist = new ArrayList();
		StarredTaskDAO dao = session.getMapper(StarredTaskDAO.class);
		starredtasklist = dao.getStarredTaskList(mid);
		return starredtasklist;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 즐겨찾기 추가
	 작성자명 : 김 정 권
	 */
	public int addstar(StarredTaskDTO dto) {
		
		System.out.println("addstar 탔음");
		StarredTaskDAO stardao = session.getMapper(StarredTaskDAO.class);
		int result = stardao.addStar(dto);
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 즐겨찾기 삭제
	 작성자명 : 김 정 권
	 */
	public int deletestar(StarredTaskDTO dto) {
		
		System.out.println("deletestar 탔음");

		StarredTaskDAO stardao = session.getMapper(StarredTaskDAO.class);
		int result = stardao.deleteStar(dto);
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : task 삭제
	 작성자명 : 김 정 권
	 */
	public int deleteTask(int tid) {
		
		System.out.println("delete tid 서비스 탔음");
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.deleteTask(tid);
		return result;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : task가 속한 스텝 삭제
	 작성자명 : 김 정 권
	 */
	public int deleteStepInTaskModal(TaskInStepDTO dto) {
		
		System.out.println("deleteStepInTaskModal 서비스 탔음");
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.deleteStepInTaskModal(dto);
		return result;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : 해당 task가 몇 개의 step에 속해 있는지 확인
	 작성자명 : 김 정 권
	 */
	public int countTaskInStep(int tid) {
		
		System.out.println("countTaskInStep 서비스 탔음");
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.countTaskInStep(tid);
		return result;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : 해당 task가 몇 개의 step에 속해 있는지 확인
	 작성자명 : 김 정 권
	 */
	public int changeTstatus(TidvalueDTO dto) {
		
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.changeTstatus(dto);
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : 코멘트 입력
	 작성자명 : 김 정 권
	 */
	public int insertComment(CommentDTO commentdto) {
		System.out.println("insertComment 서비스 메소드 실행");
		CommentDAO commentdao = session.getMapper(CommentDAO.class);
		int result = commentdao.insertComment2(commentdto);
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 할당시 코멘트 입력
	 작성자명 : 김 정 권
	 */
	public int insertAssignComment(CommentDTO commentdto) {
		
		System.out.println("insertAssignComment 서비스 메소드 실행");
		
		CommentDAO commentdao = session.getMapper(CommentDAO.class);
		int insert_comment_result = commentdao.insertComment(commentdto);
		
		// receiver 테이블에 insert
		Map map = new HashMap();
		int tid = commentdto.getTid();
		int cmtid = commentdto.getCmtid();
		 
		List<String> midlist = commentdao.selectCommentMidlist(tid);
		System.out.println("코멘트 태스크아이디에 할당된 멤버 아이디 select 성공");
		 
		map.put("cmtid", commentdto.getCmtid());
		int result = 0;
		for(int i =0 ;i<midlist.size();i++) {
			 map.put("mid", midlist.get(i));
			 result += commentdao.insertReceiver(map);
			 System.out.println("receiver 테이블에 인서트 횟수 : " + result);
		 }
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : Task 상태 변경자 이름 가져오기
	 작성자명 : 김 정 권
	 */
	public String getTaskModifierName(String mid) {
		
		MemberDAO memberdao = session.getMapper(MemberDAO.class);
		String name = memberdao.getTaskModifierName(mid);
		return name;
	}

	/**
	 * 
	 날      짜 : 2018. 6. ??.
	 기      능 : 
	 작성자명 : 신 호 용
	 */
	public int insertInboxComment(CommentDTO commentdto){
		System.out.println("!!인서트 코멘트 서비스!!");
		CommentDAO commentdao = session.getMapper(CommentDAO.class);
		Map map = new HashMap();
		//넘어오는 commentdto  안에는 현재 mid이름 , tid , 코멘트 내용
		 int insertcmt =commentdao.insertComment(commentdto);
		 System.out.println("!!코멘트 테이블에 인서트 성공?!!"+ insertcmt);
		 int tid = commentdto.getTid();
		 
	     int cmtid = commentdto.getCmtid();
		 
		 List<String> midlist = commentdao.selectCommentMidlist(tid);
		 System.out.println("!!코멘트 태스크아이디에 할당된 멤버 아이디 select 성공??!!");
		 
		 
		 map.put("cmtid", commentdto.getCmtid());
		 int result = 0;
		 boolean exist = false;
		 for(int i =0 ;i<midlist.size();i++) {
			 if(midlist.get(i).equals(commentdto.getMid())){
				 exist = true;
			 }
			 map.put("mid", midlist.get(i));
			 result = commentdao.insertReceiver(map);
			 System.out.println("!!리시버 테이블에 인서트중!!");
		 }
		 
		 if(exist == false) {
			 map.put("mid", commentdto.getMid());
			 result = commentdao.insertReceiver(map);
			 System.out.println("!!그 테스크에 할당된 사람이 아니어도 보낸 사람이면 넣어줌!!");
		 }
		 System.out.println("!!리시버 테이블에 인서트 성공??!!");
		 return result;
	}
	
	

	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : tid로 스텝들 가져오기
	 작성자명 : 김 정 권
	 */
	public ArrayList<StepDTO> getStepListByTid(int tid) {

		StepDAO stepdao = session.getMapper(StepDAO.class);
		ArrayList<StepDTO> steplist = new ArrayList();
		steplist = stepdao.getStepListByTid(tid);
		
		return steplist;
	}
	
		
	/**
	 * 
	 날      짜 : 2018. 6. 23.
	 기      능 : 테스크 모달 창에서 스텝 추가 버튼을 누르면 실행
	 작성자명 : 김 정 권
	 */
	public int addTaskInStepInTaskModal(TaskInStepDTO taskinstepdto) {
		
		System.out.println("테스크 모달 내 스텝 추가 서비스 메소드 실행");
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.addTaskInStepInTaskModal(taskinstepdto);
		
		System.out.println("스텝 추가 결과 : " + result);
		
		return result;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 23.
	 기      능 : tid를 통해 step들의 이름을 가져온다
	 작성자명 : 김 정 권
	 */
	public ArrayList<StepDTO> getStepNamesbytid(int tid) {
		
		ArrayList<StepDTO> list = new ArrayList();
		StepDAO stepdao = session.getMapper(StepDAO.class);
		list = stepdao.getStepNamesbytid(tid);
		
		return list;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : 같은 테스크 담당자들 불러옴 
	 작성자명 : 김 정 권
	 */
	public ArrayList<MemberDTO> getSameTaskAssignee(int tid){
		
		ArrayList<MemberDTO> list = new ArrayList();
		MemberDAO memberdao = session.getMapper(MemberDAO.class);
		list = memberdao.getSameTaskMemberList(tid);
		
		return list;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : 같은 프로젝트이지만 해당 테스크의 담당자가 아닌 사람들 목록을 불러온다 
	 작성자명 : 김 정 권
	 */
	public ArrayList<MemberDTO> getSameProjectButNotSameTaskMemberList(TidpidDTO dto){
		
		System.out.println("assignee 추가를 위한 이중 모달 데이터 서비스 실행");
		
		ArrayList<MemberDTO> list = new ArrayList();
		MemberDAO memberdao = session.getMapper(MemberDAO.class);
		list = memberdao.getSameProjectButNotSameTaskMemberList(dto);
		
		return list;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : 테스크 모달 내 업무 담당자 삭제
	 작성자명 : 김 정 권
	 */
	public int deleteAssignee(MidtidDTO dto) {
		
		System.out.println("deleteAssignee 서비스 탔음");
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.deleteAssignee(dto);
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 테스크 모달에서 assignee 추가하는 이중모달 내에서 플러스 버튼 누르면 실행
	 			  테스크에 업무 담당자를 할당하고 코멘트 입력한다
	 작성자명 : 김 정 권
	 */
	public int addAssigneeInTaskModal(MidtidDTO midtiddto) {
		
		System.out.println("assignee 추가 서비스 탔음");
		AssigneeDAO dao = session.getMapper(AssigneeDAO.class);
		int result = dao.addAssigneeInTaskModal(midtiddto);
		System.out.println("assignee 추가 결과 : " + result);
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 테스크 모달에서 sday 를 데이트 피커에서 누르면 sday를 변경
	 작성자명 : 김 정 권
	 */
	public int changeSdayOfTask(TaskDTO dto) {
	
		System.out.println("sday service 실행");
		
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.changeSdayOfTask(dto);
		
		return result;	
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 테스크 모달에서 eday 를 데이트 피커에서 누르면 eday를 변경
	 작성자명 : 김 정 권
	 */
	public int changeEdayOfTask(TaskDTO dto) {

		System.out.println("eday service 실행");
	
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.changeEdayOfTask(dto);
		
		return result;	
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : tid로 tname 을 가져온다
	 작성자명 : 김 정 권
	 */
	public String getTname(int tid) {
		
		System.out.println("getTname service 실행");
		
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		String tname = taskdao.getTname(tid);
		System.out.println("tname 테스트 출력: " + tname);
		return tname;
		
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : sub task 추가 창에서 enter(keycode = 13) 실행시 작동
	 작성자명 : 김 정 권
	 */
	public int addSubTask(SubtaskDTO dto) {
		
		System.out.println("addSubTask 서비스 실행");
		
		SubtaskDAO subtaskdao = session.getMapper(SubtaskDAO.class);
		int result = subtaskdao.addSubTask(dto);
		
		return result;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : Task 모달 띄울 때에 해당 task의 서브 테스크들을 가져온다 
	 작성자명 : 김 정 권
	 */
	public ArrayList<SubtaskDTO> getSubTasks(int tid){
		
		System.out.println("getSubTasks 서비스 실행됨");
		
		ArrayList<SubtaskDTO> list = new ArrayList();
		SubtaskDAO subtaskdao = session.getMapper(SubtaskDAO.class);
		list = subtaskdao.getSubTasks(tid);
		
		return list;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 서브테스크의 완료/미완료 여부를 변경한다
	 작성자명 : 김 정 권
	 */
	public int changeSubtask(SubtaskDTO dto) {
		
		System.out.println("changeSubtask 서비스 실행됨");
		
		SubtaskDAO subtaskdao = session.getMapper(SubtaskDAO.class);
		int result = subtaskdao.changeSubtask(dto);
		
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : subtask 를 삭제
	 작성자명 : 김 정 권
	 */
	public int deleteSubtask(int subtaskid) {
		
		System.out.println("deleteSubtask 서비스 실행됨");
		
		SubtaskDAO subtaskdao = session.getMapper(SubtaskDAO.class);
		int result = subtaskdao.deleteSubtask(subtaskid);
		return result;
		
	}
	
}



