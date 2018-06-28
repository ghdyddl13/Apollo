$(function() {
	
	
	// 스텝 우클릭하여 수정 버튼 클릭시 이벤트
    $(document).on("click","#side-update-step",function(){
        var custom_menu =  $(this).parents("ul.custom-menu")[0];
        var sid = $(custom_menu).find("input[name=sid]").val();
        var fid = $(custom_menu).find("input[name=fid]").val();
        var pid = $(custom_menu).find("input[name=pid]").val();

        $.when(stepinfo(sid)).done(function(data){
        	var defaultmid = data.selectstep.mid;

        	$.ajax(
                    {
                    type : "post",
                    url  : "getprojectmembers.htm",
                    data : "pid="+ pid, 
                    success : function(data){
                    	console.log(data.memberlist);
                        //해당 pid 에 참여한 멤버 불러오기
                        $('#update-step-mgr-assignee').empty();
                        $(data.memberlist).each(function (){
                           var option = jQuery("<option>",{
                              "value":this.mid,
                              "text":this.mname
                           })
                           if(this.mid = defaultmid) {
                        	   $('#update-step-mgr-assignee').prepend(option);
                           }else {
                        	   $('#update-step-mgr-assignee').append(option);
                           }
                        });
                       
                    } // end-success
                 }); // end-ajax        	
             	   
        	
        	}); // end done
        

    }); //end side-bar step btn
    
    // 스텝 modal 에서 수정 버튼 클릭시
    $('#update-step-btn').click(function() {
        var updatestep = $('#update-step-form').serialize();       
        console.log(updatestep);
        
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
                    var step = makeSideStep(data.stepDTO);
                    
                    if($('#insert-step-fid').val() == "") {
						(step.sname=="백로그")?$(step).prependTo("#p-dir"+step.pid):$(step).appendTo("#p-dir"+step.pid);
						$(".close").click();
					} else {
						step.appendTo("#f-dir"+step.fid);
						$(".close").click();
					}
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

