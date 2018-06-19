package com.apollo.project.dao;

import java.util.ArrayList;

import com.apollo.vo.ProjectDTO;
/**
 * 
  클래스명 : ProjectDAO
  날      짜 : 2018. 6. 14.
  작성자명 : 김 래 영
 */
public interface ProjectDAO {
	/*
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 생성
	 작성자명 : 김 래 영
	 */
	public int insertProject(ProjectDTO projectdto);
	/*
	 날      짜 : 2018. 6. 18.
	 기      능 : pid 가져와서 project 상세정보 보여주기
	 작성자명 : 김 래 영
	 */
	public ArrayList<ProjectDTO> getProject(String pid);
	
	/*
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 수정
	 작성자명 : 김 래 영
	 */
	public int updateProject(ProjectDTO projectdto);
	
	/*
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 삭제
	 작성자명 : 김 래 영
	 */
	public int deleteProject(ProjectDTO projectdto);
	
}
