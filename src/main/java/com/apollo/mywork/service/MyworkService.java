package com.apollo.mywork.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
  클래스명 : MyworkService
  날      짜 : 2018. 6. 15.
  작성자명 : 이 진 우
 */
import org.springframework.stereotype.Service;

import com.apollo.mywork.dao.MyWorkDAO;
import com.apollo.vo.TaskDTO;
@Service
public class MyworkService {
	@Autowired
	private SqlSession sqlsession;
	
	public ArrayList<TaskDTO> getMyWork(String mid){
		ArrayList<TaskDTO> myworklist = new ArrayList<TaskDTO>();
		MyWorkDAO dao = sqlsession.getMapper(MyWorkDAO.class);
		myworklist = dao.getMyWorkList(mid);
		return myworklist;
	}
}
