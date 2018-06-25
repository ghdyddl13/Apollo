package com.apollo.task.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.apollo.vo.CommentDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.StarredTaskDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TaskInStepDTO;
import com.apollo.vo.TidpidDTO;
import com.apollo.vo.TidvalueDTO;


public interface TaskDAO {

	public ArrayList<TaskDTO> getTasks(String pid);
	public ArrayList<TaskDTO> getAssignedTasks(String pid);
	public ArrayList<TaskDTO> getNotAssignedTasks(String pid);
	public ArrayList<TaskDTO> getTasksInSteps(String sid);
	public ArrayList<TaskDTO> getTasksByStepId(int sid);	
	public int updateTask(TaskDTO taskdto);
	public int insertBoardTask(TaskDTO taskdto);
	public int insertBoardTaskInStep(TaskInStepDTO dto);
	public TaskDTO getTask(int tid);

	public int deleteTask(int tid);

	public int deleteStepInTaskModal(TaskInStepDTO dto);
	public int countTaskInStep(int tid);
	public int changeTstatus(TidvalueDTO dto);
	public int addTaskInStepInTaskModal(TaskInStepDTO taskinstepdto);
	public ArrayList<TaskDTO> selectTasksByMidAndSid(HashMap<String, Object> map);

}
