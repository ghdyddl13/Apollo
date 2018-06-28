/**
 프로젝트 : Apollo
 파일이름 : StreamDAO.java 
 날      짜 : 2018. 6. 12.
 작 성  자 : 이 진 우
*/

package com.apollo.stream.dao;

import java.util.ArrayList;
import java.util.Map;

import com.apollo.vo.CommentDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.TaskInStepDTO;

public interface StreamDAO {
	
	public ArrayList<Integer> getNewtid(int pid);
	
	public ArrayList<ProjectDTO> getPidlist(String mid);
	
	public ArrayList<CommentDTO> getStreamlist(Map map);
	
	public ArrayList<TaskInStepDTO> selectSidlist(Map map2);

}
