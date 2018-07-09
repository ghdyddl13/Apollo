package com.apollo.task.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.apollo.inbox.dao.InboxDAO;
import com.apollo.member.dao.MemberDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.AssigneeDAO;
import com.apollo.task.dao.CommentDAO;
import com.apollo.task.dao.FileDAO;
import com.apollo.task.dao.StarredTaskDAO;
import com.apollo.task.dao.SubtaskDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.task.dao.TstatusDAO;
import com.apollo.utils.DeleteFileUtils;
import com.apollo.utils.UploadFileUtils;
import com.apollo.vo.CommentAndMemberDTO;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.FileDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidtidDTO;
import com.apollo.vo.ReceiverDTO;
import com.apollo.vo.StarredTaskDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.SubtaskDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TaskInStepDTO;
import com.apollo.vo.TidpidDTO;
import com.apollo.vo.TidvalueDTO;
import com.apollo.vo.TstatusDTO;
import com.apollo.vo.filedataDTO;

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
	public ArrayList<TstatusDTO> gettstatuslist(int tid) {
		
		ArrayList<TstatusDTO> tstatuslist = new ArrayList();
		TstatusDAO tstatusdao = session.getMapper(TstatusDAO.class);
		tstatuslist = tstatusdao.getTstatuslist(tid);
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
	public int insertComment(CommentDTO dto) {
		System.out.println("insertComment 서비스 메소드 실행");
		
		System.out.println("서비스에서 검증");
		System.out.println(dto.getCmtid());
		System.out.println(dto.getComments());
		System.out.println(dto.getTid());
		System.out.println(dto.getMid());
		System.out.println(dto.getCmtkind());
		System.out.println("=================");
		
		CommentDAO commentdao = session.getMapper(CommentDAO.class);
		int result = commentdao.insertComment2(dto);
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
	 날      짜 : 2018. 7. 2.
	 기      능 : 웹소켓에서 사용
	 작성자명 : 신 호 용
	 */
		public ArrayList<String> getMidinAssingnee(int tid) {
		
		System.out.println("changeTname 서비스 실행");
		AssigneeDAO assigneedao = session.getMapper(AssigneeDAO.class);
		ArrayList<String> result = assigneedao.getMidinAssingnee(tid);
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
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : 해당 테스크에 관련한 코멘트 모든 정보와 mid에 관한 member 테이블 모두 가져옴
	 작성자명 : 김 정 권
	 */
	public ArrayList<CommentAndMemberDTO> getCommentsAndMember(int tid){
		
		System.out.println("getCommentsAndMember 서비스 실행됨");
		
		InboxDAO dao = session.getMapper(InboxDAO.class);
		ArrayList<CommentAndMemberDTO> list =  new  ArrayList<CommentAndMemberDTO>();
		list = dao.getCommentsAndMember(tid);
		
		return list;
		
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 27.
	 기      능 : @ 를 사용한 코멘트 입력시 작동 함수 
	 작성자명 : 김 정 권
	 */
	public int insertInboxComment2(CommentDTO commentdto, String receiver){
		
		System.out.println("insertInboxComment2 서비스 실행");
		CommentDAO commentdao = session.getMapper(CommentDAO.class);
		
     	int insertcmt =commentdao.insertComment(commentdto);
		System.out.println("코멘트 테이블에 인서트 성공 여부 : "+ insertcmt);
		
		System.out.println("----------------------------");
		System.out.println("mid : " + commentdto.getMid());
		System.out.println("receiver : " + receiver);
		System.out.println("----------------------------");
		
		ReceiverDTO receiverdto = new ReceiverDTO();
		receiverdto.setCmtid(commentdto.getCmtid());
		receiverdto.setMid(commentdto.getMid());
		receiverdto.setIsarchive(0);
		int result = commentdao.insertReceiver2(receiverdto);
		System.out.println("Receiver에 insert 완료!");
		
		// mid 를 receiver 말고 보낸이를 넣어서 다시 receiver에 insert
		receiverdto.setMid(receiver);
		result += commentdao.insertReceiver2(receiverdto);
		
		System.out.println("@를 이용한 코멘트 입력 총 결과 : " + result);
		
		return result;
	
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 29.
	 기      능 : tname을 변경 
	 작성자명 : 김 정 권
	 */
	public int changeTname(TaskDTO dto) {
		
		System.out.println("changeTname 서비스 실행");
		TaskDAO taskdao = session.getMapper(TaskDAO.class);
		int result = taskdao.changeTname(dto);
		return result;
	}
	

	/**
	 * 
	 날      짜 : 2018. 7. 8.
	 기      능 : Task 모달 내 파일 업로드
	 작성자명 : 김 정 권
	 */
	public LinkedList<filedataDTO> upLoadFileInTaskModal(int tid, MultipartHttpServletRequest request){
		System.out.println("upLoadFileInTaskModal 서비스 실행");
		
		String savepath= "resources/upload_files";
		LinkedList<filedataDTO> files = new LinkedList<filedataDTO>();
		filedataDTO filedata = null;
		
		Iterator<String> itr =request.getFileNames();
		MultipartFile mpf = null;
		while(itr.hasNext()) {
			mpf = request.getFile(itr.next());
			
			//파일 정보가 없는 경우
			if(mpf ==null || mpf.getSize()<=0) {
				return null;
			}
			//fileMetaDTO에 파일정보 입력
			filedata = new filedataDTO();
			System.out.println("파일 이름이에요: "+mpf.getOriginalFilename());
			filedata.setFilename(mpf.getOriginalFilename());
			filedata.setFileSize(mpf.getSize()/1024+"kb");
			filedata.setFileType(mpf.getContentType());
			
			//경로 설정
			String filename =null;
			String originalName = mpf.getOriginalFilename();
			
			//SQL 파일 입력
			FileDAO dao = session.getMapper(FileDAO.class);
			
			FileDTO filedto = new FileDTO();
			try {
				//FILE DATADTO에 바이트 정보 입력
				filedata.setBytes(mpf.getBytes());
				
				//AWS S3에 파일 업로드
				filename = UploadFileUtils.uploadFile(savepath, 0, originalName, mpf.getBytes());
				
				//DB에 파일 정보 입력
				filedto.setTid(tid);
				filedto.setFilename(filename);
				
				System.out.println("tid, filename 테스트 출력");
				System.out.println("tid : " + tid);
				System.out.println("filename : " + filename);
				dao.upLoadFileInTaskModal(filedto);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			files.add(filedata);
		}
		
		return files;
	}
	

	/**
	 * 
	 날      짜 : 2018. 7. 8.
	 기      능 : Task 모달 내 파일 업로드 후 파일 목록 리셋
	 작성자명 : 김 정 권
	 */
	public ArrayList<FileDTO> getFileList(int tid){
		
		System.out.println("getFileList 서비스 실행");
		ArrayList<FileDTO> filelist = new ArrayList();
		FileDAO dao = session.getMapper(FileDAO.class);
		filelist = dao.getFileList(tid);
		
		return filelist;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 8.
	 기      능 : Task 모달 내 파일 삭제
	 작성자명 : 김 정 권
	 */
	public ArrayList<FileDTO> deleteFile(int tid, String filename, String filepath) {
		
		System.out.println("delete file 서비스 시작");
		try {
			DeleteFileUtils.deleteFile(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("파일 삭제 완전히 완료");		
		
		FileDAO dao = session.getMapper(FileDAO.class);
		int result = dao.deleteFile(filename);
		System.out.println("RDB 에서 파일 삭제 완료");
		
		ArrayList<FileDTO> filelist = new ArrayList();
		filelist = dao.getFileList(tid);
		System.out.println("새 목록 가져와서 view로 던져줌");
		
		return filelist;
	}
	
	
	public void downLoadFileInTaskModal(String filename) {
	
//		String downloadPath = "resources/upload_files/" + filename;
//		
//		try {
//			DownloadFileUtils.downFiles(downloadPath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
	
}



