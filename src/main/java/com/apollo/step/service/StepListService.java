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
	 ��      ¥ : 2018. 6. 14.
	 ��      �� : Step id�� �� ������ ���� ������Ʈ�� id�� �����´�.
	 �ۼ��ڸ� : �� �� ��
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
