package com.apollo.stream.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.inbox.service.InboxService;
import com.apollo.stream.service.StreamService;
import com.apollo.vo.CommentDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.TaskInStepDTO;

@Controller
public class StreamController {

	@Autowired
	private StreamService service;
	
	
	
	@RequestMapping("/stream.htm")
	public String stream(HttpSession session,Model model) {
		System.out.println("stream controller");
		session.setAttribute("location", "/stream.htm");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		int pid = 0;
		Map map = new HashMap();
		Map map2 = new HashMap();
		int newtid = 0;
		ArrayList<TaskInStepDTO> sidlist = null;
		System.out.println("pmember 가져올거야");
		ArrayList<ProjectDTO> pidlist = service.getPidlist(mid);//테스트용 아이디
		System.out.println("pmember 가져옴~~");
		if(!pidlist.isEmpty()) {
			pid = pidlist.get(0).getPid();
			
			ArrayList<Integer> tidlist = service.getNewtid(pid);
			
			if(tidlist.size()>0) {
				map2.put("tidlist", tidlist);
				sidlist = service.selectSidlist(map2);
				newtid= tidlist.get(0);
			}
			map.put("newtid", newtid);
			map.put("pid", pid);
		}else {
			map.put("newtid", 0);
			map.put("pid", 0);
		}
		System.out.println("pid 리스트 있으면 가져왔다.");
		
		ArrayList<CommentDTO> streamlist = service.getStreamlist(map);//테스트용 아이디
		
		LinkedHashMap<Integer, ArrayList<CommentDTO>> commentmap = new LinkedHashMap<Integer, ArrayList<CommentDTO>>();
		
		for(CommentDTO dto : streamlist) {
			if(commentmap.containsKey(dto.getTid())) {
				commentmap.get(dto.getTid()).add(dto);
			}else {
				commentmap.put(dto.getTid(), new ArrayList<CommentDTO>());
				commentmap.get(dto.getTid()).add(dto);
			}
		}
		
		
		System.out.println("pid 리스트 있으면 가져왔다.");
		
		model.addAttribute("sidlist", sidlist);
		model.addAttribute("commentmap", commentmap);
		model.addAttribute("pidlist", pidlist);
		
		return "header/stream";
	}
	
	@RequestMapping("/selectpidstream.htm")
	public String selectstream(int pid,HttpSession session, Model model) {
		System.out.println("selectstream controller");
		String mid = (String)session.getAttribute("mid");
		int newtid =0;
		Map map = new HashMap();
		Map map2 = new HashMap();
		ArrayList<TaskInStepDTO> sidlist = null;
		
		ArrayList<Integer> tidlist = service.getNewtid(pid);

		if(tidlist.size()>0) {
			map2.put("tidlist", tidlist);
			sidlist = service.selectSidlist(map2);
			newtid= tidlist.get(0);
		}
		map.put("newtid", newtid);
		map.put("pid", pid);
		
		ArrayList<ProjectDTO> pidlist = service.getPidlist(mid);//테스트용 아이디
		ArrayList<CommentDTO> streamlist = service.getStreamlist(map);//테스트용 아이디
		
		
		LinkedHashMap<Integer, ArrayList<CommentDTO>> commentmap = new LinkedHashMap<Integer, ArrayList<CommentDTO>>();
		
		for(CommentDTO dto : streamlist) {
			System.out.println(dto.toString());
			if(commentmap.containsKey(dto.getTid())) {
				commentmap.get(dto.getTid()).add(dto);
			}else {
				commentmap.put(dto.getTid(), new ArrayList<CommentDTO>());
				commentmap.get(dto.getTid()).add(dto);
			}
		}
		
		model.addAttribute("sidlist", sidlist);
	//	model.addAttribute("streamlist", streamlist);
		model.addAttribute("commentmap", commentmap);
		model.addAttribute("pidlist", pidlist);
		
		return "header/stream";
	}
	
}
