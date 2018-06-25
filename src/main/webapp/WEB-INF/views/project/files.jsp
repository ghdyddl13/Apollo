<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<div class="container-fluid">
<h4>파일페이지</h4>
<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include> 

<style>
	th{
		background-color:lightgray;
	}
</style>


<div>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>File명</th>
				<th>관련 Task</th>
			</tr>
			<c:forEach var="f" items="${f}">
			<c:set var="count" value="${count + 1}" />
			<tr>
		
			<td>${count}</td>
			<td>${f.filename}</td>
			<td>${f.tname}&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp; ${f.sname}</td>
			</tr>
			</c:forEach>
		</thead>
		<tbody>
		</tbody>
	</table>

</div>
</div>  