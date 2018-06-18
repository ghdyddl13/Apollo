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
	
	public Map<String, List<MyWorkTaskDTO>> getMyWork(String mid) throws Exception{
		MyWorkDAO dao = sqlsession.getMapper(MyWorkDAO.class);
		List<MyWorkTaskDTO> tasklist = dao.getMyWorkList(mid);
		List<MyWorkStepDTO> steplist = dao.getMyWorkStep(mid);
		List<MyWorkTaskDTO> todaylist = new ArrayList<MyWorkTaskDTO>();
		List<MyWorkTaskDTO> thisweeklist = new ArrayList<MyWorkTaskDTO>();
		List<MyWorkTaskDTO> nextweeklist = new ArrayList<MyWorkTaskDTO>();	
		List<MyWorkTaskDTO> laterlist = new ArrayList<MyWorkTaskDTO>();
		Map<String, List<MyWorkTaskDTO>> taskmap = new HashMap<String, List<MyWorkTaskDTO>>();
		
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String today = calender.get(Calendar.YEAR)+"-" + (calender.get(Calendar.MONTH)+1) +"-"+ calender.get(Calendar.DATE); // 오늘 날짜
		Date todaydate =dateFormat.parse(today);
	
		for(MyWorkTaskDTO task:tasklist) {// Task를 하나하나 비교하기 시작한다
			for(MyWorkStepDTO step :steplist) {// Task가 할당된 step을 집어넣어준다
				if(task.getTid() == step.getTid()) {
					if(task.getSteps()==null) {
						task.setSteps(new ArrayList<MyWorkStepDTO>());
					}
					task.getSteps().add(step);
				}	
			}//안쪽for문 end
			
			if(task.getEday()==null) {//시작날이 지정이 안되어 있으면
				todaylist.add(task);
			}else {
				task.setDate(makeDate(task.getEday())); //요일 만들어준다
				Date endday = dateFormat.parse(task.getEday());
				Date startday =dateFormat.parse(task.getSday());
				
				int tecompare = todaydate.compareTo(endday);
				//오늘 날짜와 task 마지막 날짜를 비교 => 0보다 크면 마지막날이 지났다! 0보다 작으면 마지막날이 안지났다
				
				int tscompare = todaydate.compareTo(startday);
				// 오늘 날짜와 task 시작 날짜를 비교 => 0보다 크면 일이 시작되었다! 0보다 작으면 아직 일이 시작 안했다
				
				if(tecompare>0) {// 오늘 기준 마지막 날이 지났다
					todaylist.add(task);
				}else if(tecompare<0 && tscompare>0){// 오늘 기준 시작날은 지났고 마지막 날이 지나지 않았다
					todaylist.add(task);
				}else if(tscompare<0) {// 오늘 기준으로 일이 아직 시작 안했다
					
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
	 기      능 : 이번주의 마지막 날을 알려주는 함수
	 작성자명 : 이 진 우
	 */
	public Date thisweeklast() {
		
		return thisweeklast;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : 다음주의 첫번째 날을 알려주는 함수
	 작성자명 : 이 진 우
	 */
	public Date nextweekfirst() {
		
		return nextweekfirst;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : 
	 작성자명 : 이 진 우
	 */
	public Date laterfirst() {
		
		return laterfirst;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : 어떤 요일인지 확인하는 함수
	 작성자명 : 이 진 우
	 */
	public int checkday(String date) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
	    Date nDate=null;
		try {
			nDate = dateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	     
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	     
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;

	    return dayNum;
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
