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
                     
//                     for(var i = 0; i < completedtasks.length; i++){
//                         tablestr += '<tr><td>' + completedtasks[i] + '</td><td>' + uncompletedtasks[i] + '</td></tr>'
//                      }
                     
                     

                     $('#task_progress_table').empty();
                     $('#task_progress_table').append(tablestr);
                     
                     } // end - success
                 }
                );
            }).trigger("change");

   
  
   /*
    날      짜 : 2018. 6. 15.
    기      능 : 프로젝트 멤버 초대 모달에서 초대하기 누르면 멤버가 초대되고 redirect
    작성자명 : 김 정 권
    */
   // 동적 생성 태그에 대한 이벤트이므로
   // 일반적으로 위에서 써오던 함수와 형태가 다르다
   $(document).on("click","#pmember_add_btn",function(){

	  $('#add_project_member').click(); 
      var mid = $(this).children().attr("id");
      
      $.ajax(
              {
               type : "post",
               url:"insertMidToPmember.htm",
               data : {
	        	   'mid': mid
	           },
                 success : function(rdata){
                                
                	 $("#main-box").empty();
					 $("#main-box").append(rdata);
                	 
                     } // end - success
                 }
                );
   });
