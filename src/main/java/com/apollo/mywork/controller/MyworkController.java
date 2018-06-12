package com.apollo.mywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
  클래스명 : MyworkController
  날      짜 : 2018. 6. 12.
  작성자명 : 이 진 우
 */
@Controller
public class MyworkController {
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : MY WORK 페이지 호출
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/myWork.htm",method=RequestMethod.GET)
	public String showMywork(String s1, Model model) {
		System.out.println("마이워크");
		return "header/myWork";
	}
}
