


/**
 * 
 날   짜 : 2018. 6. 14.
 기   능 : 태스크 담당자 프로필 만들어주는 함수
 작성자명 : 박 민 식
 */
function getTaskAssignees(tid, imgsize){
	console.log("getTaskAssignees실행")
	console.log("img size :" + imgsize);
	var div = jQuery("<div>",{"class":"container-fluid"});
	$.ajax({
		url:"getTaskAssignees.htm",
		data:{tid:tid},
		dataType:"json",
		async:false,
		success:function(data){
			if(data!=null){
				$(data.taskassignees).each(function(index,el){
					var profile_container = jQuery("<div>",{"class":"profile-img-container"});
					profile_container.css({"width":imgsize,"height":imgsize});
					var img = jQuery("<img>",{"class":"profile-img","id":el.mid});
					var src = (el.image ==null)?"img/user_image.png" :"profileImg/"+el.image;
					img.attr("src",src);
					$(profile_container).append(img);
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
 날   짜 : 2018. 6. 14.
 기   능 : 프로필사진 클릭시, 프로필창 띄워주기
 작성자명 : 박 민 식
 */
$(document).on("click",".profile-img-container",function(evt){
	console.log("프로필 띄워주기 - targetId : " + evt.target.id);
})
	




