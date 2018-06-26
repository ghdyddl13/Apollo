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
			<img class="profile-image" src="img/frog.png" alt="">
		</div>
		<div class="center-container Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${task.tid}">
			<div class="task-name-container">${task.tname}</div>
			<div class="task-step-container">
				<c:forEach var="step" items="${task.steps}">
					<span class="step-container" id="s${step.sid}">${step.sname}</span>
				</c:forEach>
			</div>
			<span class="status-container" style="color: ${task.color}">${task.tstatus}</span>
			<c:choose>
				<c:when test="${task.overdue=='overdue'}">
					<div class="date-container task-date-overdue">${task.date}</div>
				</c:when>
				<c:otherwise>
					<div class="date-container">${task.date}</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</c:forEach>