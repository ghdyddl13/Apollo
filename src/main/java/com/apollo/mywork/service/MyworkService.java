package com.apollo.mywork.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apollo.mywork.dao.MyWorkDAO;
import com.apollo.vo.MyWorkStepDTO;
import com.apollo.vo.MyWorkTaskDTO;
import com.apollo.vo.TaskDTO;


/**
 * 
  클래스명 : MyworkService
  날      짜 : 2018. 6. 15.
  작성자명 : 이 진 우
 */
@Service
public class MyworkService {
	@Autowired
	private SqlSession sqlsession;
	
	public ArrayList<TaskDTO> getMyWork(String mid){
		MyWorkDAO dao = sqlsession.getMapper(MyWorkDAO.class);
		List<MyWorkTaskDTO> tasklist = dao.getMyWorkList(mid);
		List<MyWorkStepDTO> steplist = dao.getMyWorkStep(mid);
		for(MyWorkTaskDTO task:tasklist) {
			for(MyWorkStepDTO step :steplist) {
				if(task.getTid() == step.getTid()) {
					if(task.getSteps()==null) {
						task.setSteps(new ArrayList<MyWorkStepDTO>());
					}
					task.getSteps().add(step);
				}	
			}
		}
		
		return null;
	}
}
