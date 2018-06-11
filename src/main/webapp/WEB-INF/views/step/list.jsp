<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.ganttView.css" />
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/date.js"></script>
<script type="text/javascript" src="js/data.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.4.js"></script>
<script type="text/javascript" src="js/jquery.ganttView.js"></script>

<nav class="navbar inside-header">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li class="nav-item"><a id="step1" class="nav-link header-menu step1">List</a></li>
			<li class="nav-item"><a id="board" class="nav-link header-menu board">Board</a></li>
			<li class="nav-item"><a id="timeline" class="nav-link header-menu timeline">Timeline</a></li>
			<li class="nav-item"><a class="nav-link header-menu" href="workload.htm">Workload</a></li>
		</ul>
	</div>
</nav>

<div id="ganttChart"></div>

<script>
$(function() {
	$("#ganttChart").ganttView(
		{
			data : ganttData ,// data.js에 있는 샘플 데이터 
			slideWidth : '100%',
			showWeekends : true,
			behavior : {
			
				onClick : function(data) {
					var msg = "You clicked on an event: { start: "
							+ data.start.toString("M/d/yyyy")
							+ ", end: "
							+ data.end.toString("M/d/yyyy") + " }";
					$("#eventMessage").text(msg);
					console.log(data); 
				},
				onResize : function(data) {
					var msg = "You resized an event: { start: "
							+ data.start.toString("M/d/yyyy")
							+ ", end: "
							+ data.end.toString("M/d/yyyy") + " }";
					$("#eventMessage").text(msg);
					console.log(data); 
				},
				onDrag : function(data) {
					var msg = "You dragged an event: { start: "
							+ data.start.toString("M/d/yyyy")
							+ ", end: "
							+ data.end.toString("M/d/yyyy") + " }";
					$("#eventMessage").text(msg);
				}
			}
	});
});
</script>
