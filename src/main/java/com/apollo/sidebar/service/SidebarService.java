package com.apollo.sidebar.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.project.dao.ProjectDAO;
import com.apollo.sidebar.dao.FolderDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.vo.FolderDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;
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
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : 참여중인 프로젝트들에 포함되어 있는 폴더의 정보를 모두 가져오는 함수
	 작성자명 : 박 민 식
	 */
	public ArrayList<FolderDTO> selectFolderList(List<Integer> pids){
		ArrayList<FolderDTO> folderlist = null;
		try {
			FolderDAO dao= sqlsession.getMapper(FolderDAO.class);
			folderlist= dao.selectFolderList(pids);
		} catch (Exception e) {
			System.out.println("selectFolderList service");
			e.printStackTrace();
		}
		
		return folderlist;
	};
	
	public ArrayList<StepDTO>  selectStepList(List<Integer> pids){
		ArrayList<StepDTO> steplist = null;
		try {
			StepDAO dao= sqlsession.getMapper(StepDAO.class);
			steplist= dao.selectStepList(pids);
		} catch (Exception e) {
			System.out.println("selectFolderList service");
			e.printStackTrace();
		}
		
		return steplist;
	};
	
}
