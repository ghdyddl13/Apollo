<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="css/common.css" type="text/css" rel="stylesheet">    
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.ganttView.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/date.js"></script>
<script type="text/javascript" src="js/data.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.4.js"></script>
<script type="text/javascript" src="js/jquery.ganttView.js"></script>
	<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/inc/sidebar.jsp"></jsp:include>
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