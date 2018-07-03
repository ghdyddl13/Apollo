$(function() {
	
	
	// 스텝 우클릭하여 수정 버튼 클릭시 이벤트
    $(document).on("click","#side-update-step",function(){
    	$("#update-step-mgr-assignee-btn").show();
        var custom_menu =  $(this).parents("ul.custom-menu")[0];
        var sid = $(custom_menu).find("input[name=sid]").val();
        var fid = $(custom_menu).find("input[name=fid]").val();
        var pid = $(custom_menu).find("input[name=pid]").val();
        $("#update-step-mgr-assignee").val("");
        $.when(stepinfo(sid)).done(function(data){
        	var defaultmid = data.selectstep.mid;
        	console.log(data);
        	$.ajax(
                    {
                    type : "post",
                    url  : "getprojectmembers.htm",
                    data : "pid="+ pid, 
                    success : function(data){
                    	console.log(data.memberlist);
                        //해당 pid 에 참여한 멤버 불러오기
                    	$('#update-step-mgr-assignee-options').empty();
                    	$(".step-update-assigned-member-wrapper").remove();
                        $(data.memberlist).each(function (){
                          	var li = jQuery("<li>",{
    							"class":"update-step-assignee-option",
    							"role":"presentation"
    							});
                          	
					  	   var member_div = jQuery("<div>",{"class":"step-assignee-wrapper"});
					  	   var member_img_wrapper = jQuery("<div>",{"class":"step-assignee-img"});
					  	   var memeber_img = makeProfileIcon(this,"30");
					  	   var member_info = jQuery("<div>",{"class":"step-assignee-info-wrapper"});
					  	   var member_name = jQuery("<div>",{"class":"step-assignee-info-name",
									 						 "text":this.mname});
					  	   var member_email = jQuery("<div>",{"class":"step-assignee-info-email",
															  "text":this.mid});
					  	   $(member_img_wrapper).append(memeber_img);
					  	   $(member_info).append(member_name,member_email);
					  	   $(member_div).append(member_img_wrapper,member_info).appendTo(li);
					  	   $('#update-step-mgr-assignee-options').append(li);
					  	   	
					  	   console.log("this.mid " + this.mid);
					  	   console.log(defaultmid);
                           if(this.mid == defaultmid){
                        	   console.log("여기");
                        	   var stepassignee_wrapper= jQuery("<div>",{"class":"step-update-assigned-member-wrapper"});
                        	   var i= jQuery("<i>",{"class":"fas fa-times cancel-update-step-assignee"});
                        	   $(stepassignee_wrapper).append($(member_div).clone(),i).appendTo("#update-step-assignee");
                        	   $("#update-step-mgr-assignee").val(this.mid);
                        	   $("#update-step-mgr-assignee-btn").hide();
                           }
                           
                        });
                       
                    } // end-success
                 }); // end-ajax        	
             	   
        	
        	}); // end done
        

    }); //end side-bar step btn
    
    //스텝 담당자 선택시 dropdown hide시킨 후, 선택된 담당자 아이콘 뿌리기
	$(document).on("click",".update-step-assignee-option",function(){
		$("#update-step-mgr-assignee-btn").hide();
		$("#update-step-mgr-assignee").val($($(this).find(".step-assignee-info-email")[0]).text());
		var stepassignee_wrapper= jQuery("<div>",{"class":"step-update-assigned-member-wrapper"});
		var stepassignee = $($(this).find(".step-assignee-wrapper")[0]).clone(); //드롭다운에서 선택된 멤버의 div태그를 복사
		var i= jQuery("<i>",{"class":"fas fa-times cancel-update-step-assignee"});
		$(stepassignee_wrapper).append(stepassignee,i);
		$("#update-step-assignee").append(stepassignee_wrapper);
		
	})
	
	/// step 담당자 호버 시 취소버튼 생성
	$(document).on("mouseenter",".step-update-assigned-member-wrapper",function(){
		$(".cancel-update-step-assignee").css("visibility","visible")
	}).on("mouseleave",".step-update-assigned-member-wrapper",function(){
		$(".cancel-update-step-assignee").css("visibility","hidden")
	});
	
	//// 스텝 담당자 취소시 초기화
	$(document).on('click',".cancel-update-step-assignee",function(){
		$(".step-update-assigned-member-wrapper").remove();
		$("#update-step-mgr-assignee").val("");
		$("#update-step-mgr-assignee-btn").show();
	})
	
    
    
    
    
    
    // 스텝 modal 에서 수정 버튼 클릭시
    $('#update-step-btn').click(function() {
        var updatestep = $('#update-step-form').serialize();       
        console.log(updatestep);
        if($(".update-step-name").val().trim() == ""){
			alert("스텝명을 입력하세요.");
			$(".update-step-name").focus();	
			return false;
		} else if($('#update-step-mgr-assignee').val() == ""){
			alert("책임자를 선택하세요.");
			return false;
		}
        $.ajax({
            type:"post",
            url:"updatestep.htm",
            data:updatestep,
            dataType:"json",
            success:function(data){
                console.log(data);
                console.log(data.updatestep);
                
                if(data.updatestep > 0){
                    alert('스텝 수정이 완료되었습니다!');
                    // 스텝 수정의 경우 이름이 변경되었을 경우만 고려하여 갱신해 주면 된다.
                    $($("#s"+data.stepDTO.sid).find(".side-content-name")).text(data.stepDTO.sname);
                    console.log($("#s"+data.stepDTO.sid).find(".side-content-name"));
               
                }else {
                    alert('스텝 수정이 실패되었습니다');
                }
                $('.close').click();
            }
        }); // end - ajax
    }); //end - event
   
    // side-bar 스텝 삭제 클릭시 
    $(document).on("click","#side-delete-step",function(){
    	var custom_menu =  $(this).parents("ul.custom-menu")[0];
    	var sid = $(custom_menu).find("input[name=sid]").val();
    	
    	console.log(sid); // 값이 찍힘
    	
    	$('#delete-step-sid').val(sid);
    });
    
    
    // 스텝 삭제 Modal 에서 삭제버튼 클릭시 발생
    $('#delete-step-btn').click(function() {
    	
    	var sid = $('#delete-step-sid').val();
    	
    	$.when(deletetaskinstep(sid)).done(function(data){
    		console.log(data);
    		
    		if(data.deletestep > 0){
                alert('스텝 삭제가 완료되었습니다!');
                $("#s"+sid).remove();
            }else {
            	 alert('스텝 삭제가 실패되었습니다');
            }
            $('.close').click();
            
    	}); // end -done
    });
    
    
    
}); // end - doc.on.ready



/**
 * 
 날      짜 : 2018. 6. 21.
 기      능 : step 수정시 step 정보 가져오기
 작성자명 : 김 래 영
 */
function stepinfo(sid){

   var ajax =  $.ajax({
        type:"post",
        url:"selectstep.htm",
         data:{sid:sid},
         dataType:"json",
         success:function(data){
             console.log(data);
             console.log(data.selectstep);
             
             $('.update-step-name').val(data.selectstep.sname);
             $('#update-step-sid').val(data.selectstep.sid);
             if(data.selectstep.sday != null) $('#update-step-sday-id').val(data.selectstep.sday.split(" ")[0]);
             if(data.selectstep.eday != null) $('#update-step-eday-id').val(data.selectstep.eday.split(" ")[0]);
             $('#update-step-detail').val(data.selectstep.detail);
             $('#update-step-methodologyid').val(data.selectstep.methodologyid);
             $('#update-step-pid').val(data.selectstep.pid);
             $('#update-step-fid').val(data.selectstep.fid);
         }
         
    }); //end -ajax1
   return ajax;
}

/**
 * 
 날      짜 : 2018. 6. 21.
 기      능 : step 삭제시 해당 sid 에 속한 task 삭제 (step 삭제시 선행되어야하는 부분)
 작성자명 : 김 래 영
 */
function deletetaskinstep(sid) {
	
	var sid = $('#delete-step-sid').val();
	console.log(sid);
	
	var ajax = $.ajax({
		type:"post",
		url:"deletestep.htm",
		data:{sid:sid},
		success:function(data) {
			var deletetaskinstep = data.deletetaskinstep;
			
			if(deletetaskinstep > 0) {
				console.log("task 삭제");
			}else {
				console.log("task 삭제 실패");
			}
		}
	}); // end - ajax
	return ajax;
}

