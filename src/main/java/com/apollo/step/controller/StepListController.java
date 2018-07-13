package com.apollo.step.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.apollo.task.service.TaskService;
import com.apollo.vo.CommentDTO;
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
	private TaskService taskservice;
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
        String sorting = (String)session.getAttribute("sorting");
        if(sorting==null) {
        	sorting="changedate";
        	session.setAttribute("sorting", sorting);
        }
        map.addAttribute("sorting",sorting);
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid,mid,sorting);
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
	public String statusFilter(int sid,String tstatusid,HttpSession session ,ModelMap map) {
		String mid=null;
		String sorting = (String)session.getAttribute("sorting");
        ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid,mid,sorting);
        map.addAttribute("tasklist",tasklist);
		return "step/listtask";
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : 사람 필터를 이용해서 값을 분류한다
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/listFilter.htm",method=RequestMethod.POST)
	public String listFilter(int sid,String tstatusid, String mid,HttpSession session, ModelMap map) {
		System.out.println("sid/"+sid+"   tstatusid/"+tstatusid+"     mid/"+mid);
		if(tstatusid.equals("0")) { // 모든 tstatus
			tstatusid =null;
		}
		if(mid.equals("")) {
			mid =null;
		}
		String sorting = (String)session.getAttribute("sorting");
		ArrayList<StepListTaskDTO> tasklist = service.getListTask(sid,tstatusid,mid,sorting);
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
		return jsonview;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 6.
	 기      능 : TASK MASS EDIT(여러 테스크의 상태를 변경하는 컨트롤러)
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/liststatustasks.htm", method=RequestMethod.POST)
	public String statusListTasks(int tstatusid,String[] tasks, HttpSession session, ModelMap map) {
		int result = service.listStatusTasks(tasks, tstatusid);
		if(result >= 1) {
			for(String tid:tasks) {
			int taskid=Integer.parseInt(tid.substring(1));
			String realtname = taskservice.getTname(taskid);
			String comment = "";
			String mid = (String) session.getAttribute("mid");
			ArrayList<TstatusDTO> tstatuslist = new ArrayList();
			tstatuslist = taskservice.gettstatuslist(taskid);
			for(TstatusDTO tstatusdto : tstatuslist) {
					if(tstatusid == tstatusdto.getTstatusid()) {
						
						String modifier = taskservice.getTaskModifierName(mid);
						comment += modifier + "님이 " + realtname +"의 상태를 " + tstatusdto.getTstatus() + "로 변경하였습니다";
					}
			}
			
			CommentDTO commentdto = new CommentDTO();
			commentdto.setComments(comment);
			commentdto.setTid(taskid);
			commentdto.setMid(mid);
			commentdto.setCmtkind(1);
			
			int insert_comment_result = taskservice.insertComment(commentdto);
			}
		}
		int sid = (Integer)session.getAttribute("sid");
		
		return "redirect:/list.htm?sid="+sid;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 6.
	 기      능 : TASK MASS EDIT 전에 STEP LIST 정보를 가지고 오는 함수
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/stepListforAddStep.htm",method=RequestMethod.POST)
	public View getStepListForMessEdit(HttpSession session, ModelMap map) {
		int sid = (Integer)session.getAttribute("sid");
		List<StepDTO> steplist=service.getStepListBeforeAddStepTasks(sid);
		map.addAttribute("steplist",steplist);
		return jsonview;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : TASK MASS EDIT(여러 테스크를 특정인에게 할당하는 컨트롤러)
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/listassigntasks.htm", method=RequestMethod.POST)
	public String assignListTasks(String mid, String[] tasks,HttpSession session, ModelMap map) {
		int result = service.listAssignTasks(tasks, mid);
		
		if(result >= 1) {
			for(String tid:tasks) {
			int taskid = Integer.parseInt(tid.substring(1));
			String realtname = taskservice.getTname(taskid);
			
			String modifier = (String) session.getAttribute("mid");
			modifier = taskservice.getTaskModifierName(modifier);
			String assignee_name = taskservice.getTaskModifierName(mid);

			String comment = "";
			comment = modifier + "님이 " + realtname +" 업무를 " + assignee_name +"님에게 할당하였습니다";
			
			// comments 테이블에 insert
			CommentDTO commentdto = new CommentDTO();
			commentdto.setComments(comment);
			commentdto.setTid(taskid);
			
			String mymid = (String) session.getAttribute("mid");
			commentdto.setMid(mymid);
			commentdto.setCmtkind(2);
			int final_result = taskservice.insertAssignComment(commentdto);
			}
		}
		
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
	/**
	 * 
	 날      짜 : 2018. 7. 6.
	 기      능 : TASK SORTING 기능
	 작성자명 : 이 진 우
	 */
	@RequestMapping(value="/sortingTasksList.htm",method=RequestMethod.POST)
	public String filterListTask(String sorting,HttpSession session, ModelMap map) {
		int sid = (Integer)session.getAttribute("sid");
		session.setAttribute("sorting", sorting);
		return "redirect:/list.htm?sid="+sid;
	}
}
