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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/common.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/login.js"></script>
<title>Insert title here</title>

</head>
<body>	
<div class="join-page-body" >
	<div class="join-form-wrapper">
		<div class="join-form-header">
			<h2 >Create Apollo Account</h2>
			<a class="sign-account" href="login.htm">or sign in to your account</a>
		</div> 
		
		<div class="join-form-body">
			<form id = "f" action="joinform.htm" method="post">
			
			
			<!-- 아이디 -->
			<div class="join-input-wrapper">
				<div class="join-input-row">
					<div class="join-input-title">
						<h5 >* E-Mail</h5>
					</div>
					<h6 id="idcheck"></h6>
					<input type="button" id="btnCheckUid" class="btn join-form-btn" value="중복확인">
				</div>
				<div class="join-input-row">
					<input type="text" id="mid" name="mid">
				</div>
			</div>
		
			<!--  비밀번호 -->
			<div class="join-input-wrapper">
				<div class=" join-input-row">
					<div class="join-input-title">
						<h5 >* 비밀번호</h5>
					</div>
					<h6 id="regexpwd-check">(영문,숫자,특수문자 포함 8자 이상 16자 이하)</h6>
				</div>
				<div class="join-input-row">
					<input type="password" id ="join-pwd" name ="pwd">
				</div>
			</div>
		
			<!-- 비밀번호 재입력 -->
			<div  class="join-input-wrapper">
				<div class=" join-input-row">
					<div class="join-input-title">
						<h5>* 비밀번호 확인</h5>
					</div>
						<h6 id="pwdcheck"></h6>
				</div>
				<div class="join-input-row ">
					<input type="password" id ="join-pwd-check" name ="pwd2">
				</div>
			</div>
		
			<!-- 이름 -->
			<div  class="join-input-wrapper">
				<div class="join-input-row ">
					<div class="join-input-title">
						<h5>* 이름</h5>
					</div>
				</div>
				<div class=" join-input-row">
					<input type="text" id = "mname" name="mname"> 			
				</div>
			</div>
		
			<hr class="hr-color">
		
			<!-- 선택사항 -->
		
			<h4>선택 사항</h4>
		
			<!-- 인증키 -->
			<div class=" join-input-wrapper">
				<div class="join-input-row">
					<div class="join-input-title">
						<h5 >인증키</h5>
					</div>
					<h6 id="keycheck"></h6>
					<input type="button" id="btnCheckkey" class="btn join-form-btn" value="인증확인">
				</div>
				<div class="join-input-row">
					<input type="text" id="apollokey"  name ="apollokey">
				</div>
			</div>
			
		
			<!-- 휴대폰 번호 -->
			<div class=" join-input-wrapper">
				<div class="join-input-row">
					<div class="join-input-title">
						<h5 >휴대폰 번호</h5>
					</div>				
				</div>
				<div  class=" join-input-row">
					<input type="text" name="pnum">
				</div>
			</div>
			<!-- 부서명 -->
			<div class=" join-input-wrapper">
				<div class="join-input-row">
					<div class="join-input-title">
						<h5 >부서명</h5>
					</div>
				</div>
				<div class=" join-input-row">
					<input type="text" name ="deptname">
				</div>
			</div>
			<!-- 직위 -->
			<div class="join-input-wrapper ">
				<div class="join-input-row">
					<div class="join-input-title">
						<h5 >직위</h5>
					</div>
				</div>
				<div class=" join-input-row">
					<input type="text" name="position">
				</div>
			</div>
			<br>
			<!-- 가입완료 버튼 -->
			<div class=" " align="right">
				<input type="button"  onclick="sendit()" class="btn join-from-submit" value="가입완료">
			</div>
			</form>
			
			<br> 
			<p class="footer-copyright">@Copyright 2018.All right reserved</p>
		</div>
	</div>
</div>
</body>