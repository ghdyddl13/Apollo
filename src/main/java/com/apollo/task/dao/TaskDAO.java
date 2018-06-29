package com.apollo.task.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.apollo.vo.MidtidDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.SubtaskDTO;
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
	public int insertBoardTask(TaskDTO taskdto);
	public int insertBoardTaskInStep(TaskInStepDTO dto);
	public TaskDTO getTask(int tid);
	public int deleteTask(int tid);

	public int deleteStepInTaskModal(TaskInStepDTO dto);
	public int countTaskInStep(int tid);

	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : sid 로 스텝에 속한 task 가져오기  (tstatus 도 가져옴)
	 작성자명 : 김 래 영
	 */
	public ArrayList<TaskDTO> getTasksAndTstatusInStep(ArrayList<StepDTO> steplist);

	public int changeTstatus(TidvalueDTO dto);
	public int addTaskInStepInTaskModal(TaskInStepDTO taskinstepdto);
	public ArrayList<TaskDTO> selectTasksByMidAndSid(HashMap<String, Object> map);
	public ArrayList<TaskDTO> getRecentTasks(String mid);
	public ArrayList<TaskDTO> getSearchTasks(HashMap<String, String> map);
	public int deleteAssignee(MidtidDTO dto);
	public ArrayList<TaskDTO> selectNotAssignedTasksBySid(int sid);

	public int changeSdayOfTask(TaskDTO dto);
	public int changeEdayOfTask(TaskDTO dto);
	public String getTname(int tid);
	public int changeTname(TaskDTO dto);
	
}
