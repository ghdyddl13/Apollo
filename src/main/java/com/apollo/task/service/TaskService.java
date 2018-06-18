package com.apollo.task.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.TaskDTO;

@Service
public class TaskService {

	@Autowired
	private SqlSession sqlsession;
	
    

}