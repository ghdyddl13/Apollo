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
					$('#proejct-page-table-no-data').remove();
				}
				
			}
		})
	})
	
	
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