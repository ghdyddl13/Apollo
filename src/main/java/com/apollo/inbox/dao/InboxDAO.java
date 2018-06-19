package com.apollo.inbox.dao;

import java.util.ArrayList;

import com.apollo.vo.CommentDTO;

public interface InboxDAO {
	
	public ArrayList<CommentDTO> getCommentlist(String mid);
	
	public ArrayList<CommentDTO> getSentlist(String mid);
	
	public ArrayList<CommentDTO> getArchivelist(String mid);
	
	public int updateArchive(CommentDTO comment);
	
	public int updateArchive2(CommentDTO comment);
	
	public ArrayList<CommentDTO> getStreamlist(String mid);
}
