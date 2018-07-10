package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.TstatusDTO;

public interface TstatusDAO {
	
	public ArrayList<TstatusDTO> getTstatuslist(int tid);

	
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : pid로 task 상태 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<TstatusDTO> getTstatuslistPid(int pid);
}
