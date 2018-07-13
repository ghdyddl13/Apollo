package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.MidpidDTO;
import com.apollo.vo.MidtidDTO;

public interface AssigneeDAO {
	
	public int addAssigneeInTaskModal(MidtidDTO midtiddto);
	
	public ArrayList<String> getMidinAssingnee(int tid);
	
	public int deleteAssigneeAfterDeletePmember(MidpidDTO midpid);
		
}
