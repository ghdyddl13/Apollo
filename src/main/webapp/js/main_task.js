



/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : task 수정/삭제 모달에 데이터 띄우기
 작성자명 : 김 정 권
 */
$(document).on("click",".Task_RUD_Modal",function(){
		console.log("a")
		var temptid = $(this).attr('id'); 
		var tid =parseInt(temptid.substring(1));
		
		if(temptid.substring(0,6) =="srch-t"){
			tid= temptid.substring(6);
			console.log(tid);
		}
		
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
			        	   // tid
			        	   var tid = rdata.task.tid;
			        	   $('#tidhidden').attr('value', tid);
			        	   $('#tidhidden2').attr('value', tid);
			        	   
			        	   // pid
			        	   var pid = rdata.task.pid;
			        	   $('#pidhidden').attr('value', pid);
			        	   
			        	   // user-mid
			        	   var usermid = rdata.userid;
			        	   $('#usermidhidden').attr('value', usermid);
			        	   $('#usermidhidden2').attr('value', usermid);
			        	   
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
			        	   snames += '<i id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
			        	   $('#Task_Modal_snames').append(snames);
			        	   ///////////////////////////////////// no redirect
			        	   $('#Task_Modal_snames2').empty();
			        	   
			        	   var snames = '<br>'
			        	   $(rdata.steps).each(function(){
			        		   snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + this.sname + '&nbsp&nbsp' + '<i class="fas fa-times task_page_delete_step_btn2" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
			        	   });
			        	   snames += '<i id="task_modal_add_step_noredirect" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
			        	   $('#Task_Modal_snames2').append(snames);

			        	   
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
			        	   ////////////////////////////////////////////////////////// no redirect
			        	   $('#Task_Modal_tstatus_selectbox_noredirect').empty();
			        	   
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
			        	   $('#Task_Modal_tstatus_selectbox_noredirect').append(tstatusoptions);
			        	   //////////////////////////////////////////////////////////

			        	   // assignee
			        	   $('#Task_Modal_assignee').empty();

			        	   var assigneestr = '';
			        	   var profile_count = 0;
			        	   
			        	   var count_sametaskmemberlist = 0;
			        	   $(rdata.sametaskmemberlist).each(function(){
			        		   count_sametaskmemberlist++;
			        	   });
			        	   
		        		   if(count_sametaskmemberlist == 0){
		        			   assigneestr += '<span>해당 Task의 담당자가 존재하지 않습니다</span>&nbsp&nbsp&nbsp';
		        		   } else{
				        	   $(rdata.sametaskmemberlist).each(function(){
				        		   
				        		   if((profile_count%4 == 0)&&(profile_count != 0)) {
				        			   assigneestr += '<br><br>'
				        		   }
				        		   assigneestr += '<span>'
				        	       assigneestr += '<img src="displayImage.htm?image=' + this.image + '" class="taskmodal_memberprofile"/>';
				        	       assigneestr += '&nbsp<span style="background-color:#f0f0f0; margin-right: 5px">' + this.mname + '&nbsp&nbsp';
				        	       assigneestr += '<i class="fas fa-times task_page_delete_assignee_btn" style="color:#808B96; cursor:pointer" id="' + this.mid + '"></i></span>';
				        	       assigneestr += '</span>'
				        	       assigneestr += '&nbsp&nbsp&nbsp'
				        	       profile_count ++;
				        	   });
		        		   } // end - else
			        	   
			        	   assigneestr += '<i id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
			        	   
			        	   $('#Task_Modal_assignee').append(assigneestr);
			        	   
			        	   // sday
		        	   	   var sday = rdata.task.sday;
		        	   	   var newsday = '';
		        	   	   
		        	   	   if(sday == undefined){

		        	   	   }else{
		        	   		   newsday = sday.substring(0,10);
		        	   	   }
		        	   	   
			        	   $('#Task_Modal_sday').empty();
			        	   $('#Task_Modal_sday').val(newsday);
			        	   ///////////////////////// no redirect
			        	   $('#Task_Modal_sday_noredirect').empty();
			        	   $('#Task_Modal_sday_noredirect').val(newsday);
			        	   
			        	   // eday
			        	   var eday = rdata.task.eday;
		        	   	   var neweday = '';
		        	   	   
		        	   	   if(eday == undefined){

		        	   	   }else{
		        	   		   neweday = eday.substring(0,10);
		        	   	   }
		        	   	   
			        	   $('#Task_Modal_eday').empty();
			        	   $('#Task_Modal_eday').val(neweday);
			        	   ///////////////////////// no redirect
			        	   $('#Task_Modal_eday_noredirect').empty();
			        	   $('#Task_Modal_eday_noredirect').val(neweday);

			        	   // subtask
			        	   $('#Task_Modal_subtasks').empty();
	   		        	   var div_str = '';
	   		        	   $(rdata.subtasklist).each(function(){
	   		        		   
	   		        		   var ischecked_class = '';
	   		        		   if(this.ischecked == 1){
	   		        			ischecked_class = 'subtask_name subtask_checked subtask_span';
	   		        		   }else{
			        			ischecked_class = 'subtask_name subtask_unchecked subtask_span';
	   		        		   }
	   		        		   
	   		        		div_str += '<div class="hover_div"><span class="' + ischecked_class + '">' + this.subtask + '</span>';
	   		        		div_str += '<i id="' + this.subtaskid + '" class="fas fa-times subtask_del_btn" style="cursor:pointer"></i></div>';
	   		        		   
	   		        	   });
	   		        	   $('#Task_Modal_subtasks').append(div_str);
			        	   
			        	   
	   		        	   
	   		        	   // detail
			        	   $('#Task_Modal_detail').val("");
			        	   $('#Task_Modal_detail').val(rdata.task.detail);
			        	   
			        	   
			        	   // comment
			        	   $('#task_detail_status').attr('src','img/loader.gif');
			        	   $('#task_detail_status').css('visibility','hidden');
			        	   
			        	   getCommentAndMemberlist();
			        	   $('#div_for_comment_input_box').empty();
			        	   var origin_inputboxstr = '<input id="comment_input_box_in_taskmodal" type="text" placeholder="코멘트를 입력 후 Enter..">'
			        	   $('#div_for_comment_input_box').append(origin_inputboxstr);
			        	   //////////////////////////////////////no redirect
			        	   $('#div_for_comment_input_box2').empty();
			        	   var origin_inputboxstr = '<input id="comment_input_box_in_taskmodal_noredirect" type="text" placeholder="코멘트를 입력 후 Enter..">'
			        	   $('#div_for_comment_input_box2').append(origin_inputboxstr);
			        	   
			        	   // files
			        	   $('#Task_Modal_files').empty();
			        	   var filesdivs = '';
			        	   $(rdata.filelist).each(function(){
			        		   let strArray = this.filename.split('/');
				        	   var shortfilename = strArray[2].substring(37);
				        	   filesdivs += '<div class="filehover_div">' + '<a class="file_name" href="taskFileDownload.htm?filename='+this.filename+'" download>' + shortfilename + '</a>';
				        	   filesdivs += '<i id="' + this.filename + '" class="fas fa-times file_del_btn" style="cursor:pointer"></i>';
				        	   filesdivs += '</div>'
			        		  
			        	   });
			        	   $('#Task_Modal_files').append(filesdivs);
			        	   
			        	   $(".starred-secondbody-image").hide();
			        		$(".modal-content2").show();	
			        	   
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
		$.ajax({
		type : "post",
		url : "addordeletestar.htm",
		data : {
			'tid' : tid,
			'starAddOrDel' : starAddOrDel
		},
		success : function(rdata) {

			console.log(rdata);

			if (rdata.result == 'added') {

				// 클릭시 별을 채워준다
				$('#task_star').attr('class', 'fas fa-star');

			} else {

				// 클릭시 별을 비워준다
				$('#task_star').attr('class', 'far fa-star');
			}

		} // end-success
	}); // end-ajax
	
});


/**
 * 
 * 날 짜 : 2018. 6. 20. 기 능 : Task 삭제 버튼 작성자명 : 김 정 권
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
						 checkCrtPage();
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
		        	   if(count == 1) {
		        		   
		        		   var step_delete_popup_div_str = '';
		        		   step_delete_popup_div_str += '<div>';
		        		   step_delete_popup_div_str +='<div>해당 Task가 속한 마지막 Step 입니다</div>';
		        		   step_delete_popup_div_str +='<div>Step 삭제시 Task가 삭제됩니다</div>';
		        		   step_delete_popup_div_str +='<div><input type="button" value="삭제하기" id="step_delete_button"></div>';
		        		   step_delete_popup_div_str +='</div>';
		        		   
		        		   $('#step_delete_popup_div').empty();
		        		   $('#step_delete_popup_div').append(step_delete_popup_div_str);
		        		   
			               let position = $('.task_page_delete_step_btn').position();
			               $('#step_delete_popup_div').css("left",position.left + 14);
			               $('#step_delete_popup_div').css("top",position.top + 10);
			               $('#step_delete_popup_div').css("display","block");
		        		   
		        		   return;
		        	   } 
		        	   
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
		        		        	   
									// step name
									   $('#Task_Modal_snames').empty();
									   
									   var snames = '<br>'
									   $(rdata.steplist_after_delete_step).each(function(){
										   var sname = this.sname;
										       snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + sname + '&nbsp&nbsp' + '<i class="far fas fa-times task_page_delete_step_btn" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
									   });
									   snames += '<i id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
									   $('#Task_Modal_snames').append(snames);
		        		        	   
		        		           } // end-success
		        		        }); // end-ajax
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 29.
 기      능 : 마지막으로 step이 하나 남은 것을 삭제할 때 실행 함수
 작성자명 : 김 정 권
 */
$(document).on("click","#step_delete_button",function(){

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
						 checkCrtPage();
	                	 
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

		        	   var step_names_popup_div_str = '';
		        	   
	        		   var countlength = Object.keys(rdata.steplist).length;
	        		   if(countlength == 0){
	        			   step_names_popup_div_str += '<div class="wrapper_comment popup_nothing">';
	        			   step_names_popup_div_str += '<div class="each_comment">';	
	        			   step_names_popup_div_str += '<div class="first_row">추가할 스텝이 없습니다</div>';
	        			   step_names_popup_div_str += '</div></div>';
	        				   
	        		   } else {
		        	   $(rdata.steplist).each(function(){
		        			   var sday = '';
				        	   var eday = '';
				        	   var emptyday = '';
				        		   
				        		  if(this.sday == null){
				        			  sday = emptyday;
				        		  }

				        		  if(this.eday == null){
				        			  eday = emptyday;
				        		  }
				        		  
				        		  if(this.sday != null){
				        			  sday = this.sday.substring(0, 10);
				        		  }
				        		  
				        		  if(this.eday != null){
				        			  eday = this.eday.substring(0, 10);
				        		  }
			        		   
			        		  step_names_popup_div_str += '<div class="wrapper_comment popup_sid" id="' + this.sid + '">';	 
			        		  step_names_popup_div_str += '<div class="each_comment">';	
			        		  step_names_popup_div_str += '<div class="first_row">' + this.sname + '</div>';
			        		  
			        		  if((sday == emptyday) && (eday == emptyday)){
			        			  step_names_popup_div_str += '<div class="second_row">(시작일과 종료일 모두 미정)</div>'
			        		  } else if((sday != emptyday) && (eday == emptyday)){
			        			  step_names_popup_div_str += '<div class="second_row">' + sday + '&nbsp~' + '</div>'
			        		  }  else if((sday == emptyday) && (eday != emptyday)){
			        			  step_names_popup_div_str += '<div class="second_row">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp~&nbsp' + eday + '</div>'
			        		  } else{
			        			  step_names_popup_div_str += '<div class="second_row">' + sday + '&nbsp~&nbsp' + eday + '</div>';	
			        		  }
			        		  
			        		  step_names_popup_div_str += '</div></div>';
		        		 
		        	   		});
	        		   }// end - else
		        	   
		        	      $('#step_names_popup_div').empty();
		        	      $('#step_names_popup_div').append(step_names_popup_div_str);
		        	      let popupdiv_width = $('#step_names_popup_div').width();
		                  let popupdiv_height = $('#step_names_popup_div').height();
		                  
		                  let position = $('#task_modal_add_step').position();
		                  $('#step_names_popup_div').css("left",position.left + 14);
		                  $('#step_names_popup_div').css("top",position.top + 50);
		                  $('#step_names_popup_div').css("display","block");
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 29.
 기      능 : 스텝 추가 pop up div 외 영역을 클릭시 없애버리는 기능 
 작성자명 : 김 정 권
 */
$(document).bind("mousedown", function(e) {
    // If the clicked element is not the #step_names_popup_div or #assignee_popup_div
    if (!$(e.target).parents("#step_names_popup_div").length > 0){
    // Hide it
      $("#step_names_popup_div").css("background-color","#FFFFFF");
      $("#step_names_popup_div").css({"display":"none","left":"-20000px","top":"-20000px"});
    
  }

    if (!$(e.target).parents("#assignee_popup_div").length > 0){
    	
    	$("#assignee_popup_div").css("background-color","#FFFFFF");
    	$("#assignee_popup_div").css({"display":"none","left":"-20000px","top":"-20000px"});
    }
    
    if (!$(e.target).parents("#step_delete_popup_div").length > 0){
    	
    	$("#step_delete_popup_div").css("background-color","#FFFFFF");
    	$("#step_delete_popup_div").css({"display":"none","left":"-20000px","top":"-20000px"});
    }
    
    if (!$(e.target).parents("#step_names_popup_div2").length > 0){
        // Hide it
          $("#step_names_popup_div2").css("background-color","#FFFFFF");
          $("#step_names_popup_div2").css({"display":"none","left":"-20000px","top":"-20000px"});
        
      }
    
    if (!$(e.target).parents("#step_delete_popup_div2").length > 0){
    	
    	$("#step_delete_popup_div2").css("background-color","#FFFFFF");
    	$("#step_delete_popup_div2").css({"display":"none","left":"-20000px","top":"-20000px"});
    }
    
});


/**
 * 
 날      짜 : 2018. 6. 23.
 기      능 : popup div 에서 스텝 누르면 추가됨
 작성자명 : 김 정 권
 */
$(document).on("click",".popup_sid",function(){
	
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
		        	   $("#step_names_popup_div").css("background-color","#FFFFFF");
		        	   $("#step_names_popup_div").css({"display":"none","left":"-20000px","top":"-20000px"});
		        	 
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
		        		        	snames += '<i id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
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
		        	       assigneestr += '<img src="displayImage.htm?image=' + this.image + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
		        	       assigneestr += '&nbsp<span style="background-color:#f0f0f0; margin-right: 5px">' + this.mname + '&nbsp&nbsp';
		        	       assigneestr += '<i class="fas fa-times task_page_delete_assignee_btn" style="color:#808B96; cursor:pointer" id="' + this.mid + '"></i></span>';
		        	       assigneestr += '</span>'
		        	       assigneestr += '&nbsp&nbsp&nbsp'
		        	       profile_count ++;
		        	   });
		        	   	   assigneestr += '<i id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
		        	   
		        	   $('#Task_Modal_assignee').append(assigneestr);
		        	   
		           } // end-success
		        }); // end-ajax
	
	
});


/**
 * 
 날      짜 : 2018. 6. 24.
 기      능 : 테스크 모달 내에서 담당자를 추가하는 플러스 버튼으로 이걸 누르면 데이터를 가져와서 팝업디브 형성
 작성자명 : 김 정 권
 */
$(document).on("click","#task_modal_add_assignee",function(){
	
	var tid = $('#tidhidden').attr('value');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "addtaskassigneemodalinfo.htm",
		           data : {
		        	    'tid': tid
		           },
		           success : function(rdata){
		        	   
		        	   var assignee_popup_div_str = '';
		        	   var countlength = Object.keys(rdata.getSameProjectButNotSameTaskMemberList).length;
	        		   if(countlength == 0){
	        			   assignee_popup_div_str += '<div class="wrapper_comment popup_nothing">';
	        			   assignee_popup_div_str += '<div class="each_comment">';	
	        			   assignee_popup_div_str += '<div class="first_row">잔여 프로젝트 인원이 없습니다</div>';
	        			   assignee_popup_div_str += '</div></div>';
	        				   
	        		   } else {
	        			   
	        			   $(rdata.getSameProjectButNotSameTaskMemberList).each(function(){
	        					 
	        			  assignee_popup_div_str += '<div class="wrapper_comment popup_member" id="' + this.mid + '">';	 
	        			  assignee_popup_div_str += '<img class ="taskmodal_memberprofile2" src="displayImage.htm?image=' + this.image + '"/>';	
	        			  assignee_popup_div_str += '<div class="each_comment">';	
	        			  assignee_popup_div_str += '<div class="first_row">' + this.mname + '</div>';	
	        			  assignee_popup_div_str += '<div class="second_row">' + this.mid + '</div>';	
	        			  assignee_popup_div_str += '</div></div>';
	        				   
			        	   });
			        	   
	        		   }

	        	   	   $('#assignee_popup_div').empty();
		        	   $('#assignee_popup_div').append(assignee_popup_div_str);
		        	   
		               let position = $('#task_modal_add_assignee').position();
		               $('#assignee_popup_div').css("left",position.left + 14);
		               $('#assignee_popup_div').css("top",position.top + 10);
		               $('#assignee_popup_div').css("display","block");
		     
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 24.
 기      능 : 업무 담당자 추가 팝업디브 내에서 사람을 누르면 실행되는 것으로 DB 변화를 하고 팝업디브를 닫은 뒤
  			  업무 담당자를 다시 empty-> append 해준다 (이중 ajax 필요한 로직이다)
 작성자명 : 김 정 권
 */
$(document).on("click",".popup_member",function(){

	
	
	var mid = $(this).attr('id'); // 추가할 대상자
	var tid = $('#tidhidden').attr('value');
	var tname = $('#tnamehidden').attr('value');
$.ajax(
	       {
	           type : "post",
	           url  : "addassigneeintaskmodal.htm",
	           data : {
	        	   'mid': mid,
	        	   'tid': tid,
	        	   'tname' : tname
	           },
	           success : function(rdata){
	        	   $.when(reappendassignee(tid)).done(function(data){
	        		   send("assign");        //웹 소켓 send 함수 추가
	        		   event.stopPropagation();
	        	   });

	        	   console.log(rdata.result);
	        	   $("#assignee_popup_div").css("background-color","#FFFFFF");
	        	   $("#assignee_popup_div").css({"display":"none","left":"-20000px","top":"-20000px"});
	        	   
	        	   // 갱신된 assignee들 append

	        	   
	           } // end-success
	           
	           
	        }); // end-ajax

});

function reappendassignee(tid){
	var ajax =$.ajax(
	       {
	           type : "post",
	           url  : "reappendassignee.htm",
	           data : {
	        	   'tid': tid,
	           },
	           success : function(rdata){
	        
	        	console.log(rdata);
      	   $('#Task_Modal_assignee').empty();

      	   var assigneestr = '';
      	   var profile_count = 0;
      	   $(rdata.sametaskmemberlist).each(function(){
      		   
      		   if(this == null){
      			   assigneestr += '<span>해당 Task의 담당자가 존재하지 않습니다</span>&nbsp&nbsp&nbsp';
      			   assigneestr += '<i id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
      			   return false;
      		   }
      		   
      		   if((profile_count%4 == 0)&&(profile_count != 0)) {
      			   assigneestr += '<br><br>'
      		   }
      		   
      		   assigneestr += '<span>'
//      	       assigneestr = '<img src="img/'+ this.image + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
      	       assigneestr += '<img src="displayImage.htm?image=' + this.image + '" id="' + this.mid + '" class="taskmodal_memberprofile"/>';
      	       assigneestr += '&nbsp<span style="background-color:#f0f0f0; margin-right: 5px">' + this.mname + '&nbsp&nbsp';
      	       assigneestr += '<i class="fas fa-times task_page_delete_assignee_btn" style="color:#808B96; cursor:pointer" id="' + this.mid + '"></i></span>';
      	       assigneestr += '</span>'
      	       assigneestr += '&nbsp&nbsp&nbsp'
      	       profile_count ++;
      	   });
      	   	   assigneestr += '<i id="task_modal_add_assignee" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
      	   
      	   $('#Task_Modal_assignee').append(assigneestr);
	        	   
	           // comment
	           getCommentAndMemberlist();
	           } // end-success
	        }); // end-ajax
	return ajax;
}


/**
 * 
 날      짜 : 2018. 6. 22.
 기      능 : Task 모달 창에서 Task 상태 변경
 작성자명 : 김 정 권
 */

$(document).on("change","#Task_Modal_tstatus_selectbox",function(){
	
	var tid = $('#tidhidden').attr('value');
	var value = $('#Task_Modal_tstatus_selectbox').val();
	var tname = $('#tnamehidden').attr('value');
	
	console.log('/'+ tid +'/' + value +'/' + tname + '/')
	
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
		        	   
			           // comment
			           getCommentAndMemberlist();
			           
		        	   
		        	  $('#task_dismiss_btn').click();
		        	     
	                  $("#main-box").empty();
					  $("#main-box").append(rdata);
					  checkCrtPage()

		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : subtask 입력에서 sub task 명을 입력하고 엔터를 치면 작동
 작성자명 : 김 정 권
 */
$(document).on("keyup","#add_sub_task",function(){
	 event.preventDefault();
	 var tid = $('#tidhidden').attr('value');
	
	 
     if (event.keyCode === 13) {
    	 var subtaskstr = $('#add_sub_task').val();
    	  $.ajax(
   		       {
   		           type : "post",
   		           url  : "addsubtask.htm",
   		           data : {
   		        	   'tid': tid,
   		        	   'subtaskstr' : subtaskstr
   		           },
   		           success : function(rdata){
   		        	   $('#add_sub_task').val('');
   		        	   console.log('success 성공 테스트 출력 : ' + rdata.result);
   		        	   
		        	   // subtask
		        	   $('#Task_Modal_subtasks').empty();
   		        	   var div_str = '';
   		        	   $(rdata.subtasklist).each(function(){
   		        		   
   		        		   var ischecked_class = '';
   		        		   if(this.ischecked == 1){
   		        			ischecked_class = 'subtask_name subtask_checked subtask_span';
   		        		   }else{
		        			ischecked_class = 'subtask_name subtask_unchecked subtask_span';
   		        		   }
   		        		   
   		        		div_str += '<div class="hover_div"><span class="' + ischecked_class + '">' + this.subtask + '</span>';
   		        		div_str += '<i id="' + this.subtaskid + '" class="fas fa-times subtask_del_btn" style="cursor:pointer"></i></div>';
   		        		   
   		        	   });
   		        	   $('#Task_Modal_subtasks').append(div_str);
   		        	 
   		        	   
   		           } // end-success
   		        }); // end-ajax
   	
     } // end - keyCode=13
});



/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : Task 모달 창내 subtask내 각각의 div를 클릭시 발동
 작성자명 : 김 정 권
 */
$(document).on("click",".hover_div",function(){
	
	 var tid = $('#tidhidden').attr('value');
	
	// 완료면 미완료로, 미완료시 완료로 토글
	var span_class = $(this).children('span').attr('class');
	var subtask_fini_or_unfini = 0;
	
		// 완료일 때 발동하는 조건으로 밑줄을 풀게 된다 
		if(span_class == 'subtask_name subtask_checked subtask_span'){
			$(this).children('span').attr('class','subtask_name subtask_unchecked subtask_span');
			subtask_fini_or_unfini = 0;
		}
		
		// 미완료일 때 발동하는 조건으로 밑줄을 치게 된다 
		else {
			$(this).children('span').attr('class','subtask_name subtask_checked subtask_span');
			subtask_fini_or_unfini = 1;
		}
	
	var subtaskid = $(this).children('i').attr('id');

	  $.ajax(
  		       {
  		           type : "post",
  		           url  : "changesubtask.htm",
  		           data : {
  		        	   'subtaskid': subtaskid,
  		        	   'tid' : tid,
  		        	   'subtask_fini_or_unfini' : subtask_fini_or_unfini
  		           },
  		           success : function(rdata){
  		        	   
  		        	   console.log('서브테스크 상태 변경 완료');
  		        	   
  		           } // end-success
  		        }); // end-ajax
	  
});


/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : Task 모달 창내 subtask를 삭제
 작성자명 : 김 정 권
 */
$(document).on("click",".subtask_del_btn",function(){
	
	 var tid = $('#tidhidden').attr('value');
	 var subtaskid = $(this).attr('id');
	 
	  $.ajax(
  		       {
  		           type : "post",
  		           url  : "deletesubtask.htm",
  		           data : {
  		        	   'tid': tid,
  		        	   'subtaskid': subtaskid
  		           },
  		           success : function(rdata){
  		        	   
  		        	   // 서브테스크 삭제 성공 -> 서브테스크 목록 다시 불러오기
  		        	   if(rdata.result == 1){
  		        		   
			        	   // subtask
			        	   $('#Task_Modal_subtasks').empty();
	   		        	   var div_str = '';
	   		        	   $(rdata.subtasklist).each(function(){
	   		        		   
	   		        		   var ischecked_class = '';
	   		        		   if(this.ischecked == 1){
	   		        			ischecked_class = 'subtask_name subtask_checked subtask_span';
	   		        		   }else{
			        			ischecked_class = 'subtask_name subtask_unchecked subtask_span';
	   		        		   }
	   		        		   
	   		        		div_str += '<div class="hover_div"><span class="' + ischecked_class + '">' + this.subtask + '</span>';
	   		        		div_str += '<i id="' + this.subtaskid + '" class="fas fa-times subtask_del_btn" style="cursor:pointer"></i></div>';
	   		        		   
	   		        	   });
	   		        	   $('#Task_Modal_subtasks').append(div_str);
  		        		   
  		        	   }
  		        	   
  		           } // end-success
  		        }); // end-ajax
	  
});


/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : 서브테스크 hover시에만 x 표 보이도록 설정
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".hover_div",function() {
    $(this).children('i').css("visibility","visible");
  }).on("mouseleave",".hover_div",function() {//마우스 호버 아웃 하면 checkbox가 다시 안보이게 함
	  $(this).children('i').css("visibility","hidden");
  });



/**
 * 
 날      짜 : 2018. 7. 8.
 기      능 : 파일에 hover시에만 x 표 보이도록 설정
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".filehover_div",function() {
    $(this).children('i').css("visibility","visible");
  }).on("mouseleave",".filehover_div",function() {//마우스 호버 아웃 하면 checkbox가 다시 안보이게 함
	  $(this).children('i').css("visibility","hidden");
  });



/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : task 상세설명에서 시간 지연 후 DB로 가서 저장하는 함수
 작성자명 : 김 정 권
 */
$(document).on("keyup","#Task_Modal_detail",function() {
	
	$('#task_detail_status').css("visibility","hidden");
	$('#task_detail_status').attr('src','img/loader.gif');
	
	delay(function(){
		   $('#task_detail_status').css("visibility","visible");
		   var tid = $('#tidhidden').attr('value');
		   var detail_content = $('#Task_Modal_detail').val();
		   
		   $.ajax(
	  		       {
	  		           type : "post",
	  		           url  : "updateTaskDetail.htm",
	  		           data : {
	  		        	   'tid' : tid,
	  		        	   'content' : detail_content 
	  		           },
	  		           success : function(rdata){
	  		        	   
	  		        	   console.log('detail update 완료');
	  		        		
	  		        	   delay(function(){
	  		        			$('#task_detail_status').attr('src','img/checked.jpg');
	  		        			
	  		       		    }, 1000 );
	  		        	   
	  		           } // end-success
	  		        }); // end-ajax
		      
		      // 0.5초 지연
		    }, 500 );
});

var delay = (function(){
	  var timer = 0;
	  return function(callback, ms){
	    clearTimeout (timer);
	    timer = setTimeout(callback, ms);
	  };
	})();



/**
 * 
 날      짜 : 2018. 6. 27.
 기      능 : 코멘트 생성
 작성자명 : 김 정 권
 */
var getCommentAndMemberlist = (function (){
	   
	var tid = $('#tidhidden').attr('value');
	var comment_str = '';
	$.ajax(
		       {
		           type : "post",
		           url  : "getcommentsandmember.htm",
		           data : {
		        	   'tid' : tid
		           },
		           async: false,
		           success : function(rdata){
		        	   
		        	   console.log('getCommentAndMemberlist get 완료');
		        	   console.log(rdata.commentandmemberlist);
		        	   
		        	   $(rdata.commentandmemberlist).each(function(){
		        		 
		        		   comment_str += '<div class="wrapper_comment">' 
		        		 //comment_str += '<img id="' + this.mid + '" class ="taskmodal_memberprofile2" src="' + this.image + '">';
		        		   comment_str += '<img id="' + this.mid + '" class ="taskmodal_memberprofile2" src="displayImage.htm?image=' + this.image + '"/>';
		        		   comment_str += '<div class="each_comment">';
		        		   comment_str += '<div class="first_row">' + this.mname + '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' + this.cmtmtime + '</div>'
		        		   comment_str += '<div class="second_row">' + this.comments + '</div>'
		        		   comment_str += '</div>'
	        			   comment_str += '</div><br><br>'
	        				   
	        				   
		        	   });
		        	   $('#Task_Modal_comments').empty();
		        	   $('#Task_Modal_comments').append(comment_str);
		        	   
		        	   var textarea = $('#Task_Modal_comments');
		        	   textarea.scrollTop(textarea[0].scrollHeight);
		        
		        	   
		           } // end-success
		           
		        }); // end-ajax
});



/**
 * 
 날      짜 : 2018. 6. 27.
 기      능 : 테스크 모달 내 코멘트 입력 부분(인풋태그)에서 작동하는 함수
 작성자명 : 김 정 권
 */
$(document).on("keyup","#comment_input_box_in_taskmodal",function(){

	var usermid = $('#usermidhidden').attr('value');
	
  // @ 쳤을 시
  if (event.keyCode === 50) {
	var pid = $('#pidhidden').attr('value');
    $.ajax(
           {
               type : "post",
               url  : "getsameprojectmembersintaskmodal.htm",
               data : {
                 'pid': pid
               },
               success : function(rdata){
                 
            	 var popupdiv_str = '';
                 $(rdata.sameprojectmembers).each(function(){
                	 
              	 if(this.mid == usermid){
              		 return true;
               	 }
                	 
                  popupdiv_str += '<div class="wrapper_comment popup_mid" id="' + this.mid + '">';	 
//                popupdiv_str += '<img class ="taskmodal_memberprofile2" src="img/' + this.image + '"/>';	
                  popupdiv_str += '<img class ="taskmodal_memberprofile2" src="displayImage.htm?image=' + this.image + '"/>';	
                  popupdiv_str += '<div class="each_comment">';	
                  popupdiv_str += '<div class="first_row">' + this.mname + '</div>';	
                  popupdiv_str += '<div class="second_row">' + this.mid + '</div>';	
                  popupdiv_str += '</div></div>';
                  
                 });

                 
                 $('#project_member_popup_div').empty();
                 $('#project_member_popup_div').append(popupdiv_str);
                 let popupdiv_width = $('#project_member_popup_div').width();
                 let popupdiv_height = $('#project_member_popup_div').height();
                 
                 let position = $('#comment_input_box_in_taskmodal').position();
                 $('#project_member_popup_div').css("left",position.left-popupdiv_width-29);
                 $('#project_member_popup_div').css("top",position.top-popupdiv_height+23);
                 $("#project_member_popup_div").css("background-color","#FFFFFF")
                 $('#project_member_popup_div').css("display","block");
                 
               } // end-success
            }); // end-ajax
   

  } // end - keyCode=13

  
  // @ 아닐 시
  if((event.keyCode != 50) && (event.keyCode != 27) && (event.keyCode != 13)){
	  
	    $('#project_member_popup_div').css("display", "none");
	    $('#project_member_popup_div').css("left", "-20000");
	    $('#project_member_popup_div').css("top", "-20000");
  } // end - != 50
  
  
  // 엔터키 칠 시
  if (event.keyCode === 13) {
	   var tid = $('#tidhidden').attr('value');
	   var comments = $('#comment_input_box_in_taskmodal').val();
	   console.log(comments);
	   if(comments != ""){
		   $.when(insertCommentReceiver(tid,comments)).done(function(data){
			   send(); //웹 소켓 send 함수 추가
			   event.stopPropagation();
			   $('#comment_input_box_in_taskmodal').val('');
		   });
	   }
	   
  } // end - keyCode=13
});

/**
 * 
 날      짜 : 2018. 7. 3.
 기      능 : ajax 순서를 위해 함수로 뻄
 작성자명 : 신 호 용
 */
function insertCommentReceiver(tid,comments){
	var ajax =$.ajax(
	   {
		   type : "post",
		   url  : "insertcommentandreceiver.htm",
		   data : {
			   'tid': tid,
			   'comments' : comments
		   },
		   success : function(rdata){
			   console.log('success 성공 테스트 출력 : ' + rdata);
			   // comment
			   getCommentAndMemberlist();
		   } // end-success	   
	   }); // end-ajax
	return ajax;
}


/**
 * 
 날      짜 : 2018. 6. 27.
 기      능 : 테스크 모달 내 pop up div 내에 있는 사람을 클릭하면 발생하는 일
 작성자명 : 김 정 권
 */
$(document).on("click",".popup_mid",function(){
	
    $('#project_member_popup_div').css("display", "none");
    $('#project_member_popup_div').css("left", "-20000");
    $('#project_member_popup_div').css("top", "-20000");

    $('#div_for_comment_input_box').empty();
    var inputboxstr = '<input id="receivermid" type="button" value="">'
    	inputboxstr+= '<input id="comment_input_box_in_taskmodal2" type="text" placeholder="코멘트를 입력 후 Enter..">'
    $('#div_for_comment_input_box').append(inputboxstr);
    
	var mid = $(this).attr('id');
	$('#receiverhidden').attr('value', mid);
	var speaker_name = '';
	var inputstr = '';
	
	 $.ajax(
		       {
		           type : "post",
		           url  : "findmname.htm",
		           data : {
		        	   'mid': mid
		           },
		           success : function(rdata){
		        	   speaker_name = rdata.speaker;
		        	   inputstr = 'TO. ' + speaker_name;
		        	   $('#receivernamehidden').attr('value', inputstr);
		        	   
		        	   $('#receivermid').attr('value', inputstr);
		        	   $('#comment_input_box_in_taskmodal2').focus();
		        		
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 27.
 기      능 : 테스크 모달 내 코멘트 입력 부분(인풋태그)에서 @ 를 사용해 새로운 input 만들고 거기서 작동하는 함수
 작성자명 : 김 정 권
 */
$(document).on("keyup","#comment_input_box_in_taskmodal2",function(){

  // 엔터키 칠 시
  if (event.keyCode === 13) {
	  
	   var tid = $('#tidhidden').attr('value');
	   var temp_comments = $('#comment_input_box_in_taskmodal2').val();
	   var receiver = $('#receiverhidden').attr('value');
	   
	   var receivername = $('#receivernamehidden').attr('value');
	   var comments = '(' + receivername + ') ' + temp_comments;
	   
	   $.ajax(
		       {
		           type : "post",
		           url  : "selectandchatintaskmodal.htm",
		           data : {
		        	   'tid': tid,
		        	   'comments' : comments,
		        	   'receiver' : receiver
		           },
		           success : function(rdata){
		        	   console.log('success 성공 테스트 출력 : ' + rdata.result);
		        	   
			           // comment
			           getCommentAndMemberlist();
			           $('#comment_input_box_in_taskmodal2').val('');
			           $('#comment_input_box_in_taskmodal2').focus();
		        	   
		           } // end-success
		        }); // end-ajax

  } // end - keyCode=13
  
  if(event.keyCode === 107){
	  alert('작동함');
	  var temp = $('#receiverhidden').attr('id');
	  alert(temp);
  }

});


/**
 * 
 날      짜 : 2018. 6. 27.
 기      능 : @ 를 이용해서 작동한 귓속말 모드를 해지하는 함수
 작성자명 : 김 정 권
 */
$(document).on("click","#receivermid",function(){
	
	 $('#div_for_comment_input_box').empty();
	    var origin_inputboxstr = '<input id="comment_input_box_in_taskmodal" type="text" placeholder="코멘트를 입력 후 Enter..">'
	 $('#div_for_comment_input_box').append(origin_inputboxstr);
	 $('#comment_input_box_in_taskmodal').focus();
	
});


/**
 * 
 날      짜 : 2018. 6. 29.
 기      능 : tname 변경을 위해 인풋태그 활성화 함수
 작성자명 : 김 정 권
 */
$(document).on("click","#Task_Modal_tname",function(){
	
	$('#Task_Modal_tname').css("display", "none");
	
	var tname = $('#Task_Modal_tname').text();
	$('#Task_Modal_tname_input').val(tname);
	$('#Task_Modal_tname_input').css("display","block");
	
});


/**
 * 
 날      짜 : 2018. 6. 29.
 기      능 : tname 변경을 쿼리를 통해 update로 실행하는 함수
 작성자명 : 김 정 권
 */
$(document).on("keyup","#Task_Modal_tname_input",function(){
	
	var tid = $('#tidhidden').attr('value');
	var tname = '';
	
	  if (event.keyCode === 13) {
		  	
		    tname = $('#Task_Modal_tname_input').val();
		    
		    $.ajax(
				       {
				           type : "post",
				           url  : "changetname.htm",
				           data : {
				        	   'tid': tid,
				        	   'tname' : tname
				           },
				           success : function(rdata){
				        	   console.log('success 성공 테스트 출력 : ' + rdata.result);
				        	   
				   			$('#Task_Modal_tname_input').css("display","none");
				   			$('#Task_Modal_tname_input').val('');
				   			
				   			$('#Task_Modal_tname').empty();
				   			$('#Task_Modal_tname').append(tname);
							$('#Task_Modal_tname').css("display", "block");
							
		                	 $("#main-box").empty();
							 $("#main-box").append(rdata);
							 checkCrtPage();
				           } // end-success
				        }); // end-ajax
	  		} // end - keyCode==13
	
});


$(document).on("click","#task_dismiss_btn_starredtask",function(){
	$(".modal-content2").hide();
	$(".starred-secondbody-image").show();
});

/**
 * 
 날      짜 : 2018. 7. 3.
 기      능 : Starred Task 페이지에서 별버튼을 누를시 즐겨찾기가 지워지는 함수
 작성자명 : 이 진 우
 */

$(document).on("click",".starred-body-task-container-top-star",function(evt){
	evt.stopPropagation();
	let tid = parseInt($(this).parents(".starred-body-task-container-top").children(".starred-body-task-container-top-title").attr("id").substring(1));
	$.ajax({

            type:"POST",
            url:"deleteStarredTask.htm",
            data:{tid:tid},
            success:function(data) {
					 $("#main-box").empty();
					 $("#main-box").append(data);
					 checkCrtPage();
            }
	});
})


/**
 * 
 날      짜 : 2018. 7. 5.
 기      능 : 테스크 명 변경 포커스아웃
 작성자명 : 김 정 권
 */
$(document).on("focusout","#Task_Modal_tname_input",function() {
	
	var tid = $('#tidhidden').attr('value');
	var tname = '';
	
	tname = $('#Task_Modal_tname_input').val();
		    
		    $.ajax(
				       {
				           type : "post",
				           url  : "changetname.htm",
				           data : {
				        	   'tid': tid,
				        	   'tname' : tname
				           },
				           success : function(rdata){
				        	   console.log('success 성공 테스트 출력 : ' + rdata.result);
				        	   
				   			$('#Task_Modal_tname_input').css("display","none");
				   			$('#Task_Modal_tname_input').val('');
				   			
				   			$('#Task_Modal_tname').empty();
				   			$('#Task_Modal_tname').append(tname);
							$('#Task_Modal_tname').css("display", "block");
							
		                	 $("#main-box").empty();
							 $("#main-box").append(rdata);
							 checkCrtPage();
				           } // end-success
				        }); // end-ajax
	
});



/**
 * 
 날      짜 : 2018. 7. 8.
 기      능 : Task 모달 내 파일 업로드
 작성자명 : 김 정 권
 */
$(document).on("click","#fileuploadbtn",function() {
	
	$('#fileuploadintaskmodal').trigger('click');

});


/**
 * 
 날      짜 : 2018. 7. 8.
 기      능 : Task 모달 내 파일 다운
 작성자명 : 김 정 권
 */
/*$(document).on("click",".file_name",function() {
	
	var filename = $(this).attr('id');
	console.log('filename: ' + filename);
	
	location.href = 'downloadfileintaskmodal.htm?filename=' + filename;

});
*/

/**
 * 
 날      짜 : 2018. 7. 8.
 기      능 : Task 모달 내 파일 삭제
 작성자명 : 김 정 권
 */
$(document).on("click",".file_del_btn",function() {
	
	var tid = $('#tidhidden').attr('value');
	var filename = $(this).attr('id');
	console.log(tid + '/' + filename);
	$.ajax(
		       {
		           type : "post",
		           url  : "deletefileintaskmodal.htm",
		           data : {
		        	   'tid': tid,
		        	   'filename' : filename
		           },
		           success : function(rdata){
		        	
		        	   // files
		        	   $('#Task_Modal_files').empty();
		        	   var filesdivs = '';
		        	   $(rdata.filelist).each(function(){
		        		   
		        		   let strArray = this.filename.split('/');
			        	   var shortfilename = strArray[2].substring(37);
			        	   filesdivs += '<div class="filehover_div">' + '<a class="file_name" href="taskFileDownload.htm?filename='+this.filename+'" download>' + shortfilename + '</a>';
			        	   filesdivs += '<i id="' + this.filename + '" class="fas fa-times file_del_btn" style="cursor:pointer"></i>';
			        	   filesdivs += '</div>'
		        		  
		        	   });
		        	   $('#Task_Modal_files').append(filesdivs);
		        	   
		           } // end-success
		        }); // end-ajax

});
//////////////////////////////////////////////////////////////////////////////// no redirect

/**
 * 
 날      짜 : 2018. 7. 9
 기      능 : Task 모달 창에서 Task 상태 변경 + no redirect
 작성자명 : 김 정 권
 */

$(document).on("change","#Task_Modal_tstatus_selectbox_noredirect",function(){
	
	var tid = $('#tidhidden').attr('value');
	var value = $('#Task_Modal_tstatus_selectbox_noredirect').val();
	var tname = $('#tnamehidden').attr('value');
	
	console.log('/'+ tid +'/' + value +'/' + tname + '/')
	
	  $.ajax(
		       {
		           type : "post",
		           url  : "changetstatusno_redirect.htm",
		           data : {
		        	   'tid': tid,
		        	   'value' : value,
		        	   'tname' : tname
		           },
		           success : function(rdata){
		        	   
			           // comment
			           getCommentAndMemberlist();

		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 7. 9.
 기      능 : Task 페이지 내 step 추가 버튼 no redirect
 작성자명 : 김 정 권
 */
$(document).on("click","#task_modal_add_step_noredirect",function(){
	
	var tid = $('#tidhidden2').attr('value');
	
	$.ajax(
		       {
		           type : "post",
		           url  : "getStepListByTid.htm",
		           data : {
		        	   'tid': tid,
		           },
		           success : function(rdata){
		        	   console.log(rdata.steplist);

		        	   var step_names_popup_div_str = '';
		        	   
	        		   var countlength = Object.keys(rdata.steplist).length;
	        		   if(countlength == 0){
	        			   step_names_popup_div_str += '<div class="wrapper_comment popup_nothing">';
	        			   step_names_popup_div_str += '<div class="each_comment">';	
	        			   step_names_popup_div_str += '<div class="first_row">추가할 스텝이 없습니다</div>';
	        			   step_names_popup_div_str += '</div></div>';
	        				   
	        		   } else {
		        	   $(rdata.steplist).each(function(){
		        			   var sday = '';
				        	   var eday = '';
				        	   var emptyday = '';
				        		   
				        		  if(this.sday == null){
				        			  sday = emptyday;
				        		  }

				        		  if(this.eday == null){
				        			  eday = emptyday;
				        		  }
				        		  
				        		  if(this.sday != null){
				        			  sday = this.sday.substring(0, 10);
				        		  }
				        		  
				        		  if(this.eday != null){
				        			  eday = this.eday.substring(0, 10);
				        		  }
			        		   
			        		  step_names_popup_div_str += '<div class="wrapper_comment popup_sid2" id="' + this.sid + '">';	 
			        		  step_names_popup_div_str += '<div class="each_comment">';	
			        		  step_names_popup_div_str += '<div class="first_row">' + this.sname + '</div>';
			        		  
			        		  if((sday == emptyday) && (eday == emptyday)){
			        			  step_names_popup_div_str += '<div class="second_row">(시작일과 종료일 모두 미정)</div>'
			        		  } else if((sday != emptyday) && (eday == emptyday)){
			        			  step_names_popup_div_str += '<div class="second_row">' + sday + '&nbsp~' + '</div>'
			        		  }  else if((sday == emptyday) && (eday != emptyday)){
			        			  step_names_popup_div_str += '<div class="second_row">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp~&nbsp' + eday + '</div>'
			        		  } else{
			        			  step_names_popup_div_str += '<div class="second_row">' + sday + '&nbsp~&nbsp' + eday + '</div>';	
			        		  }
			        		  
			        		  step_names_popup_div_str += '</div></div>';
		        		 
		        	   		});
	        		   }// end - else
		        	   
		        	      $('#step_names_popup_div2').empty();
		        	      $('#step_names_popup_div2').append(step_names_popup_div_str);
		        	      let popupdiv_width = $('#step_names_popup_div2').width();
		                  let popupdiv_height = $('#step_names_popup_div2').height();
		                  
		                  let position = $('#task_modal_add_step_noredirect').position();
		                  $('#step_names_popup_div2').css("left",position.left + 850);
		                  $('#step_names_popup_div2').css("top",position.top + 50);
		                  $('#step_names_popup_div2').css("display","block");
		        	   
		           } // end-success
		        }); // end-ajax
	
});

/**
 * 
 날      짜 : 2018. 7. 9.
 기      능 : Task 페이지 내 step 삭제 버튼 클릭시 작동 + no redirect
 작성자명 : 김 정 권
 */
$(document).on("click",".task_page_delete_step_btn2",function(){

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
		        	   if(count == 1) {
		        		   
		        		   var step_delete_popup_div_str = '';
		        		   step_delete_popup_div_str += '<div>';
		        		   step_delete_popup_div_str +='<div>해당 Task가 속한 마지막 Step 입니다</div>';
		        		   step_delete_popup_div_str +='<div>Step 삭제시 Task가 삭제됩니다</div>';
		        		   step_delete_popup_div_str +='<div><input type="button" value="삭제하기" id="step_delete_button"></div>';
		        		   step_delete_popup_div_str +='</div>';
		        		   
		        		   $('#step_delete_popup_div2').empty();
		        		   $('#step_delete_popup_div2').append(step_delete_popup_div_str);
		        		   
			               let position = $('.task_page_delete_step_btn2').position();
			               $('#step_delete_popup_div2').css("left",position.left + 850);
			               $('#step_delete_popup_div2').css("top",position.top + 10);
			               $('#step_delete_popup_div2').css("display","block");
		        		   
		        		   return;
		        	   } 
		        	   
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
		        		        	   
									// step name
									   $('#Task_Modal_snames2').empty();
									   
									   var snames = '<br>'
									   $(rdata.steplist_after_delete_step).each(function(){
										   var sname = this.sname;
										       snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + sname + '&nbsp&nbsp' + '<i class="far fas fa-times task_page_delete_step_btn2" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
									   });
									   snames += '<i id="task_modal_add_step_noredirect" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
									   $('#Task_Modal_snames2').append(snames);
		        		        	   
		        		           } // end-success
		        		        }); // end-ajax
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 6. 23.
 기      능 : popup div 에서 스텝 누르면 추가됨 no redirect
 작성자명 : 김 정 권
 */
$(document).on("click",".popup_sid2",function(){
	
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
		        	   $("#step_names_popup_div2").css("background-color","#FFFFFF");
		        	   $("#step_names_popup_div2").css({"display":"none","left":"-20000px","top":"-20000px"});
		        	 
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
		        		        	$('#Task_Modal_snames2').empty();

		        		        	var snames = '<br>'
		        		        	$(rdata.steplist).each(function(){
		        		        	snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + this.sname + '&nbsp&nbsp' + '<i class="fas fa-times task_page_delete_step_btn2" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
		        		        	});
		        		        	snames += '<i id="task_modal_add_step" class="fas fa-plus-circle" style="cursor:pointer" ></i>'
		        		        	$('#Task_Modal_snames2').append(snames);
		        		        	
		        		           } // end-success
		        		        }); // end-ajax
		        	   
		           } // end-success
		        }); // end-ajax
});

/**
 * 
 날      짜 : 2018. 7. 9.
 기      능 : 테스크 모달 내 코멘트 입력 부분(인풋태그)에서 작동하는 함수 + no redirect
 작성자명 : 김 정 권
 */
$(document).on("keyup","#comment_input_box_in_taskmodal_noredirect",function(){
	
	var usermid = $('#usermidhidden2').attr('value');
	
  // @ 쳤을 시
  if (event.keyCode === 50) {
	  
	var pid = $('#pidhidden').attr('value');
    $.ajax(
           {
               type : "post",
               url  : "getsameprojectmembersintaskmodal.htm",
               data : {
                 'pid': pid
               },
               success : function(rdata){
                 
            	 console.log('성공이다');
            	 console.log(rdata.sameprojectmembers);
            	   
            	 var popupdiv_str = '';
                 $(rdata.sameprojectmembers).each(function(){
                	 
              	 if(this.mid == usermid){
              		 return true;
               	 }
                	 
                  popupdiv_str += '<div class="wrapper_comment popup_mid2" id="' + this.mid + '">';	 
//                popupdiv_str += '<img class ="taskmodal_memberprofile2" src="img/' + this.image + '"/>';	
                  popupdiv_str += '<img class ="taskmodal_memberprofile2" src="displayImage.htm?image=' + this.image + '"/>';	
                  popupdiv_str += '<div class="each_comment">';	
                  popupdiv_str += '<div class="first_row">' + this.mname + '</div>';	
                  popupdiv_str += '<div class="second_row">' + this.mid + '</div>';	
                  popupdiv_str += '</div></div>';
                  
                 });

                 
                 $('#project_member_popup_div2').empty();
                 $('#project_member_popup_div2').append(popupdiv_str);
                 let popupdiv_width = $('#project_member_popup_div2').width();
                 let popupdiv_height = $('#project_member_popup_div2').height();
                 
                 let position = $('#comment_input_box_in_taskmodal_noredirect').position();
                 $('#project_member_popup_div2').css("left",position.left-popupdiv_width+150);
                 $('#project_member_popup_div2').css("top",position.top-popupdiv_height-20);
                 $("#project_member_popup_div2").css("background-color","#FFFFFF")
                 $('#project_member_popup_div2').css("display","block");
                 
               } // end-success
            }); // end-ajax
   

  } // end - keyCode=13

  
  // @ 아닐 시
  if((event.keyCode != 50) && (event.keyCode != 27) && (event.keyCode != 13)){
	  
	    $('#project_member_popup_div2').css("display", "none");
	    $('#project_member_popup_div2').css("left", "-20000");
	    $('#project_member_popup_div2').css("top", "-20000");
  } // end - != 50
  
  
  // 엔터키 칠 시
  if (event.keyCode === 13) {
	   var tid = $('#tidhidden').attr('value');
	   var comments = $('#comment_input_box_in_taskmodal_noredirect').val();
	   console.log(comments);
	   if(comments != ""){
		   $.when(insertCommentReceiver(tid,comments)).done(function(data){
			   send(); //웹 소켓 send 함수 추가
			   event.stopPropagation();
			   $('#comment_input_box_in_taskmodal_noredirect').val('');
		   });
	   }
	   
  } // end - keyCode=13
});

/**
 * 
 날      짜 : 2018. 7. 9
 기      능 : 테스크 모달 내 pop up div 내에 있는 사람을 클릭하면 발생하는 일 + no redirect
 작성자명 : 김 정 권
 */
$(document).on("click",".popup_mid2",function(){
	
    $('#project_member_popup_div2').css("display", "none");
    $('#project_member_popup_div2').css("left", "-20000");
    $('#project_member_popup_div2').css("top", "-20000");

    $('#div_for_comment_input_box2').empty();
    var inputboxstr = '<input id="receivermid2" type="button" value="">'
    	inputboxstr+= '<input id="comment_input_box_in_taskmodal2" type="text" placeholder="코멘트를 입력 후 Enter..">'
    $('#div_for_comment_input_box2').append(inputboxstr);
    
	var mid = $(this).attr('id');
	$('#receiverhidden').attr('value', mid);
	var speaker_name = '';
	var inputstr = '';
	
	 $.ajax(
		       {
		           type : "post",
		           url  : "findmname.htm",
		           data : {
		        	   'mid': mid
		           },
		           success : function(rdata){
		        	   speaker_name = rdata.speaker;
		        	   inputstr = 'TO. ' + speaker_name;
		        	   $('#receivernamehidden').attr('value', inputstr);
		        	   
		        	   $('#receivermid2').attr('value', inputstr);
		        	   $('#comment_input_box_in_taskmodal2').focus();
		        		
		           } // end-success
		        }); // end-ajax
	
});
//////////////////////////////////////////////// comment_input_box_in_taskmodal_noredirect에서 키코드 이벤트들 안먹힌다
/**
 * 
 날      짜 : 2018. 7. 9
 기      능 : @ 를 이용해서 작동한 귓속말 모드를 해지하는 함수 + no redirect
 작성자명 : 김 정 권
 */
$(document).on("click","#receivermid2",function(){
	
	 $('#div_for_comment_input_box2').empty();
	    var origin_inputboxstr = '<input id="comment_input_box_in_taskmodal_noredirect" type="text" placeholder="코멘트를 입력 후 Enter..">'
	 $('#div_for_comment_input_box2').append(origin_inputboxstr);
	 $('#comment_input_box_in_taskmodal_noredirect').focus();
	
});