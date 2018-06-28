<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/task.js"></script>


<!DOCTYPE html >
 <h3>타임라인</h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>



<div class="step-subheader container">
	  <select  id="timeline-tstatus-filter">
	  	<option value="all">All Tasks</option>
		<c:forEach var="tstatus" items="${tstatuses}">
	       	<option class="timeline-tstatus-filter" value="${tstatus.tstatusid}">${tstatus.tstatus}</option>		
		</c:forEach>
      </select>
      
      <select  id="timeline-assignee-filter">
	  	<option value="all">All Assignees</option>
		<c:forEach var="assignee" items="${assignees}">
	       	<option class="timeline-tstatus-filter" value="${assignee.mid}">${assignee.mname}</option>		
		</c:forEach>
      </select>
</div>

<div class="container-fluid row" id="timeline">
	<div class="col-lg-3 step-timeline-div-table" >	
		<div class="table-responsive step-timeline-table">
			<table style="border-right:none" class="table step-timeline-table table-hover" id="timeline-table" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>No</th>
						<th>상태</th>
						<th>Task이름</th>
						<th>담당자</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<div id="gantt-border" class="col-lg-9 step-timeline-div">
		<div class="gantt-target"></div>
	</div>
</div>

	