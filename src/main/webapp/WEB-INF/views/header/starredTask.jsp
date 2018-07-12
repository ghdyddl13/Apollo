
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script type="text/javascript">
$(function() {
	$(".modal-content3").hide();
});

</script>
<script type="text/javascript" src="js/task.js"></script>
<div class="main-body-container">
  <div class="main-body-twopannel">
    <div class="main-body-twopannel-left">
      <div class="main-body-twopannel-left-wrapper">
        <div class="main-body-twopannel-left-header">
          <div class="main-body-twopannel-left-header-wrapper">
            <div class="main-body-twopannel-left-header-title-textwrapper">
              <div class="main-body-twopannel-left-header-title">
                <span class="main-body-twopannel-left-header-title-text">Starred Task</span>
                 <input id ="inboxkind" type="hidden" value="${inbox}">
              </div>
            </div>
          </div>
        </div>
        <div class="main-body-twopannel-left-body">
          <div class="main-body-twopannel-left-body-wrapper">
            <div class="starred-body-wrapper">
              <div class="starred-body-tasks">
				<c:forEach var="task" items="${tasklist}">
	                <div class="starred-body-task Task_RUD_Modal" id="t${task.tid}">
	                  <div class="starred-body-task-wrapper" >
	                    <div class="starred-body-task-image-wrapper">
	                      <div class="starred-body-task-image"><img class="starred-body-task-imagetag" src="img/frog.png" alt=""></div>
	                    </div>
	                    <div class="starred-body-task-container">
	                      <div class="starred-body-task-container-top">
	                     
	                        <div class="starred-body-task-container-top-title" id="t${task.tid}">${task.tname}</div>
	                        <c:choose>
								<c:when test="${task.overdue=='overdue'}">
			                        <div class="starred-body-task-container-top-date date-overdue">${task.date}</div>
								</c:when>
								<c:otherwise>
			                        <div class="starred-body-task-container-top-date">${task.date}</div>
								</c:otherwise>
							</c:choose>
	                        <div class="starred-body-task-container-top-star"><i class="fas fa-star"></i></div>
	                      </div>
	                      <div class="starred-body-task-container-bottom">
	                        <div class="starred-body-task-container-bottom-status" style="color:${task.color}">${task.tstatus}</div>
	                        <div class="starred-body-task-container-bottom-steps">
	                          <c:forEach var="step" items="${task.steps}">
	                          	<div class="starred-body-task-container-bottom-step" id="s${step.sid}">${step.sname}</div>	                          
	                          </c:forEach>
	                        </div>
	                      </div>
	                    </div>
	                  </div>
	                </div>
				</c:forEach>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="main-body-twopannel-right">
     
      
    <div class="starred-secondbody-image"></div>
        
		<div class="modal-content3">

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
						
						<div class="col-sm-12" id="Task_Modal_snames2">
						</div>
						<div id="step_names_popup_div2"></div>
						<div id="step_delete_popup_div2"></div>
						
					</div>
				</div>
				<!-- end row 1 -->
							
							
				<!-- row 2 -->
				<div class="task-modal-body">
					<div class="task-modal-content-row">
						<div class="task-modal-content-td">
							<select id="Task_Modal_tstatus_selectbox_noredirect">
							</select>
						</div>
						<div class="task-modal-content-td">
		                    <span>시작일&nbsp:&nbsp</span>
	                   		<input id="Task_Modal_sday_noredirect" readonly="readonly" type="text" name="sday" placeholder="Start Date" class="date date_sday_noredirect">
						</div>
						<div class="task-modal-content-td">
							<span>종료일&nbsp:&nbsp</span>
		                    <input id="Task_Modal_eday_noredirect" readonly="readonly" type="text" name="eday" placeholder="End Date" class="date date_eday_noredirect">
						</div>
					</div>	
				</div>
				<!-- end row 2 -->
				
				<!-- end modal-header -->

				<div class="modal-body">
					<div class="task-modal-content-assignee">
						<div class="modal-title">업무 담당자</div><br />
						<div id="Task_Modal_assignee"></div>
						<div id="assignee_popup_div"></div>
					</div>
								
        		<div class="task-modal-content-file">
					<div class="modal-title">파일 업로드</div><br />
					<div class="task-modal-content-filelist-wrapper">
		                <div id="Task_Modal_files"></div>
		                <br>
		                <a type="button" name="fileuploadbtn" id="fileuploadbtn">Click Here To Upload Your File</a>
						<form  action="" method="post" >
							<input style="display:none" type="file" name="member_image" id="fileuploadintaskmodal" data-url="uploadfileintaskmodal.htm">
						</form>
					</div>
				</div>	
				    <script type="text/javascript">
						 $(function(){
							 
							$("#fileuploadintaskmodal").fileupload({
								dataType:"json",
								add:function(e,data){
					                var uploadFile = data.files[0];

					                data.submit();
					                
								},
								done:function(e,data){
									
									$.ajax(
										       {
										           type : "post",
										           url  : "resetfilelist.htm",
										           success : function(rdata){
										        	   
										        	   // files
										        	   $('#Task_Modal_files').empty();
										        	   var filesdivs = '';
										        	   $(rdata.filelist).each(function(){
										        		   let strArray = this.filename.split('/');
											        	   var shortfilename = strArray[2].substring(37);
											        	   filesdivs += '<div class="filehover_div">' + '<a class="file_name" href="taskFileDownload.htm?filename='+this.filename+'" download>' + shortfilename + '</a>';
											        	   filesdivs += '<i id="' + this.filename + '" class="fas fa-times file_del_btn" style="cursor:pointer"></i>';
											        	   filesdivs += '</div>'
										        	   });
										        	   $('#Task_Modal_files').append(filesdivs);
										        	   
										           } // end-success
										        }); // end-ajax
								
									
									
								}								
							})
						});
					</script>
					
				 <hr>
						<div class="modal-title">Sub Task</div><br />
				
				 <input type="text" name="pname" id="add_sub_task"
							placeholder="Sub Task의 제목을 입력 후 Enter..."
							class="text ui-widget-content ui-corner-all"><br><br>
				 <div id="Task_Modal_subtasks"></div><br>
                <hr />
                <div class="task-modal-content-detail">
					<div class="modal-title">
						<p>상세설명&nbsp&nbsp<img id="task_detail_status" src="img/loader.gif"></p>
					</div>
						<div style="text-align:center">
						<textarea id="Task_Modal_detail" rows="7%" cols="60%" name="detail" placeholder="내용을 입력하세요"></textarea>
						</div>
				</div>
                <hr/>
                
					<div class="modal-title">Comment</div><br />
					<div id="Task_Modal_comments">
					</div>
					<hr>
					<div id="project_member_popup_div2" class="project_member_popup"></div>
					
					<div id="div_for_comment_input_box2">
					<input id="comment_input_box_in_taskmodal_noredirect" type="text" placeholder="코멘트를 입력 후 Enter..">
					</div>
							
					<input type="hidden" id="pidhidden" value="">					
					<input type="hidden" id="usermidhidden" value="">					
					<input type="hidden" id="usermidhidden2" value="">					
					<input type="hidden" id="tidhidden2" value="">
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