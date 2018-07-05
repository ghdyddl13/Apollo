<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script>
$(function() {
	var rows = $('#header-member-table-tbody').children();
	console.log(rows);
	for(var rowcount = rows.length ; rowcount <= 15 ; rowcount++) {
		var row = "<tr id='header-member-table-no-data'><td></td><td></td><td></td><td></td><td></td></tr>";
		$('#header-member-table-tbody').append(row);
	}
});
</script>
<h4>사원 목록</h4>
<br><br>
	<div align="center" class="header-member-table">
		<table class="table" id="header-member-table-id">
			<thead>
				<tr>
					<th>No</th>
					<th>이름</th>
					<th>직위</th>
					<th>이메일</th>
					<th>핸드폰번호</th>
				</tr>
			</thead>
			<tbody id="header-member-table-tbody">
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