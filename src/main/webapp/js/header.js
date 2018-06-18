$(function() {
	// 헤더 inbox 페이지
	$("#inbox-page").click(function(evt){
		$.ajax({
			url:"inbox.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	//// 헤더 mywork 페이지
	$("#myWork-page").click(function(evt){
		$.ajax({
			type:"GET",
			url:"myWork.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	
	//// 헤더 starred task 페이지
	$("#starredTask-page").click(function(evt){
		$.ajax({
			url:"starredTask.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	
	//// 헤더 report 페이지
	$("#report-page").click(function(evt){
		$.ajax({
			url:"report.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	
	//// 헤더 stream 페이지
	$("#stream-page").click(function(evt){
		$.ajax({
			url:"stream.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
});