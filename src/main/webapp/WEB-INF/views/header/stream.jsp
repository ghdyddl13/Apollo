<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript">
$(function() {
	
	
	$(".stream-select-list").click(function(evt){
		var curpid = $(this).find("input[type=hidden]").val();
		console.log(curpid);
		 $.ajax({
			url:"selectpidstream.htm",
			type: 'POST',
			data:{pid:curpid},
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$("#pidselect").val(curpid).prop("selected", true);
			}
		})
	});
	
	/* var startpos, diffpos = 0, range = 400;
	var isEnable = false;

	document.getElementById("center").onmousedown = on_mouse_down;
	document.onmouseup = on_mouse_up;
	document.onmousemove = on_mouse_move;
	function on_mouse_down(e) {
		startpos = event.clientX + diffpos;
		isEnable = true;
		return false;
	}

	function on_mouse_up(e) {
		isEnable = false;
		return false;
	}

	function on_mouse_move(e) {
		if (isEnable) {
			pos = event.clientX;
			diffpos = startpos - pos;
			var width = (window.innerWidth-250) / 2;
			if (diffpos > -(width - range) && diffpos < (width - range)) {
				document.getElementById("left").style.width = width -25 - diffpos + "px";
				document.getElementById("right").style.width = width - 25+ diffpos + "px";
			}
		}
	} */
	
});
			
			
</script>



<div class = "main-body-container">
	<div class ="main-body-onepannel">

		<div class="stream_header_container">
			<span class = "stream_header">STREAM</span>
			
		</div>
		<div class="stream-main-container">
		
			<div class="stream_main" style="overflow: auto;">
			
					<c:forEach var="commentmap" items="${commentmap}" varStatus="status">
						<div class="stream_division">
							<div class ="stream_division-header">
								<input type='hidden' value="${commentmap.key}">
								<div class="stream_division-header-sname-wrapper">
								<c:forEach var="sidlist" items="${sidlist}">
									<c:if test="${commentmap.key eq sidlist.tid}">
										 <span class ="stream_division-header-sname">${sidlist.sname}</span>
									</c:if>
								
								</c:forEach>
								</div>
								<div class="stream_division-header-tname">${commentmap.value[0].tname}</div>
							</div>
							<div class ="stream_division-body">
								<div class="stream_division-body-comment-wrapper">
									<c:forEach var="commentdto" items="${commentmap.value}" varStatus="status">
										<div>
											<span><img src="img/user.png" width="30px" height="30px"></span>
										<span style="flex: 1 1 auto;" class = "stream_comments"><c:if test="${commentdto.cmtkind eq 0}">${commentdto.mname} : </c:if>${commentdto.comments}</span>
										<div style="flex: 0 0 auto; float: right;">${commentdto.cmtmtime}</div> 			
									
									</div>
									</c:forEach>
								</div>
							</div>
							
		
						</div>
						<%-- <!-- <div class = "stream_task_division"> -->
						<c:if test="${streamlist[status.index-1].tname ne streamlists.tname}">
								<span style="padding-top: 10px; padding-left: 10px;"><span style="font-size: 25px;"><b>${streamlists.tname}</b></span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<span style="font-size: 15px; color: #666;">&#60;</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<c:forEach var="sidlist" items="${sidlist}">
								
								</c:forEach>
								
								<center><h4>수정사항</h4></center>
								<hr class = "stream_hr">
						</c:if>
						<!-- </div> -->
						<div class = "stream_list">
							<span><img src="img/user.png" width="30px" height="30px"></span>
							<span style="flex: 1 1 auto;" class = "stream_comments"><c:if test="${streamlists.cmtkind eq 0}">${streamlists.mname} : </c:if>${streamlists.comments}</span>
							<div style="flex: 0 0 auto; float: right;">${streamlists.cmtmtime}</div> 						
						</div>		
									
						</div> --%>
					</c:forEach>
			</div>
		</div>
	</div>
	<div class="stream-third-pannel">
		<div class="stream-third-pannel-header" align="center">
			<h4>Stream Filter</h4>
		</div>
		<div class="stream-select-menu">
			<c:forEach var="pidlist" items="${pidlist}">
				<div class="stream-select-list">
					<input type="hidden" value="${pidlist.pid}">
					<div class="stream-project-select-method">
						<c:choose>
							<c:when test ="${pidlist.methodologyid eq '1'}">
								<div class="stream-project-method-icon">W</div>
							</c:when>
							<c:when test ="${pidlist.methodologyid eq '2'}">
								<div class="stream-project-method-icon">A</div>
							</c:when>
							<c:when test ="${pidlist.methodologyid eq '3'}">
								<div class="stream-project-method-icon">C</div>
							</c:when>
						</c:choose>
					</div>
					<div class="stream-project-select-info">
						<div class="stream-project-select-info-pname">${pidlist.pname}</div>
						<div class="stream-project-select-info-mid">
							<p>담당자  : ${pidlist.mid}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div> 
</div>


