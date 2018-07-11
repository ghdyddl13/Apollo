<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html >
<script type="text/javascript" src="js/task.js"></script>
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
            </div>
          </div>
          <div class="list-header-bottom">
            <div class="list-header-filter">
              <span class="list-header-filter-status">
                <span class="list-header-filter-status-tag" id="status-button">STATUS:ALL</span>
              </span>
              <span class="list-header-filter-people" id="people-button">
                <span class="list-header-filter-people-tag" id="people-button-tag">TO:ALL</span>
              </span>
            </div>
            <div class="list-header-sorting">
              <div class="list-header-sorting-cover" >
                <button id="sorting-button" class="list-header-sorting-button"type="button" name="button">
                	<c:choose>
						<c:when test="${sorting=='changedate'}">
		                  <span class="list-header-sorting-tag">By Changed Time</span>
						</c:when>
						<c:when test="${sorting=='duedate'}">
		                  <span class="list-header-sorting-tag">By Due Date</span>
						</c:when>
						<c:when test="${sorting=='status'}">
		                  <span class="list-header-sorting-tag">By Status</span>
						</c:when>
						<c:when test="${sorting=='title'}">
		                  <span class="list-header-sorting-tag">By Title</span>
						</c:when>
					</c:choose>
                </button>
              </div>
            </div>
          </div>
        </div>
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
          <div class="list-header-filter-people-container">
            <input type="text" id="filter-people-input" class="list-header-filter-people-input" placeholder="Search user">
            <div class="list-header-filter-people-tagscontainer">
            </div>
          </div>
        </div>
        <!-- SORTING FILTER  -->
        <div class="list-header-sorting-selecting">
          <div class="list-header-sorting-container">
          	<div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="changedate"><span class="list-header-sorting-text">Changed Time</span></div>
            </div>
            <div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="duedate"><span class="list-header-sorting-text">Due Date</span></div>
            </div>
            <div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="status"><span class="list-header-sorting-text">Status</span></div>
            </div>
            <div class="list-header-sorting-item">
              <div class="list-header-sorting-textcontainer" id="title"><span class="list-header-sorting-text">Title</span></div>
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
	                      <div class= "image-container"><img class="profile-image" src="img/user.png" alt=""></div>
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
									<div class="date-overdue date-container">${task.date}</div>
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
        <div class="list-section-third-complpercent-graph"id="uppergraph"></div>
      </div>
      <div class="list-section-third-deadline">
        <div class="list-section-third-title">
          <span class="list-section-third-title-text">Deadline of This Step</span>
        </div>
        <div class="list-section-third-deadline-graph" id="middlegraph"></div>
      </div>
      <div class="list-section-third-completion">
        <div class="list-section-third-title">
          <span class="list-section-third-title-text">Status of Task</span>
        </div>
        <div class="list-section-third-completion-graph" id="bottomgraph"></div>
      </div>
      <script type="text/javascript">
	      var dom = document.getElementById("uppergraph");
	      var myChart = echarts.init(dom);
	      var app = {};
	      option = null;
	      app.title = '상단그래프';
	      option = {
	          tooltip : {
	              trigger: 'axis',
	              axisPointer : {
	                  type : 'shadow'
	              }
	          },
	          grid: {
	              left: '3%',
	              right: '4%',
	              bottom: '3%',
	              containLabel: true
	          },
	          xAxis:  {
	              type: 'value'
	          },
	          yAxis: {
	              type: 'category',
	              data: ' '
	          },
	          series: [
	              {
	                  name: '미완료 작업',
	                  type: 'bar',
	                  stack: '일',
	                  label: {
	                      normal: {
	                          show: true
	                      }
	                  },
	                  data: [<%=(Integer)request.getAttribute("unfinishedtask")%>]
	              },
	              {
	                  name: '완료된 작업',
	                  type: 'bar',
	                  stack: '일',
	                  label: {
	                      normal: {
	                          show: true
	                      }
	                  },
	                  data: [<%=(Integer)request.getAttribute("completedtask")%>]
	              }
	          ],
	          color:['#96add6','#c4d97f'],
	          textStyle:{
	              color:'#ffffff',
	              fontSize:20,
	          }
	      };;
	      if (option && typeof option === "object") {
	          myChart.setOption(option, true);
	      }
	
	      
	      var dom = document.getElementById("middlegraph");
	      var myChart = echarts.init(dom);
	      var app = {};
	      option = null;
	      app.title = '상단그래프';
	      option = {
	          tooltip : {
	              trigger: 'axis',
	              axisPointer : {
	                  type : 'shadow'
	              }
	          },
	          grid: {
	              left: '3%',
	              right: '4%',
	              bottom: '3%',
	              containLabel: true
	          },
	          xAxis:  {
	              type: 'value'
	          },
	          yAxis: {
	              type: 'category',
	              data: ' '
	          },
	          series: [
	              {
	                  name: '지난 일',
	                  type: 'bar',
	                  stack: '일',
	                  label: {
	                      normal: {
	                          show: true
	                      }
	                  },
	                  data: [<%=(Integer)request.getAttribute("thepast")%>]
	              },
	              {
	                  name: '남은 일',
	                  type: 'bar',
	                  stack: '일',
	                  label: {
	                      normal: {
	                          show: true
	                      }
	                  },
	                  data: [<%=(Integer)request.getAttribute("therest")%>]
	              }
	          ],
	          color:['#96add6','#c4d97f'],
	          textStyle:{
	              color:'#ffffff',
	              fontSize:20,
	          }
	      };;
	      if (option && typeof option === "object") {
	          myChart.setOption(option, true);
	      }
	      
	      
	      var dom = document.getElementById("bottomgraph");
	      var myChart = echarts.init(dom);
	      var app = {};
	      option = null;
	      option = {
	
	          tooltip : {
	              trigger: 'item',
	              formatter: "{a} <br/>{b} : {c} ({d}%)"
	          },
	          series : [
	              {
	                  name: 'Task마감',
	                  type: 'pie',
	                  radius : '50%',
	                  center: ['45%', '50%'],
	                  data:[
	                      {value:<%=(Integer)request.getAttribute("noday")%>, name:'미지정'},
	                      {value:<%=(Integer)request.getAttribute("afternextweek")%>, name:'다음주 이후'},
	                      {value:<%=(Integer)request.getAttribute("untilthisweek")%>, name:'이번주까지'},
	                      {value:<%=(Integer)request.getAttribute("completedtask")%>, name:'완료'},
	                      {value:<%=(Integer)request.getAttribute("overduetask")%>, name:'기한만료'}
	                  ],
	                  itemStyle: {
	                      emphasis: {
	                          shadowBlur: 10,
	                          shadowOffsetX: 0,
	                          shadowColor: 'rgba(0, 0, 0, 0.5)'
	                      }
	                  }
	              }
	          ],
	          color:['rgb(235, 188, 87)',
	                'rgb(196, 217, 127)',
	                'rgb(255, 255, 255)',
	                'rgb(150, 173, 214)',
	                'rgb(255, 117, 117)']
	      };
	      if (option && typeof option === "object") {
	          myChart.setOption(option, true);
	      };
      </script>
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
      	<span class="list-section-third-selectpage-text">Add Status</span>
      	<c:forEach var="tstatus" items="${tstatuslist}">
	        <span class="selectpage-body-statusbutton" id="${tstatus.tstatusid}"style="background-color:${tstatus.color}"data-toggle="modal" data-target="#list_Status_Tasks">${tstatus.tstatus}</span>
        </c:forEach>
      </div>
      <div class="list-section-third-selectpage-addasignee">
        <span class="list-section-third-selectpage-text">Add Asignee</span>
        <span class="selectpage-body-button" id="selectpage-addasignee-button">Add Asignee</span>
      </div>
      <div class="list-section-third-selectpage-addstep">
        <span class="list-section-third-selectpage-text">Add Step</span>
        <span class="selectpage-body-button" id="selectpage-addsteps-button">Add Step</span>
      </div>
      <div class="list-section-third-selectpage-deletetask">
        <span class="list-section-third-selectpage-text">Delete tasks</span>
        <span class="selectpage-body-button" id="selectpage-deletetask-button" data-toggle="modal" data-target="#list_Delete_Tasks">Delete Tasks</span>
      </div>
    </div>
  </div>
  <!--THIRD PANNEL ADD STEP -->
 <div class="list-section-third-addstep">
   <div class="list-section-third-addstep-wrapper">
     <div class="list-section-third-addstep-header">
       <div class="list-section-third-addstep-headwrapper">
         <div class="list-section-third-addstep-head-text">Current Step</div>
         <div class="list-section-third-addstep-head-cur_step">
           <div class="list-section-third-addstep-head-cur_step-cover">
             <span class="list-section-third-addstep-head-cur_step-text">${stepinfo.sname}</span>
           </div>
         </div>
       </div>
     </div>
     <div class="list-section-third-addstep-text">ADD STEPS</div>
     <div class="list-section-third-addstep-steplist">
       <div class="list-section-third-addstep-steplist-inputsection">
         <input class="list-section-third-addstep-steplist-input" id="addstep-insert_step"type="text" autocomplete="off" placeholder="Search step">
       </div>
       <div class="list-section-third-addstep-steplist-wrapper">
         <div class="list-section-third-addstep-steplist-wrapper_cover">
         </div>
       </div>
     </div>
   </div>
 </div>
 <!--THIRD PANNEL ASSIGN-->
 <div class="list-section-third-addasignee">
  <div class="list-section-third-addasignee-container">
    <div class="list-section-third-addasignee-wrapper">
      <div class="list-section-third-addasignee-input_container">
        <div class="list-section-third-addasignee-input_wrapper">
          <div class="list-section-third-addasignee-input-text">
            <input class="list-section-third-addasignee-input" id="assign-insert_member"type="text" placeholder="Search member">
          </div>
        </div>
      </div>
      <div class="list-section-third-addasignee-memberlist_wrapper">
        <div class="list-section-third-addasignee-memberlist">
        </div>
      </div>
    </div>
  </div>
</div>
</div><!-- BODY CONTAINER END  -->
