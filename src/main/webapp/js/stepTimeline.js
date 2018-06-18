
/**
 * 
 날      짜 : 2018. 6. 12.
 기      능 : 간트차트에 뿌릴 데이터 가져오는 함수
 작성자명 : 박 민 식
 */
function getGanttItems(sid) {
	var tasks;
	$.ajax({
		url : "step/getTimelineTasks.htm",
		data : {
			sid : sid
		},
		async:false,
		dataType : "json",
		success : function(data) {
			tasks = data;
		},
		
	});
	return tasks;
};

/**
 * 
 * 날 짜 : 2018. 6. 12.
 * 기 능 : 타임라인 간트차트 생성 
 * 작성자명 : 박 민 식
 */
function makeTimelineGantt(tasks) {
	var ganttdatabundle =[];
	// 간트차트에 들어 갈 데이터 가공 작업
	$(tasks).each(function(index,item){
		var ganttdata={
				start:item.sday,	
				end:item.eday,
				name:item.tname,
				id: 'Task '+item.tid,
		 };

		ganttdatabundle.push(ganttdata);
	})
	
	$(".gantt-target").empty();
	var gantt_chart = new Gantt(".gantt-target", ganttdatabundle, {
		
		on_click : function(task) {
			var array =task.id.split(" ");
			console.log("test")
			console.log(array[1]);
		},
		on_date_change : function(task, sday, eday) {
			var array =task.id.split(" ");
			console.log(task, sday, eday);
			////////////////// 드래그로 Task 날짜 변경하기
			
			var data = {tid: array[1],
						sday: sday.toLocaleDateString(),
						eday: eday.toLocaleDateString()
						}
			$.ajax({
				url: "updateTask.htm",
				data:data,
				dataType:"html",
				success:function(data){
					console.log(data);					
				}				
			})
		},
		on_progress_change : function(task, progress) {
			console.log(task, progress);
		},
		on_view_change : function(mode) {
			console.log(mode);
		}
	});
}

/**
 * 
 날   짜 : 2018. 6. 15.
 기   능 : 타임라인 task table 구성하는 함수
 작성자명 : 박 민 식
 */
function makeTimelineTable(tasks){
	var no = 1;
	$("#timeline-table > tbody").empty();
	$(tasks).each(function(index,item){
	
		//좌측 테이블에 들어갈 데이터 작업
	
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
		var td_assingee= jQuery("<td>",{"css":{"padding":"1px"}}).append(getTaskAssignees(item.tid,"27px"));
		$(row).append(td_no);
		$(row).append(td_tstatus);
		$(row).append(td_tname);
		$(row).append(td_assingee);
		$("#timeline-table > tbody").append(row);
	})
}


/**
 * 
 날   짜 : 2018. 6. 15.
 기   능 : 타임라인 Task 상태로 필터걸어주는 함수 
 작성자명 : 박 민 식
 */
$(document).on("change","#timeline-tstatus-filter",function(){
	
	var data = getGanttItems($("#current-sid").val());
	var tasks = data.tasks;
	if($(this).val()=="all"){
		makeTimelineTable(tasks);
		makeTimelineGantt(tasks);
	}else{
		var selecedtasks = [];
		$(tasks).each(function(index,item){
			console.log(item);
			if(item.statusid==$("#timeline-tstatus-filter").val()){
				selecedtasks.push(item);
			}
		})
		makeTimelineTable(selecedtasks);
		makeTimelineGantt(selecedtasks);
	}
	
})




