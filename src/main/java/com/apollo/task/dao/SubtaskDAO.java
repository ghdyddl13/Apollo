package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.SubtaskDTO;

public interface SubtaskDAO {

	public int addSubTask(SubtaskDTO dto);
	public ArrayList<SubtaskDTO> getSubTasks(int tid);
	public int changeSubtask(SubtaskDTO dto);
	public int deleteSubtask(int subtaskid);

}
