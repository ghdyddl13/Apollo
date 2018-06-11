<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/projectInfo.js"></script>
<h3>인포메이션</h3>
<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>
<input type=button value="test" id="testbtn">

<div id=outerbox style="margin-left: 200px">

  <div class="row" style="padding:20px"> <!-- row_1 -->
    
    <div id="Task_Status_Donut" class="col-lg-5" style="height:400px; margin: 2px; padding:10px; background-color:#f0f0f0;">여기는 step별 task 목록
    <br>
    <br>
    <br>
    <br>
    <br>
    </div>

    <div id="Task_Situation_Table" class="col-lg-5" style="overflow:auto; height:400px; margin: 2px; padding:10px; background-color:#f0f0f0;">여기는 step별 task 목록
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>1
    <br>
    </div>
  </div> <!-- end_row_1 -->
  
  <div class="row" style="padding:20px"> <!-- row_2 -->
    <div id="Step_Progress_Graph" class="col-lg-10" style="height:400px; margin: 1px; padding:10px; background-color:#f0f0f0;">col lg 6</div>
  	<br>
  	<br>
  	<br>
  </div> <!-- end_row_2 -->

  <div class="row" style="padding:20px"> <!-- row_3 -->
    <div id="Project_Member_Table" class="col-lg-10"  style="overflow:auto; height:400px; margin: 1px; padding:10px; background-color:#f0f0f0;">col lg 6</div>
  	<br>
  	<br>
  	<br>
  </div> <!-- end_row_3 -->

</div> <!-- end_outerbox -->