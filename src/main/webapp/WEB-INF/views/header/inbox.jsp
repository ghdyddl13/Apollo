<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!-- TASK_JK -->
<script type="text/javascript" src="js/task.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function() {
		
		/**
		 * 
		 날      짜 : 2018. 7. 9.
		 기      능 : 인박스에서 X버튼 눌렀을때 서드패널에서 사라짐
		 작성자명 : 신 호 용
		 */
		$(document).on("click","#task_dismiss_btn_inbox",function(){
			$(".modal-content2").hide();
			$(".starred-secondbody-image").show();
		});
		
		
		$(".modal-content2").hide();
		$(".Task_RUD_Modal").click(function(evt) {
			$(this).children().children(".inbox_newcheck").empty();
			console.log($(this).children().children().children(".cmtid").val());
			console.log($(this).children().children(".inbox_mid2").val())
			$.ajax({
				url:"newcheck.htm",
				type: 'POST',
				data:{cmtid : $(this).children().children().children(".cmtid").val(),
					  mid2 : $(this).children().children(".inbox_mid2").val()},
				success:function(data){
					
				}
			})
		});
		
		
		$("#incomming-page").click(function(evt){
			
			$.ajax({
				url:"inbox.htm",
				dataType:"html",
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					$('#incomming-page').css('border-bottom','2px solid transparent');
					$('#incomming-page').css('border-color','#286cb0');
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
					$('#sent-page').css('border-bottom','2px solid transparent');
					$('#sent-page').css('border-color','#286cb0');
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
					$('#archive-page').css('border-bottom','2px solid transparent');
					$('#archive-page').css('border-color','#286cb0');
				}
			})
		});
		
		$(".archiveupdate").click(function(evt){
			evt.stopPropagation();
			$.ajax({
				url:"archiveupdate.htm",
				dataType:"html",
				type: 'POST',
				data:{cmtid:$(this).children(".cmtid").val(),
					  inboxkind:$("#inboxkind").val()},
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					if($('#inboxkind').val()== 'incomming'){
						$('#incomming-page').css('border-bottom','2px solid transparent');
						$('#incomming-page').css('border-color','#286cb0');
					}else if($('#inboxkind').val()== 'sent'){
						$('#sent-page').css('border-bottom','2px solid transparent');
						$('#sent-page').css('border-color','#286cb0');
					}else{
						$('#archive-page').css('border-bottom','2px solid transparent');
						$('#archive-page').css('border-color','#286cb0');
					}
				
				}
			})
		});
		$(".archiveupdate2").click(function(evt){
			evt.stopPropagation();
			$.ajax({
				url:"archiveupdate2.htm",
				dataType:"html",
				type: 'POST',
				data:{cmtid:$(this).children(".cmtid").val(),
					  inboxkind:$("#inboxkind").val()},
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);
					$('#archive-page').css('border-bottom','2px solid transparent');
					$('#archive-page').css('border-color','#286cb0');
				}
			})
		});
		
		$("#cmt_insert").click(function(evt){
			$.ajax({
				url:"insertcomment.htm",
				dataType:"html",
				type: 'POST',
				data:{comments:$("#cmt_cmts").val(),
					  tid:$("#cmt_tid").val()},
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
		<div class ="inbox-section-left" id="left" style="overflow:auto;">
<!-- 		<p class ="inbox-header">&nbsp; Inbox</p> -->
		<div class = "inbox-nav">
		<div class="inbox-topnav">
 			<a id="incomming-page" ><b>INCOMMING</b></a>
  			<a id="sent-page" ><b>SENT</b></a>
 			<a id = "archive-page" ><b>ARCHIVE</b></a>
  			<a href="javascript:void(0);" class="icon" onclick="myFunction()">
    		<i class="fa fa-bars"></i>
  			</a>
		</div>
		</div>
			<center><h3 style="color:#717171;">TODAY</h3><hr class="inbox-hr"></center>	
			<c:forEach var="commentlist" items="${cmtlist}" varStatus="status">
			<c:choose>
				<c:when test="${fn:substring(commentlist.cmtmtime,0,10) eq today}">
					<div class="inbox-list">
					<div class ="Task_RUD_Modal" id="t${commentlist.tid}" style="cursor: pointer;">
						<div class ="yyTask_RUD_Modal"><span class="inbox-span1-color">
						<span class="inbox-span1"><b>${commentlist.tname}</b></span></span></div>
						<span class="inbox-span2"><span style="margin-right: 5px; font-size: 15px;">&#60;&nbsp;</span>${commentlist.pname}</span>
						<span class="inbox-span3">
						<c:choose>
							<c:when test="${inbox eq 'archive'}">
								<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
								<c:if test="${commentlist.newcheck eq 0}">
									<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
								</c:if>
							
								<span class="archiveupdate2" style="cursor: pointer;">
								<img src="img/archive2.png" width="15px" height="15px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:when>
							<c:otherwise>
							<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
								<c:if test="${commentlist.newcheck eq 0}">
									<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
								</c:if>
								
								<span class="archiveupdate" style="cursor: pointer;">
								<img src="img/archive.png" width="15px" height="15px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp;${commentlist.cmtmtime}</span>
						<div class="inbox-div4"><img src="img/user.png" width="30px" height="30px">&nbsp;&nbsp;&nbsp;
						<c:if test="${commentlist.cmtkind eq 0}">${commentlist.mname} : </c:if>${commentlist.comments}</div>
					</div>
					</div>
					<hr class="inbox-hr">
				</c:when>
				<c:otherwise>
					<c:if test="${fn:substring(cmtlist[status.index-1].cmtmtime,0,10) ne fn:substring(commentlist.cmtmtime,0,10)}">
						<center>
							<h3 style="color:#717171;">${fn:substring(commentlist.cmtmtime,0,10)}</h3>
							<hr class="inbox-hr">
						</center>
					</c:if>
					<div class="inbox-list"><div class ="Task_RUD_Modal" id="t${commentlist.tid}" style="cursor: pointer;">
						<div class ="yyTask_RUD_Modal"><div><span class="inbox-span1-color"><span class="inbox-span1"><b>${commentlist.tname}</b></span></span></div></div>
						<span class="inbox-span2" ><span style="margin-right: 5px; font-size: 15px;">&#60;&nbsp;</span> ${commentlist.pname}</span> 
						<span class="inbox-span3">
						<c:choose>
							<c:when test="${inbox eq 'archive'}">
							<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
								<c:if test="${commentlist.newcheck eq 0}">
									<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
								</c:if>
								<span class="archiveupdate2" style="cursor: pointer;">
								<img src="img/archive2.png" width="15px" height="15px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:when>
							<c:otherwise>
							<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
								<c:if test="${commentlist.newcheck eq 0}">
									<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
								</c:if>
								<span class="archiveupdate" style="cursor: pointer;">
								<img src="img/archive.png" width="15px" height="15px">
								<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
								</span>
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp;${commentlist.cmtmtime}</span>
						<div class="inbox-div4"><img src="img/user.png" width="30px" height="30px">&nbsp;&nbsp;&nbsp;${commentlist.mname} : ${commentlist.comments}</div>
					</div>
					</div>
					<hr class="inbox-hr">
				</c:otherwise>
			</c:choose>
			</c:forEach>
			
			
			
			
			
		</div>
		<div class ="inbox-section-center" id="center">
		</div>
		
		
		
		<div class ="inbox-section-right" id="right" style="overflow:auto; ">	
		<div class="starred-secondbody-image"></div>
			
			
			<div class="modal-content2">

				<div class="modal-header">
				<!-- row 1 -->
				<div class="row">
					<div class="col-sm-10">
						<h4 class="modal-title" id="Task_Modal_tname">Task_Modal_tname</h4>
						<input type="text" id="Task_Modal_tname_input" placeholder="클릭하여 Task 이름 변경.." value="">
					</div>

					<div class="col-sm-2" id="star_trash">
						<span id="span_task_star"></span>&nbsp&nbsp
						<i class="fas fa-trash" id="task_trash" data-toggle="modal" data-target="#Trash_Modal"></i>&nbsp&nbsp&nbsp
						<button type="button" class="close" id="task_dismiss_btn_inbox">&times;</button>
					</div>
					
					<div class="col-sm-12" id="Task_Modal_snames">
					</div>
					<div id="step_names_popup_div"></div>
					<div id="step_delete_popup_div"></div>
					
				</div>
				<!-- end row 1 -->
				<hr>
							
							
				<!-- row 2 -->
				<div class="row">
					<div class="col-sm-2">
							<select id="Task_Modal_tstatus_selectbox_noredirect">
							</select>
					</div>	
					<div class="col-sm-5">
	                    <span>시작일&nbsp:&nbsp</span>
                   		<input id="Task_Modal_sday_noredirect" type="text" name="sday" placeholder="Start Date" class="date date_sday_noredirect">
					</div>
					<div class="col-sm-5">
						<span>종료일&nbsp:&nbsp</span>
	                    <input id="Task_Modal_eday_noredirect" type="text" name="eday" placeholder="End Date" class="date date_eday_noredirect">
					</div>
				</div>
				<!-- end row 2 -->
				<hr>
				
				<!-- end modal-header -->

				<div class="modal-body">

					<div class="modal-title">업무 담당자</div><br />
					<div id="Task_Modal_assignee"></div>
					<div id="assignee_popup_div"></div>

								
                <hr />
					<div class="modal-title">파일 업로드</div><br />
	                <div id="Task_Modal_files"></div>
	                <br>
	                <input id="Task_Modal_add_file_btn" type="button" value="파일추가"><br /><br />
                <hr>

				<div class="modal-title">Sub Task</div><br />
				
				 <input type="text" name="pname" id="add_sub_task"
							placeholder="Sub Task의 제목을 입력 후 Enter..."
							class="text ui-widget-content ui-corner-all"><br><br>
				 <div id="Task_Modal_subtasks"></div><br>

                <hr />
                
				<div class="modal-title">
					<p>상세설명&nbsp&nbsp<img id="task_detail_status" src="img/loader.gif"></p>
					<div style="text-align:center">
					<textarea id="Task_Modal_detail" rows="7%" cols="60%" name="detail" placeholder="내용을 입력하세요"></textarea>
					</div>
				</div>
                <hr />
                
					<div class="modal-title">Comment</div><br />
					<div id="Task_Modal_comments">
					</div>
					<hr>
					<div id="project_member_popup_div"></div>
					
					<div id="div_for_comment_input_box">
					<input id="comment_input_box_in_taskmodal" type="text" placeholder="코멘트를 입력 후 Enter..">
					</div>
							
					<input type="hidden" id="pidhidden" value="">					
					<input type="hidden" id="usermidhidden" value="">					
					<input type="hidden" id="tidhidden" value="">
					<input type="hidden" id="receiverhidden" value="">
					<input type="hidden" id="receivernamehidden" value="">
					<input type="hidden" id="tnamehidden" value="">
					<input type="hidden" id="origin_sday" value="">
					<input type="hidden" id="origin_eday" value="">
					<input type="hidden" id="stepdeletehidden" data-toggle="modal" data-target="#step_delete_Modal">
					
			 	</div>
			 	<!-- end modal-body -->
				
			</div>
		</div>
	</div>
</div>	

	
	
	
	
	