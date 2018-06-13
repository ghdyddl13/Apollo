package com.apollo.task.dao;
import java.util.ArrayList;

import com.apollo.vo.TaskDTO;

public interface TaskDAO {

	public ArrayList<TaskDTO> getTasks(String pid);
	public ArrayList<TaskDTO> getTasksInSteps(String sid);
	public ArrayList<TaskDTO> getTasksByStepId(String sid);	
	
}
