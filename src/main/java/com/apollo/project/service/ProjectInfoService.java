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
	
	public ArrayList<TaskDTO> getTasks(String pid){
		ArrayList<TaskDTO> tasklist = new ArrayList<TaskDTO>();
		TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
		tasklist = dao.getTasks(pid);
		return tasklist;
	}
	
	public ArrayList<StepDTO> getSteps(String pid){
		ArrayList<StepDTO> steplist = new ArrayList<StepDTO>();
		StepDAO dao = sqlsession.getMapper(StepDAO.class);
		steplist = dao.getSteps(pid);
		return steplist;
	}
	
	public ArrayList<TaskDTO> getTasksInSteps(String sid){
		ArrayList<TaskDTO> taskinsteplist = new ArrayList<TaskDTO>();
		TaskDAO dao = sqlsession.getMapper(TaskDAO.class);
		taskinsteplist = dao.getTasksInSteps(sid);
		return taskinsteplist;
	}
	
	public ArrayList<MemberDTO> getProjectMembers(String pid){
		
		ArrayList<MemberDTO> getProjectMemberlist = new ArrayList<MemberDTO>();
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		getProjectMemberlist = dao.getProjectMemberlist(pid);
		
		return getProjectMemberlist;
	}
	
}
