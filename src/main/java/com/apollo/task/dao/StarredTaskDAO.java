package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.StarredTaskDTO;

public interface StarredTaskDAO {
	
	public ArrayList<StarredTaskDTO> getStarredTaskList(String mid);

}
