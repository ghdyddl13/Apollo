package com.apollo.report.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.apollo.report.service.ReportService;
import com.apollo.vo.ProjectDTO;

@Controller
public class ReportController {

	
	@Autowired 
	private ReportService reportservice;
	
	@Autowired
	private View jsonview;
	
	
	@RequestMapping("/report.htm")
	public String report(HttpSession session) {
		
		session.setAttribute("location", "/report.htm");
		
		return "header/report";
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 1.
	 기      능 : 해당 유저가 속한 프로젝트들을 가져와서 모달 내 셀렉트 바의 옵션들을 채워 준다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/gerusersprojects.htm")
	public View gerUserProjects(HttpSession session, Model map) {
		
		System.out.println("gerUserProjects 컨트롤러 실행");
		
		String mid = (String) session.getAttribute("mid");
		ArrayList<ProjectDTO> list = new ArrayList();
		list = reportservice.gerUserProjects(mid);
		map.addAttribute("projectlist", list);
		
		System.out.println("유저가 속한 프로젝트들 모두 가져옴(성공)");
		
		return jsonview;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 1.
	 기      능 : 다운로드 버튼을 누르면 작동하는 함수로 최종적으로 사용자의 선택에 맞는 Report를 만들어서 엑셀로 추출한다
	 작성자명 : 김 정 권
	 */
	@RequestMapping("/downloadreport.htm")
	public void downloadReport(int pid, String report_kind, String report_title, HttpSession session, Model map, HttpServletResponse response) {
		
		System.out.println("downloadReport 컨트롤러 실행");
		
		System.out.println(pid + "/" + report_kind + "/" + report_title);
		
		String mid = (String) session.getAttribute("mid");
		
		try {
			reportservice.writeData(pid, report_kind, report_title, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("report 추출 성공!");
	}
	
}
