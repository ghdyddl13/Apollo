<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="member" value="${profileinfo}"></c:set>
	<h4 id="project-table-page-pname">해당 프로젝트명 들어가는 곳</h4>
	<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>
	<div class="project-table-content">
		<table class="table project-page-table">
			<thead>
				<tr>
					<th class="project-page-table-title">Title</th>
					<th>Start</th>
					<th>Due</th>
					<th>Duration</th>
					<th>Status</th>
					<th>Assigned to</th>
				</tr>
			</thead>
			<tbody>
			
				<!-- 폴더에 속하지 않은 스텝 뿌려주기 -->
				<c:forEach var="step" items="${steplist}">
				
				<!-- Duration 구하기 위한 날짜형식 변경 및 비교 -->
			 	<fmt:parseDate var="stepstart"  value="${step.sday}" pattern="yy-MM-dd"/>
				<fmt:parseDate  var="stepend" value="${step.eday}" pattern="yy-MM-dd"/>
				
				<fmt:parseNumber value="${stepstart.time/(1000*60*60*24)}" var="step_startsday" integerOnly="true"/>
				<fmt:parseNumber value="${stepend.time/(1000*60*60*24)}" var="step_endeday" integerOnly="true"/>
				
					<c:if test="${step.fid eq null}">
						<tr class="project-table-tr-steps">
							<td><i class="side-dir-step-icon far fa-file-alt"></i>&nbsp;${step.sname}</td>
							<td class="project-table-day">${step.sday}</td>
							<td class="project-table-day">${step.eday}</td>
							<c:choose>
								<c:when test="${(step_endeday - step_startsday) == 0}">
									<td class="project-table-duration"></td>
								</c:when>
								<c:otherwise>
									<td class="project-table-duration">${step_endeday - step_startsday}일</td>
								</c:otherwise>
							</c:choose>
							<td></td>
							
							<td class="project-page-table-td-assignee" id="${step.mid}"></td>
							
						<!-- 스텝에 속한 task 뿌려주기 -->
						<c:forEach var="task" items="${tasklist}">
						
						<!-- Duration 구하기 위한 날짜형식 변경 및 비교 -->
					 	<fmt:parseDate var="taskstart"  value="${task.sday}" pattern="yy-MM-dd"/>
						<fmt:parseDate  var="taskend" value="${task.eday}" pattern="yy-MM-dd"/>
						
						<fmt:parseNumber value="${taskstart.time/(1000*60*60*24)}" var="task_startsday" integerOnly="true"/>
						<fmt:parseNumber value="${taskend.time/(1000*60*60*24)}" var="task_endeday" integerOnly="true"/>
						
							<c:if test="${task.sid eq step.sid}">
								<tr class="project-table-tr-tasks Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${task.tid}">
									<td style="padding-left: 30px">┗ ${task.tname}</td>
									<td class="project-table-day">${task.sday}</td>
									<td class="project-table-day">${task.eday}</td>
									<c:choose>
										<c:when test="${(task_endeday - task_startsday) == 0}">
											<td class="project-table-duration"></td>
										</c:when>
										<c:otherwise>
											<td class="project-table-duration">${task_endeday - task_startsday}일</td>
										</c:otherwise>
									</c:choose>
									<td class="project-table-td-tstatus" style="background-color:${task.color}">${task.tstatus}</td>
									<td></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
				<!-- 폴더 뿌려주기 -->
				<c:forEach var="folder" items="${folderlist}">
					<tr class="project-table-tr-folders">
						<td><i class="side-dir-folder-icon fas fa-folder"></i>&nbsp;${folder.fname}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<!-- 폴더에 속한 스텝 뿌려주기 -->
					<c:forEach var="step" items="${steplist}">
						<c:if test="${step.fid eq folder.fid}">
							<tr class="project-table-tr-steps" data-toggle="modal" data-target="#update-step" id="${step.sid}">
								<td style="padding-left: 30px"><i class="side-dir-step-icon far fa-file-alt"></i>&nbsp;${step.sname}</td>
								<td class="project-table-day">${step.sday}</td>
								<td class="project-table-day">${step.eday}</td>
								<c:choose>
								<c:when test="${(step_endeday - step_startsday) == 0}">
									<td class="project-table-duration"></td>
								</c:when>
								<c:otherwise>
									<td class="project-table-duration">${step_endeday - step_startsday}일</td>
								</c:otherwise>
								</c:choose>
								<td></td>
								<td></td>
							</tr>
							<!-- 스텝에 속한 task 뿌려주기 -->
							<c:forEach var="task" items="${tasklist}">
								<c:if test="${task.sid eq step.sid}">
									<tr class="project-table-tr-tasks Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${task.tid}"> 
										<td style="padding-left: 60px">┗ ${task.tname}</td>
										<td class="project-table-day">${task.sday}</td>
										<td class="project-table-day">${task.eday}</td>
										<c:choose>
										<c:when test="${(task_endeday - task_startsday) == 0}">
											<td class="project-table-duration"></td>
										</c:when>
										<c:otherwise>
											<td class="project-table-duration">${task_endeday - task_startsday}일</td>
										</c:otherwise>
										</c:choose>
										<td class="project-table-td-tstatus" style="background-color:${task.color}">${task.tstatus}</td>
										<td></td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>

