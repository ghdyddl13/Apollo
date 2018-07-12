<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js" integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>

	<!-- 프로젝트 생성 Mo dal 창 -->
	<div class="modal fade" id="project-insert" role="dialog">
		<div class="modal-dialog modal-project-dialog">
			<div class="modal-content modal-add-project-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Project 생성 &nbsp;&nbsp; <i data-toggle="tooltip" class="question-project far fa-question-circle modal-question"></i>
					</h4>
				</div>
				<div class="modal-body project-modal-body">
					<div>
					
					 <form id="project-add-form" method="post" onsubmit="return false;">
						<fieldset>
							<div class="modal-flexbox-row">
								<br>
								<div class="project-setting-left">
									<input type="text" name="pname"  id="add-project-name"
										placeholder="프로젝트명을 입력하세요"
										class="text ui-widget-content ui-corner-all">
									<br>
									<div class="modal-flex-col">
										<div class="modal-title">
											<p>방법론&nbsp;&nbsp; <i class="question-methodology far fa-question-circle modal-question"></i></p>
										</div>
										<br>
										<div>
											<div class="modal-flex-col">
												<input type="radio" id="method1" name="methodologyid" value="3" checked>
												<span class="method"> Customizing</span> <br>
											</div>
											<div class="modal-project-method-detail">
												<!-- Customizing 설명 작성  -->
												사용자가 직접 템플릿을 구성합니다. Step과 Folder를 자유롭게 활용하여 프로젝트에 알맞게 활용할 수 있습니다.   
											</div>
										</div>
										<div>
											<div class="modal-flex-col">
												<input type="radio" id="method2" name="methodologyid" value="2">
												<span class="method"> Agile</span>
											</div>
											<div class="modal-project-method-detail">
												<!-- Agile 설명 작성  -->
												애자일 방법론에 적합한 템플릿을 제공합니다. Step을 하나의 Sprint 단위로 활용하여 백로그에 등록된 Task들을 체계적으로 관리할 수 있습니다.
											</div>
										</div>
										<div>
											<div class="modal-flex-col">
												<input type="radio" id="method3" name="methodologyid" value="1">
												<span class="method"> Waterfall</span><br>
											</div>
											<div class="modal-project-method-detail">
												<!-- Waterfall 설명 작성  -->
												폭포수 방법론에 적합한 템플릿을 제공합니다.
												계획, 요구분석, 설계, 구현, 테스트, 유지보수로 이루어진 Step들을 각 단계별로 진행되는 Task들을 단계별로 관리할 수 있습니다. 
											</div>
										</div>
									</div>
									<br> <br>
								</div>
								<div class="project-setting-right">
									<div class="modal-title">
										<p>기간 설정</p>
									</div>
									<br>
									<div class="modal-flexbox-row">
										<div class="modal-flexbox-col">
											<p>시작일</p>
											<div class="modal-flexbox-row">
												<input type="text" id="insert-project-sday-id" name="sday" placeholder="Start Date" class="date  sdate-img" readonly="readonly">
											</div>
										</div>
										<div class="modal-flexbox-col">
											<p>종료일</p>
											<div class= "modal-flexbox-row">
												<input type="text" id="insert-proejct-eday-id" name="eday" placeholder="End Date" class="date  edate-img" readonly="readonly">
											</div>	
										</div>
									</div>
									<br><br>
									<div class="modal-flexbox-col">
										<div class="modal-title">
											<p>상세설명</p>
											<br>
										</div>
										<textarea rows="11%" cols="49%" onresize="false" id="project-detail" name="detail" placeholder="내용을 입력하세요"></textarea>
									</div>
									<br>
									<br>
									<div align="right">	
										<input type="button" class="btn add-btn" id="insert-project-btn" value="생성">&nbsp;&nbsp;&nbsp;
										<input type="button" class="btn cancel-btn"
											data-dismiss="modal" value="취소">
									</div>
								</div>
								<input type="hidden" name="mid" value="${mid}">
							</div>
						</fieldset>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Folder 생성 Modal 창 -->
	<div class="modal fade" id="add-folder" role="dialog">
		<div class="modal-dialog modal-folder-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-folder-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Folder 생성&nbsp;&nbsp; <i data-toggle="tooltip" class="question-folder far fa-question-circle modal-question"></i></h4>
				</div>
				<div class="modal-body modal-folder-body">
					<form id="insert-folder-form" method="post" onsubmit="return false;">
						<fieldset>
							<input type="text" id="add-folder-name" name="fname" class="add-folder-text modal-folder-text" placeholder="folder명을 입력하세요">
							<input type="hidden" id="add-folder-pid" name="pid">
							<br><br>
							<div align="center">
								<input type="button" class="btn add-btn" id="insert-folder-btn" value="생성">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
							</div>
						</fieldset>
					</form>
				</div>
				<br>
			</div>
		</div>
	</div>

<!-- 스텝 생성 Modal 창 -->
	<div class="modal fade" id="insert-step" role="dialog">
		<div class="modal-dialog modal-step-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-step-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Step 생성&nbsp;&nbsp; <i data-toggle="tooltip" class="question-step far fa-question-circle modal-question"></i></h4>
				</div>
				<div class="modal-body modal-step-body modal-flexbox-col">
					<form id="step-add-form" method="post" onsubmit="return false;">
						<fieldset>
							<div>
								<div class="modal-title">
									<p>Step명</p>
								</div>
								<input type="text" name="sname" class="add-step-name" placeholder="step명을 입력하세요">
							</div>
							<br>
							<div class="modal-title">
								<p>책임자</p>
							</div>
							<div id="add-step-assignee" >
								<input id="add-step-mgr-assignee" type="hidden" name="mid" value="">
								<div class="dropdown">
								    <a class="dropdown-toggle select-step-assignee" type="button" id="add-step-mgr-assignee-btn" data-toggle="dropdown">책임자를 선택하세요
								    </a>
								    <ul id="add-step-mgr-assignee-options" class="dropdown-menu " role="menu" aria-labelledby="add-step-mgr-assignee-btn">
								    </ul>
							   </div>
							</div>
							<br>
							<div class="modal-title">
								<p>기간 설정</p>
							</div>
							<div class="modal-flexbox-row">
								<div class="modal-flexbox-col">
										<p>시작일</p>
									<div class="modal-flexbox-row">	
										<input type="text" id="insert-step-sday-id" name="sday" placeholder="Start Date"
										class="date sdate-img" readonly="readonly">
									</div>
								</div>
								<div class="modal-flexbox-col">
										<p>종료일</p>
									<div class="modal-flexbox-row">	
									<input type="text" id="insert-step-eday-id" name="eday" placeholder="End Date"
										class="date edate-img" readonly="readonly">
									</div>
								</div>
							</div>
							<br>
							<div class="modal-title">
								<p>상세설명</p>
							</div>
							<br>
							<textarea rows="9%" cols="60%" id="step-detail" name="detail" placeholder="내용을 입력하세요"></textarea>
								<br><br>
							<!-- 아래는 임시로 작성해둔 것임 -->
							<input type="hidden" id="insert-step-methodologyid" name="methodologyid" >
							<input type="hidden" id="insert-step-pid" name="pid" >
							<input type="hidden" id= "insert-step-fid" name="fid">
							<div align="center">
								<input type="button" class="btn add-btn" id="insert-step-btn" value="생성">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
							</div>
						</fieldset>
					</form>
				</div>
				<br>
			</div>
		</div>
	</div>


	<!-- 프로젝트 수정 Modal 창 -->
	<div class="modal fade" id="update-project" role="dialog">
		<div class="modal-dialog modal-project-dialog">
			<div class="modal-content modal-update-project-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Project 수정</h4>
				</div>
				<div class="modal-body project-modal-body">
					<div>
					
					 <form id="update-project-form" method="post" onsubmit="return false;">
						<fieldset>
							<input type="hidden" id="update-project-pid" name="pid">
							<div class="modal-flexbox-row">
								<br>
								<div class="project-setting-left">
									<input type="text" name="pname"  id="update-project-pname"
										placeholder="프로젝트명을 입력하세요"
										class="text ui-widget-content ui-corner-all">
									<br>
									<div class="modal-flex-col">
										<div class="modal-title">
											<p>방법론</p>
								
										</div>
										<br>
										<div>
											<div class="modal-flex-col">
												<input type="radio" id="update-project-methodologyid-3" disabled="disabled">
												<span class="method"> Customizing</span> <br>
											</div>
											<div class="modal-project-method-detail">
												<!-- Customizing 설명 작성  -->
												사용자가 직접 템플릿을 구성합니다. Step과 Folder를 자유롭게 활용하여 프로젝트에 알맞게 활용할 수 있습니다.   
											</div>
										</div>
										<div>
											<div class="modal-flex-col">
												<input type="radio" id="update-project-methodologyid-2" disabled="disabled">
												<span class="method"> Agile</span>
											</div>
											<div class="modal-project-method-detail">
												<!-- Agile 설명 작성  -->
												애자일 방법론에 적합한 템플릿을 제공합니다. Step을 하나의 Sprint 단위로 활용하여 백로그에 등록된 Task들을 체계적으로 관리할 수 있습니다.
											</div>
										</div>
										<div>
											<div class="modal-flex-col">
												<input type="radio" id="update-project-methodologyid-1"  disabled="disabled">
												<span class="method"> Waterfall</span><br>
											</div>
											<div class="modal-project-method-detail">
												<!-- Waterfall 설명 작성  -->
												폭포수 방법론에 적합한 템플릿을 제공합니다.
												계획, 요구분석, 설계, 구현, 테스트, 유지보수로 이루어진 Step들을 각 단계별로 진행되는 Task들을 단계별로 관리할 수 있습니다. 
											</div>
										</div>
									</div>
									<br> <br>
								</div>
								<div class="project-setting-right">
									<div class="modal-title">
										<p>기간 설정</p>
									</div>
									<br>
									<div class="modal-flexbox-row">
										<div class="modal-flexbox-col">
											<p>시작일</p>
											<div class="modal-flexbox-row">
												<input type="text" id="update-project-sday" name="sday" placeholder="Start Date" class="date  sdate-img" readonly="readonly">
											</div>
										</div>
										<div class="modal-flexbox-col">
											<p>종료일</p>
											<div class= "modal-flexbox-row">
												<input type="text" id="update-project-eday" name="eday" placeholder="End Date" class="date  edate-img" readonly="readonly">
											</div>	
										</div>
									</div>
									<br><br>
									<div class="modal-flexbox-col">
										<div class="modal-title">
											<p>상세설명</p>
											<br>
										</div>
										<textarea rows="11%" cols="49%" onresize="false" id="update-project-detail" name="detail" placeholder="내용을 입력하세요"></textarea>
									</div>
								
									<br>
									<div align="right">	
										<input type="button" class="btn add-btn" id="update-project-btn" value="수정">&nbsp;&nbsp;&nbsp;
										<input type="button" class="btn cancel-btn"
											data-dismiss="modal" value="취소">
									</div>
								</div>
							</div>
						</fieldset>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	


<!-- Project 완료/ 삭제 Modal 창 -->
	<div class="modal fade" id="move-project" role="dialog">
		<div class="modal-dialog modal-small-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="move-project-header" class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="move-project-pid">
					<input type="hidden" id="move-project-pstatuscode">
					<p id="move-project-message"></p>
					<p id="move-project-submessage"></p>
				</div>
				<div align="center">
					<input id="move-project-btn" type="button" class="btn delete-btn" value="확인">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn cancel-btn"
						data-dismiss="modal" value="취소">
				</div>
				<br>
			</div>
		</div>
	</div>	



<!-- Task 수정/삭제 Modal 창 -->
	<div class="modal fade modal-task-dialog" id="Task_RUD_Modal" role="dialog">
		<div class="modal-dialog task-modal-wrapper">
			<div class="modal-content ">

				<div class="modal-header">
				<!-- row 1 -->
					<div class="row">
						<div class="col-sm-10">
							<div id="projectinTask" style="font-size: 11px; color:#717171; margin-left: 2px; clear: left"></div>
							<br>
							<h4 class="modal-title" id="Task_Modal_tname">Task_Modal_tname</h4>
							<input type="text" id="Task_Modal_tname_input" placeholder="클릭하여 Task 이름 변경.." value="">
						</div>
	
						<div class="col-sm-2" id="star_trash">
							<span id="span_task_star"></span>&nbsp;&nbsp;
							<i class="fas fa-trash" id="task_trash" data-toggle="modal" data-target="#Trash_Modal"></i>&nbsp;&nbsp;&nbsp;
							<button type="button" class="close" data-dismiss="modal" id="task_dismiss_btn">&times;</button>
						</div>
						
						<div class="col-sm-12" id="Task_Modal_snames">
						</div>
						<div id="step_names_popup_div"></div>
						<div id="step_delete_popup_div"></div>
						
					</div>
				<!-- end row 1 -->
				</div>
		
				
			<!-- task modal body  -->				
			<div class=" task-modal-body">
				<!-- row 2 -->
				<div class="task-modal-content-row">
					<div class="task-modal-content-td">
						<select id="Task_Modal_tstatus_selectbox">
						</select>
					</div>
							
					<div class="task-modal-content-td">
	                    <span>시작일&nbsp;&nbsp;</span>
                   		<input id="Task_Modal_sday" readonly type="text" name="sday" 
                   		placeholder="Start Date" class="date date_sday">
					</div>

					<div class="task-modal-content-td">
						<span>종료일&nbsp;&nbsp;</span>
	                    <input id="Task_Modal_eday" readonly type="text" name="eday"
	                    placeholder="End Date" class="date date_eday">
					</div>
				</div>
				<!-- end row 2 -->
				
				<!-- end modal-header -->

				<div class="modal-body">
					
					<div class="task-modal-content-assignee">
						<div class="modal-title">업무 담당자</div>
						<div id="Task_Modal_assignee"></div>
						<div id="assignee_popup_div"></div>
					</div>

								
          			<div class="task-modal-content-file">
						<div class="modal-title">파일 업로드</div><br />
						<div class="task-modal-content-filelist-wrapper">
			                <div id="Task_Modal_files">
			                </div>
			                <a type="button"  id="fileuploadbtn" >Click Here To Upload Your File</a>
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
						<p>상세설명&nbsp;&nbsp;<img id="task_detail_status" src="img/loader.gif"></p>
					</div>
					<div style="text-align:center">
						<textarea id="Task_Modal_detail"  name="detail" placeholder="내용을 입력하세요"></textarea>
					</div>
                </div>
                <hr />
                
					<div class="modal-title">Comment</div><br />
					<div id="Task_Modal_comments">
					</div>
					<hr>
					<div id="project_member_popup_div" class="project_member_popup"></div>
					
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
			</div>	<!-- task modal body -->
		
		</div>
	</div>
</div>

<!-- 프로필 모달 -->
<div class="modal fade" id="profile-modal-dialog" role="dialog">
	<div class="modal-dialog profile-modal-dialog">
		<!-- Modal content-->
		<div class="modal-content" id="profile-modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">PROFILE</h4>
			</div>
			<div class="modal-body profile-modal-body">
				<div class="col-sm-4 profile-modal-left">
					<div id="profile-modal-img-content">
						<img id="profile-modal-img" src="">
					</div>
				</div>
				<div class="col-sm-8" id="profile-modal-info">
					<table class="profile-modal-info-table">
						<tbody>
							<tr>
								<td>NAME</td>
								<th id="profile-modal-mname"></th>
							</tr>
							<tr>
								<td>E-MAIL</td>
								<th id="profile-modal-mid"></th>
							</tr>
							<tr>
								<td>PHONE</td>
								<th id="profile-modal-pnum"></th>
							</tr>
							<tr>
								<td>DEPT</td>
								<th id="profile-modal-deptname"></th>
							</tr>
							<tr>
								<td>POSITION</td>
								<th id="profile-modal-position"></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<br>
		</div>
	</div>
</div>


<!-- Folder 수정 Modal 창 -->
	<div class="modal fade" id="update-folder" role="dialog">
		<div class="modal-dialog modal-folder-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-folder-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Folder 수정</h4>
				</div>
				<div class="modal-body modal-folder-body">
					<form id="update-folder-form" method="post">
						<fieldset>
							<input type="text" id="update-folder-name" name="fname" class="modal-folder-text">
							<input type="hidden" name="fid" id="update-folder-fid">
							<input type="hidden" name="pid" id="update-folder-pid">
							<br><br>
							<div align="center">
								<input type="button" class="btn add-btn" id="update-folder-btn" value="수정">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
							</div>
						</fieldset>
					</form>
				</div>
				<br>
			</div>
		</div>
	</div>	
	
	
	<!-- Folder 삭제 Modal 창 -->
	<div class="modal fade" id="delete-folder" role="dialog">
		<div class="modal-dialog modal-delete-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Folder 삭제</h4>
				</div>
				<div class="modal-body" style="text-align:center">
				<form id="delete-folder-form" method="post">
					<p>해당  폴더를 삭제하시겠습니까?</p>
					<p>(삭제시 해당 폴더는 복구 불가능하며, 폴더에 속한 스텝도 삭제됩니다.)</p>
					<input type="hidden" name="fid" id="delete-folder-fid">
				</form>
				</div>
				
				<div align="center">
					<input type="button" class="btn delete-btn" id="delete-folder-btn" value="삭제">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn cancel-btn"
						data-dismiss="modal" value="취소">
				</div>
				<br>
			</div>
		</div>
	</div>
	

<!-- Task 삭제 창 -->
	<div class="modal fade" id="Trash_Modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="task_delete_dismiss_btn">&times;</button>
					<h4 class="modal-title">Task 삭제</h4>
				</div>
				
				<div class="modal-body" style="text-align:center">
				<h4>해당 Task를 삭제하시겠습니까?<br><h5 style="color:red">(삭제 후 복구 불가능합니다)</h5></h4>					

					<div align="center">
						<input type="button" class="btn add-btn" id="task_trash_btn" value="삭제">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
	

	<!-- 스텝 수정 Modal 창 -->
	
<div class="modal fade" id="update-step" role="dialog">
	<div class="modal-dialog modal-step-dialog">
		<!-- Modal content-->
		<div class="modal-content modal-step-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Step 수정</h4>
			</div>
			<div class="modal-body modal-step-body modal-flexbox-col">
				<form id="update-step-form" method="post" onsubmit="return false;">
					<fieldset>
						<div>
							<div class="modal-title">
								<p>Step명</p>
							</div>
							<input type="text" name="sname" class="update-step-name">
						</div>
						<br>
						<div class="modal-title">
							<p>책임자</p>
						</div>
						<div id="update-step-assignee" >
							<input id="update-step-mgr-assignee" type="hidden" name="mid" value="">
							<div class="dropdown">
							    <a class="dropdown-toggle select-step-assignee" type="button" id="update-step-mgr-assignee-btn" data-toggle="dropdown" >책임자를 선택하세요
							    </a>
							    <ul id="update-step-mgr-assignee-options" class="dropdown-menu " role="menu" aria-labelledby="update-step-mgr-assignee-btn">
							    </ul>
						  	</div>
						</div>
						<br>
						<div class="modal-title">
							<p>기간 설정</p>
						</div>
						<div class="modal-flexbox-row">
							<div class="modal-flexbox-col">
									<p>시작일</p>
								<div class="modal-flexbox-row">	
									<input type="text" id="update-step-sday-id" name="sday" placeholder="Start Date"
									class="date sdate-img" readonly="readonly">
								</div>
							</div>
							<div class="modal-flexbox-col">
									<p>종료일</p>
								<div class="modal-flexbox-row">	
								<input type="text" id="update-step-eday-id" name="eday" placeholder="End Date"
									class="date edate-img" readonly="readonly"> 
								</div>
							</div>
						</div>
						<br>
						<div class="modal-title">
							<p>상세설명</p>
						</div>
						<br>
						<textarea rows="9%" cols="60%" id="update-step-detail" name="detail" placeholder="내용을 입력하세요"></textarea>
							<br><br>
						<!-- 아래는 임시로 작성해둔 것임 -->
						<input type="hidden" id="update-step-methodologyid" name="methodologyid" >
						<input type="hidden" id="update-step-pid" name="pid" >
						<input type="hidden" id="update-step-sid" name="sid">
						<input type="hidden" id= "update-step-fid" name="fid">
						<div align="center">
							<input type="button" class="btn add-btn" id="update-step-btn" value="수정">&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
						</div>
					</fieldset>
				</form>
			</div>
			<br>
		</div>
	</div>
</div>
<!-- 스텝 이동 모달창  -->
	
	<div class="modal fade" id="move-step" role="dialog">
		<div class="modal-dialog modal-small-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="move-step-header" class="modal-title">Step 이동</h4>
				</div>
				<div class="modal-body"align="center">
					<br>
					<form id="move-step-form">
						<input id="move-step-sid" type="hidden" name="sid" value="">
						<input id="move-step-selected-fid" type="hidden" name="fid" value="no-folder">
						<div class="dropdown move-step-dropdown">
							<div id="move-step-folder-list" data-toggle="dropdown" class="dropdown-toggle move-step-folder-list-wrapper">
								<div id="move-step-folder-name" >
									
								</div>
								<span class="caret"></span>
							
							</div>
							<ul id="move-step-folder-options" class="dropdown-menu"
								role="menu" aria-labelledby="move-step-folder-list">
							</ul>
						</div>
					</form>
					<br>
					<div align="center">
						<input id="move-step-btn" type="button" class="btn delete-btn" value="확인">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- TaskInStep 돌고 1:1 매핑일때 스텝 삭제 모달창 -->
	<div class="modal fade" id="step_delete_Modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="taskinstep_delete_dismiss_btn">&times;</button>
					<h4 class="modal-title">Step 삭제</h4>
				</div>
				
				<div class="modal-body" style="text-align:center">
				<h4>해당 Task가 속한 마지막 Step을 삭제하면 Task도 같이 삭제됩니다.<br>
				진행하시겠습니까?<br><h5 style="color:red">(삭제 후 복구 불가능합니다)</h5></h4>					

					<div align="center">
						<input type="button" class="btn add-btn" id="task_trash_btn" value="삭제">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
	
	
	<!-- 스텝 추가 Modal -->
	<div class="modal fade" id="step_add_modal_in_taskmodal">
		<div class="modal-dialog modal-add-project-dialog">
			<!-- Modal content-->
			
			
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="step_add_modal_in_taskmodal_dismiss">&times;</button>
						<h4 class="modal-title">New Step</h4>
					</div>
				
				
				<div class="modal-body ">

		<table id="steplist_in_taskmodal" class="table">
		
		</table>
		
				</div>
				
				<div align="center">
					<input type="button" class="btn" id="cancel-btn"
						data-dismiss="modal" value="돌아가기">
				</div>
				<br>
			</div>
		</div>
	</div>
	
	


	<!-- Step 삭제 Modal 창 -->
	<div class="modal fade" id="delete-step" role="dialog">
		<div class="modal-dialog modal-delete-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Step 삭제</h4>
				</div>
				<div class="modal-body" style="text-align:center">
				<form id="delete-step-form" method="post">
					<p>해당 스텝을 삭제하시겠습니까?</p>
					<p>(삭제시 해당 스텝은 영구삭제되며, 해당 스텝에 속한 task도 삭제됩니다)</p>
					<input type="hidden" name="sid" id="delete-step-sid">
					<input type="hidden" name="tid" id="delete-step-tid">
				</form>
				<div align="center">
					<input type="button" class="btn delete-btn" id="delete-step-btn" value="삭제">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn cancel-btn"
						data-dismiss="modal" value="취소">
				</div>
				</div>
				<br>
			</div>
		</div>
	</div>

<!-- 개인정보수정 Modal -->
<div class="modal fade" id="edit-profile" role="dialog">
	<div class="modal-dialog modal-edit-profile-dialog">
		<div class="modal-content modal-edit-profile-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">개인정보수정</h4>
			</div>
			<div class="modal-body">
				<div id="edit-profile-img-content">
					<div class="profile-modal-container">
						<div class="profile-modal-image-container">
							<div class="profile-modal-image-wrapper">
								<img class="profile-modal-image" id="profile-modal-image"src="/bit/displayImage.htm" alt=""> 
								<label class="profile-modal-image-upload-button" for="edit-profile-image">Upload</label>
							</div>
							<form hidden class="profile-modal-image_uploader">
								<input type="file" id="edit-profile-image" data-url="updatememberimage.htm" class="profile-modal-image-upload_input">
							</form>
						</div>
						<div class="profile-modal-text-container">
							<div class="profile-modal-text-mname"></div>
							<div class="profile-modal-text-mid"></div>
						</div>
					</div>
					<script type="text/javascript">
						$(function() {
							let count=0;
							$("#edit-profile-image").fileupload(
									{
										dataType : "json",
										add : function(e, data) {
											var uploadFile = data.files[0];
											var isValid = true;
											if (!(/png|jpe?g|gif|svg/i)
													.test(uploadFile.name)) {
												alert('png, jpg, gif 만 가능합니다');
												isValid = false;
											}
											if (isValid) {
												data.submit();
											}
										},
										done : function(e, data) {
											let modalstr="";
											let profilestr="";
											console.log(count);
											if((count%2)==0){
												modalstr+='<img class="profile-modal-image" id="profile-modal-image"src="/bit/displayImage.htm?sid=123" alt="">'
												modalstr+='<label class="profile-modal-image-upload-button" for="edit-profile-image">Upload</label>';
												profilestr ='<img id="loginimg" src="/bit/displayImage.htm?sid=123">';
												count++;
											}else{
												modalstr+='<img class="profile-modal-image" id="profile-modal-image"src="/bit/displayImage.htm" alt="">'
												modalstr+='<label class="profile-modal-image-upload-button" for="edit-profile-image">Upload</label>';
												profilestr='<img id="loginimg" src="/bit/displayImage.htm">';
												count++;
											}
											$(".profile-modal-image-wrapper").empty();
											$(".profile-modal-image-wrapper").append(modalstr);
											$("#profile-modal-image").empty();
											$("#profile-modal-image").append(profilestr);
										}
									})

						});
					</script>

				</div>
				<form id="edit-profile-form">
					<fieldset>
						<div class="edit-profile-part1">
							<br>
							<div>

								<br>
								<div class="edit-profile-all-title">
									<label class="edit-profile-title" for="edit-profile-mname">이름</label>
									<input type="text" name="mname" class="edit-profile-input-tag" id="edit-profile-mname">
								</div>
								
								<div class="edit-profile-all-title">
									<label class="edit-profile-title" for="edit-profile-mid">이메일</label>
									<input type="text" name="mid" class="edit-profile-input-tag" id="edit-profile-mid" readonly="readonly">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div id="edit-profile-title-apollokey">
										<label id="edit-profile-apollokey-label" class="edit-profile-title" for="edit-profile-apollokey">인증키</label>
										<input class="btn" id="profile-apollokey-check" type="button" value="인증확인">

									</div>
									<input type="text" name="apollokey" class="edit-profile-input-tag" id="edit-profile-apollokey">
									
									
								</div>
								
								<div class="edit-profile-all-title">
									<label class="edit-profile-title" for="edit-profile-pnum">휴대폰 번호</label>
									<input type="text" name="pnum" class="edit-profile-input-tag" id="edit-profile-pnum">
								</div>
								
								<div class="edit-profile-all-title">
									<label class="edit-profile-title" for="edit-profile-deptname">부서명</label>
									<input type="text" name="deptname" class="edit-profile-input-tag" id="edit-profile-deptname">
								</div>
								
								<div class="edit-profile-all-title">
									<label class="edit-profile-title" for="edit-profile-position">직위</label>
									<input type="text" name="position" class="edit-profile-input-tag" id="edit-profile-position">
								</div>
								<br>
							</div>
						</div>
						<div align="center">
							<input type="button" class="btn" id="update-edit-profile-btn" value="수정완료">
							<br><br>
							<input class="btn" type="button" value="비밀번호 변경" id="edit-pwd-btn" data-toggle="modal" data-target="#edit-pwd-modal-dialog">
						</div>
					</fieldset>
				</form>
				</div>
			</div>
		</div>
	</div>	
	
	<!-- 비밀번호 변경 Modal -->
	<div class="modal fade" id="edit-pwd-modal-dialog" role="dialog">
		<div class="modal-content modal-dialog modal-edit-pwd-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">비밀번호 변경</h4>
			</div>
			<div class="modal-body edit-profile-part2-body">
				<form id="edit-pwd-form" method="post">
					<fieldset>
						<div class="edit-profile-part2">
							<div class="edit-profile-all-title">
								<label class="edit-profile-title" for="edit-profile-cpwd">현재 비밀번호</label> 
								<input type="password" name="pwd" id="edit-profile-cpwd">
							</div>
							<div class="edit-profile-all-title">
								<label class="edit-profile-title" for="edit-profile-upwd">변경 비밀번호</label> 
								<input type="password" name="pwd" id="edit-profile-upwd">
							</div>
							<div class="edit-profile-all-title">
								<label class="edit-profile-title" for="edit-profile-upwd2">변경 비밀번호 확인</label> 
								<input type="password" name="pwd2" id="edit-profile-upwd2">
							</div>
						</div>
						<div align="center">
							<input type="button" class="btn" id="update-pwd-btn" value="비밀번호 변경">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

		




	<!-- Assignee 추가 Modal -->
	<div class="modal fade" id="assignee_add_modal_in_taskmodal">
		<div class="modal-dialog">
			<!-- Modal content-->
			
			
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="assignee_add_modal_in_taskmodal_dismiss">&times;</button>
						<h4 class="modal-title">New Assignee</h4>
					</div>
				
				
				<div class="modal-body ">

				<table id="assignee_in_taskmodal" class="table">
		
				</table>
		
				</div>
				
				<div align="center">
					<input type="button" class="btn" id="cancel-btn"
						data-dismiss="modal" value="돌아가기">
				</div>
				<br>
			</div>
		</div>
	</div>
	
	
	<!-- File 삭제 창 -->
	<div class="modal fade" id="file_delete_modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="file_delete_dismiss_btn">&times;</button>
					<h4 class="modal-title">File 삭제</h4>
				</div>
				
				<div class="modal-body" style="text-align:center">
				<h4>해당 File를 삭제하시겠습니까?<br><h5 style="color:red">(삭제 후 복구 불가능합니다)</h5></h4>					

					<div align="center">
						<input type="button" class="btn add-btn" id="file_delete_btn" value="삭제">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
						<input type="hidden" id="starbucks" value="">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>


	<!-- 로그아웃 -->
	<div class="modal fade" id="hedaer-logout-dialog" role="dialog">
		<div class="modal-dialog logout-modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-header-logout-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">로그아웃</h4>
				</div>
	
				<div class="modal-body" style="text-align: center">
					<div>정말 로그아웃하시겠습니까?</div>
					<br>
					<div align="center">
						<input type="button" class="btn add-btn" id="logout-btn" value="확인">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>



	<!-- Report -->
	<div class="modal fade" id="report_info_modal" role="dialog">
		<div class="modal-dialog report_info_modal">
			<!-- Modal content-->
			<div class="modal-content report_info_modal_content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Report 정보 입력</h4>
				</div>
	
				<div class="modal-body" style="text-align: left">
				
				<div>Report 대상 프로젝트 선택</div>
					<select id="user_projectlist_selectbox">
					</select>
				<br>
				
					<br>
					<div align="center">
						<input type="button" class="btn add-btn" id="download_report_btn" value="다운로드">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn" id="download_report_cancel_btn" data-dismiss="modal" value="취소">
						<input type="hidden" id="report_kind" value="">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
<!-- LIST PAGE TASK 단체 STATUS 변경 창 -->
	<div class="modal fade list-delete-tasks-modal" id="list_Status_Tasks" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="list_status_tasks_dismiss_btn">&times;</button>
					<h4 class="modal-title">Task 상태 변경</h4>
					<input type="hidden" id="list_status_selectedtstatusid" value="">
				</div>
				<div class="modal-body" style="text-align:center">
				<h4 id="list-task-status-ment"></h4>					
					<div align="center">
						<input type="button" class="btn add-btn" id="list_Status_Tasks_btn" value="변경">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
<!-- LIST PAGE TASK 단체 ASSIGN 창 -->
	<div class="modal fade list-delete-tasks-modal" id="list_Assign_Tasks" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="list_assign_tasks_dismiss_btn">&times;</button>
					<h4 class="modal-title">Task 할당</h4>
					<input type="hidden" id="list_assign_selectedtmid" value="">
				</div>
				<div class="modal-body" style="text-align:center">
				<h4 style="font-size:14px" id="list-task-assign-ment"></h4>					
					<div align="center">
						<input type="button" class="btn add-btn" id="list_Assign_Tasks_btn" value="할당">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
<!-- LIST PAGE TASK 단체 ADDSTEP 창 -->
	<div class="modal fade list-delete-tasks-modal" id="list_AddStep_Tasks" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="list_addstep_tasks_dismiss_btn">&times;</button>
					<h4 class="modal-title">Step 추가</h4>
					<input type="hidden" id="list_addstep_selectedsid" value="">
				</div>
				<div class="modal-body" style="text-align:center">
				<h4 style="font-size:14px" id="list-addstep-tasks-ment"></h4>					
					<div align="center">
						<input type="button" class="btn add-btn" id="list_AddStep_Tasks_btn" value="추가">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>	
<!-- LIST PAGE TASK 단체 삭제 창 -->
	<div class="modal fade list-delete-tasks-modal" id="list_Delete_Tasks" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="list_delete_tasks_dismiss_btn">&times;</button>
					<h4 class="modal-title">Task 삭제</h4>
				</div>
				<div class="modal-body" style="text-align:center">
				<h4 style="font-size:14px" id="list-delete-tasks-ment"></h4>					
					<div align="center">
						<input type="button" class="btn add-btn" id="list_Delete_Tasks_btn" value="삭제">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
	
	<!-- 멤버 삭제 모달창 -->
	<div class="modal fade information-pmember-delete" id="information-pmember-delete" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="list_delete_tasks_dismiss_btn">&times;</button>
					<h4 class="modal-title">프로젝트 멤버 삭제</h4>
				</div>
				<div class="modal-body" style="text-align:center">
				<h4>해당 멤버를 프로젝트에서 제외 하시겠습니까?</h4>
					<input type="hidden" id="delete-pmember-mid">					
					<div align="center">
						<input type="button" class="btn add-btn" id="information-pmember-delete-btn" value="삭제">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
	
	<!-- jQuery ui widget -->
	<script src="js/jquery.ui.widget.js"></script>
	<!-- FILE UPLOAD -iFame -->
	<script type="text/javascript" src="js/jquery.iframe-transport.js"></script>
	<!-- FILE UPLOAD -->
	<script type="text/javascript" src="js/jquery.fileupload.js"></script>
