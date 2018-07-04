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

<!--Mywork CSS  -->
<link href="css/myWork.css" type="text/css" rel="stylesheet">

<title>Insert title here</title>
<script type="text/javascript">

	$(function() {
		var msg = "${msg}";
		if (msg != "") {
			alert(msg);
		}
		
		$("#google-login-btn").click(function(){
			$()
		});

		$("#pwdreset").click(function() {
			$("#findpwd")[0].reset();
		});
		$("#keybtn").click(function() {
			$("#apollokey").submit();
		});
		$("#keyreset").click(function() {
			$("#apollokey")[0].reset();
		});

		$('#pwdbtn').click(function() {

			var data = {
				mname : $('input[name=mname2]').val(),
				mid : $('input[name=mid2]').val()
			}
			console.log(data)
			$.ajax({
				type : 'post',
				url : 'findpwd.htm',
				data : data,
				success : function(data) {
					$('input[name=mname2]').val('')
					$('input[name=mid2]').val('')
					if (data.result == '성공') {
						alert('입력하신 Email로 임시비밀번호를 발송했습니다.')
					} else if (data.result == '실패') {
						alert('입력하신 이름과 Email이 일치하지 않습니다.\n다시한번 확인 하시기 바랍니다.')
					} else {
						alert('입력하신  Email이 존재하지 않습니다.\n다시한번 확인 하시기 바랍니다.')
					}
				}
			})

		})
		
	     var input = document.getElementById("pwd");
	     input.addEventListener("keyup", function(event) {
	         event.preventDefault();
	         if (event.keyCode === 13) {
	             document.getElementById("login_btn").click();
	         }
	     });
		
	});

	function login() {
		if ($("#mid").val() == "") {
			alert("아이디를 입력하지 않았습니다.");
			$("#mid").focus();
			return false;
		}

		if ($("#pwd").val() == "") {
			alert("비밀번호 입력하지 않았습니다.");
			$("#pwd").focus();
			return false;
		}
		$("#f").submit();
		$("#mid").val("");
		$("#pwd").val("");
	}
	
      
</script>
</head>
<body>
	<div class="container-fluid" align="center">
		<div class="login-container">

		<form id="f" action="login.htm" method="post">
				<!-- 제목 -->
				<h1 class="login-head">
					<b>Login to Apollo</b>
				</h1>
				<br>
				<!-- <i> 사람모양 열쇠모양 , input 부분 -->
				
				
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user fa i-width2"></i></span> 
						<input type="text" class="form-control" placeholder="Username" name="mid" id="mid">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="	fa fa-key i-width"></i></span> 
						<input type="password" class="form-control" placeholder="****" name="pwd" id="pwd">
				</div>
			
				
				<!-- 구매 취소 부분  -->
				<input id="login_btn" type="button" onclick="login()" class="btn btn-block login-btn" value="로그인"> 
				<input type="button" class="btn btn-block buy-license-btn" value="구매하기" data-toggle="modal" data-target="#apollokey-modal-dialog">
				
				
				<div class="btn btn-block googlelogin-btn" id="google-login-btn"  onclick="location.href='<%=request.getContextPath()%>/google.htm'">
					<img class="google_img" alt="구글 로그인" src="<%=request.getContextPath()%>/img/googlelogin.png" >
					<span class="btn google_login">GOOGLE 로그인</span>
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
				<br> <br> <br>
				</form>
				
				<p class="footer">@ Copyright 2018.All right reserved</p>
			
		</div>
	</div>


	<!-- Modal창 구매하기 창 -->

		<div class="modal fade" id="apollokey-modal-dialog" role="dialog">
			<div class="modal-dialog">
				<form action="apollokey.htm" id="apollokey" method="post">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" id="modal">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">인증키 구매</h4>
					</div>
					<!--이메일 회사명  -->
					<div class="modal-body" id="modal2">
						<!-- 회사명 input -->
						<div class="row">
							<div class="col-sm-3 div-font-size">
								<label>회사명</label>
							</div>
							<div class="col-sm-9">
								<input name="cname" class="form-control input-size" placeholder="회사명을 입력해주세요">
								<br>
							</div>
						</div>
						<!--이메일 input  -->
						<div class="row">
							<div class="col-sm-3 div-font-size">
								<label>이메일</label>
							</div>
							<div class="col-sm-9">
								<input name="email" class="form-control input-size" placeholder="이메일을 입력해주세요">
							</div>
						</div>
						<h6 class="font-header6">가입하신 이메일로 인증키를 전송해드립니다.</h6>
						<br>
						<button id="keybtn" type="button" class="btn btn-default btn3"
							data-dismiss="modal">구매</button>
						<button id="keyreset" type="button" class="btn btn-default btn2"
							data-dismiss="modal">취소</button>
					</div>
					

				</div>
				</form>
			</div>
		</div>
	
	<!-- Modal창 비밀번호 찾기 창 -->
	<form action="findpwd.htm" id="findpwd" method="post">
	<div class="container">
		<div class="modal fade" id="pwdModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" id="modal">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">비밀번호 찾기</h4>
					</div>
					
					<div class="modal-body" id="modal2">
					<!-- 이름 input -->
						<div class="row">
							<div class="col-sm-3 div-font-size">
								<label>이름</label>
							</div>
							<div class="col-sm-9">
								<input name="mname2" class="form-control input-size" placeholder="이름을 입력해주세요">
								<br>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-3 div-font-size">
								<label>이메일</label>
							</div>
							<div class="col-sm-9">
								<input name="mid2" class="form-control input-size" placeholder="이메일을 입력해주세요">
								<br>
							</div>
						</div>
						<h6 class="font-header6">가입하신 이메일로 임시비밀번호를 전송해드립니다.</h6>
						<br> <br>
						<button id="pwdbtn" type="button" class="btn btn-default btn3"
							data-dismiss="modal">찾기</button>
						<button id="pwdreset" type="button" class="btn btn-default btn2"
							data-dismiss="modal">취소</button>
					</div>
					

				</div>

			</div>
		</div>

	</div>
	</form>
</body>

