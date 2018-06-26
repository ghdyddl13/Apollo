<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3></h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>



<nav class="navbar inside-header ">
   <div class="container-fluid ">

   </div>
</nav>

<!-- <script type="text/javascript">
//board 좌우로 움직이는 함수
$(function() {
	    autoWidth();
	     sortable();
	    
	    $('#board-content-md').draggable(
	            {
	               axis: "x"
	          },{
	              stop: function() {
	                  
	                  var left = $('#board-content-md')[0].offsetLeft
	                  console.log("left : " + left);
	                  var maxwidth = $(window).width() - $('#board-content-md').width()
	                  if(left > 0){
	                      $('#board-content-md').css('left','0px')
	                     
	                  }else if($(window).width() > $('#board-content-md').width()){
	                      if(left < 0){ //화면크기가 div길이보다 크고 left가 0보다 작으면!!
	                          $('#board-content-md').css('left','0px')
	                      }
	                     }else if($(window).width() < $('#board-content-md').width()){
	                      if(left < maxwidth){ //화면크기가 div길이보다 작고 left가 maxwidth보다 작으면!!
	                          $('#board-content-md').css('left',maxwidth-80)
	                      }
	                  }
	                  $('#board-content-md').off('mousemove')
	              }
	          }
	      ) 
	});
</script> -->

<div class="container-fluid" id="board-main-div">
   <div class="container-fluid"  id="board-content-md">
      <div  class="container-fluid" id="board-status-div">
         <c:forEach var="b" items="${b}">            
            <div >
               <p align="center" id="board-status-name">${b.tstatus}
               <input type="hidden" value="${b.tstatusid}">
               
               </p>
               <hr id="board-hr" style="background-color:${b.color};" >
                 <%-- <div class="listbox">
                         <div class="listtitle" id="listnum">
                               <a class="cardcreate" onclick="addCardView(this, ${b.tstatusid})">Add a card...</a>
                         </div>
                    </div> --%>
                    <div class="board-task-adder_containers" id="body-start${b.tstatusid}">
                    <div class="board-task-adder" id="task-adder${b.tstatusid}" onclick="addTaskView(${b.tstatusid})">
                    
                          <input type="hidden" id="board-tstatusid" value="${b.tstatusid}">
                            New Task
                    </div>
                   </div>
                    
               
               <ul id="board-sortable" class="tstatuslist">
                  <c:forEach var="t" items="${t}">
                     <%-- <input type="hidden" id="board-pid" value="${t.pid}"> --%>
                     <c:choose>
                     
                        <c:when test="${b.tstatus eq t.tstatus}">
                           <li class="ui-state-default Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id="t${t.tid}" value="${t.tid}">${t.tname}</li>
                                                      
                        </c:when>
                     </c:choose>
                  </c:forEach>
               </ul>
            
            </div>
         </c:forEach>
      </div>
   </div>
</div>