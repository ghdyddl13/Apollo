package com.apollo.step.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.TaskDTO;
/**
 * 
  클래스명 : StepTimelineService
  날      짜 : 2018. 6. 15.
  작성자명 : 박 민 식
 */
import com.apollo.vo.TstatusDTO;
@Service
public class StepTimelineService {
	
	@Autowired
	private SqlSession sqlsession;
	

	public ArrayList<TaskDTO> getTasksByStepId(int sid){

		ArrayList<TaskDTO> tasklist= new ArrayList<TaskDTO>();
		try {

			TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
			tasklist = dao.getTasksByStepId(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasklist;
	}
	
	
	public ArrayList<TstatusDTO> selectTstatusBySid(int sid){
	
		ArrayList<TstatusDTO> result = null;
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			result = dao.selectTstatusBySid(sid);
		}catch (Exception e) {
		   System.out.println(e.getMessage());
		}
		
		System.out.println("result : " + result);
		return result;
	
	}
}
