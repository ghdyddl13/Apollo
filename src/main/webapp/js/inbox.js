$(function() {
	/**
	 * 
	 날      짜 : 2018. 7. 9.
	 기      능 : 인박스에서 X버튼 눌렀을때 서드패널에서 사라짐
	 작성자명 : 신 호 용
	 */
	$(document).on("click","#task_dismiss_btn_inbox",function(){
		$(".modal-content2").hide();
		$(".starred-secondbody-image").show();
	});
	
	

	
	
		$(document).on("click","#incomming-page",function(evt){	
		$.ajax({
			url:"inbox.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$('#incomming-page').css('border-bottom','2px solid transparent');
				$('#incomming-page').css('border-color','#286cb0');
			}
		})
	});
	
	$(document).on("click","#sent-page",function(evt){	
		
		$.ajax({
			url:"sent.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$('#sent-page').css('border-bottom','2px solid transparent');
				$('#sent-page').css('border-color','#286cb0');
			}
		})
	});
	$(document).on("click","#archive-page",function(evt){	
		
		$.ajax({
			url:"archive.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$('#archive-page').css('border-bottom','2px solid transparent');
				$('#archive-page').css('border-color','#286cb0');
			}
		})
	});
	$(document).on("click",".archiveupdate",function(evt){	
		evt.stopPropagation();
		$.ajax({
			url:"archiveupdate.htm",
			dataType:"html",
			type: 'POST',
			data:{cmtid:$(this).children(".cmtid").val(),
				  inboxkind:$("#inboxkind").val()},
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				if($('#inboxkind').val()== 'incomming'){
					$('#incomming-page').css('border-bottom','2px solid transparent');
					$('#incomming-page').css('border-color','#286cb0');
				}else if($('#inboxkind').val()== 'sent'){
					$('#sent-page').css('border-bottom','2px solid transparent');
					$('#sent-page').css('border-color','#286cb0');
				}else{
					$('#archive-page').css('border-bottom','2px solid transparent');
					$('#archive-page').css('border-color','#286cb0');
				}
			
			}
		})
	});
	
	$(document).on("click",".archiveupdate2",function(evt){	
		evt.stopPropagation();
		$.ajax({
			url:"archiveupdate2.htm",
			dataType:"html",
			type: 'POST',
			data:{cmtid:$(this).children(".cmtid").val(),
				  inboxkind:$("#inboxkind").val()},
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$('#archive-page').css('border-bottom','2px solid transparent');
				$('#archive-page').css('border-color','#286cb0');
			}
		})
	});
	
	$(document).on("click","#cmt_insert",function(evt){	
		$.ajax({
			url:"insertcomment.htm",
			dataType:"html",
			type: 'POST',
			data:{comments:$("#cmt_cmts").val(),
				  tid:$("#cmt_tid").val()},
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});

});