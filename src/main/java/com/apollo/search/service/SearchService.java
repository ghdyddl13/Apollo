package com.apollo.search.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.member.dao.MemberDAO;
import com.apollo.project.dao.ProjectDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;

@Service
public class SearchService {
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 :  최근 3일 이내에 생성 혹은 수정된 테스크 10건 추출
	 작성자명 : 박 민 식
	 */
	public ArrayList<TaskDTO> getRecentTasks(String mid){
		ArrayList<TaskDTO> tasks = null;
		
		try {
			TaskDAO dao = sqlSession.getMapper(TaskDAO.class);
			tasks = dao.getRecentTasks(mid);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return tasks;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 검색된 테스크의 정보
	 작성자명 : 박 민 식
	 */
	public ArrayList<TaskDTO> getSearchTasks(HashMap<String, String> map){
		ArrayList<TaskDTO> result = null;
		try {
			TaskDAO dao = sqlSession.getMapper(TaskDAO.class);
			result =dao.getSearchTasks(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 검색된 프로젝트 정보
	 작성자명 : 박 민 식
	 */
	public ArrayList<ProjectDTO> getSearchProjects(HashMap<String, String> map){
		ArrayList<ProjectDTO> result = null;
		
		try {
			ProjectDAO dao = sqlSession.getMapper(ProjectDAO.class);
			result= dao.getSearchProjects(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 검색된 스텝 정보
	 작성자명 : 박 민 식
	 */
	public ArrayList<StepDTO> getSearchSteps(HashMap<String, String> map){
		ArrayList<StepDTO> result = null;
		
		try {
			StepDAO dao = sqlSession.getMapper(StepDAO.class);
			result = dao.getSearchSteps(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 검색된 멤버의 정보
	 작성자명 : 박 민 식
	 */
	public ArrayList<MemberDTO> getSearchMembers(HashMap<String, String> map){
		ArrayList<MemberDTO> result = null;
		try {
			MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
			result = dao.getSearchMembers(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}


/**
 프로젝트 : Apollo
 파일이름 : SearchController.java 
 날      짜 : 2018. 6. 25.
 작 성  자 : 
*/