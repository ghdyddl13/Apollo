package com.apollo.mywork.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
/**
 * 
  클래스명 : MyworkController
  날      짜 : 2018. 6. 12.
  작성자명 : 이 진 우
 */

import com.apollo.mywork.service.MyworkService;
import com.apollo.vo.TaskDTO;
@Controller
public class MyworkController {
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : MY WORK 페이지 호출
	 작성자명 : 이 진 우
	 */
	@Autowired
	private View jsonview;
	
	@Autowired
	private MyworkService service;
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : Mywork 첫 페이지 로드
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/myWork.htm",method=RequestMethod.GET)
	public String showMyworkPage(HttpServletRequest request,ModelMap map) {
		String mid = (String)request.getSession().getAttribute("mid");
		System.out.println("아이디 : " +mid);
		String id = "testid4";
		ArrayList<TaskDTO> myworklist = service.getMyWork(id);

		
		return "header/myWork";
	}
	/**
	 * 
	 날      짜 : 2018. 6. 15.
	 기      능 : Mywork 페이지 리스트 뿌려주기
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/myWork.htm",method=RequestMethod.POST)
	public View myWorkList(HttpServletRequest request, ModelMap map) {

		
		return jsonview;
	}
}
