package com.apollo.inbox.dao;

import java.util.ArrayList;
import java.util.Map;

import com.apollo.vo.CommentAndMemberDTO;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.MidpidDTO;

public interface InboxDAO {
	
	public ArrayList<CommentDTO> getCommentlist(String mid);
	
	public ArrayList<CommentDTO> getSentlist(String mid);
	
	public ArrayList<CommentDTO> getArchivelist(String mid);
	
	public int updateArchive(CommentDTO comment);
	
	public int updateArchive2(CommentDTO comment);
	
	public ArrayList<CommentAndMemberDTO> getCommentsAndMember(int tid);
	
	public int newCheck(Map map);
	
	public int newCount(String mid);
	
	public int updateNewCount(String mid);
	
	public int updateNewCheckSent(String mid);
	public int deleteReceiverAfterDeletePmember(MidpidDTO midpid);
}
