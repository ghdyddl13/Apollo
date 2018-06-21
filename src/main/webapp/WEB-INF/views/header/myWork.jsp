<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div class="main-container container">
	<div class="main-section-left" id="left">
		<div class="week-wrapper" id="today">
			<div class="main-section-header" id="today">
				<div class="week-section">TODAY</div>
				<div class="date-section">${today}</div>
				<div class="week-count-section" id="todaycount" style="float: right">${counttodaylist}</div>
			</div>
			<c:forEach var="todaytask" items="${todaylist}">
				<div class="main-section-task" id="t${todaytask.tid}">
					<div class="task-image-section"></div>
					<c:choose>
						<c:when test="${todaytask.overdue=='overdue'}">
							<div class="task-date-section task-date-overdue"style="float: right">${todaytask.date}</div>	
						</c:when>
						<c:otherwise>
							<div class="task-date-section"style="float: right">${todaytask.date}</div>						
						</c:otherwise>
					</c:choose>
					<div class="task-description-section">
						<div class="task-status" style="background-color:${todaytask.color}">${todaytask.tstatus}</div>
						<div class="task-name-section">${todaytask.tname}</div>
						<div class="task-detail-section">${todaytask.detail}</div>
					</div>
					<div class="task-step-section">
						<div class="div-step-section">
							<c:forEach var="step" items="${todaytask.steps}">
								<span class="span-step-section" id="s${step.sid}">${step.sname}</span>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="main-section-center" id="center"></div>
	<div class="main-section-right" id="right">
		<div class="week-wrapper" id="thisweek">
			<div class="main-section-header">
				<div class="week-section">THIS WEEK</div>
				<div class="date-section">${thisweek}</div>
				<div class="week-count-section" id="todaycount" style="float: right">${countthisweeklist}</div>
			</div>
			<c:forEach var="thisweektask" items="${thisweeklist}">
				<div class="main-section-task" id="t+${thisweektask.tid}">
					<div class="task-image-section"></div>
					<div class="task-date-section"
						style="float: right">${thisweektask.date}</div>
					<div class="task-description-section">
						<div class="task-status" style="background-color:${thisweektask.color}">${thisweektask.tstatus}</div>
						<div class="task-name-section">${thisweektask.tname}</div>
						<div class="task-detail-section">${thisweektask.detail}</div>
					</div>
					<div class="task-step-section">
						<div class="div-step-section">
							<c:forEach var="step" items="${thisweektask.steps}">
								<span class="span-step-section" id="s+${step.sid}">${step.sname}</span>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="week-wrapper" id="nextweek">
			<div class="main-section-header">
				<div class="week-section">NEXT WEEK</div>
				<div class="date-section">${nextweek}</div>
				<div class="week-count-section" id="todaycount" style="float: right">${countnextweeklist}</div>
			</div>
			<c:forEach var="nextweektask" items="${nextweeklist}">
				<div class="main-section-task" id="t+${nextweektask.tid}">
					<div class="task-image-section"></div>
					<div class="task-date-section"
						style="float: right">${nextweektask.date}</div>
					<div class="task-description-section">
						<div class="task-status" style="background-color:${nextweektask.color}">${nextweektask.tstatus}</div>
						<div class="task-name-section">${nextweektask.tname}</div>
						<div class="task-detail-section">${nextweektask.detail}</div>
					</div>
					<div class="task-step-section">
						<div class="div-step-section">
							<c:forEach var="step" items="${nextweektask.steps}">
								<span class="span-step-section" id="s+${step.sid}">${step.sname}</span>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="week-wrapper" id="later">
			<div class="main-section-header">
				<div class="week-section">LATER</div>
				<div class="date-section">${later}</div>
				<div class="week-count-section" id="todaycount" style="float: right">${countlaterlist}</div>
			</div>
			<c:forEach var="latertask" items="${laterlist}">
				<div class="main-section-task" id="t+${latertask.tid}">
					<div class="task-image-section"></div>
					<div class="task-date-section"
						style="float: right">${latertask.date}</div>
					<div class="task-description-section">
						<div class="task-status" style="background-color:${latertask.color}">${latertask.tstatus}</div>
						<div class="task-name-section">${latertask.tname}</div>
						<div class="task-detail-section">${latertask.detail}</div>
					</div>
					<div class="task-step-section">
						<div class="div-step-section">
							<c:forEach var="step" items="${latertask.steps}">
								<span class="span-step-section" id="s+${step.sid}">${step.sname}</span>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript">
			
</script>