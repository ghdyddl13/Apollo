<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>
<head>
<title>Home</title>
</head>
 <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Website Font style -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1">


	

		

<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
/* 폰트 */
body {
	font-family: 'Noto Sans KR';
}
/* 로그인 input 간격  */
.input-group{
margin-top:1em;
margin-bottom:1em;
}

.login-box{
	line-height:2.3em;
	font-size:1em;
	margin-top:1em;
	margin-bottom:1em;
	padding-top:0.5em;
	padding-bottom:0.5em;
}

/* 모달 센터 맞추는 부분  */
.modal {
        text-align: center;
}
 
@media screen and (min-width: 768px) { 
        .modal:before {
                display: inline-block;
                vertical-align: middle;
                content: " ";
                height: 100%;
        }
}
 
.modal-dialog {
        display: inline-block;
        text-align: left;
        vertical-align: middle;
}

/* 버튼 맞추는 부분 */
.btn{
	margin-right: 40px; 
	padding-top: 12px;
    padding-bottom: 12px;
    padding-right: 30px;
    padding-left: 30px;
    background-color: B9062F;
    color:white;
    
}

/* 버튼 맞추는 부분 */
.btn2{
	margin-right: 180px;
	padding-top: 12px;
    padding-bottom: 12px;
    padding-right: 30px;
    padding-left: 30px;
    background-color: 000069;
    color:white;
}

.btn3{
	margin-right: 40px; 
	padding-top: 12px;
    padding-bottom: 12px;
    padding-right: 30px;
    padding-left: 30px;
    background-color: B9062F;
    color:white;
    margin-left:160px;
    
}
/* 모달 색깔 바꾸는 부분 */
#modal,#modal2,#modal3{
	background-color: white;
}

.footer{ /* 꼬리말 부분 */
text-align: center;
color: 999999;
}

.legend{
text-align: center; 
font-size: 40px;
}

.header{
	margin-right: 0px;
    margin-left: 500px;
    margin-top: 250px;
}
.magin{
margin-left: 150px;
}

.margin2{
width: 350px;
}

.btn-block{
	background-color: 000069; 
	color: white;
}
.btn-color{
background-color: B9062F;
color: white;
}
.p-color{
font-size: 15px;
text-align: center; 
color: black;
}
.p-color2{
font-size: 15px; 
text-align: center; 
color: black;
}
.i-width,.i-width2{
width:40px;
}
.head{
	 margin-left: 35px;
	 margin-bottom: 25px;
     margin-top: 0px;
     font-size:40px;	
}
.div-font-size{
	font-size: 21px; 
	text-align: center;
}

.input-size{
	width: 326px;
}

 .font-header6{
 	 margin-left: 35px;
 }

</style>
<script>

		
	
	
</script>



<body>
	<div class="container header" >
	 <div class="row magin">
	 	<div class="col-sm-5">
		 	<form action=""  class="margin2">
		 		<!-- 제목 -->
		 		<h1 class="head"><b>Login to Apollo</b></h1>
		 		<br>
		 		
		 		<!-- <i> 사람모양 열쇠모양 , input 부분 -->
		 		<div class="input-group">
		 			<span class="input-group-addon">
		 			<i class="fa fa-user fa i-width2" ></i></span>
		 			<input type="text" class="form-control" placeholder="Username">
		 		</div>
		 		<div class="input-group">
		 			<span class="input-group-addon"
		 			><i class="	fa fa-key i-width" ></i></span>
		 			<input type="text" class="form-control" placeholder="****">
		 		</div>
		 		
		 		<!-- 구매 취소 부분  -->
		 		<button type="submit" class="btn btn-default btn-block" >로그인</button>
		 		<button type="button" class="btn btn-default btn-block btn-color"
		 		data-toggle="modal" data-target="#myModal">구매하기</button>
		 		<br>
		 		<!-- 비밀번호 ,회원가입 페이지 링크 -->
		 		<p class="p-color"><a href="#">회원가입이 필요하신가요?</a></p>
		 		<p class="p-color2"><a href="#" data-toggle="modal" data-target="#pwdModal">비밀번호를 잊어버리셨나요?</a></p>
		 		<br>
		 		<br>
		 		<br>
		 		<p class="footer">@ Copyright 2018.All right reserved</p>
		 	</form>
		 </div>
		 </div>
	 
     </div> 
     
      
  <!-- Modal창 구매하기 창 -->
  <div class="container" >
  <div class="modal fade" id="myModal" role="dialog" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" id="modal">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">인증키 구매</h4>
        </div>
        
        
        
        <!--이메일 회사명  -->
        <div class="modal-body"  id="modal2">
        <!-- 회사명 input -->
        <div class="row">
        <div class="col-sm-3 div-font-size">
          <label>회사명</label>
        </div>
        <div class="col-sm-9">
          <input class="form-control input-size" placeholder="회사명을 입력해주세요" >
          <br>
        </div>
         </div>
        <!--이메일 input  -->
        <div class="row">
        <div class="col-sm-3 div-font-size">
          <label>이메일</label>
        </div>
        <div class="col-sm-9">
          <input class="form-control input-size" placeholder="이메일을 입력해주세요" >
        </div>
         </div>
           <h6 class="font-header6">가입하신 이메일로 인증키를 전송해드립니다.</h6>
           <br>
           <button type="button" class="btn btn-default btn3" data-dismiss="modal" >구매</button>
          <button type="button" class="btn btn-default btn2" data-dismiss="modal" >취소</button>
        </div>
        
      </div>
      
    </div>
  </div>
  
  <!-- Modal창 비밀번호 찾기 창 -->
  <div class="container" >
  <div class="modal fade" id="pwdModal" role="dialog" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" id="modal">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">비밀번호 찾기</h4>
        </div>
        <div class="modal-body"  id="modal2">
          <div class="row">
        <div class="col-sm-3 div-font-size">
          <label>이메일</label>
        </div>
        <div class="col-sm-9">
          <input class="form-control input-size" placeholder="이메일을 입력해주세요" >
           <br>
        </div>
         </div>
          <h6 class="font-header6">가입하신 이메일로 임시비밀번호를 전송해드립니다.</h6>
          <br>
          <br>
          <button type="button" class="btn btn-default btn3" data-dismiss="modal" >구매</button>
          <button type="button" class="btn btn-default btn2" data-dismiss="modal" >취소</button>
        </div>
        
      </div>
      
    </div>
  </div>
  
</div>
</body>
</html>
