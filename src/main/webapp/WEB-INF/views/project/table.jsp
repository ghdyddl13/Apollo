<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<script>	

</script>
<div class="main-body-container">
  <div class="main-body-onepannel">
    <div class="main-body-onepannel-container">
      <div class="main-body-onepannel-header">
		<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>
        <div class="main-body-onepannel-header-bottom">
        	<div class="table-header-filter">
           		<select class="table-header-filter-status-tag" id="table-status-button">
            		<option value="all">STATUS:ALL</option>
            		<c:forEach var="tstatus" items="${tstatuslist}">
            			<option value="${tstatus.tstatus}" style="color:${tstatus.color}">${tstatus.tstatus}</option>
            		</c:forEach>
           		</select>
        	</div>
        </div>
      </div>
      <div class="main-body-onepannel-body">
		<div class="project-table-content" align="center">
		
	      <table class="table project-page-table">
	         <thead>
	            <tr>
	               <th class="project-page-table-title">Title</th>
	               <th>Start</th>
	               <th>Due</th>
	               <th>Duration</th>
	               <th>Assignee / Status</th>
	            </tr>
	         </thead>
	         <tbody id="project-page-tbody">
	       
	            <!-- 폴더에 속하지 않은 스텝 뿌려주기 -->
	            <c:forEach var="step" items="${steplist}">
	              
	            <!-- Duration 구하기 위한 날짜형식 변경 및 비교 -->
	             <fmt:parseDate var="stepstart"  value="${step.sday}" pattern="yy-MM-dd"/>
	            <fmt:parseDate  var="stepend" value="${step.eday}" pattern="yy-MM-dd"/>
	            
	            <fmt:parseNumber value="${stepstart.time/(1000*60*60*24)}" var="step_startsday" integerOnly="true"/>
	            <fmt:parseNumber value="${stepend.time/(1000*60*60*24)}" var="step_endeday" integerOnly="true"/>
	            
	               <c:if test="${step.fid eq null}">
	               <!-- step Assignee 뿌려주기 -->
	               <c:forEach var="member" items="${memberlist}">
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
	                     <c:choose>
	                     	<c:when test="${member.image eq null}">
	                     		<td><img class="project-table-member-image" src="img/user.png">${member.mname}</td>
	                     	</c:when>
	                     	<c:otherwise>
	                     		<td id="project-table-member-td"><div class="project-table-member-image">${member.image}</div>${member.mname}</td>
	                     	</c:otherwise>
	                     </c:choose>
	                     </c:forEach>
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
	                           </tr>
	                        </c:if>
	                     </c:forEach>
	                  </c:if>
	               </c:forEach>
	            </c:forEach>
	         </tbody>
	      </table>
	      <div id="project-page-table-no-data">
	      	<div id="proejct-page-table-img-content">
	      		<img src="img/rocket.png" id="proejct-page-table-img">
	      	</div>
	      	<div id="project-page-table-no-data-content">
	      		NO DATA!
	      	</div>
	      </div>
		 </div><!--END OF TABLE  -->
      </div>
    </div>
  </div>
</div>
