<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<script type="text/javascript" src="js/header.js"></script>
<nav class="navbar navbar-default navbar-fixed-top" id="header">

	<div class="container-fluid">
		<div class="navbar-header container-fluid">
			<a href="index.htm"><img id="gohome" class="header_logo"
				src="img/apollo_logo.png" /></a>
		</div>
		<ul class="nav navbar-nav ">
			<li class="nav-item"><a class="nav-link header-menu"
				id="inbox-page" style="color: white">Inbox</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="myWork-page" style="color: white">My work</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="starredTask-page" style="color: white">Starred Task</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="report-page" style="color: white">Report</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="stream-page" style="color: white">Stream</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				style="color: white"><span class="glyphicon glyphicon-user"
					id="loginimg"></span>김래영님</a>
				<ul class="dropdown-menu">
					<li data-toggle="modal" data-target="#edit-profile" id="header-profile-edit"><a>개인정보수정</a></li>
					<li data-toggle="modal" data-target="#memberlist" id="header-memberlist"><a>사원목록</a></li>
					<li><a href="#">로그아웃</a></li>
				</ul></li>
		</ul>
	</div>
</nav>

