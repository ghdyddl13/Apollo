<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/projectInfo.js"></script>
<link href="css/projectinfo.css" type="text/css" rel="stylesheet">

<h3>인포메이션</h3>
<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>

<input type=button value="test" id="testbtn1">
<input type=button value="test" id="testbtn2">
<input type=button value="test" id="testbtn3">
<input type=button value="도넛데이터 get" id="testbtn4">
<input type=button value="완/미완 스텝명들 get" id="testbtn5">
<input type=button value="sid별 task들" id="testbtn6">
<input type=button value="같은 프로젝트 멤버들 mid get" id="testbtn7">

<div id=projectinfo_outerbox>
  <div class="row" id="projectinfo_first_row"> <!-- row_1 -->



	<div id="projectinfo_Task_Status_Donut" class="col-lg-5">
		마감기한 기준 Task 현황
		<canvas id="projectinfo_DonutChart"></canvas>
	</div>

    <div id="projectinfo_Task_Situation_Table" class="col-lg-5">

			<span align='left'>Step별 Task 완료/미완료 현황</span>
			<span align='right'>
					<select id="projectinfo_Task_Situation_Table_selectbar">
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
					</select>
				</span>
	 </div>

  </div> <!-- end_row_1 -->

  <div class="row" style="padding:20px"> <!-- row_2 -->
    <div id="projectinfo_Step_Progress_Graph" class="col-lg-10">col lg 6</div>
  	<br>
  </div> <!-- end_row_2 -->

  <div class="row" style="padding:20px"> <!-- row_3 -->
    <div id="projectinfo_Project_Member_Table" class="col-lg-10">col lg 6</div>
  	<br>
  </div> <!-- end_row_3 -->

</div> <!-- end_outerbox -->