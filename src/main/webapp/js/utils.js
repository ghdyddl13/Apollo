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

	var profile_container = jQuery("<div>",{"class":"profile-img-container",
											"id":"profile"+memberdata.mid,
											"data-toggle":"modal",
											"data-target":"#profile-modal-dialog"});

	profile_container.css({"width":imgsize,"height":imgsize});
	var img = jQuery("<img>",{"class":"profile-img"});
	var src = (memberdata.image ==null)?"img/user_image.png" :"profileImg/"+memberdata.image;
	img.attr("src",src);
	$(profile_container).append(img);
	return profile_container;
}


/**
 * 
 날   짜 : 2018. 6. 14.
 기   능 : 프로필사진 클릭시, 프로필창 띄워주기
 작성자명 : 박 민 식
 */
$(document).on("click",".profile-img-container",function(evt){
	console.log("프로필 띄워주기 - targetId : " + this.id);

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
	});

});

/**
 * 
 날      짜 : 2018. 6. 18.
 기      능 : Task 수정/ 삭제 페이지에 날짜 클릭으로 넣는 datepicker 넣는 코드
 작성자명 : 김 정 권
 */
$( ".date-img" ).datepicker({
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

		// tid를 클릭한 태그의 id에서 가져올 것
		// var tid = $(this).attr("id"); 
		
		var pid = '1';
		var tid = '12';
		
		$.ajax(
			       {
			           type : "post",
			           url  : "getTask.htm",
			           data : {
			        	   'pid': pid,
			        	   'tid': tid
			           },
			           success : function(rdata){

			        	   console.log(rdata);

			        	   // tname
			        	   $('#Task_Modal_tname').empty();
			        	   $('#Task_Modal_tname').append(rdata.task.tname);

			        	   
			        	   // star, trash
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
			        		   var sname = this.sname;
			        		       snames += '<span>' + sname + '</span>';
			        	   });
			        	   snames += '<i class="fas fa-plus-circle"></i>'
			        	   $('#Task_Modal_snames').append(snames);
			        	   
			        	   
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

