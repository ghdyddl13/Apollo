package com.apollo.project.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.member.dao.MemberDAO;
import com.apollo.sidebar.dao.FolderDAO;
import com.apollo.step.dao.StepDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.task.dao.TstatusDAO;
import com.apollo.vo.FolderDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;

/**
 * 
  클래스명 : ProjectTableService
  날      짜 : 2018. 6. 22.
  작성자명 : 김 래 영
 */
@Service
public class ProjectTableService {
	
	@Autowired
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : pid 에 속한 step 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<StepDTO> getStepInProject(int pid) {
		ArrayList<StepDTO> steplist = null;
		try {
			StepDAO stepdao = sqlsession.getMapper(StepDAO.class);
			steplist = stepdao.getStepInProject(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return steplist;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : pid 에 속한 folder 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<FolderDTO> selectFolderList(List<Integer> pid){
		ArrayList<FolderDTO> folderlist = null;
		try {
			FolderDAO folderdao = sqlsession.getMapper(FolderDAO.class);
			folderlist = folderdao.selectFolderList(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folderlist;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 22.
	 기      능 : sid 에 속한 task 및 tstatus 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<TaskDTO> getTasksInStep(ArrayList<StepDTO> steplist) {
		ArrayList<TaskDTO> tasklist = null;
		try {
			TaskDAO taskdao = sqlsession.getMapper(TaskDAO.class);
			tasklist = taskdao.getTasksAndTstatusInStep(steplist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasklist;
		
	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : pid로 task 상태 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<TstatusDTO> getTstatuslistPid(int pid) {
		ArrayList<TstatusDTO> tstatuslist = null;
		try {
			TstatusDAO tstatusdao = sqlsession.getMapper(TstatusDAO.class);
			tstatuslist = tstatusdao.getTstatuslistPid(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tstatuslist;
		
	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : steplist로 memberlist 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<MemberDTO> selectStepAssignees (ArrayList<StepDTO> steplist) {
		ArrayList<MemberDTO> memberlist = null;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			memberlist = dao.selectStepAssignees(steplist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberlist;
		
	}
}

