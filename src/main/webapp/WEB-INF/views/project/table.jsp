<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<div class="container-fluid">
	<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>
	<h4 id="project-table-page-pname">해당 프로젝트명 들어가는 곳</h4>
	<div>
		<input type="hidden" name="pid" id="proejct-table-pid">
		<table class="table project-page-table">
			<thead>
				<tr>
					<th>TITILE</th>
					<th>START</th>
					<th>DUE</th>
					<th>DURATION</th>
					<th>STATUS</th>
				</tr>
			</thead>
			<tbody>
				<!-- 폴더에 속하지 않은 스텝 뿌려주기 -->
				<c:forEach var="step" items="${steplist}">
					<c:if test="${step.fid eq null}">
						<tr id="project-table-tr-steps">
							<td><span class="glyphicon glyphicon glyphicon-list-alt"></span>&nbsp;${step.sname}</td>
							<td class="project-table-day">${fn:substring(step.sday,0,10)}</td>
							<td class="project-table-day">${fn:substring(step.eday,0,10)}</td>
							<td></td>
							<td></td>
						</tr>
						<!-- 스텝에 속한 task 뿌려주기 -->
						<c:forEach var="task" items="${tasklist}">
							<c:if test="${task.sid eq step.sid}">
								<tr id="project-table-tr-tasks">
									<td style="padding-left: 30px">┗ ${task.tname}</td>
									<td class="project-table-day">${fn:substring(task.sday,0,10)}</td>
									<td class="project-table-day">${fn:substring(task.eday,0,10)}</td>
									<td>${task.eday  - task.sday}</td>
									
									<td id="project-table-td-tstatus" style="background-color:${task.color}">${task.tstatus}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
				<!-- 폴더 뿌려주기 -->
				<c:forEach var="folder" items="${folderlist}">
					<tr id="project-table-tr-folders">
						<td><span class="glyphicon glyphicon-folder-close"></span>&nbsp;${folder.fname}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<!-- 폴더에 속한 스텝 뿌려주기 -->
					<c:forEach var="step" items="${steplist}">
						<c:if test="${step.fid eq folder.fid}">
							<tr id="project-table-tr-steps">
								<td style="padding-left: 30px"><span
									class="glyphicon glyphicon glyphicon-list-alt"></span>&nbsp;${step.sname}</td>
								<td class="project-table-day">${fn:substring(step.sday,0,10)}</td>
								<td class="project-table-day">${fn:substring(step.eday,0,10)}</td>
								<td></td>
								<td></td>
							</tr>
							<!-- 스텝에 속한 task 뿌려주기 -->
							<c:forEach var="task" items="${tasklist}">
								<c:if test="${task.sid eq step.sid}">
									<tr>
										<td style="padding-left: 60px">┗ ${task.tname}</td>
										<td class="project-table-day">${fn:substring(task.sday,0,10)}</td>
										<td class="project-table-day">${fn:substring(task.eday,0,10)}</td>
										<td class="project-table-day">${task.eday - task.sday}</td>
										<td id="project-table-td-tstatus" style="background-color:${task.color}">${task.tstatus}</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
