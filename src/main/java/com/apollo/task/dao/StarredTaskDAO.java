package com.apollo.task.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.apollo.vo.MyWorkMemberDTO;
import com.apollo.vo.MyWorkStepDTO;
import com.apollo.vo.MyWorkTaskDTO;
import com.apollo.vo.StarredTaskDTO;

public interface StarredTaskDAO {
	
	public ArrayList<StarredTaskDTO> getStarredTaskList(String mid);
	
	public int addStar(StarredTaskDTO dto);
	
	public int deleteStar(StarredTaskDTO dto);
	/**
	 * 
	 날      짜 : 2018. 6. 29.
	 기      능 : Starred  Task 페이지에서 Task를 뿌려주기 위한 함수 
	 작성자명 : 이 진 우
	 */
	public ArrayList<MyWorkTaskDTO> getStarredTasks(String mid);
	/**
	 * 
	 날      짜 : 2018. 6. 29.
	 기      능 : Starred Task 페이지에 뿌려진 Task에 뿌려주는 스텝
	 작성자명 : 이 진 우
	 */
	public ArrayList<MyWorkStepDTO> getStarredSteps(String mid);
	/**
	 * 
	 날      짜 : 2018. 6. 29.
	 기      능 : Starred Task 페이지에 뿌려진 Task에 뿌려주는 멤버들
	 작성자명 : 이 진 우
	 */
	public ArrayList<MyWorkMemberDTO> getStarredMembers(String mid);
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : Starred Task 페에지에 뿌려진 Task에 별을 클릭할시 즐겨찾기가 삭제되는 함수
	 작성자명 : 이 진 우
	 */
	public int deleteStarredTask(StarredTaskDTO dto);
}
