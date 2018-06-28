package com.apollo.step.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apollo.step.service.StepBoardService;
import com.apollo.step.service.StepListService;
import com.apollo.vo.StepDTO;
import com.apollo.vo.StepListTaskDTO;
import com.apollo.vo.TaskDTO;
import com.apollo.vo.TstatusDTO;
@Controller
public class StepListController {

	@Autowired
	private StepListService service;
	@Autowired
	private StepBoardService boardservice;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 리스트페이지 비동기 화면전환 
	 작성자명 : 박 민 식, 이 진 우
	 */
    @RequestMapping(value="/list.htm",method=RequestMethod.GET)
    public String list(int sid, HttpServletRequest request, ModelMap map) {
       
    	System.out.println(sid);
    	
      // 스텝 id 와 프로젝트 id 세션값 갱신
        int pid = service.getProjectIdByStepId(sid);
        request.getSession().setAttribute("sid", sid);
        request.getSession().setAttribute("pid", pid);
        //스텝이 소속된 프로젝트의 방법론 정보
        ArrayList<TstatusDTO> tstatuslist = service.getListTstatusList(sid);
        //스텝 정보
        StepDTO stepinfo =service.getListStepName(sid);
		//테스크 리스트
        int tstatusid =0;
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid);
        //그래프 관련 데이터
  	  	int completedtask = service.listCountCompletedTask(sid);
  		int unfinishedtask = service.listCountUnfinishedTask(sid);
  		int thepast = service.listCountThePast(sid);
  		int therest = service.listCountTheRest(sid);
  		int noday = service.listCountNoDay(sid);
  		int afternextweek = service.listCountAfterNextWeek(sid);
  		int untilthisweek = service.listCountUntilThisWeek(sid);
  		int overduetask = service.listCountOverdueTask(sid);
        
       
        
        map.addAttribute("stepinfo", stepinfo);
        map.addAttribute("tstatuslist", tstatuslist);
        map.addAttribute("tasklist",tasklist);
        map.addAttribute("completedtask",completedtask);
        map.addAttribute("unfinishedtask",unfinishedtask);
        map.addAttribute("thepast",thepast);
        map.addAttribute("therest",therest);
        map.addAttribute("noday",noday);
        map.addAttribute("afternextweek",afternextweek);
        map.addAttribute("untilthisweek",untilthisweek);
        map.addAttribute("overduetask",overduetask);
        
        return "step/list";
    }
    /**
     * 
     날      짜 : 2018. 6. 25.
     기      능 : Task insert 해주는 함수 
     작성자명 : 이 진 우
     */
	@RequestMapping(value="/listtaskcreate.htm",method=RequestMethod.GET)
	public String createTask(TaskDTO taskdto, HttpServletRequest request,ModelMap map) {
		taskdto.setPid((Integer)request.getSession().getAttribute("pid"));
		int sid = (Integer) request.getSession().getAttribute("sid");
		try {
			//task insert service
			boardservice.insertBoardTask(taskdto);
			//task 생성 후 시퀀스로 생성된 tid를 해당 step에 insert 하는 하는
			boardservice.insertBoardTaskInStep(taskdto.getTid(), sid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/list.htm?sid="+sid;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : STATUS FILTER이용해서 받으면  
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/liststatusfilter.htm",method=RequestMethod.POST)
	public String statusFilter(int tstatusid, String stepid, ModelMap map) {
		int sid = Integer.parseInt(stepid);
		System.out.println("값이 넘어오나요"+tstatusid+"/"+sid);
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid);
        map.addAttribute("tasklist",tasklist);
		return "step/listtask";
	}
	
	
	public String showTaskList(String s1, Model model) {
		return null;
	}

	public String changeTasks(String[] s1, String s2, String s3, Model model) {
		return null;
	}

	public String deleteTasks(String[] s1, Model model) {
		return null;
	}
	
	
	
}
