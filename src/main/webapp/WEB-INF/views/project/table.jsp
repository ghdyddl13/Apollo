<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script type="text/javascript" src="js/project_table.js"></script>
<link href="css/project_table.css" type="text/css" rel="stylesheet">

<div class="container-fluid">
	<h4>해당 프로젝트명 들어가는 곳</h4>
	<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>

	<div>
		<input type="hidden" name="pid" id="proejct-table-pid">
		<table class="table project-page-table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Start</th>
					<th>Due</th>
					<th>Duration</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
</div>
