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
			}
		})
	})
	
	
	// 프로젝트 files 페이지
	$(document).on("click","#files-page",function(){	
		$.ajax({
			url:"files.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		});
	});
	
	// 스텝 list 페이지
	
	
	
	
});