<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Report</title>
<link href="css/report.css" type="text/css" rel="stylesheet">
</head>
<body>
<br>
<h4>Report</h4>
<br>

<div id="background_div">

	<div class="row">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
		<div class="col-lg-1" >
		</div>
	
		<!-- 진행중인 task -->
		<div class="col-lg-2 btn_div report_progress">
			<br>진행중인 Task<br>
			<img style="margin-right: 10px" class="jpg_images" src="img/report/report_progress_stop.jpg">
			<img class="gif_images" src="img/report/report_progress.gif">
		</div>
		
		<!-- Task 상태별 현황 -->
		<div class="col-lg-2 btn_div report_status" >
			<br>Task 상태별 현황<br>
			<img style="margin-right: 10px" class="jpg_images" src="img/report/report_progress_stop.jpg">
			<img class="gif_images" src="img/report/report_progress.gif">
		</div>
		
		<!-- 마감기한 지난 Task -->
		<div class="col-lg-2 btn_div report_expired">
			<br>마감기한이 지난 Task<br>
			<img style="margin-right: 10px" class="jpg_images" src="img/report/report_progress_stop.jpg">
			<img class="gif_images" src="img/report/report_progress.gif">
		</div>
		
		<!-- 마감기한이 임박한 Task -->
		<div class="col-lg-2 btn_div report_drawnear">
			<br>마감기한이 임박한 Task<br>
			<img style="margin-right: 10px" class="jpg_images" src="img/report/report_progress_stop.jpg">
			<img class="gif_images" src="img/report/report_progress.gif">
		</div>
		
		<!-- 담당자 미정 Task -->
		<div class="col-lg-2 btn_div report_unassigned">
			<br>담당자 미정 Task<br>
			<img style="margin-right: 10px" class="jpg_images" src="img/report/report_progress_stop.jpg">
			<img class="gif_images" src="img/report/report_progress.gif">
		</div>
		
		<div class="col-lg-1" >
		</div>
		
	</div> <!-- end/row -->
	
</div> <!-- end/background_div -->

</body>
</html>