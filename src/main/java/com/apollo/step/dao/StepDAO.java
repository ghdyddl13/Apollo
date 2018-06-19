package com.apollo.step.dao;

import java.util.ArrayList;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TstatusDTO;


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
	/*
	 날      짜 : 2018. 6. 14.
	 기      능 : pid, fid 받아와서 Step 생성
	 작성자명 : 김 래 영
	 */
	public int insertStep(StepDTO stepdto);
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : sid 받아서 해당 step에 들어있는 task 목록을 board에 가져옴
	 작성자명 : 이 창 훈
	 */
	public ArrayList<TstatusDTO> selectTstatusBySid(int sid);

	
	
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : tid 이용해서 sid 목록 가져오기
	 작성자명 : 김 정 권
	 */
	public ArrayList<StepDTO> getStepsid(String tid);

}
