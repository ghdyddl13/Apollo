
	
	/**
	 * 
 		날      짜 : 2018. 6. 12.
 		기      능 : Time
 		작성자명 : 박 민 식
	 */
var tasks = [
			{
				start: '2018-10-01',
				end: '2018-10-08',
				name: 'Redesign website',
				id: "Task 0",
	
			},
			{
				start: '2018-10-03',
				end: '2018-10-06',
				name: 'Write new content',
				id: "Task 1",
		
			
			},
			{
				start: '2017-10-04',
				end: '2018-10-08',
				name: 'Apply new styles',
				id: "Task 2",
		
		
			},
			{
				start: '2018-10-08',
				end: '2018-10-09',
				name: 'Review',
				id: "Task 3",
		
		
			},
			{
				start: '2018-10-08',
				end: '2018-10-10',
				name: 'Deploy',
				id: "Task 4",
			
			},
			{
				start: '2018-10-11',
				end: '2019-10-11',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
			
				custom_class: 'bar-milestone'
			},
			{
				start: '2018-10-11',
				end: '2019-10-11',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
			
				custom_class: 'bar-milestone'
			},
			{
				start: '2018-10-11',
				end: '2019-10-11',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
			
				custom_class: 'bar-milestone'
			},
			{
				start: '2018-10-11',
				end: '2019-10-11',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
			
				custom_class: 'bar-milestone'
			},
			{
				start: '2018-10-11',
				end: '2019-10-11',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
				custom_class: 'bar-milestone'
			},
			{
				start: '2018-10-11',
				end: '2019-10-11',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
			
				custom_class: 'bar-milestone'
			},
			
		]

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
