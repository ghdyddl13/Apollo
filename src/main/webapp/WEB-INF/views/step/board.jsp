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
function addCardView(e, tstatusid) {
	console.log("addcardview 왓낭 : " + e + " / " + tstatusid)
	var div = "<div class='' id='addcard'>" +
			"<input class='inputtext' type='text' placeholder='task 이름을 입력하세요' name='title' >" +
/* 			"onkeypress='if(event.keyCode==13) {addCard($(this).parent().children(\"a\"), "+ tstatusid + ");}' " +
			"onfocusout='focusOutCardDelay("+tstatusid+")' onkeyup='fnChkByte(this, 26)'>" + */
			"<a onclick='addCard(this, "+ tstatusid + ")'>완료</a></div>";
	console.log("이건뭘까여>>>"+$(e).before(div));
	$('#addcard').children('input').focus();
}

//카드 등록 성공
function addCard(obj, tstatusid){
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
					}
		
		/* ,
			success:function(data){
				$(parent).remove();
				$('#contentDetail').empty();
			}  */
		})
	}  
}
	
	
	
	
	//board에서 나오는 status 목록 width 크기 지정하는 함수
	function autoWidth() {
		var width = (($('.tstatuslist').length * 350)
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
	 background: #eee; 
	padding: 5px;
	width: 300px;
}

#board-sortable li{
	margin: 5px;
	padding: 5px;
	font-size: 1.2em;
	width: 280px;
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
	border: 0; 
	height: 10px; 
	width: 300px;
}

#board-status-name{
	font-size: 20px;
	color: black;
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
					  <div class="listbox">
             				<div id="listnum" class="listtitle">
               					 <a class="cardcreate" onclick="addCardView(this, ${b.tstatusid})">Add a card...</a>
             				</div>
          			 </div>
					
					<ul id="board-sortable" class="tstatuslist">
						<c:forEach var="t" items="${t}">
							<%-- <input type="hidden" id="board-pid" value="${t.pid}"> --%>
							<c:choose>
							
								<c:when test="${b.tstatus eq t.tstatus}">
									<li class="ui-state-default" value="${t.tid}">${t.tname}</li>
																		
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				
				</div>
			</c:forEach>
		</div>
	</div>
</div>
