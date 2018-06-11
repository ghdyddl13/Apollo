<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/header_sidebar.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/common.css" type="text/css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>	
<div class="container" align="center">
	<h1 class="header-fontsize">Create Apollo Account</h1>
	<div class="input-group div-width">
		<h4 class="sign-account">
			<a>or sign in to your account</a>
		</h4>
	</div>
<!--private String mid;
	private String mname;
	private String pwd;
	private Date rday;
	private String pnum;
	private String image;
	private String deptname;
	private String apollokey;
	private String position; -->
	
	<!-- 바디 1 아이디 -->
	<form action="join.htm" method="post">
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*아이디</h5>
		</div>
		<div class="col-sm-8 div-btn-position">
			<button class="btn ">중복확인</button>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" name="mid">
	</div>

	<!-- 바디 2 비밀번호 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*비밀번호</h5>
		</div>
		<div class="col-sm-8 div-btn-position">
			<h6>(특수문자 포함해서 8자 이상 입력하세요)</h6>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="password" class="form-control" name ="pwd">
	</div>

	<!-- 바디 3 비밀번호 재입력 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*비밀번호 확인</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="password" class="form-control">
	</div>



	<!-- 바디 4 이름 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*이름</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" name="mname"> <br> <br>
		<hr class="hr-color">
	</div>



	<!-- 선택사항 글자 -->
	<div class="input-group div-width">
		<h4 class="div-font">선택 사항</h4>
	</div>

	<br>

	<!-- 인증키 -->
	<!-- 바디 1 아이디 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>인증키</h5>
		</div>
		<div class="col-sm-8 div-btn-position">
			<button class="btn">인증확인</button>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" name ="apollokey">
	</div>

	<!-- 휴대폰 번호 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>휴대폰 번호</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" name="pnum">
	</div>
	<!-- 부서명 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>부서명</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" name ="deptname">
	</div>

	<!-- 직위 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>직위</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" name="position">
	</div>
	<br> <br>
	<!-- 가입완료 버튼 -->
	<div class="input-group div-width" align="right">
		<button type="submit" class="btn">가입완료</button>
	</div>
	</form>
	
	<br> <br>
	<h5 class="footer-color">@Copyright 2018.All right reserved.</h5>
	<br> <br> <br>
</div>
</body>