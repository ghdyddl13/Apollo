<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script>
$(function() {
	$("#login_btn").click(function(){
		console.log("Test")
		location.href="login.htm"
	})
})	
</script>	
	
<div class="container header">
	<div class="row magin">
		<div class="col-sm-5">
			<form action="" class="margin2">
				<!-- 제목 -->
				<h1 class="head">
					<b>Login to Apollo</b>
				</h1>
				<br>

				<!-- <i> 사람모양 열쇠모양 , input 부분 -->
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="fa fa-user fa i-width2"></i></span> <input type="text"
						class="form-control" placeholder="Username">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i
						class="	fa fa-key i-width"></i></span> <input type="text"
						class="form-control" placeholder="****">
				</div>

				<!-- 구매 취소 부분  -->
				<button id="login_btn" type="submit" class="btn btn-default btn-block">로그인</button>
				<a href="login.htm">로그인</a>
				<button type="button" class="btn btn-default btn-block btn-color"
					data-toggle="modal" data-target="#myModal">구매하기</button>
				<br>
				<!-- 비밀번호 ,회원가입 페이지 링크 -->
				<p class="p-color">
					<a href="#">회원가입이 필요하신가요?</a>
				</p>
				<p class="p-color2">
					<a href="#" data-toggle="modal" data-target="#pwdModal">비밀번호를
						잊어버리셨나요?</a>
				</p>
				<br> <br> <br>
				<p class="footer">@ Copyright 2018.All right reserved</p>
			</form>
		</div>
	</div>

</div>


<!-- Modal창 구매하기 창 -->
<div class="container">
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

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
							<input class="form-control input-size" placeholder="회사명을 입력해주세요">
							<br>
						</div>
					</div>
					<!--이메일 input  -->
					<div class="row">
						<div class="col-sm-3 div-font-size">
							<label>이메일</label>
						</div>
						<div class="col-sm-9">
							<input class="form-control input-size" placeholder="이메일을 입력해주세요">
						</div>
					</div>
					<h6 class="font-header6">가입하신 이메일로 인증키를 전송해드립니다.</h6>
					<br>
					<button type="button" class="btn btn-default btn3"
						data-dismiss="modal">구매</button>
					<button type="button" class="btn btn-default btn2"
						data-dismiss="modal">취소</button>
				</div>

			</div>

		</div>
	</div>
</div>
<!-- Modal창 비밀번호 찾기 창 -->
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
					<div class="row">
						<div class="col-sm-3 div-font-size">
							<label>이메일</label>
						</div>
						<div class="col-sm-9">
							<input class="form-control input-size" placeholder="이메일을 입력해주세요">
							<br>
						</div>
					</div>
					<h6 class="font-header6">가입하신 이메일로 임시비밀번호를 전송해드립니다.</h6>
					<br> <br>
					<button type="button" class="btn btn-default btn3"
						data-dismiss="modal">구매</button>
					<button type="button" class="btn btn-default btn2"
						data-dismiss="modal">취소</button>
				</div>

			</div>

		</div>
	</div>

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
  
=======
>>>>>>> a941efa76c1880df4c7ad03de2af3896ab2b88d8
</div>

