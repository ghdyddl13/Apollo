package com.apollo.project.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.project.dao.FileDAO;
import com.apollo.utils.S3Util;
import com.apollo.vo.FileDTO;

@Service
public class ProjectFilesService {
	
	@Autowired
	private SqlSession sqlsession;
	
	//S3 util 이용
	S3Util s3 = new S3Util();
	String bucketName = "projectapollo";
	
	
	public ArrayList<FileDTO> selectFileListByProjectId(int pid){
		ArrayList<FileDTO> result = null;
		System.out.println("파일 서비스 들어왔다." + pid);
		try {
			FileDAO dao = sqlsession.getMapper(FileDAO.class);
			result = dao.selectFileListByProjectId(pid);
			System.out.println("result : " + result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int filesDeleteByFileId(String filename) {
		int result = 0;
		FileDAO dao = sqlsession.getMapper(FileDAO.class);
		String filepath = "resources/upload_files/" + filename;
		
		s3.fileDelete(bucketName, filepath);
		try {
			result = dao.filesDeleteByFileId(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
