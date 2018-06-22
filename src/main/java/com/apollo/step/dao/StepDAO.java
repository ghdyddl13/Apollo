package com.apollo.step.dao;
import java.util.ArrayList;
import java.util.List;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;

public interface StepDAO {
	
	/*
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 생성
	 작성자명 : 김 정 권
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
	 기      능 : 참여중인 모든 프로젝트에 속한 스텝 리스트 가져오기
	 작성자명 : 박 민 식
	 */
	public ArrayList<StepDTO> selectStepList(List<Integer> pids);

	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : tid 이용해서 sid 목록 가져오기
	 작성자명 : 김 정 권
	 */
	public ArrayList<StepDTO> getStepsid(String tid);
	
	/**
	 *
	 날      짜 : 2018. 6. 21.
	 기      능 : sid 로 step 정보 가져오기
	 작성자명 : 김 래 영
	 */
	public StepDTO selectStep(int sid);
	 
	/* 
	 날      짜 : 2018. 6. 21.
	 기      능 : 스텝 위치 이동
	 작성자명 : 박 민 식
	 */
	public int moveStep(StepDTO stepdto);
	
	public ArrayList<StepDTO> getStepsid(int tid);

	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : step 수정
	 작성자명 : 김 래 영
	 */
	public int updateStep(StepDTO stepdto);
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : sid 에 속한 task 삭제 (선행)
	 작성자명 : 김 래 영
	 */
	public int deleteTaskInStep(int sid);
	
	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : sid 로 step 삭제 (후행)
	 작성자명 : 김 래 영
	 */
	public int deleteStep(int sid);
}
