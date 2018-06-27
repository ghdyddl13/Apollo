package com.apollo.stream.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.inbox.dao.InboxDAO;
import com.apollo.stream.dao.StreamDAO;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.TaskInStepDTO;
@Service
public class StreamService {
	
	@Autowired	
	private SqlSession sqlsession;
	
	public ArrayList<ProjectDTO> getPidlist(String mid){
		System.out.println("service pidlist");
		StreamDAO dao = sqlsession.getMapper(StreamDAO.class);
		ArrayList<ProjectDTO> pidlist = dao.getPidlist(mid);
		return pidlist;
	}
	
	public ArrayList<CommentDTO> getStreamlist(Map map){
		System.out.println("service stream");
		StreamDAO dao = sqlsession.getMapper(StreamDAO.class);
		ArrayList<CommentDTO> streamlist = dao.getStreamlist(map);
		return streamlist;
	}
	
	public ArrayList<Integer> getNewtid(int pid){
		System.out.println("service stream");
		StreamDAO dao = sqlsession.getMapper(StreamDAO.class);
		ArrayList<Integer> newpidlist = dao.getNewtid(pid);
		
		
		return newpidlist;
	}
	
	public ArrayList<TaskInStepDTO> selectSidlist(Map map2){
		System.out.println("service stream");
		StreamDAO dao = sqlsession.getMapper(StreamDAO.class);
		ArrayList<TaskInStepDTO> sidlist = dao.selectSidlist(map2);
		
		
		return sidlist;
	}


}
