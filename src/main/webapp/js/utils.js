
$(function(){
	
	$(document).on("keyup",function(event){
		if(event.keyCode ===27){
			$('.close').click();
		}
	});
});


/**
 * 
 날   짜 : 2018. 6. 14.
 기   능 : 태스크 담당자 프로필 만들어주는 함수
 작성자명 : 박 민 식

 */



function getTaskAssignees(tid,imgsize){
	var div = jQuery("<div>");

	var left_member = [];
	var left_count = 0;
	$.ajax({
		url:"getTaskAssignees.htm",
		data:{tid:tid},
		dataType:"json",
		async:false,
		success:function(data){
			if(data.taskassignees.length!=0){
				$(data.taskassignees).each(function(index,el){
					var profile_container = makeProfileIcon(el,imgsize);
					if(index <1){
						$(div).append(profile_container);
					}else{
						left_member.push(el);
						left_count++;
					};
				});
				if(left_count>0){
		
					
					var left_div_wrapper = jQuery("<div>",{"class":"assignee-left-div-wrapper",
														   "text":"Other Assignees"});
					var left_div =jQuery("<div>",{"class":"assignee-left-div"});
					
					$(left_member).each(function(index,left){
						var left_assigee_container = makeProfileIcon(left,imgsize);
						$(left_div).append(left_assigee_container);
					});
					$(left_div_wrapper).append(left_div);
				//	let test = 1
					$(div).on("mouseenter",function(){
						$(".assignee-left-div-wrapper").remove();
						console.log($(this).offset())
						console.log($(this).width())
						$(left_div_wrapper).css({
							top: $(this).offset().top+ "px",
							left : $(this).offset().left+$(this).width()+10 + "px",
						}).appendTo("body");
					});
					
					$(document).bind("mousedown", function(e) {

						// If the clicked element is not the menu
						if (!$(e.target).parents(".assignee-left-div-wrapper").length > 0) {

							// Hide it
							$(".assignee-left-div-wrapper").remove();
						}
					})	
				};

				
			}else{
				var noassigned_container = jQuery("<div>",{"class":"noassignee-icon-container"});
				noassigned_container.css({"width":imgsize,"height":imgsize});
				var i = jQuery("<i>",{"class":"fas fa-user-plus no-assignee-task-icon",
										  "src" :"noassignee."});
				$(noassigned_container).append(i).appendTo(div);
			};
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
	var profile_container = jQuery("<div>",{"class":"profile-img-container","id":"profile"+memberdata.mid,"data-toggle":"modal", "data-target":"#profile-modal-dialog"});
	profile_container.css({"width":imgsize,"height":imgsize});
	var img = jQuery("<img>",{"class":"profile-img"});
	var src = (memberdata.image ==null)?"img/user.png" :"displayImage.htm?image="+memberdata.image;
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
    var mid = this.id.substr(7); // mid 만 가져오기

    profileinfo(mid);
    
}); // end - profile modal

/**
 * 
 날      짜 : 2018. 6. 28.
 기      능 : 프로필 사진 클릭시 프로필 Modal 이 뜨는 함수
 작성자명 : 김 래 영
 */
function profileinfo(mid) {
	$.ajax({
        url:"profilemember.htm",
        data:{mid:mid},
        dataType:"json",
        success:function(data) {
        	//console.log(data.profileinfo);
            var image = (data.profileinfo.image)?"displayImage.htm?image="+data.profileinfo.image:"img/user.png";
            $('#profile-modal-img').attr("src",image);
            $('#profile-modal-mname').text(data.profileinfo.mname)
            $('#profile-modal-mid').text(data.profileinfo.mid);
            $('#profile-modal-pnum').text(data.profileinfo.pnum);
            $('#profile-modal-deptname').text(data.profileinfo.deptname);
            $('#profile-modal-position').text(data.profileinfo.position);
            
            //선택사항을 입력하지 않은 null이 뜨지 않도록 빈 문자열로 대체
            if($('#profile-modal-pnum').text() == "null") {
            	$('#profile-modal-pnum').text(" ");
            }
            if($('#profile-modal-deptname').text() == "null") {
            	$('#profile-modal-deptname').text(" ");
            }
            if($('#profile-modal-position').text() == "null") {
            	$('#profile-modal-position').text(" ");
            }
        } // end - success
	}); // end - ajax
} // end - function



/**
 * 
날   짜 : 2018. 7. 5.
기   능 : 리다이렉트 이후 실행되어야 할 부분을 처리해주는 함수 
작성자명 : 박 민 식
 */
function checkCrtPage(){
	if($(document).find("#timeline").length !=0){
		$.when(getGanttItems()).done(function(ajax){
			var tasks = ajax.tasks;
			makeTimelineGantt(tasks);
			makeTimelineTable(tasks);
		});
	} else if( $(document).find(".project-page-table").length !=0){
		$("#project-page-table-no-data").remove();
	} else if( $(document).find("#board-main-div").length !=0){
		doDraggable();
	} else if( $(document).find(".file-table-tr-td").length !=0){
		$("#project-page-table-no-data").remove();
	} else if( $(document).find(".stream-main-container").length !=0){
		if($(".stream-select-list").length!=0){
			$("#stream-current-project").text($($(".stream-select-list")[0]).find(".stream-project-select-info-pname").text());
			if($(".stream_main").children().length==0){
				var msg =  $("<div>", {"class":"no-stream-div-msg",
					"text":"No Stream!"});
				$(".stream_main").append(msg);
			}
		}else{
			var msg =  $("<div>", {"class":"no-stream-div-msg",
				"text":"No Project!"});
			$(".stream_main").append(msg);
		}
		
	};
	
};













