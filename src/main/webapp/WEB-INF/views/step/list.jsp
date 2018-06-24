<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script type="text/javascript" src="js/stepInsideHeader.js"></script>
<script type="text/javascript">
      $(function() {
        /////////////////////////////////태스크 새로 입력하는 부분/////////////////////////////////////////
        $(document).on("click","#list-task-adder",function() {
          $("#list-task-adder").remove();
          let inputtag="<div class='list-task-adder-addmode'><input class='form-control' id='insert-task' name='tname' type='text' placeholder='새로운 작업을 입력하세요'></div>"
          $("#body-start").prepend(inputtag);
          $("#insert-task").focus();
          console.log("뭐가 문제니??");
        })
        $(document).on("keyup","#insert-task",function(event) {
          let addtag="<div class='list-task-adder' id='list-task-adder'>New task</div>";
          if(event.which==13){
            let newtask=$.trim($(this).val());
            if(newtask===""){
              //문자열이 빈값이면 발생하는 함수가 아무것도 없음
            }else{
              console.log(newtask);
            $("#insert-task").val("");
              /*
              $.ajax(
                      {
                        type:"POST",
                        url:"",
                        date:"",
                        success:function(data) {

                        }
                      }
              )
              */
            }
          }else if(event.which==27){
            $(".list-task-adder-addmode").remove();
            $("#body-start").prepend("<div class='list-task-adder' id='list-task-adder'>New task</div>");
          }
        });
        $(document).on("focusout","#insert-task",function() {
          let newtask = $.trim($(this).val());
          let addtag="<div class='list-task-adder' id='list-task-adder'>New task</div>";
          if(newtask===""){//빈 문자일 경우 그냥 바로 나온다
            $(".list-task-adder-addmode").remove();
            $("#body-start").prepend(addtag);
          }else{
            /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
            $(".list-task-adder-addmode").remove();
            $("#body-start").prepend(addtag);
          }
        })


        /////////////////////////////////STATUS OF TASK GRAPH/////////////////////////////////////////
        let ctx = document.getElementById('Step-list-DonutChart').getContext('2d');
        let myDoughnutChart = new Chart(ctx, {

              type: 'doughnut',
              data: {
                      datasets: [{
                          data: [3, 4, 5, 6, 7],
                          backgroundColor: [
                                            'rgba(190, 190, 190, 1)',
                                            'rgba(241, 196, 15, 1)',
                                            'rgba(244, 7, 7, 1)',
                                            'rgba(52, 152, 219, 1)',
                                            'rgba(46, 204, 113, 1)'
                          ],
                          borderColor:[
                                        'rgba(190, 190, 190, 1)',
                                        'rgba(241, 196, 15, 1)',
                                        'rgba(244, 7, 7, 1)',
                                        'rgba(52, 152, 219, 1)',
                                        'rgba(46, 204, 113, 1)'
                          ],
                          borderWidth: 1
                      }],
                      labels:
                ['미지정','다음주 이후','이번주 까지','완료','기한 만료']
              },
              options: {
                        maintainAspectRatio: false,
                        cutoutPercentage: 50,
                        legend: {
                                  display: true,
                                  position: 'right',
                                  labels: {
                                            fontSize: 12,
                                            fontFamily: 'sans-serif',
                                            fontColor: '#ffffff',
                                            fontStyle: 'bold'
                        }
              }
              }
        });



        /////////////////////////////////TASK CONTAINER HOVER/////////////////////////////////////////
        var checkbox = [];
        $(document).on("mouseenter",".list-task-container",function() {//마우스 호버 하면 checkbox가 보임
          $(this).children(".list-task-checkbox-container").children(".list-task-checkbox").css("visibility","visible");
        }).on("mouseleave",".list-task-container",function() {//마우스 호버 아웃 하면 checkbox가 다시 안보이게 함
          $(this).children(".list-task-checkbox-container").children(".list-task-checkbox").css("visibility","hidden");
        })
        $(document).on("click",".list-task-checkbox",function() {//선택되지 않은 select box를 않은 눌렀을 시에
          $(this).removeClass("list-task-checkbox").addClass("list-task-checkbox-selected");
          $(".list-section-third-default").css({"visibility":"hidden","position":"absolute"});
          $(".list-section-third-selectpage").css({"visibility":"visible","position":"relative"})
          checkbox.push($(this).parent().parent().attr('id'));
          console.log(checkbox);
          if(checkbox.length>0){
            $("#selected-tag-count").text(checkbox.length+"개 선택됨");
          };
        })
        $(document).on("click",".list-task-checkbox-selected",function () {//선택된 select box를 클릭했을 시에
          $(this).removeClass("list-task-checkbox-selected").addClass("list-task-checkbox");
          checkbox.splice(checkbox.indexOf($(this).parent().parent().attr('id')),1);// 선택을 풀면서 거기에 담겨있는 id를 가지고와 중복되는 값을 제거한다
          console.log(checkbox);
          $("#selected-tag-count").text(checkbox.length+"개 선택됨");
          if(checkbox.length==0){
            $(".list-section-third-selectpage").css({"visibility":"hidden","position":"absolute"});
            $(".list-section-third-default").css({"visibility":"visible","position":"relative"})
          }
        })
        $(document).on("click","#list-closebutton",function() {//닫기 버튼을 누를시
          $(".list-section-third-selectpage").css({"visibility":"hidden","position":"absolute"});
          $(".list-section-third-default").css({"visibility":"visible","position":"relative"})
          checkbox=[];
          $(".list-task-checkbox-selected").removeClass("list-task-checkbox-selected").addClass("list-task-checkbox");
          console.log(checkbox);
          $(".list-task-checkbox").css("visibility","hidden");
        })
        $(document).on("click","#selectpage-complete-button",function() {//완료 버튼 선택시에
          alert("완료되었습니다")
           /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
          checkbox=[];
          console.log(checkbox);
        })
        $(document).on("click","#different-status",function() {//다른 선택 영역을 눌렀을 시에
          alert("상태를 추가합니다")
          /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
          checkbox=[];
          console.log(checkbox);
        })
        $(document).on("click","#selectpage-addasignee-button",function() {//추가 할당자들을 눌렀을 시에
          alert("할당자들을 추가합니다")

          /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
            checkbox=[];
          console.log(checkbox);
        })
        $(document).on("click","#selectpage-addsteps-button",function() {// 추가 버튼을 눌렀을 시에
          alert("할당자들을 추가합니다")

           /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
          checkbox=[];
          console.log(checkbox);
        })
        $(document).on("click","#selectpage-deletetask-button",function() {//삭제 버튼을 눌렀을 시에
          alert("삭제합니다")

          /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
          checkbox=[];
          console.log(checkbox);
        })








        /////////////////////////////////STATUS SELECTING BOTTON/////////////////////////////////////////
        $(document).on("click","#status-button",function() {
          /**/
          let p =$("#status-button");
          let position = p.position();
          $(".list-header-filter-status").css("background-color","#dfe6f0")
          $(".list-header-filter-status-selecting").css({"visibility":"visible","left":position.left,"top":position.top+24});
        });

        // 사이드 우클릭 메뉴 닫는 함수
        $(document).bind("mousedown", function(e) {
            // If the clicked element is not the menu
            if (!$(e.target).parents(".list-header-filter-status-selecting").length > 0) {
            // Hide it
              $(".list-header-filter-status").css("background-color","")
              $(".list-header-filter-status-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
          }
        });
        $(document).on("click",".list-header-menu-list-item",function () {
          let status = $(this).attr("id");
          $(".list-header-filter-status").css("background-color","")
          $(".list-header-filter-status-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
              /*
              $.ajax(
                      {
                        type:"POST",
                        url:"",
                        date:"",
                        success:function(data) {

                        }
                      }
              )
              */
        });

        /////////////////////////////////PEOPLE SELECTING BOTTON/////////////////////////////////////////
        $(document).on("click","#people-button",function(){
          $(".list-header-filter-people").css("background-color","#dfe6f0")

        });

        /////////////////////////////////SORTING BOTTON//////////////////////////////////////////////////
        $(document).on("click","#sorting-button",function() {
          let p =$("#sorting-button");
          let position = p.position();
          $(".list-header-sorting-button").css("background-color","#dfe6f0")
          $(".list-header-sorting-selecting").css({"visibility":"visible","left":position.left,"top":position.top+26});
        });
        $(document).bind("mousedown", function(e) {
            // If the clicked element is not the menu
            if (!$(e.target).parents(".list-header-sorting-selecting").length > 0) {
            // Hide it
              $(".list-header-sorting-button").css("background-color","") //
              $(".list-header-sorting-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
          }
        });
        $(document).on("click",".list-header-sorting-textcontainer",function() {
          let sorting = $(this).attr("id");
          console.log(sorting);
          $(".list-header-sorting-button").css("background-color","")
          $(".list-header-sorting-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
          $("#sorting-button").empty();
          $("#sorting-button").append("<span class='list-header-sorting-tag'>By "+ sorting+"</span>");
        })
      })
  </script>
</head>
<body>
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
	                    <div class="list-task-container" id="t${task.tid}">
	                      <div class= "list-task-checkbox-container">
	                        <span class="list-task-checkbox"></span>
	                      </div>
	                      <div class= "image-container"><img class="profile-image" src="img/frog.png" alt=""></div>
	                      <div class="center-container">
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
