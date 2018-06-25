<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="js/task.js"></script>
    
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div class="main-body">
  <div class="main-section-left" id="left">
    <div class="main-section-left-container">
      <div class="main-section-left-wrapper">
        <!-- LIST HEADER -->
        <div class="list-header">
          <div class="list-header-top">
            <div class="list-header-title" id="s${stepinfo.sid}">${stepinfo.sname}</div>
            <div class="list-header-selector">
              <span class="list-header-selector-container-selected">
                <span class="list-header-selector-item" id ="list-page">LIST</span>
              </span>
              <span class="list-header-selector-container">
                <span class="list-header-selector-item" id ="board-page">BOARD</span>
              </span>
              <span class="list-header-selector-container">
                <span class="list-header-selector-item" id ="timeline-page">TIMELINE</span>
              </span>
              <span class="list-header-selector-container">
                <span class="list-header-selector-item" id ="workload-page">WORKLOAD</span>
              </span>
            </div>
          </div>
          <div class="list-header-bottom">
            <div class="list-header-filter">
              <span class="list-header-filter-status" id="status-button">
                <span class="list-header-filter-status-tag">STATUS:ALL</span>
              </span>
              <span class="list-header-filter-people" id="people-button">
                <span class="list-header-filter-people-tag">TO:ALL</span>
              </span>
            </div>
            <div class="list-header-sorting">
              <div class="list-header-sorting-cover" >
                <button id="sorting-button" class="list-header-sorting-button"type="button" name="button">
                  <span class="list-header-sorting-tag">By Date</span>
                </button>
              </div>
            </div>
          </div>
        </div>
        <!--
          사람선택
          .list-header-filter-status-selecting{}
          분류
          .list-header-sorting-selecting{}
         -->
        <!-- DIV TAG -->
        <div id="status-selecting-tag"class="list-header-filter-status-selecting">
          <ul class="list-header-filter-status-items">
            <li class="list-header-menu-list-item-titletext">
              <span class="list-header-menu-title">Agile Teamwork Workflow</span>
            </li>
            <c:forEach var="tstatus" items="${tstatuslist}">
	            <li class="list-header-menu-list-item" id="${tstatus.tstatusid}">
	              <div class="list-header-menu-list-item-container">
	                <div class="color-tag" style="background-color:${tstatus.color}"></div><span class="list-header-menu-list-item-text">${tstatus.tstatus}</span>
	              </div>
	            </li>
            </c:forEach>
          </ul>
        </div>
        <!-- PEOPLE FILTER -->
        <div class="list-header-filter-people-selecting">

        </div>
        <!-- SORTING FILTER  -->
        <div class="list-header-sorting-selecting">
          <div class="list-header-sorting-container">
            <div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="Date"><span class="list-header-sorting-text">Date</span></div>
            </div>
            <div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="Status"><span class="list-header-sorting-text">Status</span></div>
            </div>
            <div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="Title"><span class="list-header-sorting-text">Title</span></div>
            </div>
          </div>
        </div>
        <!-- LIST BODY -->
        <div class="list-task-body">
          <div class="list-task-tasklist">
            <div class="list-task-lister">
              <div class="list-task-lister-container">
                <div class="list-task-adder_containers" id="body-start">
                  <div class="list-task-adder" id="list-task-adder">
                    New task
                  </div>
                  <!--LIST CONTAINER  -->
                  <div class="list-task-containers" >
                  	<c:forEach var="task" items="${tasklist}">
	                    <div  class="list-task-container">
	                      <div class= "list-task-checkbox-container">
	                        <span class="list-task-checkbox"></span>
	                      </div>
	                      <div class= "image-container"><img class="profile-image" src="img/frog.png" alt=""></div>
	                      <div class="center-container Task_RUD_Modal"data-toggle="modal" data-target="#Task_RUD_Modal" id="t${task.tid}">
	                        <div class= "task-name-container">${task.tname}</div>
	                        <div class= "task-step-container">
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
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div><!-- LEFT CONTAINER END  -->
  <div class="main-section-center" id="center"></div>
  <!-- <div class="main-section-right" id="right"></div> -->
  <!-- THIRD PANNEL DEFAULT -->
  <div class="list-section-third-default">
    <div class="list-section-third-cover">
      <div class="list-section-third-complpercent">
        <div class="list-section-third-title">
          <span class="list-section-third-title-text">Percent of Task Completion</span>
        </div>
        <div class="list-section-third-complpercent-graph">

        </div>
      </div>
      <div class="list-section-third-deadline">
        <div class="list-section-third-title">
          <span class="list-section-third-title-text">Deadline of This Step</span>
        </div>
        <div class="list-section-third-deadline-graph">

        </div>
      </div>
      <div class="list-section-third-completion">
        <div class="list-section-third-title">
          <span class="list-section-third-title-text">Status of Task</span>
        </div>
        <div class="list-section-third-completion-graph">
            <canvas id="Step-list-DonutChart"></canvas>
        </div>
      </div>
    </div>
  </div>
   <!--THIRD PANNEL SELECTED   -->
  <div class="list-section-third-selectpage">
    <div class="list-section-third-selectpage-header">
      <div class="list-section-third-selectpage-header-container">
        <div class="list-section-third-selectpage-header-close">
          <span class="list-section-third-selectpage-header-closebutton"id="list-closebutton"></span>
        </div>
        <div class="list-section-third-selectpage-header-title">
          <div class="list-section-third-selectpage-header-text" id="selected-tag-count">0개 선택됨</div>
        </div>
      </div>
    </div>
    <div class="list-section-third-selectpage-body">
      <div class="list-section-third-selectpage-statuschange">
        <span class="selectpage-body-button" id="selectpage-complete-button">Completed</span>
        <span class="list-section-third-selectpage-text" id="different-status">Choose Different status</span>
      </div>
      <div class="list-section-third-selectpage-addasignee">
        <span class="list-section-third-selectpage-text">Add Asignees</span>
        <span class="selectpage-body-button" id="selectpage-addasignee-button">Add</span>
      </div>
      <div class="list-section-third-selectpage-addstep">
        <span class="list-section-third-selectpage-text">Add Steps</span>
        <span class="selectpage-body-button" id="selectpage-addsteps-button">Add</span>
      </div>
      <div class="list-section-third-selectpage-deletetask">
        <span class="list-section-third-selectpage-text">Delete tasks</span>
        <span class="selectpage-body-button" id="selectpage-deletetask-button">Delete</span>
      </div>
    </div>
  </div>
</div><!-- BODY CONTAINER END  -->
