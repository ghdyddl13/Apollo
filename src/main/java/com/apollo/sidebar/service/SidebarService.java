package com.apollo.sidebar.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.apollo.sidebar.dao.FolderDAO;
import com.apollo.vo.ProjectDTO;
/* 
  클래스명 : SidebarService
  날      짜 : 2018. 6. 13.
  작성자명 : 김 래 영
 */
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
		FolderDAO dao = sqlsession.getMapper(FolderDAO.class);
		result = dao.insertProject(projectdto);
		
		return result;
		
	}
	/*
	 날      짜 : 2018. 6. 13.
	 기      능 : 프로젝트 수정
	 작성자명 : 김 래 영
	 */
	public int updateProejct(ProjectDTO projectdto) {
		int result = 0;
		FolderDAO dao = sqlsession.getMapper(FolderDAO.class);		
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
		FolderDAO dao = sqlsession.getMapper(FolderDAO.class);
		result = dao.deleteProject(projectdto);
		
		return result;
	}
	
}
