package com.apollo.step.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TaskInStepDTO;
import com.apollo.vo.TstatusDTO;

@Service
public class StepBoardService {

	@Autowired
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : board에서 task 생성 
	 작성자명 : 이 창 훈
	 */
	public int insertBoardTask(TaskDTO taskdto) {
		int result = 0;
		try {
			TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
			result = dao.insertBoardTask(taskdto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int insertBoardTaskInStep(int tid, int sid) {
		System.out.println("tid : " + tid + "/ sid : " + sid);
		int result = 0;
		try {
			TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
			
			TaskInStepDTO dto = new TaskInStepDTO();
			dto.setSid(sid);
			dto.setTid(tid);
			
			result = dao.insertBoardTaskInStep(dto);
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : board에서 task를 드래그를 통해 이동하여 update함. 
	 작성자명 : 이 창 훈
	 */
	public int updateBoardTaskByTid(TaskDTO taskdto) {
		int result = 0;
		try {
			TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
			result = dao.updateTask(taskdto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : step-board에서 각 task상태 불러오는 함수 
	 작성자명 : 이 창 훈
	 */

	public ArrayList<TstatusDTO> selectTstatusBySid(int sid) {
		ArrayList<TstatusDTO> result = null;
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			result = dao.selectTstatusBySid(sid);
		}catch (Exception e) {
		   System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : sid를 이용해서 해당 step 소속 task를 가져옴
	 작성자명 : 이 창 훈
	 */
	public ArrayList<TaskDTO> getTasksByStepId(int sid){
		ArrayList<TaskDTO> result = null;
		try {
			TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
			result = dao.getTasksByStepId(sid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	
}
