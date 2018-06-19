package com.apollo.sidebar.service;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apollo.member.dao.MemberDAO;
import com.apollo.project.dao.ProjectDAO;
import com.apollo.sidebar.dao.FolderDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.vo.FolderDTO;
import com.apollo.vo.MemberDTO;
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
			System.out.println("project insert success");
		}else {
			System.out.println("project insert fail");
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
	/*
	 날      짜 : 2018. 6. 18.
	 기      능 : 스텝 생성
	 작성자명 : 김 래 영
	 */
	public int insertStep(StepDTO stepdto) {
		System.out.println("service step");
		int result = 0;
		
		try {
			StepDAO stepdao = sqlsession.getMapper(StepDAO.class);
			result = stepdao.insertStep(stepdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			System.out.println("step insert success");
		}else {
			System.out.println("step insert fail");
		}
		return result;
		
	}
	/*
	 날      짜 : 2018. 6. 18.
	 기      능 : 스텝 생성시 pid를 이용하여 프로젝트 참여자 명단 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<MemberDTO> getMemberList(int pid) {
		
		//System.out.println("pid : " + pid);
		
		ArrayList<MemberDTO> memberlist = new ArrayList<MemberDTO>();
		
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			memberlist = dao.getProjectMemberlist2(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberlist;
		
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
	/*
	 날      짜 : 2018. 6. 19.
	 기      능 : 폴더 생성
	 작성자명 : 김 래 영
	 */
	public int insertfolder(FolderDTO folderdto) {
		int result = 0;
		try {
			FolderDAO folderdao = sqlsession.getMapper(FolderDAO.class);
			result = folderdao.insertfolder(folderdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			System.out.println("folder insert success");
		}else {
			System.out.println("folder insert fail");
		}
		
		return result;
		
	}
	
}
