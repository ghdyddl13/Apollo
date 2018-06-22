package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.CommentDTO;
import com.apollo.vo.StarredTaskDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TaskInStepDTO;
import com.apollo.vo.TidvalueDTO;

public interface TaskDAO {

	public ArrayList<TaskDTO> getTasks(String pid);
	public ArrayList<TaskDTO> getAssignedTasks(String pid);
	public ArrayList<TaskDTO> getNotAssignedTasks(String pid);
	public ArrayList<TaskDTO> getTasksInSteps(String sid);
	public ArrayList<TaskDTO> getTasksByStepId(int sid);	
	public int updateTask(TaskDTO taskdto);
	public TaskDTO getTask(int tid);
	public int addStar(StarredTaskDTO dto);
	public int deleteStar(StarredTaskDTO dto);
	public int deleteTask(int tid);
	public int deleteStepInTaskModal(TaskInStepDTO dto);
	public int countTaskInStep(int tid);
	public int changeTstatus(TidvalueDTO dto);
	public int insertComment(CommentDTO commentdto);
	public String getTaskModifierName(String mid);
	public ArrayList<StepDTO> getStepListByTid(int tid);
	
}
