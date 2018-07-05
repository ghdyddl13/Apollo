package com.apollo.mywork.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.mywork.dao.MyWorkDAO;
import com.apollo.vo.MyWorkMemberDTO;
import com.apollo.vo.MyWorkStepDTO;
import com.apollo.vo.MyWorkTaskDTO;


/**
 * 
  클래스명 : MyworkService
  날      짜 : 2018. 6. 15.
  작성자명 : 이 진 우
 */
@Service
public class MyworkService {
	@Autowired
	private SqlSession sqlsession;
	

	/**
	 * 
	 날      짜 : 2018. 6. 21.
	 기      능 : 사용자가 속해있는 일을 가져오기위한 함수
	 작성자명 : 이 진 우
	 */
	public Map<String, List<MyWorkTaskDTO>> getMyWork(String mid){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		MyWorkDAO dao = sqlsession.getMapper(MyWorkDAO.class);
		
		List<MyWorkTaskDTO> tasklist = dao.getMyWorkList(mid);
		List<MyWorkStepDTO> steplist = dao.getMyWorkStep(mid);
		List<MyWorkMemberDTO> memberlist = dao.getMyWorkMember(mid);
		List<MyWorkTaskDTO> todaylist = new ArrayList<MyWorkTaskDTO>();
		List<MyWorkTaskDTO> thisweeklist = new ArrayList<MyWorkTaskDTO>();
		List<MyWorkTaskDTO> nextweeklist = new ArrayList<MyWorkTaskDTO>();	
		List<MyWorkTaskDTO> laterlist = new ArrayList<MyWorkTaskDTO>();
		Map<String, List<MyWorkTaskDTO>> taskmap = new HashMap<String, List<MyWorkTaskDTO>>();
	
		for(MyWorkTaskDTO task:tasklist) {// Task를 하나하나 비교하기 시작한다
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
			
			
			if(task.getEday()==null) {//시작날이 지정이 안되어 있으면
				todaylist.add(task);
			}else {
				task.setDate(makeDate(task.getEday())); //요일 만들어준다
				Date endday=null;
				Date startday=null;
				try {
					endday = dateFormat.parse(task.getEday());
					startday = dateFormat.parse(task.getSday());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				
				int tecompare = getdate("today").compareTo(endday);
				//오늘 날짜와 task 마지막 날짜를 비교 => 0보다 크면 마지막날이 지났다! 0보다 작으면 마지막날이 안지났다
				int tscompare = getdate("today").compareTo(startday);
				// 오늘 날짜와 task 시작 날짜를 비교 => 0보다 크면 일이 시작되었다! 0보다 작으면 아직 일이 시작 안했다
				int twscompare = getdate("thisweeklast").compareTo(startday);
				// 이번주 마지막 날짜와 시작일을 비교 => 0보다 크면 시작일이 이번주내에 있다, 0보다 작으면 이번주는 아니다!
				int lscompare = getdate("laterfirst").compareTo(startday);
				// 다다음주 첫째날과 시작일을 비교 => 0보다 크면 시작일이 다다음주 보다 전이다 0보다 작으면 시작일이 다다음주이다!
				
				
				if(tecompare>0) {// 오늘 기준 마지막 날이 지났다
					task.setOverdue("overdue");
					todaylist.add(task);
					System.out.println("Overdue됨");
				}else if(tecompare<=0 && tscompare>=0){// 오늘 기준 시작날은 지났고 마지막 날이 지나지 않았다
					todaylist.add(task);
					System.out.println("일이 한창 진행중임");
				}else if(tscompare<0) {// 오늘 기준으로 일이 아직 시작 안했다
					if(twscompare>=0) {// 이번주 마지막날 기준으로 시작일이 이번주 내에 있다
						thisweeklist.add(task);
						System.out.println("이번주에 시작할꺼야");
					}else {//이번주 이후의 시작일
						if(lscompare>0) {//시작일이 다다음주에 비해 작
							nextweeklist.add(task);
							System.out.println("다음주에 시작할꺼야");
						}else {
							laterlist.add(task);
							System.out.println("다다음주에 시작할 꺼야");
						}
					}
				}
			}
		}//바깥for문 end	
		taskmap.put("todaylist", todaylist);
		taskmap.put("thisweeklist", thisweeklist);
		taskmap.put("nextweeklist", nextweeklist);
		taskmap.put("laterlist", laterlist);

		return taskmap;
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
	 날      짜 : 2018. 6. 21.
	 기      능 : 화면단에 뿌려줄 날짜를 원하는 문자 형태로 뿌려주는 역할을 하는 함수
	 작성자명 : 이 진 우
	 */
	public String dateToString(Date date) {
		String stringdate = String.valueOf(date);
		String stringfydate = stringdate.substring(4, 10);
		
		return stringfydate;
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
}
