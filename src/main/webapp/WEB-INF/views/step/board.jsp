<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3></h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
<nav class="navbar inside-header ">
   <div class="container-fluid ">

   </div>
</nav>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script>
//task 생성하기 위해 이름 입력하는 text 창 생성
/* function addCardView(e, tstatusid) {
   console.log("addcardview 왓낭 : " + e + " / " + tstatusid)
   var div = "<div class='' id='addcard'>" +
         "<input class='inputtext' type='text' placeholder='task 이름을 입력하세요' name='title' >" +
         "<a onclick='addCard(this, "+ tstatusid + ")'>완료</a></div>";
   console.log("이건뭘까여>>>"+$(e).before(div));
   $('#addcard').children('input').focus();

   



} */




//task 생성 성공
/* function addCard(obj, tstatusid){
   console.log("addCard 들어왔어요");
   var parent = $(obj).closest('div')
   var value = parent[0].firstChild.value //cardname
   var pid = $("#board-pid").val()
   console.log("board-pid : " + $("#board-pid").val())
   console.log("parent : " + parent)
   console.log("tstatusid : " + tstatusid)
   console.log("value : " + value)
    if(value.trim() != ""){
      $.ajax({
         url : "boardInsertTask.htm",
         data : {
               tstatusid : tstatusid, 
               tname : value
               },
         success:function(data){
            $("#main-box").empty();
            $("#main-box").append(data);
         }  
      })
   }  
} */
function addTaskView(tstatusid){
   $("#task-adder"+tstatusid).remove();
   let inputtag="<div class='board-task-adder-addmode'><input class='form-control' id='insert-task"+tstatusid+"' name='tname' type='text' placeholder='새로운 작업을 입력하세요' onkeyup='addTask_keyup("+tstatusid+")' onfocusout='addTask_focusout("+tstatusid+")'></div>"
    $("#body-start"+tstatusid).prepend(inputtag);
    $("#insert-task"+tstatusid).focus();
}

function addTask_keyup(tstatusid){
   let addtag="<div class='board-task-adder' id='task-adder"+tstatusid+"' onclick='addTaskView("+tstatusid+")'>New task</div>";
      if(event.which==13){
         let newtask=$.trim($('#insert-task'+tstatusid).val());
      
        if(newtask===""){
             $(".board-task-adder-addmode").remove();
             $("#body-start"+tstatusid).prepend(addtag);
         }else{
            console.log(newtask);
            $('#insert-task'+tstatusid).val('');
               $.ajax({
                   url : "boardInsertTask.htm",
                   data : {
                         tstatusid : tstatusid, 
                         tname : newtask
                         },
                   success:function(data){
                      $("#main-box").empty();
                      $("#main-box").append(data);
                   }  
                })
         } 
      } 
}

function addTask_focusout(tstatusid){
   let newtask=$.trim($('#insert-task'+tstatusid).val());
   let addtag="<div class='board-task-adder' id='task-adder"+tstatusid+"' onclick='addTaskView("+tstatusid+")'>New task</div>";
   if(newtask===""){
       $(".board-task-adder-addmode").remove();
       $("#body-start"+tstatusid).prepend(addtag);
   }else{
         $.ajax({
              url : "boardInsertTask.htm",
              data : {
                    tstatusid : tstatusid, 
                    tname : newtask
                    },
              success:function(data){
                 $("#main-box").empty();
                 $("#main-box").append(data);
              }  
           })
              
   }
}
   
   //board에서 나오는 status 목록 width 크기 지정하는 함수
   function autoWidth() {
      var width = (($('.tstatuslist').length * 310)
            + "px");
      $('#board-content-md').css("width", width)
   }
   
   //board에서 각 tast 위치 옮기는 함수
   function sortable(){
      var i = 1;
      var tstatus;
      var tid;
      $("ul.tstatuslist").sortable({
         connectWith : "ul",
         update: function(event, ui) {      
           if (i++ == 2){
            tstatusid =  $(this).closest('div')[0].childNodes[1].childNodes[1].value;
            tid =  ui.item[0].value;

         $.ajax({
            url : 'boardTaskStatusUpdate.htm',
            data : { 
               tstatusid : tstatusid,
               tid : tid
                  } 
         }) 
            
            //update event 발생시 이동전 위치와 이동후 위치를 나타내주는 변수를 초기화함
            i = 1;
             
         } 
           
         
         }
      });
   }

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
</script>


<style>
#board-sortable{
   list-style-type: none;
   margin: 0;
    float: left; 
   margin-right: 10px;
   padding: 5px;
   width: 270px;
   height: 650px;
   overflow: auto;
}

#board-sortable li{
   margin: 10px;
   padding: 5px;
   font-size: 20px;
   width: 220px;
   text-align: center;
   background: white;
   
   border-style: outset;
   
   
}

#board-content-md {
   height: 100%;
   
}

#board-main-div {
   overflow-x: auto;
   max-width: calc(100% -250px);
   height: 100%;
   

}

#board-status-div{
   display: flex;
   flex-wrap: nowrap;
   flex-direction: row;
   justify-content: flex-start;
}

#board-hr{
   margin-right : 50px;
   margin-top : -5px;
   border: 0; 
   height: 10px; 
   width: 220px;
}

#board-status-name{
   font-size: 25px;
   color: black;
   margin-right: 50px;
   
}

.board-task-adder_containers{
    box-sizing: border-box;
    position: relative;
    background-color: #fff;
    }
.board-task-adder{
    border:1px solid black;
    background: url("img/adder.png") no-repeat 49px center #fff;
    display: block;
    box-sizing: border-box;
    padding: 7.5px 1px 10px 70px;
    margin-left:15px;
    height:50px;
    width: 220px;
    font-size: 20px;
    line-height: 31px;
    color: #5285b8;
    border-top: 1px solid #e0e0e0;
    cursor: pointer;
    list-style: none;
    }
.board-task-adder-addmode{
    border:1px solid black;
    height:50px;
    width: 220px;
    display: block;
    box-sizing: border-box;
    padding: 8.5px 0px 0px 0px;
    margin-left:15px;
    width: 220px;
    font-size: 13px;
    line-height: 31px;
    color: #5285b8;
    border-top: 1px solid #e0e0e0;
    cursor: pointer;
    list-style: none;
    }
    .ui-sortable-handle:hover{
       cursor:-webkit-grab;
    }
  
</style>


<div class="container-fluid" id="board-main-div">
   <div class="container-fluid"  id="board-content-md">
      <div  class="container-fluid" id="board-status-div">
         <c:forEach var="b" items="${b}">            
            <div >
               <p align="center" id="board-status-name" >${b.tstatus}
               <input type="hidden" value="${b.tstatusid}">
               
               </p>
               <hr id="board-hr" style="background-color:${b.color};">
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
                           <li class="ui-state-default Task_RUD_Modal" id="t${t.tid}" value="${t.tid}">${t.tname}</li>
                                                      
                        </c:when>
                     </c:choose>
                  </c:forEach>
               </ul>
            
            </div>
         </c:forEach>
      </div>
   </div>
</div>