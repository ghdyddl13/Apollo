
	
	function getGanttItems(sid){
		var tasks;
		$.ajax({
			url:"step/getTimelineTasks.htm"
			
		})
		
	};


	/**
	 * 
			날      짜 : 2018. 6. 12.
			기      능 : 타임라인 간트차트 생성 
			작성자명 : 박 민 식
	 */
	function makeTimeline(tasks){
		var gantt_chart = new Gantt(".gantt-target", tasks, {
			on_click: function (task) {
				console.log(task);
			},
			on_date_change: function(task, start, end) {
				console.log(task, start, end);
			},
			on_progress_change: function(task, progress) {
				console.log(task, progress);
			},
			on_view_change: function(mode) {
				console.log(mode);
			}
		});
	}
