package com.apollo.stream.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.inbox.dao.InboxDAO;
import com.apollo.stream.dao.StreamDAO;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.ProjectDTO;
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
	
	public ArrayList<CommentDTO> getStreamlist(int pid){
		System.out.println("service stream");
		StreamDAO dao = sqlsession.getMapper(StreamDAO.class);
		ArrayList<CommentDTO> streamlist = dao.getStreamlist(pid);
		return streamlist;
	}
}
