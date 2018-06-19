package com.apollo.sidebar.dao;

import java.util.ArrayList;
import java.util.List;

import com.apollo.vo.FolderDTO;
import com.apollo.vo.StepDTO;

public interface FolderDAO {
	/**
	 * 
	 날      짜 : 2018. 6. 19.
	 기      능 : 참여중인 프로젝트에 속한 폴더들의 리스트 가져오기
	 작성자명 : 박 민 식
	 */
	public ArrayList<FolderDTO> selectFolderList(List<Integer> pids);
	
	

}
