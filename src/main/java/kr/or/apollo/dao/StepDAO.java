package kr.or.apollo.dao;

import java.util.ArrayList;

import kr.or.apollo.vo.StepDTO;
import kr.or.apollo.vo.TaskDTO;

public interface StepDAO {

	public ArrayList<StepDTO> getSteps(String pid);
	
}
