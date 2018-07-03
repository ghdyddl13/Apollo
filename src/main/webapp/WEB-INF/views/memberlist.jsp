<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<h4>사원 목록</h4>
<br><br>
	<div align="center">
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
				<c:set var="count" value='${count + 1}'></c:set>
					<tr class="header-memberinfo-table" data-toggle="modal" data-target="#profile-modal-dialog">
						<td class="header-member-table-no">${count}</td>
						<td>${member.mname}</td>
						<td>${member.position}</td>
						<td>${member.mid}</td>
						<td>${member.pnum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>