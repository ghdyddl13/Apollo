
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
function makeTimeline(tasks) {
	console.log(tasks)
	var gantt_chart = new Gantt(".gantt-target", tasks, {
		
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
