package com.apollo.task.service;


import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.CommentDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.task.dao.TstatusDAO;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
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
	public TaskDTO getTask(String tid) {
		
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
	public ArrayList<StepDTO> getStepid(String tid) {
		
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
	public ArrayList<TstatusDTO> gettstatuslist(String pid) {
		
		ArrayList<TstatusDTO> tstatuslist = new ArrayList();
		TstatusDAO tstatusdao = session.getMapper(TstatusDAO.class);
		tstatuslist = tstatusdao.getTstatuslist(pid);
		return tstatuslist;
	}
	
	public int insertComment(CommentDTO commentdto) {
		System.out.println("!!인서트 코멘트 서비스!!");
		CommentDAO commentdao = session.getMapper(CommentDAO.class);
		
		 int insertcmt =commentdao.insertComment(commentdto);
		 System.out.println("!!코멘트 테이블에 인서트 성공?!!   "+ insertcmt);
		 ArrayList<String> midlist = commentdao.selectCommentMidlist();
		 System.out.println("!!코멘트 태스크아이디에 할당된 멤버 아이디 select 성공??!!   "+ midlist.get(0));
		 int receiverinsert = commentdao.insertReceiver(midlist);
		 System.out.println("!!리시버 테이블에 인서트 성공??!!   "+receiverinsert);
		 return receiverinsert;
	}
	
	
}



