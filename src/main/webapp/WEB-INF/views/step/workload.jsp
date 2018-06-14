<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<link rel="stylesheet" href="css/frappe-gantt.css" />
	<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
	<script src="js/frappe-gantt.js"></script>
	<script>
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
				start: '2018-10-04',
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
				end: '2018-10-11',
				name: 'Go Live!',
				id: "Task 5",
				//custom_class: 'bar-milestone'
			},
		]
		var gantt_chart = new Gantt(".gantt-target", tasks, {
			on_click: function (task) {
				//console.log(task); 해당 task 정보 
			},
			on_date_change: function(task, start, end) {
				//console.log(task, start, end);
			},
			on_progress_change: function(task, progress) {
				//console.log(task, progress);
			},
			on_view_change: function(mode) {
				console.log(mode); //날짜 설정 -> 현재 Day
			}
		});
		//console.log(gantt_chart); //gantt_chart 세부정보
	</script>
	<div class="col-lg-3">
		<div class="container-fluid row">
		</div>	
	</div>
	<div class="col-lg-9">
		<div class="gantt-target"></div>
	</div>