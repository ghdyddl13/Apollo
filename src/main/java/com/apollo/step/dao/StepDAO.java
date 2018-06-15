package com.apollo.step.dao;

import java.util.ArrayList;

import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;

public interface StepDAO {
	/*
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 생성
	 작성자명 : 
	 */
	public ArrayList<StepDTO> getSteps(String pid);
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 :스텝아이디로 프로젝트 아이디 가져오기 >> session관리를 위해 
	 작성자명 : 박 민 식
	 */
	public int getProjectIdByStepId(int sid);

}
