/**
 * 
 날   짜 : 2018. 6. 14.
 기   능 : 태스크 담당자 프로필 만들어주는 함수
 작성자명 : 박 민 식
 */
function getTaskAssignees(tid){
	console.log("getTaskAssignees실행")
	var div = jQuery("<div>",{"class":"container-fluid"});
	$.ajax({
		url:"getTaskAssignees.htm",
		data:{tid:tid},
		dataType:"json",
		async:false,
		success:function(data){
			if(data!=null){
				$(data.taskassignees).each(function(index,el){
					var profile_container = makeProfileIcon(el);
					if(index <=1){
						$(div).append(profile_container);
					}
				})
			}
		}
	})
	return div;
}

/**
 * 
 날   짜 : 2018. 6. 18.
 기   능 : 프로필 아이콘을 생성해주는 버튼
 작성자명 : 박 민 식
 */
function makeProfileIcon(memberdata, imgsize){
	var profile_container = jQuery("<div>",{"class":"profile-img-container"});
	profile_container.css({"width":imgsize,"height":imgsize});
	var img = jQuery("<img>",{"class":"profile-img","id":memberdata.mid});
	var src = (memberdata.image ==null)?"img/user_image.png" :"profileImg/"+memberdata.image;
	img.attr("src",src);
	$(profile_container).append(img);
	return profile_container;
}


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 프로필사진 클릭시 프로필 Modal 생성
 작성자명 : 김 래 영
 */
$(document).on("click",".profile-img-container",function(evt){
	console.log("프로필 띄워주기 - targetId : " + evt.target.id);
    var mid = this.id.substr(7); // mid 만 가져오기
    console.log(mid);

	
	$.ajax({
        url:"profilemember.htm",
        data:{mid:mid},
        dataType:"json",
        success:function(data) {
            $('#profile-modal-img').text(data.profileinfo.image);
            $('#profile-modal-mname').text(data.profileinfo.mname)
            $('#profile-modal-mid').text(data.profileinfo.mid);
            $('#profile-modal-pnum').text(data.profileinfo.pnum);
            $('#profile-modal-deptname').text(data.profileinfo.deptname);
            $('#profile-modal-position').text(data.profileinfo.position);
            
            if($('#profile-modal-img').text() == null) {
                $('#profile-modal-img').text("");
            }

        }
	}); // end - ajax
}); // end - profile modal




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

			        	   console.log(rdata);

			        	   //tid
			        	   var tid = rdata.task.tid;
			        	   $('#tidhidden').attr('value', tid);
			        	   
			        	   // tname
			        	   $('#Task_Modal_tname').empty();
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
			        	   
			        	   /////////////////////////////////
			        	   var stepnamesarr = [];
			        	   $(rdata.steps).each(function(){
			        		   stepnamesarr.push(this.sid);
			        	   });			        	   
			        	   /////////////////////////////////
			        	   
			        	   var snames = '<br>'
			        	   $(rdata.steps).each(function(){
			        		   console.log('여기여기')
			        		   console.log(rdata.steps)
			        		   snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + this.sname + '&nbsp&nbsp' + '<i class="fas fa-times task_page_delete_step_btn" style="color:#808B96; cursor:pointer" id="' + this.sid + '"></i></span>';
			        	   });
			        	   snames += '<i class="fas fa-plus-circle"></i>'
			        	   $('#Task_Modal_snames').append(snames);
				        	   
			        	   
//			        	   var snames = '<br>'
//				        
//			        	   for(var i = 0; i < rdata.steps.length; i++){
//		        		   snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + rdata.steps[i].sname + '&nbsp&nbsp' + '<i class="fas fa-times task_page_delete_step_btn" style="color:#808B96; cursor:pointer">' 
//		        		   snames += '<input type="hidden" class="sid" value="'+ rdata.steps[i].sid +'"></i></span>';
//			        	   }
//			        	   snames += '<i class="fas fa-plus-circle"></i>'
//			        	   $('#Task_Modal_snames').append(snames);
			        	   
		        	   
			        	   
			        	   // tstatus
			        	   $('#Task_Modal_tstatus').empty();
			        	   
			        	   var tstatusoptions = '<select>'
			        	   $(rdata.tstatuslist).each(function(){
			        		   tstatusoptions += '<option style='
			        		   tstatusoptions += '"color: ' + this.color + '">'
			        		   tstatusoptions += this.tstatus
			        		   tstatusoptions += '</option>'
				           });
			        	   tstatusoptions += '</select>'
			        	   $('#Task_Modal_tstatus').append(tstatusoptions);
			        	   
			        	   
			        	   // assignee
//			        	   $('#Task_Modal_assignee').empty();
//			        	   $('#Task_Modal_assignee').append(rdata.task.tname);
//			        	   
//			        	   
//			        	   
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
			        		        		       snames += '<span style="background-color:#f0f0f0; margin-right: 5px">' + sname + '&nbsp&nbsp' + '<i class="far fas fa-times task_page_delete_step_btn" style="color:#808B96" id="' + this.sid + '"></i></span>';
			        		        	   });
			        		        	   snames += '<i class="fas fa-plus-circle"></i>'
			        		        	   $('#Task_Modal_snames').append(snames);
			        		        	   
		        		        	   }
		        		        	   
		        		           } // end-success
		        		        }); // end-ajax
		        	   
		           } // end-success
		        }); // end-ajax
	
});

