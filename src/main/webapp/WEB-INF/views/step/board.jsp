<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>보드페이지-여기다가 해당 step명 입력하깅</h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>
<nav class="navbar inside-header ">
   <div class="container-fluid ">

   </div>
</nav>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	function autoWidth() {
		var width = (($('.droptrue').length * 350)
				+ "px");
		console.log(width)
		$('#board-content-md').css("width", width)
	}

	$(function() {

		autoWidth();
		
		$("ul.droptrue").sortable({
			connectWith : "ul"
		});


		$("#sortable1").disableSelection();
		
		
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
#sortable1{
	list-style-type: none;
	margin: 0;
	 float: left; 
	margin-right: 10px;
	 background: #eee; 
	padding: 5px;
	width: 300px;
}

#sortable1 li{
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
					<p align="center" id="board-status-name">${b.tstatus}</p>
					<hr id="board-hr" style="background-color:${b.color};">
					<ul id="sortable1" class="droptrue">
						<c:forEach var="t" items="${t}">
							<c:choose>
								<c:when test="${b.tstatus eq t.tstatus}">
						<li class="ui-state-default">${t.tname}</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				
				</div>
			</c:forEach>
		</div>
	</div>
</div>
