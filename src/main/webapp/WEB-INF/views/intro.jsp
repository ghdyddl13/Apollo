<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Project Apollo</title>
<meta charset="utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- INTRO CSS -->
<link href="css/intro.css" type="text/css" rel="stylesheet">
<link href="css/common.css" type="text/css" rel="stylesheet">
<!-- AWESOMEFONT를 위한 cdn -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

<link rel="shortcut icon" href="img/FAVINEW.ico">
<link rel="icon" href="img/FAVINEW.ico" sizes="16x16">

<script>
$(function() {
	$('#intro-login-btn').click(function() {
		location.href="login.htm";
	});
	$('#intro-main-btn').click(function() {
		location.href="main.htm";
	})
	
});

  </script>
<style>
.carousel-inner>img {
	width: 100%; /* Set width to 100% */
	height: 300px;
}
</style>
</head>
<body>
<!-- header -->
<nav class="navbar navbar-default navbar-fixed-top" id="header">
	<div>
		<div class="navbar-header container-fluid">
			<a href="index.htm"><img id="gohome" class="header_logo"
				src="img/apollo_logo.png" /></a>
		</div>
		<div class="nav navbar-nav navbar-right" id="intro-navber-btns">
			<c:choose>
				<c:when test="${sessionScope.mid eq null}">
					<input type="button" class="btn" value="로그인" id="intro-login-btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<input type="button" class="btn" value="메인으로" id="intro-main-btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn" value="로그아웃" id="intro-login-btn">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>

	<div>
		<!-- 최상단 img -->
		<div id="intro-page-top">
			<div>
				<div class="intro-page-top-img">
					<div class="intro-page-top-img-content">
						<div id="intro-page-top-img-content-logo">Welcome To Apollo</div>
						<br>
						<div id="intro-page-top-img-content-sub">Much Easier, Faster</div>
					</div>
					<div class="intro-page-top-img-cover"></div>
				</div>
			</div>
		</div>
		<!-- Apollo 페이지 강점 (3가지) -->
		<div class="intro-page-three-content">
			<div class="intro-page-content">
				<div class="intro-page-img">
					<i class="fas fa-random intro-check-img"></i>
				</div>
				<div class="intro-page-titleandcontent">
					<h3>자율적인 프로젝트 생성</h3>
					<br>
					<div class="intro-page-content">
						커스텀마이징 가능한 프로젝트 생성으로<br>팀 동료와의 협업을 향상시킬 수 있습니다.
					</div>
				</div>
			</div>
			<div class="intro-page-content">
				<div class="intro-page-img">
					<i class="fas fa-book-open intro-check-img"></i>
				</div>
				<div class="intro-page-titleandcontent">
					<h3>오늘 해야할 업무 관리</h3>
					<br>
					<div class="intro-page-content">
						기본적으로 오늘 해야할 업무를 확인하고,<br>중요한 업무는 별도로 관리하세요.
					</div>
				</div>
			</div>
			<div class="intro-page-content">
				<div class="intro-page-img">
					<i class="fas fa-file-alt intro-check-img"></i>
				</div>
				<div class="intro-page-titleandcontent">
					<h3>체계적인 파일 관리시스템</h3>
					<br>
					<div class="intro-page-content">
						찾기 어려운 파일을 한눈에 볼 수 있습니다.<br>더 이상 파일을 찾느라 시간을 허비하지 않아도 됩니다.
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div>
				<div id="intro-page-slide" class="carousel slide" data-ride="carousel">
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<div class="intro-page-slide-img1">
								<div class="carousel-caption">
									<h1>한눈에 일정을 확인하는 '타임라인'</h1>
									<h3>프로젝트의 업무일정 및 진행상태를 한번에 확인하세요!</h3>
								</div>
								<br> 
								<img src="img/timeline.png">
							</div>
						</div>

						<div class="item">
							<div class="intro-page-slide-img2">
								<div class="carousel-caption">
									<h1>시각화된 그래프로 보는 '리스트'</h1>
									<h3>급한 업무부터 다음주 업무까지 그래프로 확인할 수 있습니다</h3>
								</div>
								<br>
								<img src="img/list.png">
							</div>
						</div>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#intro-page-slide"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#intro-page-slide"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
		<hr>
		<!-- Tutorial Video -->
		<div class="container text-center">
			<h1>Intro Video</h1>
			<br>
			<div class="video-container">
				<div class="jetpack-video-wrapper">
					<span class="embed-youtube"	style="text-align: center; display: block;">
						<iframe width="850" height="500" src="http://www.youtube.com/embed/GlFOM2zTRcU" frameborder="0" allowfullscreen></iframe>
	
					</span>
				</div>
			</div>
			<hr>
		</div>
	</div>

	<footer class="container-fluid text-center" id="intro-footer">
		<p>@ Copyright 2018.All right reserved</p>
	</footer>

</body>
</html>