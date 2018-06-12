package kr.or.apollo.dao;

import java.util.ArrayList;

import kr.or.apollo.vo.TaskDTO;

public interface TaskDAO {

	public ArrayList<TaskDTO> getTasks(String pid);

}
