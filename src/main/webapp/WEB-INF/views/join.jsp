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
<title>Insert title here</title>

<script type="text/javascript">

$(function() {
	$('#btnCheckUid').click(function(){
		if ($("#mid").val()== "") {
            alert("아이디를 입력하지 않았습니다.");
            $("#mid").focus();
            return false;
        }
		$.ajax(
			{
				type:"post",
				url:"midcheck.htm",
				data:{"mid" : $('#mid').val()},
				success:function(data){
					if(data.result=="fail"){
						alert('중복된 아이디 입니다.');
						$("#mid").focus();
					}else{
						alert('사용가능한 아이디 입니다.');
					}
				}
			}	
		);
	});
	
	$('#btnCheckkey').click(function(){
		if ($("#apollokey").val()== "") {
            alert("인증키를 입력하지 않았습니다.");
            $("#apollokey").focus();
            return false;
        }
		$.ajax(
			{
				type:"post",
				url:"keycheck.htm",
				data:{"apollokey" : $('#apollokey').val()},
				success:function(data){
					if(data.result=="fail"){
						alert('존재하지 않는 인증키 입니다.');
						$("#mid").focus();
					}else{
						alert('인증 되었습니다.');
					}
				}
			}	
		);
	});
	
});

var RegexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i; //이메일 유효성검사

	function sendit() {
		if ($("#mid").val()== "") {
            alert("아이디를 입력하지 않았습니다.");
            $("#mid").focus();
            return false;
        }
		
		if ($("#pwd").val()== "") {
            alert("비밀번호 입력하지 않았습니다.");
            $("#pwd").focus();
            return false;
        }

		if ($("#pwd").val()!=$("#pwd2").val()) {
            alert("비밀번호 확인이 일치하지 않습니다.");
            $("#pwd").focus();
            return false;
        }
		
		if ($("#mname").val()== "") {
            alert("이름을 입력하지 않았습니다.");
            $("#mname").focus();
            return false;
        }
		if ( !RegexEmail.test($.trim($("#mid").val())) ){
			alert("이메일 형식이 아닙니다.");
			$("#email").focus();
			return false;

		}
		if ($("#pwd").val().length < 8) {
            alert("비밀번호를 8자리 이상입력해주세요.");
            $("#pwd").focus();
            return false;
        }

		$("#f").submit();
	}
</script>

</head>
<body>	
<div class="container" align="center">
	<h1 class="header-fontsize">Create Apollo Account</h1>
	<div class="input-group div-width">
		<h4 class="sign-account">
			<a>or sign in to your account</a>
		</h4>
	</div>
	
	<!-- 바디 1 아이디 -->
	<form id = "f" action="join.htm" method="post">
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*아이디</h5>
		</div>
		<div class="col-sm-8 div-btn-position">
			<input type="button" id="btnCheckUid" class="btn" value="중복확인">
		</div>
	</div>
	<div class="input-group div-width">
		<input type="email" class="form-control" id="mid" name="mid">
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
		<input type="password" class="form-control" id ="pwd" name ="pwd">
	</div>

	<!-- 바디 3 비밀번호 재입력 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*비밀번호 확인</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="password" class="form-control" id ="pwd2" name ="pwd2">
	</div>



	<!-- 바디 4 이름 -->
	<div class="input-group div-width">
		<div class="col-sm-4 div-font">
			<h5>*이름</h5>
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" id = "mname" name="mname"> <br> <br>
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
			<input type="button" id="btnCheckkey" class="btn" value="인증확인">
		</div>
	</div>
	<div class="input-group div-width">
		<input type="text" class="form-control" id="apollokey"  name ="apollokey">
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
		<input type="button"  onclick="sendit()" class="btn" value="가입완료">
	</div>
	</form>
	
	<br> <br>
	<h5 class="footer-color">@Copyright 2018.All right reserved.</h5>
	<br> <br> <br>
</div>
</body>