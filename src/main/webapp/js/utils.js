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
 날   짜 : 2018. 6. 14.
 기   능 : 프로필사진 클릭시, 프로필창 띄워주기
 작성자명 : 박 민 식
 */
$(document).on("click",".profile-img-container",function(evt){
	console.log("프로필 띄워주기 - targetId : " + evt.target.id);
})
	




