<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function() {	
		$("#incomming-page").click(function(evt){
			$.ajax({
				url:"inbox.htm",
				dataType:"html",
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
				}
			})
		});
		$("#sent-page").click(function(evt){
			$.ajax({
				url:"sent.htm",
				dataType:"html",
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					
				}
			})
		});
		$("#archive-page").click(function(evt){
			$.ajax({
				url:"archive.htm",
				dataType:"html",
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					
				}
			})
		});
		
		$(".archiveupdate").click(function(evt){
			$.ajax({
				url:"archiveupdate.htm",
				dataType:"html",
				type: 'POST',
				data:{cmtid:$(this).children(".cmtid").val(),
					  inboxkind:$("#inboxkind").val()},
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
				}
			})
		});
		
		$(".archiveupdate2").click(function(evt){
			$.ajax({
				url:"archiveupdate2.htm",
				dataType:"html",
				type: 'POST',
				data:{cmtid:$(this).children(".cmtid").val(),
					  inboxkind:$("#inboxkind").val()},
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					
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
	<input id ="inboxkind" type="hidden" value="${inbox}">
	<div class ="inbox-main-container">
		<div class ="inbox-section-left" id="left">
		<p class ="inbox-header">&nbsp; inbox</p>
		<div class = "inbox-nav">
		<div class="inbox-topnav" id="myTopnav">
 			<a id="incomming-page" href="#Inbox">Incomming</a>
  			<a id="sent-page" href="#Sent">Sent</a>
 			<a id = "archive-page" href="#Archive">Archive</a>
  			<a href="javascript:void(0);" class="icon" onclick="myFunction()">
    		<i class="fa fa-bars"></i>
  			</a>
		</div>
		</div>
			<center><h3>TODAY</h3><hr class="inbox-hr"></center>	
			<c:forEach var="commentlist" items="${cmtlist}" varStatus="status">
			
			<c:choose>
				<c:when test="${fn:substring(commentlist.cmtmtime,0,10) eq today}">
					<div class="inbox-list">
						<!-- <div id="archiveupdate" style="cursor: pointer;">
							<img src="img/archive.png" width="18px" height="18px">
						</div> -->
						<div class ="Task_RUD_Modal" id="t${commentlist.cmtid}" style="cursor: pointer;"><span class="inbox-span1">${commentlist.tname}</span></div>&#60; 
						<span class="inbox-span2">${commentlist.pname}</span>
						<span class="inbox-span3">
						<c:choose>
							<c:when test="${inbox eq 'archive'}">
								<span class="archiveupdate2" style="cursor: pointer;">
								<img src="img/archive2.png" width="18px" height="18px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:when>
							<c:otherwise>
								<span class="archiveupdate" style="cursor: pointer;">
								<img src="img/archive.png" width="18px" height="18px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp;${commentlist.cmtmtime}</span>
						<div class="inbox-div4"><img src="img/user.png" width="30px" height="30px">&nbsp;&nbsp;&nbsp;${commentlist.mname} : ${commentlist.comments}</div>
					</div>
					<hr class="inbox-hr">
				</c:when>
				<c:otherwise>
					<c:if test="${fn:substring(cmtlist[status.index-1].cmtmtime,0,10) ne fn:substring(commentlist.cmtmtime,0,10)}">
						<center>
							<h3>${fn:substring(commentlist.cmtmtime,0,10)}</h3>
							<hr class="inbox-hr">
						</center>
					</c:if>
					<div class="inbox-list">
						<div class ="Task_RUD_Modal" id="t${commentlist.cmtid}" style="cursor: pointer;"><span class="inbox-span1">${commentlist.tname}</span></div>&#60; 
						<span class="inbox-span2">${commentlist.pname}</span> 
						<span class="inbox-span3">
						<c:choose>
							<c:when test="${inbox eq 'archive'}">
								<span class="archiveupdate2" style="cursor: pointer;">
								<img src="img/archive2.png" width="18px" height="18px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:when>
							<c:otherwise>
								<span class="archiveupdate" style="cursor: pointer;">
								<img src="img/archive.png" width="18px" height="18px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp;${commentlist.cmtmtime}</span>
						<div class="inbox-div4"><img src="img/user.png" width="30px" height="30px">&nbsp;&nbsp;&nbsp;${commentlist.mname} : ${commentlist.comments}</div>
					</div>
					<hr class="inbox-hr">
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</div>
		<div class ="inbox-section-center" id="center">
		</div>
		<div class ="inbox-section-right" id="right">

		</div>
	</div>