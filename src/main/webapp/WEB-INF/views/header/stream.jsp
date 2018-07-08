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
		var selected_list = $(this);
		console.log(curpid);
		 $.ajax({
			url:"selectpidstream.htm",
			type: 'POST',
			data:{pid:curpid},
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$("#stream-current-project").text($(selected_list).find(".stream-project-select-info-pname").text())
				console.log($(".stream_main").children().length)
				if($(".stream_main").children().length==0){
					var msg =  $("<div>", {"class":"no-stream-div-msg",
										   "text":"No Stream!"});
								$(".stream_main").append(msg);
				}
				
			}
		})
	});
	
});
			
			
</script>

<div class = "main-body-container">
	<div class ="main-body-onepannel">

	
		<div class="stream_header_container">
			<span class = "stream_header">STREAM</span>
			
		</div>
		<div class="stream-main-container">
			<div id="stream-current-project">
				
			</div>
		
			<div class="stream_main">
				<c:forEach var="commentmap" items="${commentmap}" varStatus="status">
					<div class="stream_division">
						<div class ="stream_division-header Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal" id ="t${commentmap.key}">
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
									<div class="steam-comment-item">
										<div class="steam-comment-item-img">
											<c:choose >
												<c:when test="${commentdto.image eq null}">
													<span><img src="img/user.png" width="30px" height="30px"></span>
												</c:when>
												<c:otherwise>
													<span><img src="img/user.png" width="30px" height="30px"></span>
												</c:otherwise> 
											</c:choose>
										</div>
										<div class="steam-comment-item-info"> 
											<div style="flex: 1 1 auto;" class = "stream_comments">
													<c:if test="${commentdto.cmtkind eq 0}">
														${commentdto.mname} : 
													</c:if>${commentdto.comments}
											</div>
											<div class="stream_comments_time">
												${commentdto.cmtmtime}
											</div> 			
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
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


