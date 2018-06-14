package com.apollo.step.dao;

import java.util.ArrayList;

import com.apollo.vo.StepDTO;

public interface StepDAO {
	
	public ArrayList<StepDTO> getSteps(String pid);
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : Step클릭시 Session으로 관리하는 Project의 Id를 갱신해주기 위함.
	 작성자명 : 박 민 식
	 */
	public int getProjectIdByStepId(int sid);
}
