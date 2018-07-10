package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.FileDTO;
import com.apollo.vo.filedataDTO;

public interface FileDAO {

	public int upLoadFileInTaskModal(FileDTO dto);
	
	public ArrayList<FileDTO> getFileList(int tid);
	
	public int deleteFile(String filename);
}
