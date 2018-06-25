package com.apollo.task.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.apollo.vo.StarredTaskDTO;
import com.apollo.vo.TaskDTO;

public interface TaskDAO {

	public ArrayList<TaskDTO> getTasks(String pid);
	public ArrayList<TaskDTO> getAssignedTasks(String pid);
	public ArrayList<TaskDTO> getNotAssignedTasks(String pid);
	public ArrayList<TaskDTO> getTasksInSteps(String sid);
	public ArrayList<TaskDTO> getTasksByStepId(int sid);	
	public int updateTask(TaskDTO taskdto);
	public TaskDTO getTask(String tid);
	public int addStar(StarredTaskDTO dto);
	public int deleteStar(StarredTaskDTO dto);
	public int deleteTask(int tid);
	public ArrayList<TaskDTO> selectTasksByMidAndSid(HashMap<String, Object> map);
}
