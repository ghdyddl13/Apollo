package com.apollo.step.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;

@Service
public class StepListService {
	
	@Autowired
	SqlSession session;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : Step id로 현 스텝이 속한 프로젝트의 id를 가져온다.
	 작성자명 : 박 민 식
	 */
	public int getProjectIdByStepId(int sid) {
		
		int pid = 0;
		try {
			StepDAO dao= session.getMapper(StepDAO.class);
			pid =dao.getProjectIdByStepId(sid);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return pid;
	}
	
}
