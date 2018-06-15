/**
 프로젝트 : Apollo
 파일이름 : MyWorkDAO.java 
 날      짜 : 2018. 6. 12.
 작 성  자 : 이 진 우
*/

package com.apollo.mywork.dao;

import java.util.ArrayList;

import com.apollo.vo.TaskDTO;

public interface MyWorkDAO {
	
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : Mywork 첫 페이지에 데이터를 가지고옴
	 작성자명 : 이 진 우
	 */
	public ArrayList<TaskDTO> getMyWorkList(String mid);
}
