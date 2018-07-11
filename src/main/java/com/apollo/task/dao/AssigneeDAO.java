package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.MidtidDTO;

public interface AssigneeDAO {
	
	public int addAssigneeInTaskModal(MidtidDTO midtiddto);
	
	public ArrayList<String> getMidinAssingnee(int tid);
		
}
