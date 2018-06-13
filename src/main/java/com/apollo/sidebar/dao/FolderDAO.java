package com.apollo.sidebar.dao;

import com.apollo.vo.ProjectDTO;
/**
 * 
  클래스명 : FolderDAO
  날      짜 : 2018. 6. 12.
  작성자명 : 김 래 영
 */
public interface FolderDAO {
	/*
	 날      짜 : 2018. 6. 12.
	 기      능 : 프로젝트 생성
	 작성자명 : 김 래 영
	 */
	public int insertProject(ProjectDTO projectdto);
	
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
