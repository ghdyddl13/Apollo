$(function() {

	
	
	/// 프로젝트 information 페이지
	$("#information-page").click(function(evt){
		$.ajax({
			url:"information.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
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
				//console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
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
		})
	})
	
	// 스텝 list 페이지
	
	
	
	
});