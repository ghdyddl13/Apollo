<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- TASK_JK -->
<script type="text/javascript" src="js/task.js"></script>
<div class="main-body-container">
	<div class="main-body-onepannel">
		<div class="main-body-onepannel-container">
			<div class="main-body-onepannel-header">
				<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
				<div class="main-body-onepannel-header-bottom"></div>
			</div>
			<div class="main-body-onepannel-body">
				<div class="container-fluid" id="board-main-div">
					<div class="container-fluid" id="board-content-md">
						<div class="container-fluid" id="board-status-div">
							<c:forEach var="b" items="${b}">
								<div>
									<p align="center" id="board-status-name">${b.tstatus}
										<input type="hidden" value="${b.tstatusid}">
									</p>
									<hr id="board-hr" style="background-color:${b.color};">
									<div class="board-task-adder_containers"
										id="body-start${b.tstatusid}">
										<div class="board-task-adder" id="task-adder${b.tstatusid}"
											onclick="addTaskView(${b.tstatusid})">

											<input type="hidden" id="board-tstatusid"
												value="${b.tstatusid}"> New Task
										</div>
									</div>
									<ul id="board-sortable" class="tstatuslist">
										<c:forEach var="t" items="${t}">
											<c:choose>
												<c:when test="${b.tstatus eq t.tstatus}">
													<li class="ui-state-default Task_RUD_Modal"
														data-toggle="modal" data-target="#Task_RUD_Modal"
														id="t${t.tid}" value="${t.tid}">${t.tname}</li>
												</c:when>
											</c:choose>
										</c:forEach>
									</ul>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
