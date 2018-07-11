package com.apollo.task.dao;

import java.util.List;
import java.util.Map;

import com.apollo.vo.CommentDTO;
import com.apollo.vo.ReceiverDTO;

public interface CommentDAO {
	
	public int insertComment(CommentDTO commentdto);
	
	public int insertComment2(CommentDTO commentdto);

	public List<String> selectCommentMidlist(int pid);

	public int insertReceiver(Map map);
	
	public int insertReceiver2(ReceiverDTO receiverdto);
	
}
