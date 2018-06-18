package com.apollo.project.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.member.dao.MemberDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;

@Service
public class ProjectInfoService {
	
	@Autowired
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : pid를 이용해서 해당 프로젝트 소속 task를 가져옴
	 작성자명 : 김 정 권
	 */
	public ArrayList<TaskDTO> getTasks(String pid){
		ArrayList<TaskDTO> tasklist = new ArrayList<TaskDTO>();
		TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
		tasklist = dao.getTasks(pid);
		return tasklist;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 원하는 프로젝트의 테스크 중 누구에게도 할당되지 않은 테스크을 가져온다
	 작성자명 : 김 정 권
	 */
	public ArrayList<TaskDTO> getAssignedTasks(String pid){
		ArrayList<TaskDTO> assignedtasklist = new ArrayList<TaskDTO>();
		TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
		assignedtasklist = dao.getAssignedTasks(pid);
		return assignedtasklist;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 원하는 프로젝트의 테스크 중 누군가에게 할당된 일만 가져온다
	 작성자명 : 김 정 권
	 */
	public ArrayList<TaskDTO> getNotAssignedTasks(String pid){
		ArrayList<TaskDTO> notassignedtasklist = new ArrayList<TaskDTO>();
		TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
		notassignedtasklist = dao.getNotAssignedTasks(pid);
		return notassignedtasklist;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : pid를 이용해서 해당 프로젝트 소속 스텝을 가져옴
	 작성자명 : 김 정 권
	 */
	public ArrayList<StepDTO> getSteps(String pid){
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		StepDAO dao = sqlsession.getMapper(StepDAO.class);
		steplist = dao.getSteps(pid);
		return steplist;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : sid를 이용해서 해당 스텝 소속 테스크들을 가져옴
	 작성자명 : 김 정 권
	 */
	public ArrayList<TaskDTO> getTasksInSteps(int sid){
		ArrayList<TaskDTO> taskinsteplist = new ArrayList<TaskDTO>();
		TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
		taskinsteplist = dao.getTasksByStepId(sid);
		return taskinsteplist;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 13.
	 기      능 : pid를 이용해 같은 프로젝트 소속 멤버들을 가져옴
	 작성자명 : 김 정 권
	 */
	public ArrayList<MemberDTO> getProjectMembers(String pid){
		
		ArrayList<MemberDTO> getProjectMemberlist = new ArrayList<MemberDTO>();
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		getProjectMemberlist = dao.getProjectMemberlist(pid);
		
		return getProjectMemberlist;
	}

}
