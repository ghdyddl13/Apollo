<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<link rel="stylesheet"href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- chart js 를 위한 cdn-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!-- ECHART.JS CDN-->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>

<!-- SIDEBAR JAVASCRIPT -->
<script type="text/javascript" src="js/sidebar.js"></script>
<!-- AWESOMEFONT를 위한 cdn -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

<!-- 공통 JavaScript 함수 모음(Utils) -->
<script type="text/javascript" src="js/utils.js"></script>
<!-- Timeline관련  -->
<script type="text/javascript" src="js/stepTimeline.js"></script>
<link rel="stylesheet" href="css/stepTimeline.css" />
<link rel="stylesheet" href="css/inbox.css" />
<link rel="stylesheet" href="dist/frappe-gantt.css" />
<script src="dist/frappe-gantt.js"></script>

<!-- File 관련 script -->
<script type="text/javascript" src="js/projectFile.js"></script>

<!-- Board관련 script-->
<script type="text/javascript" src="js/stepBoard.js"></script>

<!-- 공통 CSS -->
<link href="css/common.css" type="text/css" rel="stylesheet">

<!--MY WORK CSS  -->
<link href="css/myWork.css" type="text/css" rel="stylesheet">

<!-- PROJECT CSS -->
<link href="css/project.css" type="text/css" rel="stylesheet">

<!-- SIDEBAR JAVASCRIPT _RY -->
<script type="text/javascript" src="js/sidebar_raeyoung.js"></script>

<!-- Project Page Table 관련 -->
<script type="text/javascript" src="js/project_table.js"></script>
<link rel="stylesheet" href="css/project_table.css" />

<!-- stream css -->
<link href="css/stream.css" type="text/css" rel="stylesheet">

<!-- task CSS -->
<link href="css/task.css" type="text/css" rel="stylesheet">

<!-- STEP LIST CSS_ JW -->
<link href="css/stepList.css" type="text/css" rel="stylesheet">
<!-- STEP STARREDWORK CSS_ JW -->
<link href="css/starredWork.css" type="text/css" rel="stylesheet">
<!-- Header Edit Profile_ RY -->
<link href="css/profileheader.css" type="text/css" rel="stylesheet">
<!-- Header Member List_ RY -->
<link href="css/memberlist.css" type="text/css" rel="stylesheet">
<!-- STEP LIST JAVASCRIPT_ JW-->
<script type="text/javascript" src="js/steplist.js?q=123"></script>
<!-- Board관련 css -->
<link rel="stylesheet" href="css/stepBoard.css" />

<!-- File관련 css -->
<link rel="stylesheet" href="css/projectFile.css" />  

<!-- main에 있어야 할 Task js -->
<script type="text/javascript" src="js/main_task.js"></script>

<!-- Report -->
<script type="text/javascript" src="js/report.js"></script>
<!--projectInsideHeader  -->
<script type="text/javascript" src="js/projectInsideHeader.js"></script>
<!--stepInsideHeader  -->
<script type="text/javascript" src="js/stepInsideHeader.js"></script>    
<title>Project Apollo</title>


<style>



</style>

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