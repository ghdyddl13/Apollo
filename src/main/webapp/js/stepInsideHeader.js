$(function() {
	

	/// step list 페이지
	$("#list-page").click(function(evt){

		var sid = $("#current-sid").val();
		

		$.ajax({
			url:"list.htm",
			data:{sid:sid},
			dataType:"html",
			success:function(data){

				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
		
	})
	
	
	// 스텝 board 페이지
	$("#board-page").click(function(evt){
		$.ajax({
			url:"board.htm",
			dataType:"html",
			success:function(data){
				console.log
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	
	// 스텝 timeline 페이지
	$("#timeline-page").click(function(evt){
		$.ajax({
			url:"step/timeline.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
				/*var tasks = getGanttItems(3);*/
				
				var data = getGanttItems(3);
				
				console.log(data.tasks);
				makeTimelineTable(data.tasks);
				makeTimelineGantt(data.tasks);
			}
		})
	})
	
	// 스텝 workload 페이지
	$("#workload-page").click(function(evt){
		$.ajax({
			url:"workload.htm",
			dataType:"html",
			success:function(data){
			
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	
	
	
});