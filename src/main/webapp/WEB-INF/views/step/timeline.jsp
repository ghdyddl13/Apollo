<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<script type="text/javascript" src="js/task.js"></script>



<!-- <jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include> -->
<div class="main-body-container">
	<div class="main-body-onepannel">
		<div class="main-body-onepannel-container">
			<div class="main-body-onepannel-header">
				<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
				<div class="main-body-onepannel-header-bottom">
					<select id="timeline-tstatus-filter">
						<option value="all">All Status</option>
						<c:forEach var="tstatus" items="${tstatuses}">
							<option class="timeline-tstatus-filter"
								value="${tstatus.tstatusid}">${tstatus.tstatus}</option>
						</c:forEach>
					</select> <select id="timeline-assignee-filter">
						<option value="all">All Assignees</option>
						<option value="No">No Assigned</option>
						<c:forEach var="assignee" items="${assignees}">
							<option class="timeline-tstatus-filter" value="${assignee.mid}">${assignee.mname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="main-body-onepannel-body">
<!-- 				<div class="step-subheader container">
	
				</div>	 -->
				<div class="timeline-wrapper">
					<div class="container-fluid row" id="timeline">
						<div class="col-lg-3 step-timeline-div-table" >	
							<div>
								<div style="border-right:none" class="table step-timeline-table table-hover" id="timeline-table" width="100%" cellspacing="0">
									<div>
										<div class="timeline-table-header">
											<div class="timeline-table-header-no">No</div>
											<div class="timeline-table-header-assignee">담당자</div>
											<div class="timeline-table-header-task">Title</div>
										</div>
									</div>
									<div id="timeline-table-body">
										
									</div>
								</div>
							</div>
						</div>
						<div id="gantt-border" class="col-lg-9 step-timeline-div">
							<div class="gantt-target"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


	