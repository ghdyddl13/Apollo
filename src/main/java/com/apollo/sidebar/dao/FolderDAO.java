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
	
	/*
	 날      짜 : 2018. 6. 19.
	 기      능 : 폴더 생성
	 작성자명 : 김 래 영
	 */
	public int insertfolder(FolderDTO folderdto);	
	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : fid 로 폴더 정보 가져오기
	 작성자명 : 김 래 영
	 */
	public FolderDTO selectFolder(String fid);
	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 폴더명 수정 
	 작성자명 : 김 래 영
	 */
	public int updateFolder(FolderDTO folderdto);
	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 폴더 삭제 (영구삭제)
	 작성자명 : 김 래 영
	 */
	public int deleteFolder(String fid);
}
