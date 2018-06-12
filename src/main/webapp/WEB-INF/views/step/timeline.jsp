<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
 <h3>타임라인</h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
<nav class="navbar inside-header ">
   <div class="container-fluid ">

   </div>
</nav>
<link rel="stylesheet" href="dist/frappe-gantt.css" />
<script src="dist/frappe-gantt.js"></script>
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
		console.log(gantt_chart);
	</script>
	<style>
		.timeline-div{
			padding:0px;
			height: 100%
		}
		
		.timeline-div thead, thead>tr{
			height:60px;
			padding: 5px;
		}
	
	 	.table-responsive thead>tr>th{
			text-align: center;
			font-size: 15px;
			padding-bottom:14px 
		}
		
		.table-responsive tbody>tr{
			height:38px;
			cursor:pointer;
			
		}
		
		.gantt-target{
			height: 100%
		}
	
		
		#timeline-table, #timeline-table >tr, td, th, thead, tbody{
			border: 0.5px solid rgba(0, 0, 0, 0.08);
			border-collapse: collapse;
		
		}
	
		#timeline {
			padding-left:20px;
			padding-right:20px;
			height: calc(100% - 170px)
		}
		
	</style>
<div class="container-fluid row" id="timeline">
<div class="col-lg-3 timeline-div" >
		<div class="table-responsive">
			<table style="border-right:none" class="table timeline-table table-hover" id="timeline-table" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>No</th>
						<th>Task이름</th>
						<th>상태</th>
						<th>담당자</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>No</td>
						<td>Task이름</td>
						<td>상태</td>
						<td>담당자</td>
					</tr>
					<tr>
						<td>No</td>
						<td>Task이름</td>
						<td>상태</td>
						<td>담당자</td>
					</tr>
					<tr>
						<td>No</td>
						<td>Task이름</td>
						<td>상태</td>
						<td>담당자</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<div class="col-lg-9 timeline-div">
<div class="gantt-target"></div>
</div>
</div>	