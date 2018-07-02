<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script type="text/javascript" src="js/task.js"></script>
<div class="main-body-container">
  <div class="main-body-twopannel">
    <div class="main-body-twopannel-left">
      <div class="main-body-twopannel-left-wrapper">
        <div class="main-body-twopannel-left-header">
          <div class="main-body-twopannel-left-header-wrapper">
            <div class="main-body-twopannel-left-header-title-textwrapper">
              <div class="main-body-twopannel-left-header-title">
                <span class="main-body-twopannel-left-header-title-text">Starred Task</span>
              </div>
            </div>
          </div>
        </div>
        <div class="main-body-twopannel-left-body">
          <div class="main-body-twopannel-left-body-wrapper">
            <div class="starred-body-wrapper">
              <div class="starred-body-tasks">
				<c:forEach var="task" items="${tasklist}">
	                <div class="starred-body-task">
	                  <div class="starred-body-task-wrapper Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${task.tid}">
	                    <div class="starred-body-task-image-wrapper">
	                      <div class="starred-body-task-image"><img class="starred-body-task-imagetag" src="img/frog.png" alt=""></div>
	                    </div>
	                    <div class="starred-body-task-container">
	                      <div class="starred-body-task-container-top">
	                        <div class="starred-body-task-container-top-title">${task.tname}</div>
	                        <c:choose>
								<c:when test="${task.overdue=='overdue'}">
			                        <div class="starred-body-task-container-top-date date-overdue">${task.date}</div>
								</c:when>
								<c:otherwise>
			                        <div class="starred-body-task-container-top-date">${task.date}</div>
								</c:otherwise>
							</c:choose>
	                        <div class="starred-body-task-container-top-star"><i class="fas fa-star"></i></div>
	                      </div>
	                      <div class="starred-body-task-container-bottom">
	                        <div class="starred-body-task-container-bottom-status" style="color:${task.color}">${task.tstatus}</div>
	                        <div class="starred-body-task-container-bottom-steps">
	                          <c:forEach var="step" items="${task.steps}">
	                          	<div class="starred-body-task-container-bottom-step" id="s${step.sid}">${step.sname}</div>	                          
	                          </c:forEach>
	                        </div>
	                      </div>
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
      <div class="starred-secondbody-wrapper">
        <div class="starred-secondbody-image"></div>
      </div>
    </div>
  </div>
</div>