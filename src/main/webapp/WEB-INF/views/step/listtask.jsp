<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:forEach var="task" items="${tasklist}">
	<div class="list-task-container">
		<div class="list-task-checkbox-container">
			<span class="list-task-checkbox"></span>
		</div>
		<div class="image-container">
			<c:choose>
				<c:when test="${empty task.members}">
					<div class="profile-image">
						<div class="list-noassignee-icon-container">
							<i class="fas fa-user-plus no-assignee-task-icon"></i>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="member" items="${task.members}" end="0">
						<img class="list-task-member"
							src='displayImage.htm?image=${member.image}'>
						<div class="list-task-member-hidden">
							<div class="list-member-hidden-wrapper">
								<c:forEach var="list_member_hidden" items="${task.members}">
									<img class="list-task-member-hidden-img"
										src='displayImage.htm?image=${list_member_hidden.image}'>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</div>

		<div class="center-container Task_RUD_Modal" data-toggle="modal"
			data-target="#Task_RUD_Modal" id="t${task.tid}">
			<div class="task-name-container">${task.tname}</div>
			<div class="task-step-container">
				<c:forEach var="step" items="${task.steps}">
					<span class="step-container" id="s${step.sid}">${step.sname}</span>
				</c:forEach>
			</div>
			<span class="status-container" style="color: ${task.color}">${task.tstatus}</span>
			<c:choose>
				<c:when test="${task.overdue=='overdue'}">
					<div class="date-overdue date-container">${task.date}</div>
				</c:when>
				<c:otherwise>
					<div class="date-container">${task.date}</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</c:forEach>