package com.apollo.step.dao;

import java.util.ArrayList;
import com.apollo.vo.StepDTO;

public interface StepDAO {
	
	public ArrayList<StepDTO> getSteps(String pid);
	/*
	 날      짜 : 2018. 6. 14.
	 기      능 : pid, fid 받아와서 Step 생성
	 작성자명 : 김 래 영
	 */
	public int insertStep(StepDTO stepdto);



}
