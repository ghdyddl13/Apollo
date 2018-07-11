/**
 프로젝트 : Apollo
 파일이름 : MyWorkDAO.java 
 날      짜 : 2018. 6. 12.
 작 성  자 : 이 진 우
*/

package com.apollo.mywork.dao;

import java.util.List;

import com.apollo.vo.MyWorkMemberDTO;
import com.apollo.vo.MyWorkStepDTO;
import com.apollo.vo.MyWorkTaskDTO;

public interface MyWorkDAO {
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : MyWork에서 mid를 이용해 Task 가지고오는 함수
	 작성자명 : 이 진 우
	 */
	public List<MyWorkTaskDTO> getMyWorkList(String mid);
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : Mywork에서 mid를 이용해 특정 사용자가 가지고이 있는 Task가 속한 Step 목록을 가지고온다 
	 작성자명 : 이 진 우
	 */
	public List<MyWorkStepDTO> getMyWorkStep(String mid);
	
	public List<MyWorkMemberDTO> getMyWorkMember(String mid);
}
