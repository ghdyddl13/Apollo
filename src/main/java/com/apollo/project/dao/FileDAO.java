package com.apollo.project.dao;

import java.util.ArrayList;

import com.apollo.vo.FileDTO;

/**
 * 
  클래스명 : FileDAO
  날      짜 : 2018. 6. 22.
  작성자명 : 이 창 훈
 */
public interface FileDAO {	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : 프로젝트에 소속된 task에 upload된 file 목록 가져오기
	 작성자명 : 이 창 훈
	 */
	public ArrayList<FileDTO> selectFileListByProjectId(int pid);
	public int filesDeleteByFileId(int fileid);
	
}
