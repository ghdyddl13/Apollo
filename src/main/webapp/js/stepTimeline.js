
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
			console.log(task);
		},
		on_date_change : function(task, sday, eday) {
			console.log(task, sday, eday);
		},
		on_progress_change : function(task, progress) {
			console.log(task, progress);
		},
		on_view_change : function(mode) {
			console.log(mode);
		}
	});
}
