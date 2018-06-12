package com.apollo.step.dao;

import java.util.List;

import com.apollo.task.dao.TaskDAO;

/**
 * 
  클래스명 : StepDAO
  날      짜 : 2018. 6. 12.
  작성자명 : 박 민 식
 */
public interface StepDAO {
	
	public List<TaskDAO> getTimeLineTasks(String stepid);

}
