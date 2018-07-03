package com.apollo.task.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.task.dao.StarredTaskDAO;
import com.apollo.vo.MyWorkMemberDTO;
import com.apollo.vo.MyWorkStepDTO;
import com.apollo.vo.MyWorkTaskDTO;
import com.apollo.vo.StarredTaskDTO;


/**
 * 
  클래스명 : StarredTaskService
  날      짜 : 2018. 6. 29.
  작성자명 : 이 진 우
 */
@Service
public class StarredTaskService {
	@Autowired	
	private SqlSession sqlsession;
	
	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : Step List 첫 페이지 로드
	 작성자명 : 이 진 우
	 */
	public List<MyWorkTaskDTO> getListTask(String mid){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		StarredTaskDAO dao= sqlsession.getMapper(StarredTaskDAO.class);
		List<MyWorkTaskDTO> tasklist = dao.getStarredTasks(mid);
		List<MyWorkMemberDTO> memberlist = dao.getStarredMembers(mid);
		List<MyWorkStepDTO> steplist = dao.getStarredSteps(mid);
		
		for(MyWorkTaskDTO task: tasklist) {
			for(MyWorkStepDTO step :steplist) {// Task가 할당된 step을 집어넣어준다
				if(task.getTid() == step.getTid()) {
					if(task.getSteps()==null) {
						task.setSteps(new ArrayList<MyWorkStepDTO>());
					}
					task.getSteps().add(step);
				}	
			}//안쪽for문 end
			for(MyWorkMemberDTO member: memberlist) {//Task에 할당된 Memb
				if(task.getTid() ==member.getTid()) {
					if(task.getMembers()==null) {
						task.setMembers(new ArrayList<MyWorkMemberDTO>());
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
	 날      짜 : 2018. 7. 3.
	 기      능 : 즐겨찾기 삭제 기능
	 작성자명 : 이 진 우
	 */
	public int deleteStarredTask(StarredTaskDTO dto) {
		StarredTaskDAO dao= sqlsession.getMapper(StarredTaskDAO.class);
		int result = dao.deleteStarredTask(dto);//즐겨찾기 삭제한다
		return result;
	}
	
}
