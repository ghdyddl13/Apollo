



/**
 * 
 날   짜 : 2018. 6. 14.
 기   능 : 태스크 담당자 프로필 만들어주는 함수
 작성자명 : 박 민 식
 */


function getTaskAssignees(tid){
	console.log("getTaskAssignees실행")
	var div = jQuery("<div>",{"class":"container-fluid"});
	var left_member = [];
	var left_count = 0;
	$.ajax({
		url:"getTaskAssignees.htm",
		data:{tid:tid},
		dataType:"json",
		async:false,
		success:function(data){
			if(data!=null){
				$(data.taskassignees).each(function(index,el){
					var profile_container = makeProfileIcon(el);
					if(index <1){
						$(div).append(profile_container);
					}else{
						left_member.push(el);
						left_count++;
					};
				});
			if(left_count>0){
				var a = jQuery("<a>",{"class":"left-assignees",
									  "text":"외 " +left_count+"명",
									  "rel":"popover",
									  "data-popover-content":"#left-assignee-"+tid});
				var left_div =jQuery("<div>",{"class":"hide assignee-left-div",
											  "id":"left-assignee-"+tid,
											  "css":{"position":"absolute",
												  	 "width":"100px"
												  	 }});
				
				$(left_member).each(function(index,left){
					console.log("left " + left);
					var left_assigee_container = makeProfileIcon(left);
					$(left_div).append(left_assigee_container);
				});
				$(a).append(left_div).appendTo(div);
			};
				
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
