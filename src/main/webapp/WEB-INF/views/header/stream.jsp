<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
$(function() {
	$("#pidselect").change(function(evt){
		var curpid = $(this).val()
		$.ajax({
			url:"selectpidstream.htm",
			type: 'POST',
			data:{pid:$(this).val()},
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$("#pidselect").val(curpid).prop("selected", true);
			}
		})
	});
	
	var startpos, diffpos = 0, range = 400;
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
	}
	
});
			
			
</script>




<div class ="inbox-main-container">
		<div class ="inbox-section-left" id="left" style="overflow:auto;">
		<div class="stream-main-container">
		<span class = "stream_header">STREAM</span><select class="form-control" id="pidselect" name="book_no" style="width:200px; float: right; margin-top: 10px; margin-bottom: 10px;margin-right: 10px;">
		
		<c:forEach var="pidlist" items="${pidlist}">
			<option value="${pidlist.pid}">${pidlist.pname}</option>
		</c:forEach>	
	</select>
	<div class="stream_main" style="overflow: auto;">
		<c:forEach var="streamlists" items="${streamlist}" varStatus="status">
			<c:if test="${streamlist[status.index-1].tname ne streamlists.tname}">
				<c:set var="count" value="${count + 1}" />
					<c:if test="${count > 1}">
						<div class="stream_division"></div>
					</c:if>
					
					<span style="padding-top: 10px; padding-left: 10px;"><span style="font-size: 25px;"><b>${streamlists.tname}</b></span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span style="font-size: 15px; color: #666;">&#60;</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<c:forEach var="sidlist" items="${sidlist}">
						<c:if test="${streamlists.tid eq sidlist.tid}">
							 <span class ="stream_stepname">${sidlist.sname}</span>
						</c:if>
					</c:forEach>
					
					<center><h4>수정사항</h4></center>
					<hr class = "stream_hr">
			</c:if>
			
			<div class = "stream_list">
				<span><img src="img/user.png" width="30px" height="30px"></span>
				<span style="flex: 1 1 auto;" class = "stream_comments"><c:if test="${streamlists.cmtkind eq 0}">${streamlists.mname} : </c:if>${streamlists.comments}</span>
				<div style="flex: 0 0 auto; float: right;">${streamlists.cmtmtime}</div> 
				<%-- task 아이디 : ${streamlists.tid}<br> --%>
				<%-- 코멘트 아이디 : ${streamlists.cmtid}<br>  --%>
				<%-- 테스크 이름 : ${streamlists.tname}<br> --%>
				
				
			</div>
			
		</c:forEach>

	</div>
</div>
		
		</div>
		<div class ="inbox-section-center" id="center">
		</div>
		<div class ="inbox-section-right" id="right" style="overflow:auto; ">
		</div>
	</div>

