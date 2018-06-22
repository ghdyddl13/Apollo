package com.apollo.task.dao;

import java.util.ArrayList;

import com.apollo.vo.TstatusDTO;

public interface TstatusDAO {
	
	public ArrayList<TstatusDTO> getTstatuslist(int pid);

}
