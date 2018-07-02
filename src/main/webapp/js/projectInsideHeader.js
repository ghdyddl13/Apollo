$(function() {

	
	
	/// 프로젝트 information 페이지
	$("#information-page").click(function(evt){
		
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
	$("#table-page").click(function(evt){
		$.ajax({
			url:"table.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				console.log($('.project-table-duration').val());
				if($('.project-table-duration').val()) {
					$('.project-table-duration').val(" ");
				}
			}
		})
	})
	
	
	// 프로젝트 files 페이지
	$("#files-page").click(function(evt){

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