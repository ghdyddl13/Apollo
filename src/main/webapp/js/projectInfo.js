$(function() {
	
	/* 나중에 지울 것
	 * 지금 버튼식으로 되어 있는데 다 빼서
	 * 로드되자마자 뿌릴 수 있도록 한다
	 * 잊지 않기 위해서 alert 작성
	 */
	alert('로드되자마자 데이터 뿌리기');

	/* 서버시각 추출을 위한 함수
	 * 호출 형식은 YYYYMMDDHHMMSS
	 * 활용 예시 : var nowdate = serverToday();
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
	
	/* donut Chart의 데이터를 불러오고 이에 맞게 분류하기 위한 함수
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
		var year = nowtime.substring(0,4);
		var month = nowtime.substring(5,7); 
		var day = nowtime.substring(8,10);
		
		
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
		            		   
		            			// dayarr[0] = year / dayarr[1] = month / dayarr[2] = day
		            			var dayarr = nowtime.split('-');
		            			
		            		   console.log('hello');
		            		   console.log(el.eday);
		            		   
		            	   });
		           }
		        }
		     );// end-ajax
		
		
	} // end - donutChartSetting
	

	
	
	
	
	
	
	
	
	
	
	
	$('#testbtn1').click(function(){
		// 20180613
			alert('testbtn1 작동');
			var nowtime = serverToday();
			nowtime = String(nowtime);
			
			var year = nowtime.substring(0,4);
			var month = nowtime.substring(4,6); 
			var day = nowtime.substring(6,8);
			
			var testdate = '2018-06-08';
			var dayarr = testdate.split('-');
			alert(dayarr[0] + dayarr[1] + dayarr[2]);
			
	});
		
	// 해당 프로젝트의 task들 불러와서 마감일 기준으로 donutChart 형성
	$('#testbtn3').click(function(){
   
	var unassigned = [];
	var nextweek = [];
	var thisweek = [];
	var completed = [];
	var expired = [];
		
	// pid를 1로 가정하고 시행
	var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "donutChart.htm",
           data : "pid="+pid,
           success : function(rdata){
               console.log(rdata.tasklist);
               
            	   $(rdata.tasklist).each(function(index, el) {
            		   console.log('hello');
            		   console.log(el.tid);
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
	
	
	$('#testbtn4').click(function(){
		var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "donutChart.htm",
           data : "pid="+pid,
           success : function(rdata){
               
               $(rdata.tasklist).each(function(index, el) {
        		   console.log(el.eday);
        	   });
               
           } 
        } 
      ) // end-ajax
        
	});
	
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
	
});