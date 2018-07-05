<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<script type="text/javascript" src="js/header.js"></script>
<link rel="stylesheet" href="css/search.css" />
<nav class="navbar navbar-default navbar-fixed-top" id="header">
	<c:set var="m" value="${memberdto}"></c:set>
	<div class="navbar-inside-div">
		<div class="navbar-header">
			<a href="main.htm"><img id="gohome" class="header_logo"
				src="img/apollo_logo.png" /></a>
		</div>

		<div class="header-menu-ul">
			<ul class="nav navbar-nav ">
				<li class="nav-item"><a class="nav-link main-header-menu"
					id="inbox-page" style="color: white">Inbox<span id="inbox_count" style="color: red"></span></a></li>
				<li class="nav-item"><a class="nav-link main-header-menu"
					id="myWork-page" style="color: white">My work</a></li>
				<li class="nav-item"><a class="nav-link main-header-menu"
					id="starredTask-page" style="color: white">Starred Task</a></li>
				<li class="nav-item"><a class="nav-link main-header-menu"
					id="report-page" style="color: white">Report</a></li>
				<li class="nav-item"><a class="nav-link main-header-menu"
					id="stream-page" style="color: white">Stream</a></li>
			</ul>	
		</div>

		<div>
			<ul id="header-right-wrapper" class="nav navbar-nav navbar-right">
				<div class="container-fluid search-bar-container">
					<i class="search-bar-imo fas fa-search"></i> <input
						id="open-right-nav" type="text">
				</div>
				<li class="nav-item dropdown">
					<c:choose>
						<c:when test="${m.image eq null}">
							<div>
								<img src="img/user.png" id="loginimg">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<img src="profileImg/${m.image}" id="loginimg">
							</div>
						</c:otherwise>
					</c:choose> 
					<a id="header-user-name" class="nav-link dropdown-toggle"
					data-toggle="dropdown" style="color: white"> ${m.mname}님</a>
					<ul class="dropdown-menu">
						<li id="header-introduce"><a>Apollo</a></li>
						<li data-toggle="modal" data-target="#edit-profile"
							id="header-profile-edit"><a>개인정보수정</a></li>
						<li id="header-memberlist"><a>사원목록</a></li>
						<li id="header-logout" data-toggle="modal" data-target="#hedaer-logout-dialog">
						<a>로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>


<div id="search-nav" class="search-nav">
  <div class="container-fluid search-nav-head row">
  	<div class="col-sm-2">
		<i  class="fas fa-search"></i>
  	</div>
  	<div class="col-sm-10 container-fluid">
		<input id ="search-bar" type="text">
  	</div>
  </div>
  <div class= "search-nav-body">
	  <div id="search-content-box">
	  </div>
  </div>
</div>

