package com.apollo.step.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;

@Service
public class StepListService {
	
	@Autowired
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : Step클릭 시 소속 프로젝트의 아이디를 가져오기 
	 작성자명 : 박 민 식
	 */
	public int getProjectIdByStepId(int sid) {
		
		int pid = 0;
		try {
			System.out.println("sess "+sqlsession); 
			
			StepDAO dao= sqlsession.getMapper(StepDAO.class);
			pid =dao.getProjectIdByStepId(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pid;
	}
	
}
