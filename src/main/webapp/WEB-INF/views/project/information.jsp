<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/projectInfo.js"></script>
<link href="css/projectinfo.css" type="text/css" rel="stylesheet">

<h3>인포메이션</h3>
<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>

<input type=button value="테스트버튼" id="testbtnjkjk">

<div id=projectinfo_outerbox>
  <div class="row" id="projectinfo_first_row"> <!-- row_1 -->

	<div id="projectinfo_Task_Status_Donut" class="col-lg-5">
		마감기한 기준 Task 현황
		<div id="prison_Donut">
		<canvas id="projectinfo_DonutChart"></canvas>
		</div>
	</div>

    <div id="projectinfo_Task_Situation_Table" class="col-lg-5">

			<div align='left'>Step별 Task 완료/미완료 현황</div>
			<br />
			<div align='right'>
					<select id="projectinfo_task_situation_table_selectbar">
						 <c:forEach var="step" items="${steplist}">
	                        <option value="${step.sid}">${step.sname}</option>
	                     </c:forEach>
					</select>
					<br>
					<br>
					<table id="task_progress_table" class="table">
						<tr><th>완료 task</th><th>미완료 task</th></tr>
					</table>
			</div>
	 </div>

  </div> <!-- end_row_1 -->

  <div class="row" style="padding:20px"> <!-- row_2 -->
    <div id="projectinfo_Step_Progress_Graph" class="col-lg-10">Step별 진행률
  	<br>
  	<br>
    	<div id="prison_progressbar">
			<canvas id="projectinfo_progressbar"></canvas>
  		</div>
    </div>
  </div> <!-- end_row_2 -->

  <div class="row" style="padding:20px"> <!-- row_3 -->
    <div id="projectinfo_Project_Member_Table" class="col-lg-10">프로젝트에 참여중인 사원 목록&nbsp&nbsp<i id ="testbtnjkjk" class="fa fa-plus-square"></i>
  	<br>
  	<br>
		<table id="project_member_table" class="table">
			<tr><th>이름</th><th>직위</th><th>부서명</th><th>이메일</th><th>핸드폰 번호</th></tr>
				 <c:forEach var="member" items="${memberlist}">
				 <tr><td>${member.mname}</td><td>${member.position}</td><td>${member.deptname}</td><td>${member.mid}</td><td>${member.pnum}</td></tr>
		         </c:forEach>
		</table>
	</div>
  </div> <!-- end_row_3 -->

</div> <!-- end_outerbox -->
