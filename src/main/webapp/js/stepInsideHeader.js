$(function() {
	

	/// step list 페이지
	$(document).on("click","#list-page",function(){
		var sid = $("#current-sid").val();

		$.ajax({
			url:"list.htm",
			data:{sid:sid},
			beforeSend:function(){
				$('#main-box').html(loadingpage);
			},
			dataType:"html",
			success:function(data){

				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
		
	})
	// 스텝 board 페이지
	$(document).on("click","#board-page",function(){
		$.ajax({
			url:"board.htm",
			dataType:"html",
			beforeSend:function(){
				$('#main-box').html(loadingpage);
			},
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				doDraggable();
				$("#board-page").removeClass("main-body-onepannel-header-top-selector").addClass("main-body-onepannel-header-top-selected");
			}
		})
	})
	
	
	// 스텝 timeline 페이지
	$(document).on("click","#timeline-page",function(){
		
		$.when(getGanttItems()).done(function(ajax){
			console.log(ajax);
			console.log(ajax.tasks);
			
			$.ajax({
				url:"step/timeline.htm",
				dataType:"html",
				beforeSend:function(){
					$('#main-box').html(loadingpage);
				},
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					$("#timeline-page").removeClass("main-body-onepannel-header-top-selector").addClass("main-body-onepannel-header-top-selected");
					makeTimelineTable(ajax.tasks);
					makeTimelineGantt(ajax.tasks);
					/*$(".modal-question").tooltip({
						 classes: {
							    "ui-tooltip": "apollo-tooltip"
						 },
						 content: function() {
						       return "<div>test</div>";
						      }
					 });   */
				}
			})
		})
	})
	
	
	
});