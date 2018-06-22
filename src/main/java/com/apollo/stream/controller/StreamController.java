package com.apollo.stream.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.inbox.service.InboxService;
import com.apollo.stream.service.StreamService;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.ProjectDTO;

@Controller
public class StreamController {

	@Autowired
	private StreamService service;
	
	
	
	@RequestMapping("/stream.htm")
	public String stream(HttpSession session,Model model) {
		System.out.println("stream controller");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		int pid = 0;
		
		ArrayList<ProjectDTO> pidlist = service.getPidlist("testid1");//테스트용 아이디
		if(!pidlist.isEmpty()) {
			pid = pidlist.get(0).getPid();
		}
		System.out.println("pid 리스트는 가져왔다.");
		
		ArrayList<CommentDTO> streamlist = service.getStreamlist(pid);//테스트용 아이디
		
		model.addAttribute("streamlist", streamlist);
		model.addAttribute("pidlist", pidlist);
		
		return "header/stream";
	}
	
	@RequestMapping("/selectstream.htm")
	public String selectstream(int pid,HttpSession session, Model model) {
		System.out.println("selectstream controller");
		
		ArrayList<ProjectDTO> pidlist = service.getPidlist("testid1");//테스트용 아이디
		ArrayList<CommentDTO> streamlist = service.getStreamlist(pid);//테스트용 아이디
		
		
		
		model.addAttribute("streamlist", streamlist);
		model.addAttribute("pidlist", pidlist);
		
		return "header/stream";
	}
	
}
