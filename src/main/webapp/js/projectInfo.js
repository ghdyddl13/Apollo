$(function() {
	   
	var pid = $('#project_infopage_pid').attr('value');
	
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
    날      짜 : 2018. 6. 14.
    기      능 : donut Chart의 데이터를 불러오고 이에 맞게 분류하고 donut Chart 형성
    작성자명 : 김 정 권
    */
   // 마감기한별로 구분된 라벨을 배열로 선언
   // 후에 반복을 돌면서 맞는 배열에 데이터를 넣는다
   var noeday = [];
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
   var today = new Date(now_year+'-'+now_month+'-'+now_day).getDay();
   
   // DB로 Task들을 호출
   // 위에서 선언한 배열에 데이터들을 넣기 위함
   $.ajax(
           {
              type : "post",
              url  : "donutChart.htm",
              data : "pid="+pid,
              success : function(rdata){
                  
            	  console.log('여기닷');
            	  console.log(rdata);
            	  
            	  
                  // 할당된 테스크 반복돌며 기한 지난 테스크의 tid를 배열에 넣기
                   $(rdata.tasklist).each(function(index, el) {
                  	 
                  	console.log('회전 몇번하니')
                  	console.log(el.tstatusid)
                  	console.log(el.tid)
                  	console.log(el.eday)
                   
                   });
            	  
            	  
                    // 할당된 테스크 반복돌며 기한 지난 테스크의 tid를 배열에 넣기
                     $(rdata.tasklist).each(function(index, el) {
                    	 
                    	console.log('---------------')
                    	console.log(el.tstatusid)
                    	console.log(el.tid)
                    	console.log(el.eday)
                    	 
                    	if((el.tstatusid == 3)||(el.tstatusid == 11)||(el.tstatusid == 15)){
                             completed.push(el.tid);

                             // 완료가 되었을 경우에는 마감 기한에 따라 배열에 넣지 않고
                             // continue 기능을 하는 return true
                             return true;
                        }
                    	 
                    	// 종료일 없을때 처리
                    	else if(el.eday == null){
                        	noeday.push(el.tid);
                        	return true;
                        }
                    	
                    	else {
                    		var eday = el.eday.toString();
                            var eday_year = parseInt(eday.substring(0,4));
                            var eday_month = parseInt(eday.substring(5,7)); 
                            var eday_day = parseInt(eday.substring(8,10));
                            
                            // 작업 기한이 만료된 경우 
                            // 년도상 과거가 만료일
                             if(eday_year < now_year){
                                expired.push(el.tid);
                               return true;
                             }
                            
                            // 년도상 미래가 만료일
                             else if(eday_year > now_year){
                                nextweek.push(el.tid);
                               return true;
                             }
                            
                            // else1
                            // 년도상 같은 년도
                             else {
                                
                                // 같은 년도, 과거 달
                                if(eday_month < now_month){
                                   expired.push(el.tid);
                                   return true;
                                }
                                // 같은 년도, 미래 달
                                else if(eday_month > now_month){
                                   nextweek.push(el.tid);
                                   return true;
                                }
                                
                                // else2
                                // 같은 년도, 같은 달
                                else {

                                   var daygap = eday_day - now_day;
                                   
                                   // 같은 년도, 같은 달, 과거 일
                                   if(daygap < 0) {
                                      expired.push(el.tid);
                                      return true;
                                   }
                                   
                                   // else3
                                   // 같은 년도, 같은 달, 같은 일 혹은 미래 일
                                   else {
                                      
                                      if((today == 0) && (daygap <= 6)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else if((today == 1) && (daygap <= 5)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else if((today == 2) && (daygap <= 4)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else if((today == 3) && (daygap <= 3)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else if((today == 4) && (daygap <= 2)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else if((today == 5) && (daygap <= 1)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else if((today == 6) && (daygap == 0)){
                                         thisweek.push(el.tid);
                                         return true;
                                      } else {
                                         nextweek.push(el.tid);
                                         return true;
                                      } 
                                   
                                   } // end - else3
                                   
                                } // end - else2
                                
                             } // end - else1
                    	}
                        
                     });
                     
                 
                      var ctx = document.getElementById('projectinfo_DonutChart').getContext('2d');
                      var myDoughnutChart = new Chart(ctx, {
                          
                           type: 'doughnut',
                           data: {
                                   datasets: [{
                                       data: [noeday.length, nextweek.length, thisweek.length, completed.length, expired.length],
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
                            maintainAspectRatio: false,
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
                       
         } // end-success
      });// end-ajax
   
  
   
   /*
    날      짜 : 2018. 6. 15.
    기      능 : Step별 진행률 그래프 데이터 가져와서 세팅
    작성자명 : 김 정 권
    */
   $.ajax(
             {
                 type : "post",
                 url  : "getProgressData.htm",
                 data : "pid="+ pid,
                 success : function(rdata){
                     console.log(rdata);
                     
                     var labelnames = [];
                     $(rdata.steplist).each(function (index, el){
                        labelnames.push(el.sname);
                     });
                     
                     var completedtasks= [];
                     var uncompletedtasks= [];
                     $(rdata.tasklistbysteps).each(function (index, element){
                       
                        var completedcount = 0;
                        var uncompletedcount = 0;
                        
                        
                           $(element).each(function (index,el) {
                              if((el.tstatusid == 3)||(el.tstatusid == 11)||(el.tstatusid == 15)){
                                  completedcount++;
                               }else {
                                  uncompletedcount++;
                               }
                           });

                        completedtasks.push(completedcount);
                        uncompletedtasks.push(uncompletedcount);
                        
                     });                     
                     
                     
                     
                     var ctx = document.getElementById('projectinfo_progressbar').getContext('2d');
                     var stackedBar = new Chart(ctx, {
                        type: 'bar',
                         data: {
                            
                            labels: labelnames,
                           datasets: [{
                              label: '완료',
                              backgroundColor: '#3498db',
                              data: completedtasks
                           }, {
                              label: '미완료',
                              backgroundColor: '#2ecc71',
                              data: uncompletedtasks
                           }]
                         },              
                         options: {
                            maintainAspectRatio: false,
                             scales: {
                                 xAxes: [{
                                     stacked: true
                                 }],
                                 yAxes: [{
                                     stacked: true
                                 }]
                             }
                         }
                     });
                 } // end-success
              } 
            ); // end-ajax

   

   /*
   날      짜 : 2018. 6. 14.
   기      능 : Step별 Task 완료/미완료 현황에서 셀렉트바 온체인지 함수
   작성자명 : 김 정 권
   */
   $('#projectinfo_task_situation_table_selectbar').on('change', function() {

     $.ajax(
             {
                type : "post",
                url  : "getTasksByStepForSituation.htm",
                data : "sid="+this.value,
                success : function(rdata){
                    
                   var completedtasks = [];
                   var uncompletedtasks = [];
                   
                   var completedtasks_tid = [];
                   var uncompletedtasks_tid = [];
                   
                    $(rdata.tasklist).each(function(index, el){
                      
                       if((el.tstatusid == 3)||(el.tstatusid == 11)||(el.tstatusid == 15)){
                          completedtasks.push(el.tname);
                          completedtasks_tid.push(el.tid);

                        }else{
                           uncompletedtasks.push(el.tname);
                           uncompletedtasks_tid.push(el.tid);
                        }
                    });
                    
                    if(completedtasks.length > uncompletedtasks.length){
                       var c1 = completedtasks.length - uncompletedtasks.length
                       for(var i = 0; i < c1; i++){
                          uncompletedtasks.push(' ');
                       }
                    
                    } else if (completedtasks.length < uncompletedtasks.length){
                       var c2 = uncompletedtasks.length - completedtasks.length
                       for(var i = 0; i < c2; i++){
                          completedtasks.push(' ');
                       }
                    }

                    
                    // 위 로직에 의해 두 배열의 길이가 같아졌으므로
                    // 아무 배열이나 잡아서 length 만큼 돌려도 상관없음
                    var tablestr = '<tr><th>완료 task</th><th>미완료 task</th></tr>';
                    for(var i = 0; i < completedtasks.length; i++){
                   	 tablestr += '<tr><td class="Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t' 
                   		 + completedtasks_tid[i] + '">' + completedtasks[i] 
                   	 + '</td><td  class="Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t' 
                   	 + uncompletedtasks_tid[i] + '">' + uncompletedtasks[i] + '</td></tr>'
                    }
                    
//                    for(var i = 0; i < completedtasks.length; i++){
//                        tablestr += '<tr><td>' + completedtasks[i] + '</td><td>' + uncompletedtasks[i] + '</td></tr>'
//                     }
                    
                    

                    $('#task_progress_table').empty();
                    $('#task_progress_table').append(tablestr);
                    
                    } // end - success
                }
               );
           }).trigger("change");

   
}); // end-document.onready