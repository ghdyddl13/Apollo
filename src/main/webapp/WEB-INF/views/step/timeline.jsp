<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
 <h3>타임라인</h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
<nav class="navbar inside-header ">
   <div class="container-fluid ">

   </div>
</nav>

<div class="container-fluid row" id="timeline">
<div class="col-lg-3 step-timeline-div" >	
		<div class="table-responsive step-timeline-table">
			<table style="border-right:none" class="table step-timeline-table table-hover" id="timeline-table" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>No</th>
						<th>Task이름</th>
						<th>상태</th>
						<th>담당자</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<div class="col-lg-9 step-timeline-div">
<div class="gantt-target"></div>
</div>
</div>	