<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<!--   CSS   -->
<!--BOOTSTRAP  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--JQUERY UI  -->
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<link rel="stylesheet"href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--AWESOMEFONT CDN -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<!-- 공통 CSS -->
<link href="css/common.css" type="text/css" rel="stylesheet">
<!--TIMELINE-->
<link rel="stylesheet" href="css/stepTimeline.css" />
<!--INBOX  -->
<link rel="stylesheet" href="css/inbox.css" />
<!--GANTT CHART  -->
<link rel="stylesheet" href="dist/frappe-gantt.css" />
<!--MY WORK CSS  -->
<link href="css/myWork.css" type="text/css" rel="stylesheet">
<!-- PROJECT CSS -->
<link href="css/project.css" type="text/css" rel="stylesheet">
<!-- PROJECT PAGE -->
<link rel="stylesheet" href="css/project_table.css" />
<!-- STREAM CSS -->
<link href="css/stream.css" type="text/css" rel="stylesheet">
<!-- TASK CSS -->
<link href="css/task.css" type="text/css" rel="stylesheet">
<!-- STEP LIST CSS-->
<link href="css/stepList.css" type="text/css" rel="stylesheet">
<!-- STEP STARREDWORK CSS-->
<link href="css/starredWork.css" type="text/css" rel="stylesheet">
<!-- Header Edit Profile-->
<link href="css/profileheader.css" type="text/css" rel="stylesheet">
<!-- Header Member List-->
<link href="css/memberlist.css" type="text/css" rel="stylesheet">
<!-- Board관련 CSS -->
<link rel="stylesheet" href="css/stepBoard.css" />
<!-- File관련 CSS -->
<link rel="stylesheet" href="css/projectFile.css" />  


<!--   JAVASCRIPT   -->
<!--JQUERY CDN-->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--BOOTSTRAP CDN-->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--CHART JS CDN-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!-- ECHART.JS CDN-->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<!-- SIDEBAR JAVASCRIPT -->
<script type="text/javascript" src="js/sidebar.js"></script>
<!-- 공통 JavaScript 함수 모음(Utils) -->
<script type="text/javascript" src="js/utils.js"></script>
<!--TIMELINE JAVASCRIPT  -->
<script type="text/javascript" src="js/stepTimeline.js"></script>
<!--GANTT CHART  -->
<script src="dist/frappe-gantt.js"></script>
<!-- File 관련 script -->
<script type="text/javascript" src="js/projectFile.js"></script>
<!-- Board관련 script-->
<script type="text/javascript" src="js/stepBoard.js"></script>
<!-- SIDEBAR JAVASCRIPT-->
<script type="text/javascript" src="js/sidebar_raeyoung.js"></script>

<link rel="stylesheet" href="css/project_table.css" />

<!-- stream css -->
<link href="css/stream.css" type="text/css" rel="stylesheet">

<!-- task CSS -->
<link href="css/task.css" type="text/css" rel="stylesheet">

<!-- STEP LIST CSS_ JW -->
<link href="css/stepList.css" type="text/css" rel="stylesheet">
<!-- Header Edit Profile_ RY -->
<link href="css/profileheader.css" type="text/css" rel="stylesheet">
<!-- Header Member List_ RY -->
<link href="css/memberlist.css" type="text/css" rel="stylesheet">
<!-- STEP LIST JAVASCRIPT_ JW-->
<script type="text/javascript" src="js/steplist.js?q=123"></script>
<!-- MAIN Task js -->
<script type="text/javascript" src="js/main_task.js"></script>
<!-- Report -->
<script type="text/javascript" src="js/report.js"></script>
<!--projectInsideHeader-->
<script type="text/javascript" src="js/projectInsideHeader.js"></script>
<!--stepInsideHeader-->
<script type="text/javascript" src="js/stepInsideHeader.js"></script>

<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<title>Project Apollo</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/inc/modalPages.jsp"></jsp:include>
	<div class="Apollo-main">
		<div class="main-header-panel" >
			<jsp:include page="/WEB-INF/views/inc/header.jsp"></jsp:include>
		</div>
		<div class="main-body-panel">
			<div class="main-side-panel" >
				<jsp:include page="/WEB-INF/views/inc/sidebar.jsp"></jsp:include>
			</div >
			<div  class="main-box-panel">
				<div  id="main-box"></div>
			</div>	
		</div>
	</div>
</body>
</html>