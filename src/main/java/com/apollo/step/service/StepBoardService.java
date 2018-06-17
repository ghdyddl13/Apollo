package com.apollo.step.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;
import com.apollo.vo.TstatusDTO;

@Service
public class StepBoardService {

	@Autowired
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : step-board에서 각 task상태 불러오는 함수 
	 작성자명 : 이 창 훈
	 */
	public ArrayList<TstatusDTO> selectTstatusBySid(int sid) {
		System.out.println("보드 서비스 들어왔다");
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
