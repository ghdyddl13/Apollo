package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.FileDTO;
import com.apollo.vo.filedataDTO;

public interface TaskFileDAO {

	public int upLoadFileInTaskModal(FileDTO dto);
	
	public ArrayList<FileDTO> getFileList(int tid);
	
	public int deleteFile(String filename);
	/**
	 * 
	 날      짜 : 2018. 7. 10.
	 기      능 : TID를 이용해 PID를 구한다
	 작성자명 : 이 진 우
	 */
	public int findPidbyTid(int tid);
}
