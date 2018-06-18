package com.apollo.inbox.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apollo.inbox.service.InboxService;
import com.apollo.vo.CommentDTO;

@Controller
public class InboxController {

	@Autowired
	private InboxService service;
	
	@RequestMapping("/inbox.htm")
	public String inbox(HttpSession session,Model model) {
		System.out.println("inbox controller");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		ArrayList<CommentDTO> commentlist = service.getCommentlist("testid1");//테스트용 아이디
		model.addAttribute("cmtlist", commentlist);
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd", Locale.KOREA);
		java.util.Date currenttime = new java.util.Date();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(currenttime);
	    String today = formatter.format (cal.getTime());
	    
	    System.out.println (today);
		model.addAttribute("today", today);
		model.addAttribute("inbox", "incomming");
		return "header/inbox";
	}
	
/*	@RequestMapping("/incoming.htm")
	public String showIncoming(HttpSession session, Model model) 	{
		
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		return null;
	}*/
	@RequestMapping("/sent.htm")
	public String showSent(HttpSession session, Model model) {
		System.out.println("sent controller");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		ArrayList<CommentDTO> sentlist = service.getSentlist("testid1");//테스트용 아이디
		model.addAttribute("cmtlist", sentlist);
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd", Locale.KOREA);
		java.util.Date currenttime = new java.util.Date();
		 
		Calendar cal = Calendar.getInstance();
	    cal.setTime(currenttime);
	    String today = formatter.format (cal.getTime());
	    
	    System.out.println (today);
		model.addAttribute("today", today);
		model.addAttribute("inbox", "sent");
		return "header/inbox";
	}
	@RequestMapping("/archive.htm")
	public String showArchive(HttpSession session, Model model){
		System.out.println("archive controller");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		ArrayList<CommentDTO> archivelist = service.getArchivelist("testid1");//테스트용 아이디
		model.addAttribute("cmtlist", archivelist);
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd", Locale.KOREA);
		java.util.Date currenttime = new java.util.Date();
		 
		Calendar cal = Calendar.getInstance();
	    cal.setTime(currenttime);
	    String today = formatter.format (cal.getTime());
	    
	    System.out.println (today);
		model.addAttribute("today", today);
		model.addAttribute("inbox", "archive");
		return "header/inbox";
	}
	
	@RequestMapping("/archiveupdate.htm")
	public String updateArchive(int cmtid,String inboxkind,HttpSession session) {
		String result ="";
		System.out.println("updatearchive controller");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		System.out.println("cmtid : " + cmtid);
		System.out.println(inboxkind);
		CommentDTO comment = new CommentDTO();
		comment.setCmtid(cmtid);
		comment.setMid("testid1");//테스트용 아이디
		
		
		
		service.updateArchive(comment);
		
		if(inboxkind.equals("incomming")) {
			result = "redirect:/inbox.htm";
		}else if(inboxkind.equals("sent")) {
			result = "redirect:/sent.htm";
		}else {
			result = "redirect:/archive.htm";
		}
		
		
		return result;
	}
	
	@RequestMapping("/archiveupdate2.htm")
	public String updateArchive2(int cmtid,HttpSession session) {
		System.out.println("updatearchive controller");
		String mid = (String)session.getAttribute("mid");
		System.out.println(mid);
		System.out.println("cmtid : " + cmtid);
		CommentDTO comment = new CommentDTO();
		comment.setCmtid(cmtid);
		comment.setMid("testid1");//테스트용 아이디
		service.updateArchive2(comment);
		
		return "redirect:/archive.htm";
	}
	
}
