$(function() {	
	/// 프로젝트 information 페이지
	$(document).on("click","#information-page",function(){
		var pid = $('#pidhidden').attr('value');
		
		$.ajax({
			url:"information.htm",
			data: "pid=" + pid,
			dataType:"html",
			success:function(data){
				 $("#main-box").empty();
				 $("#main-box").append(data);	 		
				 $("#information-page").removeClass("main-body-onepannel-header-top-selector").addClass("main-body-onepannel-header-top-selected");
			}
		})
		
	})
	
	
	// 프로젝트 table 페이지
	$(document).on("click","#table-page",function(){
		$.ajax({
			url:"table.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$("#table-page").removeClass("main-body-onepannel-header-top-selector").addClass("main-body-onepannel-header-top-selected");
				//step 또는 folder data 가 존재하지 않을 경우 이미지로 대체 
				if($('#project-page-tbody').children().length != 0) {
					$('#project-page-table-no-data').remove();
				}
			}
		})
	})
	
	// 프로젝트 table 페이지에서 select로 tstatus 변경 시 
	$(document).on("change","#table-status-button",function(){
		var status = $('#table-status-button').val(); //select 하위 option value 값
		if(status == 'all'){ // option value 가 all 일 경우
			$(document).find('.project-table-tr-tasks').show(); // 모든 task 보여주기
		}else { 
			if($(document).find('.project-table-td-tstatus:contains('+status+')').length != 0){ // task 에서 tstatus 가 있을 경우 
				$(document).find('.project-table-tr-tasks').show(); //먼저 전체보여주기 실행하고
				//선택한 해당 task 이외의 값은 숨겨줌
				$(document).find('.project-table-td-tstatus:not(:contains('+status+'))').parent('.project-table-tr-tasks').hide(); 
			}else {
				// 해당 task 가 없을 경우  
				alert('해당 task가 없습니다.');
			}
		}		
	});

	
	// 프로젝트 files 페이지
	$(document).on("click","#files-page",function(){	
		$.ajax({
			url:"files.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$("#files-page").removeClass("main-body-onepannel-header-top-selector").addClass("main-body-onepannel-header-top-selected");
			}
		});
	});
});