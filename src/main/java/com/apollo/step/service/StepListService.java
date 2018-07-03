package com.apollo.step.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.step.dao.StepDAO;
import com.apollo.vo.AssigneeDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.StepListMemberDTO;
import com.apollo.vo.StepListStepDTO;
import com.apollo.vo.StepListTaskDTO;
import com.apollo.vo.TaskInStepDTO;
import com.apollo.vo.TstatusDTO;

@Service
public class StepListService {
	
	@Autowired
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 20.
	 기      능 : Step클릭 시 소속 프로젝트의 아이디를 가져오기 
	 작성자명 : 박 민 식
	 */
	public int getProjectIdByStepId(int sid) {
		
		int pid = 0;
		try {
			StepDAO dao= sqlsession.getMapper(StepDAO.class);
			pid =dao.getProjectIdByStepId(sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pid;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : Step 클릭 시 Step의 methodology list를 가지고옴
	 작성자명 : 이 진 우
	 */
	public ArrayList<TstatusDTO> getListTstatusList(int sid){
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		ArrayList<TstatusDTO> tstatuslist =dao.getListTstatus(sid);
		return tstatuslist;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : Step 클릭시 Step의 이름과 아이디를 가지고옴
	 작성자명 : 이 진 우
	 */
	public StepDTO getListStepName(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		StepDTO stepname =dao.getListStepName(sid);
		return stepname;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : Step List 첫 페이지 로드
	 작성자명 : 이 진 우
	 */
	public ArrayList<StepListTaskDTO> getListTask(int sid, String tstatusid,String mid){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		HashMap<String, String> map = new HashMap<String, String>();
		String sids = ""+sid;
		map.put("sid", sids);
		map.put("tstatusid" , tstatusid);
		map.put("mid", mid);
		ArrayList<StepListTaskDTO> tasklist = dao.getStepListTask(map);
		ArrayList<StepListMemberDTO> memberlist = dao.getStepListMember(map);
		ArrayList<StepListStepDTO> steplist = dao.getStepListStep(map);
		
		for(StepListTaskDTO task: tasklist) {
			for(StepListStepDTO step :steplist) {// Task가 할당된 step을 집어넣어준다
				if(task.getTid() == step.getTid()) {
					if(task.getSteps()==null) {
						task.setSteps(new ArrayList<StepListStepDTO>());
					}
					task.getSteps().add(step);
				}	
			}//안쪽for문 end
			for(StepListMemberDTO member: memberlist) {//Task에 할당된 Memb
				if(task.getTid() ==member.getTid()) {
					if(task.getMembers()==null) {
						task.setMembers(new ArrayList<StepListMemberDTO>());
					}
					task.getMembers().add(member);
				}
			}
			if(task.getEday()==null) {
				//마지막날 지정이 안되어 있으면 그냥 넘어간다
			}else {
				task.setDate(makeDate(task.getEday()));//보기좋게 만들어준다
				Date endday=null;
				try {
					endday = dateFormat.parse(task.getEday());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				int tecompare = getdate("today").compareTo(endday);
				//오늘 날짜와 task 마지막 날짜를 비교 => 0보다 크면 마지막날이 지났다! 0보다 작으면 마지막날이 안지났다
				if(tecompare>0) {
					task.setOverdue("overdue");
				}
			}
		}
		return tasklist;
	}

	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : 특정 위치의 날짜를 가지고 올 수 있는 함수
	 작성자명 : 이 진 우
	 */
	public Date getdate(String when) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		int restday = 7 - calendar.get(Calendar.DAY_OF_WEEK);
		String todaystring = calendar.get(Calendar.YEAR)+"-" + (calendar.get(Calendar.MONTH)+1) +"-"+ (calendar.get(Calendar.DATE));
		Date today = null;
		try {
			today = dateFormat.parse(todaystring);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		calendar.setTime(today);
		if(when.equals("today")) {
			//따로 연산을 하지 않는다
		}else if(when.equals("thisweekfirst")) {
			calendar.add(Calendar.DAY_OF_MONTH, restday-6);
		}else if(when.equals("thisweeklast")) {
			calendar.add(Calendar.DAY_OF_MONTH, restday);
		}else if (when.equals("nextweekfirst")) {
			calendar.add(Calendar.DAY_OF_MONTH, restday+1);
		}else if (when.equals("nextweeklast")) {
			calendar.add(Calendar.DAY_OF_MONTH, restday+7);			
		}else if (when.equals("laterfirst")){
			calendar.add(Calendar.DAY_OF_MONTH, restday+8);						
		}else {
			calendar.add(Calendar.DAY_OF_MONTH, restday+14);						
		}
		return calendar.getTime();
	}
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : Eday를 뿌려주기 좋은 데이터로 만들어주기 위해 쓰는 함수 (IN : Eday (ex:2018-07-01)/ OUT: date(ex: Jun 02)
	 작성자명 : 이 진 우
	 */
	public String makeDate(String eday) {
		String month=eday.substring(5,7);
		String day = eday.substring(8,10);
		String date="";
		
		if(month.equals("01")) {
			date = "Jan";
		} else if(month.equals("02")){
			date = "Feb";
		} else if (month.equals("03")){
			date = "Mar";
		} else if (month.equals("04")){
			date = "Apr";
		} else if (month.equals("05")){
			date = "May";
		} else if (month.equals("06")){
			date = "Jun";
		} else if (month.equals("07")){
			date = "Jul";
		} else if (month.equals("08")){
			date = "Aug";
		} else if (month.equals("09")){
			date = "Sep";
		} else if (month.equals("10")){
			date = "Oct";
		} else if (month.equals("11")){
			date = "Nov";
		} else{
			date = "Dec";
		}
		date += " "+day;
		return date;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 :  LIST PAGE 우측 상단의 TASK 완료/미완료 Service 
	 작성자명 : 이 진 우
	 */
	public int listCountCompletedTask(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		int completedtask =dao.listCountCompletedTask(sid);
		return completedtask;
	}
	public int listCountUnfinishedTask(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		int unfinishedtask =dao.listCountUnfinishedTask(sid);
		return unfinishedtask;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 : LIST PAGE 우측 중간의 STEP 기한 
	 작성자명 : 이 진 우
	 */
	public int listCountThePast(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		Integer thepast =dao.listCountThePast(sid);
		if(thepast==null) {
			thepast=0;
		}
		return thepast;
	}
	public int listCountTheRest(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		Integer therest =dao.listCountTheRest(sid);
		if(therest==null) {
			therest=0;
		}
		return therest;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 : LIST PAGE 우측 하단의 TASK 상태
	 작성자명 : 이 진 우
	 */
	public int listCountNoDay(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		int noday =dao.listCountNoDay(sid);
		System.out.println("noday:"+noday);
		return noday;
	}	
	public int listCountAfterNextWeek(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		int afternextweek =dao.listCountAfterNextWeek(sid);
		System.out.println("afternextweek"+afternextweek);
		return afternextweek;
	}
	public int listCountUntilThisWeek(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		int untilthisweek =dao.listCountUntilThisWeek(sid);
		System.out.println("untilthisweek:" +untilthisweek);
		return untilthisweek;
	}
	public int listCountOverdueTask(int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		int overduetask =dao.listCountOverdueTask(sid);
		System.out.println("overdue:"+overduetask);
		return overduetask;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 : LIST PAGE 맴퍼 필터용 맴버 리스트
	 작성자명 : 이 진 우
	 */
	public ArrayList<MemberDTO> listProjectMemberList(int sid){
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		ArrayList<MemberDTO> projectmemberlist = dao.listProjectMemberList(sid);
		return projectmemberlist;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : LIST PAGE MASS EDIT에서 특정인에게 여러 Task 할당
	 작성자명 : 이 진 우
	 */
	public int listAssignTasks(String[] tasks, String mid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		List<AssigneeDTO> list = new ArrayList<AssigneeDTO>();
		List<AssigneeDTO> existassignee = dao.listBeforeAssignTasks(mid);
		for(String tid:tasks) {
			int count =0;
			int taskid = Integer.parseInt(tid.substring(1));
			for(AssigneeDTO assignee:existassignee) {
				if(taskid==assignee.getTid()) {//현재 넣어줄 tid와 이미 존재하는 tid가 같다면 카운트 증가
					count++;
				}
			}
			if(count==0) {//한번도 같은 적이 없어야 그 것을 리스트에 넣는다
				AssigneeDTO dto = new AssigneeDTO();
				dto.setMid(mid);
				dto.setTid(taskid);
				list.add(dto);
			}
		}
		int result=0;
		if(list.size()!=0) {//아무것도 들어있지않으면 insert를 못하니 그 경우를 막는다
			result = dao.listAssignTasks(list);
		}
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : LIST PAGE MASS EDIT에서 특정스텝에 여러 Task 추가
	 작성자명 : 이 진 우
	 */
	public int listAddStepTasks(String[] tasks, int sid) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		List<TaskInStepDTO> list = new ArrayList<TaskInStepDTO>();
		List<TaskInStepDTO> existstep = dao.listBeforeAddStepTasks(sid);
		for(String tid:tasks) {
			int count=0;
			int taskid = Integer.parseInt(tid.substring(1));
			for(TaskInStepDTO taskinstep:existstep) {
				if(taskinstep.getTid()==taskid) {//현재 넣어줄 tid와 이미 존재하는 tid가 같다면 카운트 증가
					count++;
				}
			}
			if(count==0) {//한번도 같은 적이 없어야 그 것을 리스트에 넣는다
				TaskInStepDTO dto = new TaskInStepDTO();
				dto.setSid(sid);
				dto.setTid(taskid);
				list.add(dto);				
			}
		}
		int result=0;
		if(list.size()!=0) {//아무것도 들어있지않으면 insert를 못하니 그 경우를 막는다
			result = dao.listAddStepTasks(list);
		}
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : LIST PAGE MASS EDIT에서 삭제 
	 작성자명 : 이 진 우
	 */
	public int listDeleteTasks(String[] tasks) {
		StepDAO dao= sqlsession.getMapper(StepDAO.class);
		List<Integer> list = new ArrayList<Integer>();
		for(String tid:tasks) {
			list.add(Integer.parseInt(tid.substring(1)));
		}
		int result= dao.listDeleteTasks(list);
		return result;
	}
}
