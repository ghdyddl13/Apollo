<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/projectInfo.js"></script>


<h3>인포메이션</h3>
<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>

<input type=button value="test" id="testbtn1">
<input type=button value="test" id="testbtn2">
<input type=button value="test" id="testbtn3">
<input type=button value="도넛데이터 get" id="testbtn4">
<input type=button value="완/미완 스텝명들 get" id="testbtn5">
<input type=button value="sid별 task들" id="testbtn6">
<input type=button value="같은 프로젝트 멤버들 mid get" id="testbtn7">

<div id=outerbox style="margin-left: 200px">
  <div class="row" style="padding:20px"> <!-- row_1 -->
    <div id="Task_Status_Donut" class="col-lg-5" style="height:400px; margin: 2px; padding:10px; background-color:#f0f0f0;">
		마감기한 기준 Task 현황
		<canvas id="DonutChart" style="height: 350px"></canvas>
	</div>

    <div id="Task_Situation_Table" class="col-lg-5" style="overflow:auto; height:400px; margin: 2px; padding:10px; background-color:#f0f0f0;">
    Step별 Task 완료/미완료 현황
    </div>
  </div> <!-- end_row_1 -->
  
  <div class="row" style="padding:20px"> <!-- row_2 -->
    <div id="Step_Progress_Graph" class="col-lg-10" style="height:400px; margin: 1px; padding:10px; background-color:#f0f0f0;">col lg 6</div>
  	<br>
  </div> <!-- end_row_2 -->

  <div class="row" style="padding:20px"> <!-- row_3 -->
    <div id="Project_Member_Table" class="col-lg-10"  style="overflow:auto; height:400px; margin: 1px; padding:10px; background-color:#f0f0f0;">col lg 6</div>
  	<br>
  </div> <!-- end_row_3 -->

</div> <!-- end_outerbox -->

