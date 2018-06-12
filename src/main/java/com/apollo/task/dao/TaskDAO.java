package com.apollo.task.dao;
import java.util.ArrayList;

import com.apollo.vo.TaskDTO;

public interface TaskDAO {

	public ArrayList<TaskDTO> getTasks(String pid);
	public ArrayList<TaskDTO> getTasksInSteps(String sid);
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : Step아이디로 Task배열 한번에 가져오기 
	 작성자명 : 박 민 식
	 */
	public ArrayList<TaskDTO> getTasksByStepId(String sid);	
	
}
