package com.apollo.task.dao;
public interface TaskDAO {

<<<<<<< HEAD
=======
	public ArrayList<TaskDTO> getTasks(String pid);
	public ArrayList<TaskDTO> getAssignedTasks(String pid);
	public ArrayList<TaskDTO> getNotAssignedTasks(String pid);
	public ArrayList<TaskDTO> getTasksInSteps(String sid);
	public ArrayList<TaskDTO> getTasksByStepId(String sid);	
	
>>>>>>> 06ffad74364a14508020d3850774fb6d9ca45b03
}
