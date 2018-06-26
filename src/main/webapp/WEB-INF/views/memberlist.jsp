<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<h3>사원 목록</h3>

<div class="container-fluid">
	<div>
		<table class="table" id="header-member-table">
			<thead>
				<tr>
					<th>No</th>
					<th>이름</th>
					<th>직위</th>
					<th>이메일</th>
					<th>핸드폰번호</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberlist}">
					<tr>
						<td></td>
						<td>${member.mname}</td>
						<td>${member.position}</td>
						<td>${member.mid}</td>
						<td>${member.pnum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>