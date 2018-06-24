
/**
 * 
 날      짜 : 2018. 6. 18.
 기      능 : Task 수정/ 삭제 페이지에 날짜 클릭으로 넣는 datepicker 넣는 코드
 작성자명 : 김 정 권
 */
$( ".date-im" ).datepicker({
    showOn: "button",
    buttonImage: "img/calendar.png",
    buttonImageOnly: true,
    dateFormat: 'yy-mm-dd'

});


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : task 수정/삭제 모달에 데이터 띄우기
 작성자명 : 김 정 권
 */
$(document).on("click",".Task_RUD_Modal",function(){
		
		var temptid = $(this).attr('id'); 
	    var tid = parseInt(temptid.substring(1));   
		
		$.ajax(
			       {
			           type : "post",
			           url  : "getTask.htm",
			           data : {
			        	   'tid': tid
			           },
			           success : function(rdata){
			        	   console.log('성공!')
			        	   console.log(rdata);

			        	   //tid
			        	   var tid = rdata.task.tid;
			        	   $('#tidhidden').attr('value', tid);
			        	   
			        	   // tname
			        	   $('#Task_Modal_tname').empty();
			        	   $('#tnamehidden').attr('value', rdata.task.tname);
			        	   $('#Task_Modal_tname').append(rdata.task.tname);

			        	   
			        	   // star
			        	   $('#span_task_star').empty();

			        	   var star_tag_class = 'far fa-star';
			        	   $(rdata.starredtasklist).each(function (){
			        		   
			        		   if(this.tid == tid){
			        			   star_tag_class = 'fas fa-star';
			        			   return false;
			        		   }
			        	   });
			        	   			        	   
			        	   $('#span_task_star').append('<i class="' + star_tag_class + '" id="task_star"></i>');
			        	   
			        	   
			        	   // step name
			        	   $('#Task_Modal_snames').empty();
			        	   
			        	   var snames = '<br>'
			        	   $(rdata.steps).each(function(){
			        		   snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + this.sname + '&nbsp&nbsp' + '<i class="fas fa-times task_page_delete_step_btn" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
			        	   });
			        	   snames += '<i data-toggle="modal" data-target="#step_add_modal_in_taskmodal" id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
			        	   $('#Task_Modal_snames').append(snames);

			        	   
			        	   // tstatus
			        	   $('#Task_Modal_tstatus_selectbox').empty();
			        	   
			        	   var selected_tstatusid = rdata.task.tstatusid;
			        	   var tstatusoptions = '';
			        	   
			        	   $(rdata.tstatuslist).each(function(){
			        		   
			        		   if(selected_tstatusid == this.tstatusid){
				        		   tstatusoptions += '<option value="' + this.tstatusid + '" selected="selected" style='
				        		   tstatusoptions += '"color: ' + this.color + '">'
				        		   tstatusoptions += this.tstatus
				        		   tstatusoptions += '</option>'
			        		   }else {
				        		   tstatusoptions += '<option value="' + this.tstatusid + '" style='
				        		   tstatusoptions += '"color: ' + this.color + '">'
				        		   tstatusoptions += this.tstatus
				        		   tstatusoptions += '</option>'
			        		   }
				           });
			        	   $('#Task_Modal_tstatus_selectbox').append(tstatusoptions);
			        	   

			        	   // assignee
			        	   $('#Task_Modal_assignee').empty();

			        	   var assigneestr = '';
			        	   var profile_count = 0;
			        	   $(rdata.sametaskmemberlist).each(function(){
			        		   
			        		   if(this == null){
			        			   assigneestr += '<span>해당 Task의 담당자가 존재하지 않습니다</span>&nbsp&nbsp&nbsp';
			        			   assigneestr += '<i data-toggle="modal" data-target="#assignee_add_modal_in_taskmodal" id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
			        			   return false;
			        		   }
			        		   
			        		   if((profile_count%4 == 0)&&(profile_count != 0)) {
			        			   assigneestr += '<br><br>'
			        		   }
			        		   
			        		   assigneestr += '<span>'
//			        	       assigneestr = '<img src="img/'+ this.image + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
			        	       assigneestr += '<img src="img/'+ '프로필사진테스트.jpg' + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
			        	       assigneestr += '&nbsp<span style="background-color:#f0f0f0; margin-right: 5px">' + this.mname + '&nbsp&nbsp';
			        	       assigneestr += '<i class="fas fa-times task_page_delete_assignee_btn" style="color:#808B96; cursor:pointer" id="' + this.mid + '"></i></span>';
			        	       assigneestr += '</span>'
			        	       assigneestr += '&nbsp&nbsp&nbsp'
			        	       profile_count ++;
			        	   });
			        	   	   assigneestr += '<i data-toggle="modal" data-target="#assignee_add_modal_in_taskmodal" id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
			        	   
			        	   $('#Task_Modal_assignee').append(assigneestr);
			        	   
			        	   
			        	   
//			        	   $('#').empty();
//			        	   $('#').append(rdata.task.tname);
//			        	   
//			        	   $('#').empty();
//			        	   $('#').append(rdata.task.tname);
			        	   
			        	   
			           } // end-success
			        } 
			      ); // end-ajax
});

/**
 * 
 날   짜 : 2018. 6. 20.
 기   능 : task 즐겨찾기
 작성자명 : 김 정 권
 */
$(document).on("click","#task_star",function(){
	
	var tid = $('#tidhidden').attr('value');
	var starAddOrDel = 0;
	var star_class = $('#task_star').attr('class');
	
	// if 빈 별(현재는 즐겨찾기가 되어있지 않음)
	if(star_class == 'far fa-star'){
		starAddOrDel = 1;
	}
	
	// if 차있는 별(이미 즐겨찾기 한 일)
	else {
		starAddOrDel = 0;
	}
	
	$.ajax(
		       {
		           type : "post",
		           url  : "addordeletestar.htm",
		           data : {
		        	   'tid': tid,
		        	   'starAddOrDel': starAddOrDel
		           },
		           success : function(rdata){

		        	   console.log(rdata);
		        	   
		        	   if(rdata.result == 'added'){
		        		  
		        		   // 클릭시 별을 채워준다
		        		   $('#task_star').attr('class','fas fa-star');
		        		   
		        	   }else {
		        		 
		        			// 클릭시 별을 비워준다
		        			$('#task_star').attr('class','far fa-star');
		        	   }
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 20.
 기      능 : Task 삭제 버튼
 작성자명 : 김 정 권
 */
$(document).on("click","#task_trash_btn",function(){
	
	var tid = $('#tidhidden').attr('value');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "deletetask.htm",
		           data : {
		        	   'tid': tid,
		           },
		           success : function(rdata){
		        	   
	        		     $('#taskinstep_delete_dismiss_btn').click();
		        	     $('#task_delete_dismiss_btn').click();
		        	     $('#task_dismiss_btn').click();
		        	     
	                	 $("#main-box").empty();
						 $("#main-box").append(rdata);
	                	 
		           } // end-success
		        }); // end-ajax
});



/**
 * 
 날      짜 : 2018. 6. 21.
 기      능 : Task 페이지 내 step 삭제 버튼 클릭시 작동
 작성자명 : 김 정 권
 */
$(document).on("click",".task_page_delete_step_btn",function(){

	var tid = $('#tidhidden').attr('value');
	var sid = $(this).attr('id');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "counttaskinstep.htm",
		           data : {
		        	   'tid': tid,
		           },
		           success : function(rdata){
		        	   
		        	   var count = rdata.countresult;
		        	   console.log('count는 : ' + count)
		        	   
		        	   $.ajax(
		        		       {
		        		           type : "post",
		        		           url  : "deletestepintaskmodal.htm",
		        		           data : {
		        		        	   'tid': tid,
		        		        	   'sid': sid
		        		           },
		        		           success : function(rdata){
		        		        	   
		        		        	   console.log(rdata);

		        		        	   if(count == 1) {
		        		        		   $('#stepdeletehidden').click();
		        		        		   
		        		        	   } else {
		        		        		   
									// step name
									   $('#Task_Modal_snames').empty();
									   
									   var snames = '<br>'
									   $(rdata.steplist_after_delete_step).each(function(){
										   var sname = this.sname;
										       snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + sname + '&nbsp&nbsp' + '<i class="far fas fa-times task_page_delete_step_btn" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
									   });
									   snames += '<i data-toggle="modal" data-target="#step_add_modal_in_taskmodal" id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
									   $('#Task_Modal_snames').append(snames);
												        		        	   
		        		        	   }
		        		        	   
		        		           } // end-success
		        		        }); // end-ajax
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 22.
 기      능 : Task 모달 창에서 Task 상태 변경
 작성자명 : 김 정 권
 */
$('#Task_Modal_tstatus_selectbox').on('change', function() {

	var tid = $('#tidhidden').attr('value');
	var value = $('#Task_Modal_tstatus_selectbox').val();
	var tname = $('#tnamehidden').attr('value');
	
	  $.ajax(
		       {
		           type : "post",
		           url  : "changetstatus.htm",
		           data : {
		        	   'tid': tid,
		        	   'value' : value,
		        	   'tname' : tname
		           },
		           success : function(rdata){
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 21.
 기      능 : Task 페이지 내 step 추가 버튼
 작성자명 : 김 정 권
 */
$(document).on("click","#task_modal_add_step",function(){
	
	var tid = $('#tidhidden').attr('value');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "getStepListByTid.htm",
		           data : {
		        	   'tid': tid,
		           },
		           success : function(rdata){
		        	   
		        	   console.log(rdata.steplist);
		        	   $('#steplist_in_taskmodal').empty();
		        	   
		        	   var tablestr = '';
		        	   tablestr += '<tr><th>스텝이름</th><th>시작일</th><th>종료일</th><th>추가</th></tr>';
		        	   $(rdata.steplist).each(function(){
		        		   
		        		  var sday = '';
		        		  var eday = '';
		        		   
		        		  if(this.sday == null){
		        			  sday = '';
		        		  }

		        		  if(this.eday == null){
		        			  eday = '';
		        		  }
		        		  
		        		  if(this.sday != null){
		        			  sday = this.sday.substring(0, 10);
		        		  }
		        		  
		        		  if(this.eday != null){
		        			  eday = this.eday.substring(0, 10);
		        		  }
		        		  
		        		  
		        		   
		        		  tablestr += '<tr><th>' + this.sname + '</th><th>' + sday + '</th><th>' + eday + '</th><th><i class="fas fa-plus-circle addbtn_taskinstep" id="' + this.sid + '" style="cursor:pointer"></i></th></tr>';
		        	   });
		        	   $('#steplist_in_taskmodal').append(tablestr);
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 23.
 기      능 : 테스크 모달 내에서 스텝 추가 버튼을 클릭시에 뜬 이중모달 내에서 추가 버튼을 누르면 작동
 작성자명 : 김 정 권
 */
$(document).on("click",".addbtn_taskinstep",function(){
	
	var sid = $(this).attr('id');
	var tid = $('#tidhidden').attr('value');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "addTaskInStepInTaskModal.htm",
		           data : {
		        	   'sid': sid,
		        	   'tid': tid
		           },
		           success : function(rdata){
		        	   
		        	   console.log(rdata.result);
		        	   $('#step_add_modal_in_taskmodal_dismiss').click();
		        	   
		        	   $.ajax(
		        		       {
		        		           type : "post",
		        		           url  : "addTaskInStepInTaskModal_2.htm",
		        		           data : {
		        		        	   'tid': tid
		        		           },
		        		           success : function(rdata){

		        		        	console.log('아래에서 확인')
		        		        	console.log(rdata.steplist);
		        		        	   
		        		        	// step name
		        		        	$('#Task_Modal_snames').empty();

		        		        	var snames = '<br>'
		        		        	$(rdata.steplist).each(function(){
		        		        	snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + this.sname + '&nbsp&nbsp' + '<i class="fas fa-times task_page_delete_step_btn" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
		        		        	});
		        		        	snames += '<i data-toggle="modal" data-target="#step_add_modal_in_taskmodal" id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
		        		        	$('#Task_Modal_snames').append(snames);
		        		        	   
		        		           } // end-success
		        		        }); // end-ajax
		        	   
		           } // end-success
		        }); // end-ajax
});


/**
 * 
 날      짜 : 2018. 6. 24.
 기      능 : 테스크 모달 내에서 각 프로필 사진 옆에 x 버튼을 누르면 해당 담당자가 삭제되고 다시 정보 불러와서 append
 작성자명 : 김 정 권
 */
$(document).on("click",".task_page_delete_assignee_btn",function(){
	
	var mid = $(this).attr('id');
	var tid = $('#tidhidden').attr('value');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "deleteassignee.htm",
		           data : {
		        	   'mid': mid,
		        	   'tid': tid
		           },
		           success : function(rdata){
		        	   
		        	   console.log(rdata);
		        	   
		        	   $('#Task_Modal_assignee').empty();

		        	   var assigneestr = '';
		        	   var profile_count = 0;
		        	   $(rdata.sametaskmemberlist).each(function(){
		        		   
		        		   if(this == null){
		        			   assigneestr += '<span>해당 Task의 담당자가 존재하지 않습니다</span>&nbsp&nbsp&nbsp';
		        			   assigneestr += '<i data-toggle="modal" data-target="#assignee_add_modal_in_taskmodal" id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
		        			   return false;
		        		   }
		        		   
		        		   if((profile_count%4 == 0)&&(profile_count != 0)) {
		        			   assigneestr += '<br><br>'
		        		   }
		        		   
		        		   assigneestr += '<span>'
//		        	       assigneestr = '<img src="img/'+ this.image + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
		        	       assigneestr += '<img src="img/'+ '프로필사진테스트.jpg' + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
		        	       assigneestr += '&nbsp<span style="background-color:#f0f0f0; margin-right: 5px">' + this.mname + '&nbsp&nbsp';
		        	       assigneestr += '<i class="fas fa-times task_page_delete_assignee_btn" style="color:#808B96; cursor:pointer" id="' + this.mid + '"></i></span>';
		        	       assigneestr += '</span>'
		        	       assigneestr += '&nbsp&nbsp&nbsp'
		        	       profile_count ++;
		        	   });
		        	   	   assigneestr += '<i data-toggle="modal" data-target="#assignee_add_modal_in_taskmodal" id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
		        	   
		        	   $('#Task_Modal_assignee').append(assigneestr);
		        	   
		           } // end-success
		        }); // end-ajax
	
	
});


/**
 * 
 날      짜 : 2018. 6. 24.
 기      능 : 테스크 모달 내에서 담당자를 추가하는 플러스 버튼으로 이걸 누르면 데이터를 가져와서 이중 모달에 채워준다
 작성자명 : 김 정 권
 */
$(document).on("click","#task_modal_add_assignee",function(){
	
	$.ajax(
		       {
		           type : "post",
		           url  : "addTaskInStepInTaskModal.htm",
		           data : {
		        	   'sid': sid,
		        	   'tid': tid
		           },
		           success : function(rdata){
		        	   
		        	   console.log(rdata.result);
		        	   $('#step_add_modal_in_taskmodal_dismiss').click();
		        	 
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 24.
 기      능 : 업무 담당자 추가 이중 모달 내에서 추가 버튼을 누르면 실행되는 것으로 DB 변화를 하고 이중 모달을 닫은 뒤
  			  업무 담당자를 다시 empty-> append 해준다 (이중 ajax 필요한 로직이다)
  			  업무 담당자가 추가되면 그 후 incoming과 stream에 insert가 발생하여야 한다
 작성자명 : 김 정 권
 */
//$(document).on("click",".",function(){

/*
$.ajax(
	       {
	           type : "post",
	           url  : "addTaskInStepInTaskModal.htm",
	           data : {
	        	   'sid': sid,
	        	   'tid': tid
	           },
	           success : function(rdata){
	        	   
	        	   console.log(rdata.result);
	        	   $('#step_add_modal_in_taskmodal_dismiss').click();
	        	   
	        	   $.ajax(
	        		       {
	        		           type : "post",
	        		           url  : "addTaskInStepInTaskModal_2.htm",
	        		           data : {
	        		        	   'tid': tid
	        		           },
	        		           success : function(rdata){

	        		        	console.log(rdata)
	        		        	
	        		        	*************************************************************************
	        		        	*************************************************************************
	        		        	업무 담당자가 추가되면 그 후 incoming과 stream에 insert가 발생하여야 한다
    							*************************************************************************
								*************************************************************************
	        		        	
	        		           } // end-success
	        		        }); // end-ajax
	        	   
	           } // end-success
	        }); // end-ajax
*/

//});

