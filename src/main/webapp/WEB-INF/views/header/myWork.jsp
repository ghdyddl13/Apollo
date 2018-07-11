<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script type="text/javascript" src="js/task.js"></script>


<div id="mywork-member-popup-div" class="mywork-member-popup">

</div>

<div class="main-body-container">
    <div class="main-body-twopannel">
      <div class="main-body-twopannel-left">
        <div class="main-body-twopannel-left-wrapper">
          <div class="main-body-twopannel-left-header">
            <div class="main-body-twopannel-left-header-wrapper">
              <div class="main-body-twopannel-left-header-title-textwrapper">
                <div class="main-body-twopannel-left-header-title">
                  <span class="main-body-twopannel-left-header-title-text">My Work</span>
                </div>
              </div>
            </div>
          </div>
          <div class="main-body-twopannel-left-body">
            <div class="main-body-twopannel-left-body-wrapper">
              <div class="mywork-left-today">
                <div class="mywork-header-wrapper">
                  <div class="mywork-header">
                    <div class="mywork-header-textwrapper">
                      today
                      <span class="mywork-header-date">${today}</span>
                      <em class="mywork-header-count">${counttodaylist}</em>
                    </div>
                  </div>
                </div>
                <div class="mywork-main-tasks">
                  <c:forEach var="todaytask" items="${todaylist}">
	                  <div class="mywork-main-task-wrapper">
	                    <div class="mywork-main-task Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${todaytask.tid}">
	                      <div class="mywork-main-task-checkbox"></div>
		                    <div class="mywork-main-task-members">
								<c:forEach var="member" items="${todaytask.members}" end="0">
									<img class="mywork-main-task-member" src='displayImage.htm?image=${member.image}'>
									<div class="mywork-main-task-member-hidden">
										<c:forEach var="member_hidden" items="${todaytask.members}">
											<img class="mywork-main-task-member" src='displayImage.htm?image=${member_hidden.image}'>
										</c:forEach>
									</div>
								</c:forEach>
											</div>
		                    <c:choose>
								<c:when test="${todaytask.overdue=='overdue'}">
									<div class="mywork-main-task-date date-overdue">${todaytask.date}</div>	
								</c:when>
								<c:otherwise>
									<div class="mywork-main-task-date">${todaytask.date}</div>						
								</c:otherwise>
							</c:choose>
	                      <div class="mywork-main-task-name">
	                        <div class="mywork-main-task-name-wrapper">
	                          <span class="mywork-main-task-name-tag">
	                            <span class="mywork-main-task-name-status" style="background-color:${todaytask.color}">${todaytask.tstatus}</span>
	                            ${todaytask.tname}
	                          </span>
	                          <span class="mywork-main-task-name-detail">${todaytask.detail}</span>
	                        </div>
	                      </div>
	                      <div class="mywork-main-task-steps">
	                      	<c:forEach var="step" items="${todaytask.steps}">
		                       <div class="mywork-main-task-step">
	                          		<span class="mywork-main-task-step-name" id="s${step.sid}">${step.sname}</span>
	                     	   </div>
							</c:forEach>
	                      </div>
	                    </div>
	                  </div>
                  </c:forEach>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>


      <div class="main-body-twopannel-right">
        <div class="mywork-right-wrapper">
          <div class="mywork-right-thisweek">
        	  <div class="mywork-header-wrapper">
                <div class="mywork-header">
                  <div class="mywork-header-textwrapper">
                    this week
                    <span class="mywork-header-date">${thisweek}</span>
                    <em class="mywork-header-count">${countthisweeklist}</em>
                  </div>
                </div>
              </div>
              <div class="mywork-main-tasks">
                <c:forEach var="thisweektask" items="${thisweeklist}">
                 <div class="mywork-main-task-wrapper">
                   <div class="mywork-main-task Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${thisweektask.tid}">
                     <div class="mywork-main-task-checkbox"></div>
                    <div class="mywork-main-task-members">
                      <img class="mywork-main-task-member" src="img/user.png" alt="">
                    </div>
                    <c:choose>
						<c:when test="${thisweektask.overdue=='overdue'}">
							<div class="mywork-main-task-date date-overdue">${thisweektask.date}</div>	
						</c:when>
						<c:otherwise>
							<div class="mywork-main-task-date">${thisweektask.date}</div>						
						</c:otherwise>
					</c:choose>
                     <div class="mywork-main-task-name">
                       <div class="mywork-main-task-name-wrapper">
                         <span class="mywork-main-task-name-tag">
                           <span class="mywork-main-task-name-status" style="background-color:${thisweektask.color}">${thisweektask.tstatus}</span>
                           ${thisweektask.tname}
                         </span>
                         <span class="mywork-main-task-name-detail">${thisweektask.detail}</span>
                       </div>
                     </div>
                     <div class="mywork-main-task-steps">
                     	<c:forEach var="step" items="${thisweektask.steps}">
                       <div class="mywork-main-task-step">
                         		<span class="mywork-main-task-step-name" id="s${step.sid}">${step.sname}</span>
                    	   </div>
					</c:forEach>
                     </div>
                   </div>
                 </div>
                </c:forEach>
              </div>
          </div>


          <div class="mywork-right-nextweek">
              <div class="mywork-header-wrapper">
                <div class="mywork-header">
                  <div class="mywork-header-textwrapper">
                    next week
                    <span class="mywork-header-date">${nextweek}</span>
                    <em class="mywork-header-count">${countnextweeklist}</em>
                  </div>
                </div>
              </div>
              <div class="mywork-main-tasks">
                <c:forEach var="nextweektask" items="${nextweeklist}">
                 <div class="mywork-main-task-wrapper">
                   <div class="mywork-main-task Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${nextweektask.tid}">
                     <div class="mywork-main-task-checkbox"></div>
                    <div class="mywork-main-task-members">
                      <img class="mywork-main-task-member" src="img/user.png" alt="">
                    </div>
                    <c:choose>
						<c:when test="${nextweektask.overdue=='overdue'}">
							<div class="mywork-main-task-date date-overdue">${nextweektask.date}</div>	
						</c:when>
						<c:otherwise>
							<div class="mywork-main-task-date">${nextweektask.date}</div>						
						</c:otherwise>
					</c:choose>
                     <div class="mywork-main-task-name">
                       <div class="mywork-main-task-name-wrapper">
                         <span class="mywork-main-task-name-tag">
                           <span class="mywork-main-task-name-status" style="background-color:${nextweektask.color}">${nextweektask.tstatus}</span>
                           ${nextweektask.tname}
                         </span>
                         <span class="mywork-main-task-name-detail">${nextweektask.detail}</span>
                       </div>
                     </div>
                     <div class="mywork-main-task-steps">
                     	<c:forEach var="step" items="${nextweektask.steps}">
                       <div class="mywork-main-task-step">
                         		<span class="mywork-main-task-step-name" id="s${step.sid}">${step.sname}</span>
                    	   </div>
					</c:forEach>
                     </div>
                   </div>
                 </div>
                </c:forEach>
              </div>
          </div>

          
          <div class="mywork-right-later">
              <div class="mywork-header-wrapper">
                <div class="mywork-header">
                  <div class="mywork-header-textwrapper">
                    later
                    <span class="mywork-header-date">${later}</span>
                    <em class="mywork-header-count">${countlaterlist}</em>
                  </div>
                </div>
              </div>
              <div class="mywork-main-tasks">
                <c:forEach var="latertask" items="${laterlist}">
                 <div class="mywork-main-task-wrapper">
                   <div class="mywork-main-task Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${latertask.tid}">
                     <div class="mywork-main-task-checkbox"></div>
                    <div class="mywork-main-task-members">
                      <img class="mywork-main-task-member" src="img/user.png" alt="">
                    </div>
                    <c:choose>
						<c:when test="${latertask.overdue=='overdue'}">
							<div class="mywork-main-task-date date-overdue">${latertask.date}</div>	
						</c:when>
						<c:otherwise>
							<div class="mywork-main-task-date">${latertask.date}</div>						
						</c:otherwise>
					</c:choose>
                     <div class="mywork-main-task-name">
                       <div class="mywork-main-task-name-wrapper">
                         <span class="mywork-main-task-name-tag">
                           <span class="mywork-main-task-name-status" style="background-color:${latertask.color}">${latertask.tstatus}</span>
                           ${latertask.tname}
                         </span>
                         <span class="mywork-main-task-name-detail">${latertask.detail}</span>
                       </div>
                     </div>
                     <div class="mywork-main-task-steps">
                     	<c:forEach var="step" items="${latertask.steps}">
                       <div class="mywork-main-task-step">
                         		<span class="mywork-main-task-step-name" id="s${step.sid}">${step.sname}</span>
                    	   </div>
					</c:forEach>
                     </div>
                   </div>
                 </div>
                </c:forEach>
              </div>
          </div>
        </div>
      </div>
    </div>
</div>
