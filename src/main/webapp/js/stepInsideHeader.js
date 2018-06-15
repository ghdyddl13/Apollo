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
				var tasks= [];
				var no = 1;
				$(data.tasks).each(function(index,item){
					// 간트차트에 들어 갈 데이터 가공 작업
					var ganttdata={
							start:item.sday,	
							end:item.eday,
							name:item.tname,
							id: 'Task '+item.tid,
					 };
					tasks.push(ganttdata);
					
					//좌측 테이블에 들어갈 데이터 작업
					/*
					 <tr>
						<td>No</td>
						<td>Task이름</td>
						<td>상태</td>
						<td>담당자</td>
					</tr>
					 
					 */
					var row = jQuery("<tr>");
					var td_no = jQuery("<td>",{"text": no++}) 
					var td_tname = jQuery("<td>",{"text": item.tname,"class":"timeline-task-edit","id":item.tid})
					var td_tstatus = jQuery("<td>",{"text": item.tstatus,
													"css":{"background-color":item.color,
														   "color":"white",
														   "font-size":"10px",
														   "padding-left":"0px",
														   "padding-right":"0px",
														   "text-align":"center"}});
					var td_assingee= jQuery("<td>",{"css":{"padding":"1px"}}).append(getTaskAssignees(item.tid,"25px"));
					$(row).append(td_no);
					$(row).append(td_tstatus);
					$(row).append(td_tname);
					$(row).append(td_assingee);
					$(row).appendTo("#timeline-table > tbody");
				})

				makeTimeline(tasks);
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