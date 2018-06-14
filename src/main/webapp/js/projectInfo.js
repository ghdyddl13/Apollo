$(function() {
	
	/* 나중에 지울 것
	 * 지금 버튼식으로 되어 있는데 다 빼서
	 * 로드되자마자 뿌릴 수 있도록 한다
	 * 잊지 않기 위해서 alert 작성
	 */
	alert('로드되자마자 데이터 뿌리기');

	
	/*
	 날      짜 : 2018. 6. 13.
	 기      능 : 서버시각 추출을 위한 함수 / 호출 형식은 YYYYMMDDHHMMSS / 활용 예시 : var nowdate = serverToday();
	 작성자명 : 김 정 권
	 */
	function serverToday(){ 
		  var xmlHttp; 
		  //분기하지 않으면 IE에서만 작동된다. 
		  if (window.XMLHttpRequest) { // IE 7.0 이상, 크롬, 파이어폭스일 경우 분기 
		    xmlHttp = new XMLHttpRequest(); 
		    xmlHttp.open('HEAD',window.location.href.toString(),false);
		    xmlHttp.setRequestHeader("Content-Type", "text/html"); 
		    xmlHttp.send(''); 
		  }else if (window.ActiveXObject) { 
		    xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
		    xmlHttp.open('HEAD',window.location.href.toString(),false);
		    xmlHttp.setRequestHeader("Content-Type", "text/html"); 
		    xmlHttp.send(''); 
		  } 
		  var st = xmlHttp.getResponseHeader("Date");
		  var curDate = new Date(st); 
		  var curDateFmt; var year = curDate.getFullYear(); 
		  var month = curDate.getMonth()+1; 
		  var day = curDate.getDate(); 
		  var hours = curDate.getHours(); 
		  var minutes = curDate.getMinutes(); 
		  if(parseInt(month) < 10){ 
		    month = 0 + "" + month; 
		  } 
		  if(parseInt(day) < 10){ 
		    day = 0 + "" + day; 
		  } 
		  if(parseInt(hours) < 10){ 
		    hours = 0 + "" + hours; 
		  } if(parseInt(minutes) < 10){ 
		    minutes = 0 + "" + minutes; 
		  } 
		  curDateFmt = parseInt(year + "" + month + "" + day + "" + hours + "" + minutes); 
		  return curDateFmt; 
		}
	
	
	/*
	 날      짜 : 2018. 6. 13.
	 기      능 : donut Chart의 데이터를 불러오고 이에 맞게 분류하기 위한 함수
	 작성자명 : 김 정 권
	 */
	function donutChartSetting(){
		
		// 마감기한별로 구분된 라벨을 배열로 선언
		// 후에 반복을 돌면서 맞는 배열에 데이터를 넣는다
		var unassigned = [];
		var nextweek = [];
		var thisweek = [];
		var completed = [];
		var expired = [];
		
		// 서버시각 추출
		var nowtime = serverToday();
		nowtime = nowtime.toString();
		var year = nowtime.substring(0,4);
		var month = nowtime.substring(4,6); 
		var day = nowtime.substring(6,8);
		
	    // 오늘에 해당하는 요일의 숫자값을 반환
		// 일, 월, 화 ... ,토 = 0, 1, 2 ... ,6
	    var today = new Date(year+'-'+month+'-'+day).getDay();
		
		// pid를 1로 가정하고 시행
		// 이 부분은 나중에 사이드바에서 가져온 pid로 변경한다
		var pid = 1;
		
		// DB로 Task들을 호출
		// 위에서 선언한 배열에 데이터들을 넣기 위함
		$.ajax(
		        {
		           type : "post",
		           url  : "donutChart.htm",
		           data : "pid="+pid,
		           success : function(rdata){
		               
		            	   $(rdata.tasklist).each(function(index, el) {
		            			
		            		    nowtime = nowtime.toString();
		            			var year = nowtime.substring(0,4);
		            			var month = nowtime.substring(4,6); 
		            			var day = nowtime.substring(6,8);
		            		   
		            		   
		            	   });
		           }
		        }
		     );// end-ajax
		
	} // end - donutChartSetting
	
	
	$('#testbtn1').click(function(){ 
		var pid = 1;
		$.ajax(
		        {
		           type : "post",
		           url  : "donutChart.htm",
		           data : "pid="+pid,
		           success : function(rdata){
		        	   console.log(rdata);
		        	   $(rdata.assignedtasklist).each(function(index, el) {

		        	   
		           }
		        }
		     );// end-ajax
	});
	
	
	// 해당 프로젝트의 task들 불러와서 마감일 기준으로 donutChart 형성
	$('#testbtn3').click(function(){
   
		// 마감기한별로 구분된 라벨을 배열로 선언
		// 후에 반복을 돌면서 맞는 배열에 데이터를 넣는다
		var unassigned = [];
		var nextweek = [];
		var thisweek = [];
		var completed = [];
		var expired = [];
		
		// 서버시각 추출
		var nowtime = serverToday();
		nowtime = nowtime.toString();
		var now_year = parseInt(nowtime.substring(0,4));
		var now_month = parseInt(nowtime.substring(4,6)); 
		var now_day = parseInt(nowtime.substring(6,8));
		
	    // 오늘에 해당하는 요일의 숫자값을 반환
		// 일, 월, 화 ... ,토 = 0, 1, 2 ... ,6
	    var today = new Date(year+'-'+month+'-'+day).getDay();
		
		// pid를 1로 가정하고 시행
		// 이 부분은 나중에 사이드바에서 가져온 pid로 변경한다
		var pid = 1;
		
		// DB로 Task들을 호출
		// 위에서 선언한 배열에 데이터들을 넣기 위함
		$.ajax(
		        {
		           type : "post",
		           url  : "donutChart.htm",
		           data : "pid="+pid,
		           success : function(rdata){
		        	   	   
		        	   	   console.log(rdata);
		        	   
			        	   $(rdata.notassignedtasklist).each(function(index, el) {
			        		   		unassigned.push(el.tid);
		            		   });
		        	   
		            	   $(rdata.assignedtasklist).each(function(index, el) {

		            		    // 종료일을 year, month, day 기준으로 분할
		            		    eday = el.eday.toString();
		            			var eday_year = parseInt(eday.substring(0,4));
		            			var eday_month = parseInt(eday.substring(5,7)); 
		            			var eday_day = parseInt(eday.substring(8,10));
		            		   
		            		    // 완료 상태인 경우
		            		    if((el.tstatusid == 3)||(el.tstatusid == 11)||(el.tstatusid == 15)){
		            		    	completed.push(el.tid);

		            		    	// 완료가 되었을 경우에는 마감 기한에 따라 배열에 넣지 않고
		            		    	// 끝내기 위해서 continue를 사용
		            		    	continue;
		            		    }
		            		    
		            		    // 작업 기한이 만료된 경우 
		            		    else if(eday_year <= now_year){

		            		    	if(eday_month <= now_year){
		            		    		// 이미 만료일이 지난 경우
		            		    		if(eday_day < now_day ){
		            		    			expired.push(el.tid);
		            		    			continue;
			            		    		} 
			            		    	}
			            		    }
		            		   		
		            		    // 누군가에게 할당이 된 테스크 중
		            		    // 1. 완료 상태가 아니며
		            		    // 2. 만료 상태가 아닌 모든 테스크
		            		    else{
		            		    	
		            		    }	

		            		    /*
		            		    	var unassigned = []; // 해결
									var completed = []; // 해결
									var expired = []; // 해결
									
									var nextweek = [];
									var thisweek = [];
		            		   	*/ 
		            		   		
		            		   } // assignedtasklist .each 반복종료
		            		   
		            	   });
								
		           }
		        }
		     );// end-ajax
		
	    var ctx = document.getElementById('DonutChart').getContext('2d');
	    var myDoughnutChart = new Chart(ctx, {

	        type: 'doughnut',
	        data: {
	                datasets: [{
	                    data: [10, 20, 30, 20, 50],
	                    backgroundColor: [

	                                    'rgba(190, 190, 190, 1)',

	                                    'rgba(241, 196, 15, 1)',

	                                    'rgba(244, 7, 7, 1)',

	                                    'rgba(52, 152, 219, 1)',

	                                    'rgba(46, 204, 113, 1)'

	                                ],

	                }],

	                labels:
	           [
	             '미지정','다음주 이후','이번주 까지','완료','기한 만료'
	           ]
	            },

	        options: {
	         cutoutPercentage: 50,
	         legend: {
	            display: true,
	            position: 'left',
	            labels: {
	                fontSize: 12,
	                fontFamily: 'sans-serif',
	                fontColor: '#000000',
	                fontStyle: 'bold'
	                  }
	            }
	      }
	    });
	    
	});
	
	/*
	$('#testbtn5').click(function(){
		var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "getSteps.htm",
           data : "pid="+pid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	
	$('#testbtn6').click(function(){
		var sid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "getTasksInSteps.htm",
           data : "sid="+sid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	
	$('#testbtn7').click(function(){
		var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "getProjectMembers.htm",
           data : "pid="+pid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	*/
});