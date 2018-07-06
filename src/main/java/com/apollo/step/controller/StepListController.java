package com.apollo.step.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.apollo.step.service.StepBoardService;
import com.apollo.step.service.StepListService;
import com.apollo.vo.MemberDTO;
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
	@Autowired
	private View jsonview;
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : 리스트페이지 비동기 화면전환 
	 작성자명 : 박 민 식, 이 진 우
	 */
    @RequestMapping(value="/list.htm",method=RequestMethod.GET)
    public String list(int sid, HttpSession session, HttpServletRequest request, ModelMap map) {
    	session.setAttribute("location", "/list.htm");
    	// 스텝 id 와 프로젝트 id 세션값 갱신
        int pid = service.getProjectIdByStepId(sid);
        session.setAttribute("sid", sid);
        session.setAttribute("pid", pid);
        //스텝이 소속된 프로젝트의 방법론 정보
        ArrayList<TstatusDTO> tstatuslist = service.getListTstatusList(sid);
        //스텝 정보
        StepDTO stepinfo =service.getListStepName(sid);
		//테스크 리스트
        String tstatusid =null;
        String mid=null;
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid,mid);
        //그래프 관련 데이터
  	  	int completedtask = service.listCountCompletedTask(sid);
  		int unfinishedtask = service.listCountUnfinishedTask(sid);
  		int thepast = service.listCountThePast(sid);
  		int therest = service.listCountTheRest(sid);
  		int noday = service.listCountNoDay(sid);
  		int afternextweek = service.listCountAfterNextWeek(sid);
  		int untilthisweek = service.listCountUntilThisWeek(sid);
  		int overduetask = service.listCountOverdueTask(sid);
        
       
        //System.out.println("미지정: "+noday+"/다음주 이후: "+afternextweek+"/이번주까지: "+untilthisweek+"/완료: "+completedtask+"/기간만료: "+overduetask);
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
	public String statusFilter(int sid,String tstatusid,ModelMap map) {
		String mid=null;
		//System.out.println("값이 넘어오나요"+tstatusid+"/"+sid);
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid,mid);
        map.addAttribute("tasklist",tasklist);
		return "step/listtask";
	}
	/**
	 * 
	 날      짜 : 2018. 6. 29.
	 기      능 : 첫 페이지가 로드 되었을때 Memberlist 뜨게 하기
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/memberlist.htm",method=RequestMethod.POST)
	public View memberlist(int sid, ModelMap map) {
		ArrayList<MemberDTO> memberlist = service.listProjectMemberList(sid);
        map.addAttribute("memberlist",memberlist);
        //System.out.println(memberlist.toString());
		return jsonview;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : 사람 필터를 이용해서 값을 분류한다
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/listpeoplefilter.htm",method=RequestMethod.POST)
	public String peopleFilter(int sid, String mid, ModelMap map) {
		String tstatusid =null;
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid,mid);
        map.addAttribute("tasklist",tasklist);
		return "step/listtask";
	}
	/**
	 * 
	 날      짜 : 2018. 7. 6.
	 기      능 : TASK MASS EDIT(여러 테스크를 특정인에게 할당하는 컨트롤러)
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/liststatustasks.htm", method=RequestMethod.POST)
	public String statusListTasks(int tstatusid,String[] tasks, HttpSession session, ModelMap map) {
		service.listStatusTasks(tasks, tstatusid);
		int sid = (Integer)session.getAttribute("sid");
		
		return "redirect:/list.htm?sid="+sid;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : TASK MASS EDIT(여러 테스크를 특정인에게 할당하는 컨트롤러)
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/listassigntasks.htm", method=RequestMethod.POST)
	public String assignListTasks(String mid, String[] tasks,HttpSession session, ModelMap map) {
		service.listAssignTasks(tasks, mid);
		int sid = (Integer)session.getAttribute("sid");
		
		return "redirect:/list.htm?sid="+sid;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : TASK MASS EDIT(여러 테스크를 특정스텝에 추가하는 컨트롤러)
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/listaddsteptasks.htm", method=RequestMethod.POST)
	public String addstepListTasks(int stepid,String[] tasks,HttpSession session, ModelMap map) {
		service.listAddStepTasks(tasks, stepid);
		int sid = (Integer)session.getAttribute("sid");
		
		return "redirect:/list.htm?sid="+sid;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : TASK MASS EDIT(삭제 버튼 클릭시 삭제하는 컨트롤러)
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/listdeletetasks.htm", method=RequestMethod.POST)
	public String deleteListTasks(String[] tasks,HttpSession session, ModelMap map) {
		int sid = (Integer)session.getAttribute("sid");
		service.listDeleteTasks(tasks);//삭제한다

		return "redirect:/list.htm?sid="+sid;
	}
	

}
