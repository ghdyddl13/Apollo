package com.apollo.task.dao;


import java.util.ArrayList;
import java.util.List;

import com.apollo.vo.CommentDTO;

public interface CommentDAO {
	public int insertComment(CommentDTO commentdto);
	
	public ArrayList<String> selectCommentMidlist();
	
	public int insertReceiver(List<String> midlist);
}
