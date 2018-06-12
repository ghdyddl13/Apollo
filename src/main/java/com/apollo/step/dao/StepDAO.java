package com.apollo.step.dao;

import java.util.ArrayList;

import com.apollo.vo.StepDTO;

public interface StepDAO {
	
	public ArrayList<StepDTO> getSteps(String pid);

}
