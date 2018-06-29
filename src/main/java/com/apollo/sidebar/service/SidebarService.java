package com.apollo.sidebar.service;

import java.util.ArrayList;

import java.util.List;

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
import com.apollo.vo.TaskDTO;

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
	public int updateProject(ProjectDTO projectdto) {
		int result = 0;
		ProjectDAO dao = sqlsession.getMapper(ProjectDAO.class);
		result = dao.updateProject(projectdto);
		
		return result;
		
	}	
		/*
	 날      짜 : 2018. 6. 18.
	 기      능 : 스텝 생성
	 작성자명 : 김 래 영
	 */
	public int insertStep(StepDTO stepdto) {
		int result = 0;
		
		try {
			StepDAO stepdao = sqlsession.getMapper(StepDAO.class);
			result = stepdao.insertStep(stepdto);
			System.out.println("result " + result);
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
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : 참여중인 프로젝트들에 포함된 모든 스텝을 가져오는 함수
	 작성자명 : 박 민 식
	 */
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
	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : fid 로 폴더 정보 가져오기
	 작성자명 : 김 래 영
	 */
	public FolderDTO selectFolder(String fid) {
		FolderDTO folderinfo = null;
		try {
			FolderDAO dao = sqlsession.getMapper(FolderDAO.class);
			folderinfo = dao.selectFolder(fid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folderinfo;
		
	}
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 푤더 수정
	 작성자명 : 김 래 영
	 */
	public int updateFolder(FolderDTO folderdto) {
		int folderresult = 0;
		try {
			FolderDAO dao = sqlsession.getMapper(FolderDAO.class);
			folderresult = dao.updateFolder(folderdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folderresult;
		
		
		
	};
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 폴더 삭제 (영구삭제)
	 작성자명 : 김 래 영
	 */
	public int deleteFolder(String fid) {
		int result = 0;		
		try {
			FolderDAO dao = sqlsession.getMapper(FolderDAO.class);
			result = dao.deleteFolder(fid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : 프로젝트 정보 가져오기
	 작성자명 : 박 민 식
	 */
	public ProjectDTO selectProject(int pid) {
		ProjectDTO projectdto= null;
		try {
			ProjectDAO dao = sqlsession.getMapper(ProjectDAO.class);
			projectdto =dao.selectProject(pid);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return projectdto;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : sid 로 폴더 정보 가져오기
	 작성자명 : 김 래 영
	 */
	public StepDTO selectStep(int sid) {
		StepDTO stepinfo = null;
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			stepinfo = dao.selectStep(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stepinfo;		
		
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : 스텝 수정
	 작성자명 : 김 래 영
	 */
	public int updateStep(StepDTO stepdto) {
		int stepresult = 0;		
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			stepresult = dao.updateStep(stepdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(stepresult > 0) {
			System.out.println("step update success");
		}else {
			System.out.println("step update fail");
		}
		return stepresult;
		
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : sid 에 속한 task 삭제 (선행)
	 작성자명 : 김 래 영
	 */
	public int deleteTaskInStep(int sid) {
		System.out.println("delete taskinstep 들어옴");
		int result = 0;
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			result = dao.deleteTaskInStep(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : step 삭제 (후행)
	 작성자명 : 김 래 영
	 */
	public int deleteStep(int sid) {
		System.out.println("delete delete Step 들어옴");
		int result = 0;
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			result = dao.deleteStep(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
		
		
	}
	
	

	public int moveStep(StepDTO stepdto) {
		
		int result = 0;
		try {
			StepDAO dao = sqlsession.getMapper(StepDAO.class);
			result = dao.moveStep(stepdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
