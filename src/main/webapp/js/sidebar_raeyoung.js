$(function() {
	
	
	// 스텝 우클릭하여 수정 버튼 클릭시 이벤트
    $(document).on("click","#side-update-step",function(){
        var custom_menu =  $(this).parents("ul.custom-menu")[0];
        var sid = $(custom_menu).find("input[name=sid]").val();
        var fid = $(custom_menu).find("input[name=fid]").val();
        var pid = $(custom_menu).find("input[name=pid]").val();
        console.log(sid);
        console.log(pid);
        //console.log(fid);

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
             	   
        	//console.log(data.selectstep.mid);
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
                }else {
                    alert('스텝 수정이 실패되었습니다');
                }
                $('.close').click();
            }
        }); // end - ajax
    }); //end - event
   
    
    
    
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
             $('#update-step-sday-id').val(data.selectstep.sday.split(" ")[0]);
             $('#update-step-eday-id').val(data.selectstep.eday.split(" ")[0]);
             $('#update-step-detail').val(data.selectstep.detail);
             $('#update-step-methodologyid').val(data.selectstep.methodologyid);
             $('#update-step-pid').val(data.selectstep.pid);
             $('#update-step-fid').val(data.selectstep.fid);
         }
         
    }); //end -ajax1
   return ajax;
}

