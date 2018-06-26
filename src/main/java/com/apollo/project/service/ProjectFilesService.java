package com.apollo.project.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.project.dao.FileDAO;
import com.apollo.vo.FileDTO;

@Service
public class ProjectFilesService {
	
	@Autowired
	private SqlSession sqlsession;
	
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
}
