package com.apollo.sidebar.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.project.dao.ProjectDAO;
import com.apollo.sidebar.dao.FolderDAO;
import com.apollo.vo.ProjectDTO;
/* 
  클래스명 : SidebarService
  날      짜 : 2018. 6. 13.
  작성자명 : 김 래 영
 */
@Service
public class SidebarService {

	@Autowired
	private SqlSession sqlsession;
	/*
	 날      짜 : 2018. 6. 13.
	 기      능 : 프로젝트 생성
	 작성자명 : 김 래 영
	 */
	public int insertProject(ProjectDTO projectdto) {
		int result = 0;
		try {
			ProjectDAO dao = sqlsession.getMapper(ProjectDAO.class);
			result = dao.insertProject(projectdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			System.out.println("insert success");
		}else {
			System.out.println("insert fail");
		}
		return result;
		
	}
	/*
	 날      짜 : 2018. 6. 13.
	 기      능 : 프로젝트 수정
	 작성자명 : 김 래 영
	 */
	public int updateProejct(ProjectDTO projectdto) {
		int result = 0;
		ProjectDAO dao = sqlsession.getMapper(ProjectDAO.class);
		result = dao.updateProject(projectdto);
		
		return result;
		
	}
	/* 
	 날      짜 : 2018. 6. 13.
	 기      능 : 프로젝트 삭제
	 작성자명 : 김 래 영
	 */
	public int deleteProject(ProjectDTO projectdto) {
		int result = 0;
		ProjectDAO dao = sqlsession.getMapper(ProjectDAO.class);
		result = dao.deleteProject(projectdto);
		
		return result;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : 프로젝트 리스트 가져오기
	 작성자명 : 박 민 식
	 */
	public ArrayList<ProjectDTO> selectProjectList(String mid){
		
		ArrayList<ProjectDTO> result = null;
		try {
			ProjectDAO dao= sqlsession.getMapper(ProjectDAO.class);
			result = dao.selectProjectList(mid);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("project list service 에러");
		}
		return result;
	}
	
}
