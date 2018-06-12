package kr.or.apollo.dao;

import kr.or.apollo.vo.ProjectDTO;

public interface ProjectDAO {

	//프로젝트 생성
	public int insertProject(ProjectDTO proejctdto);
	
	//프로젝트 생성
	public int updateProject(ProjectDTO proejctdto);
}
