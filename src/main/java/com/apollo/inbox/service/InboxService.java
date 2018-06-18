package com.apollo.inbox.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.inbox.dao.InboxDAO;
import com.apollo.vo.CommentDTO;


@Service
public class InboxService {
	@Autowired
	private SqlSession sqlsession;
	
	public ArrayList<CommentDTO> getCommentlist(String mid){
		System.out.println("service commemtlist");
		InboxDAO dao = sqlsession.getMapper(InboxDAO.class);
		ArrayList<CommentDTO> commemtlist = dao.getCommentlist(mid);
		return commemtlist;
	}
	
	public ArrayList<CommentDTO> getSentlist(String mid){
		System.out.println("service sentlist");
		InboxDAO dao = sqlsession.getMapper(InboxDAO.class);
		ArrayList<CommentDTO> sentlist = dao.getSentlist(mid);
		return sentlist;
	}
	public ArrayList<CommentDTO> getArchivelist(String mid){
		System.out.println("service archivelist");
		InboxDAO dao = sqlsession.getMapper(InboxDAO.class);
		ArrayList<CommentDTO> archivelist = dao.getArchivelist(mid);
		return archivelist;
	}
	public int updateArchive(CommentDTO comment) {
		System.out.println("service archiveupdate ");
		System.out.println(comment.getCmtid());
		System.out.println(comment.getMid());
		InboxDAO dao = sqlsession.getMapper(InboxDAO.class);
		int result = dao.updateArchive(comment);
		
		return result; 
	}
	
	public int updateArchive2(CommentDTO comment) {
		System.out.println("service archiveupdate ");
		System.out.println(comment.getCmtid());
		System.out.println(comment.getMid());
		InboxDAO dao = sqlsession.getMapper(InboxDAO.class);
		int result = dao.updateArchive2(comment);
		
		return result; 
	}
}
