package com.apollo.mywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyworkController {
	@RequestMapping(value="/myWork.htm",method=RequestMethod.GET)
	public String showMywork(String s1, Model model) {
		System.out.println("마이워크");
		return "header/myWork";
	}
}
