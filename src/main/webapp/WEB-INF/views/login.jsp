<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<<<<<<< HEAD
test1
=======
	<div class="login-wrapper" align="center">
		<div class="login-container">
			<input type="hidden" value="${msg}" id="login-msg">
			<input type="hidden" value="${emailmsg}" id="email-msg">
			<form id="login-form" action="login.htm" method="post">
				<!-- 제목 -->
				<div class="login-head">
					<img class="login-page-logo" src="img/login-header.png">
				</div>
				<br>
				<div class="login-msg">${msg}</div>
				<div class="email-msg">${emailmsg}</div>
				<div>
				<input type="text" class="login-input" placeholder="E-mail" name="mid" id="mid"> 
				<input type="password" class="login-input" placeholder="Password" name="pwd" id="pwd">
				<br>
				<input id="login_btn" type="button" onclick="login()" class="btn btn-block login-btn" value="로그인"> 
				<input type="button" class="btn btn-block buy-license-btn" value="구매하기" data-toggle="modal" data-target="#apollokey-modal-dialog">
				</div>
				<div class=" googlelogin-btn btn-block" id="google-login-btn"  onclick="location.href='<%=request.getContextPath()%>/google.htm'">
					<img class="google_img" alt="구글 로그인" src="<%=request.getContextPath()%>/img/googlelogin.png" >
					<span class="google_login">GOOGLE 로그인</span>
				</div>	
				
				<br> 
				<!-- 비밀번호 ,회원가입 페이지 링크 -->
				<p class="p-color">
					<a href="join.htm">회원가입이 필요하신가요?</a>
				</p>
				<p class="p-color2">
					<a data-toggle="modal" data-target="#pwdModal">비밀번호를
						잊어버리셨나요?</a>
				</p>
			
			</form>
				
			
		</div>
		<br><br>
			<p class="footer">@ Copyright 2018.All right reserved</p>
	</div>


	<!-- Modal창 구매하기 창 -->
 	<form action="apollokey.htm" id="apollokey" method="post">
		<div class="modal fade" id="apollokey-modal-dialog" role="dialog">
			<div class="modal-dialog buy-apollokey-modal-dialog">
				<div class="modal-content" id="apollokey-modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">인증키 구매</h4>
					</div>
					<div class="modal-body apollokey-modal-body">
						<!-- 회사명 input -->
						<!-- Modal content-->
							<div class="apollokey-modal-title">
								<label id="company-name" for="company-name-content">회사명</label>
								<input type="text" id="company-name-content" name="cname" placeholder="회사명을 입력해주세요">
							</div>
							<div class="apollokey-modal-title">
								<label id="user-email" for="user-email-content">이메일</label>
								<input type="text" id="user-email-content" name="email" placeholder="이메일을 입력해주세요">
							</div>
							<div class="apollokey-modal-description">가입하신 이메일로 인증키를 전송해드립니다.</div>
						</div>
						<br>
						<div align="center">
							<input type="button" id="keybtn" class="btn buy-apollokey-btn" value="구매">&nbsp;&nbsp;&nbsp;
							<input type="button" id="keyreset" class="btn cancel-btn" data-dismiss="modal" value="취소">
						</div>
					</div>
			</div>
		</div>
	</form>
	
	<!-- Modal창 비밀번호 찾기 창 -->
	<form action="findpwd.htm" id="findpwd" method="post">
		<div class="modal fade" id="pwdModal" role="dialog">
			<div class="modal-dialog find-pwd-modal-dialog">
				<!-- Modal content-->
				<div class="modal-content find-pwd-modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">비밀번호 찾기</h4>
					</div>
					<div class="modal-body find-pwd-modal-body">
						<div>
							<div class="find-pwd-modal-title">
								<label id="find-pwd-modal-mname" for="find-pwd-modal-mname-content">이름</label> 
									<input type="text" name="mname2" id="find-pwd-modal-mname-content" placeholder="이름을 입력해주세요">
							</div>
							<div class="find-pwd-modal-title">
								<label id="find-pwd-modal-mid" for="find-pwd-modal-mid-content">이메일</label>
								<input type="text" name="mid2" id="find-pwd-modal-mid-content" placeholder="이메일을 입력해주세요">
							</div>
						</div>
						<div class="apollokey-modal-description">가입하신 이메일로 임시비밀번호를 전송해드립니다.</div>
					</div>
					<div align="center">
						<input id="pwdbtn" type="button" class="btn find-pwd-btn" value="찾기"> 
						<input id="pwdreset" type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
					</div>
				</div>
			</div>
		</div>
	</form>
>>>>>>> 865e29116250a79a28cc3ecb8e2434e9895e149e
</body>

