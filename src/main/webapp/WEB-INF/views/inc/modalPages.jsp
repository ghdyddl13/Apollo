<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js" integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>
<script>
	
</script>
	<!-- 프로젝트 생성 Modal 창 -->
	<div class="modal fade" id="project-insert" role="dialog">
		<div class="modal-dialog modal-add-project-dialog">
			<div class="modal-content modal-add-project-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Project 생성</h4>
				</div>
				<div class="modal-body">
				
					 <form id="project-add-form" method="post" onsubmit="return false;">
						<fieldset>
							<div class="row">
								<br>
								<div class="col-sm-6 project-setting-left">
									<input type="text" name="pname"  id="add-project-name"
										placeholder="프로젝트명을 입력하세요"
										class="text ui-widget-content ui-corner-all"><br>
									<br> 
									<div class="modal-title">
										<p>방법론</p>
									</div>
										<input type="radio" id="method1" name="methodologyid" value="3"
										checked><span class="method"> Customizing</span> <br>
									<div>
										<!-- Customizing 설명 작성  -->
										가나다라마바사아자차카타파하 커스텀마이징 설명작성 필요
									</div>
									<br> <input type="radio" id="method2" name="methodologyid" value="2"><span
										class="method"> Agile</span><br>
									<div>
										<!-- Agile 설명 작성  -->
										가나다라마바사아자차카타파하 애자일 설명작성 필요
									</div>
									<br> <input type="radio" id="method3" name="methodologyid" value="1"><span
										class="method"> Waterfall</span><br>
									<div>
										<!-- Waterfall 설명 작성  -->
										가나다라마바사아자차카타파하 폭포수 설명작성 필요
									</div>
									<br> <br>
								</div>
								<div class="col-sm-6 project-setting-right">
									<div class="modal-title">
										<p>기간 설정</p>
									</div>
									<br>
									<div class="col-sm-6">
										<div class="modal-title">
											<p>시작일</p>
										</div>
										<input type="text" id="insert-project-sday-id" name="sday" placeholder="Start Date" class="date sdate-img">
									</div>
									<div class="col-sm-6">
										<div class="modal-title">
											<p>종료일</p>
										</div>
										<input type="text" id="insert-proejct-eday-id" name="eday" placeholder="End Date" class="date edate-img">
										<br><br>
									</div>
									<div class="modal-title">
										<p>상세설명</p>
									</div>
									<textarea rows="7%" cols="49%" id="project-detail" name="detail" placeholder="내용을 입력하세요"></textarea>
								</div>
							</div>
							<input type="hidden" name="mid" value="${mid}">
						<br>
						<div align="center">	
							<input type="button" class="btn add-btn" id="insert-project-btn" value="생성">&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn cancel-btn"
								data-dismiss="modal" value="취소">
						</div>
						</fieldset>
					</form>
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
					<h4 class="modal-title">Folder 생성</h4>
				</div>
				<div class="modal-body">
					<form id="insert-folder-form" method="post" onsubmit="return false;">
						<fieldset>
							<input type="text" id="add-folder-name" name="fname" class="add-folder-text" placeholder="folder명을 입력하세요">
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
					<h4 class="modal-title">Step 생성</h4>
				</div>
				<div class="modal-body">
				
					<form id="step-add-form" method="post" onsubmit="return false;">
						<fieldset>
							<div class="modal-title">
								<p>Step명</p>
							</div>
							<input type="text" name="sname" class="add-step-name" placeholder="step명을 입력하세요">
							<br><br>
							<div class="modal-title">
								<p>책임자</p>
							</div>
							<div >
								<select name="mid" id="add-step-mgr-assignee">
									
								</select> 
							</div>
							<br>
							<div class="modal-title">
								<p>기간 설정</p>
							</div>
							<div class="col-sm-6">
								<div class="modal-title">
									<p>시작일</p>
								</div>
								<input type="text" id="insert-step-sday-id" name="sday" placeholder="Start Date"
									class="date sdate-img"><br>
							</div>
							<div class="col-sm-6">
								<div class="modal-title">
									<p>종료일</p>
								</div>
								<input type="text" id="insert-step-eday-id" name="eday" placeholder="End Date"
									class="date edate-img"><br><br><br>
							</div>
							<div class="modal-title">
								<p>상세설명</p>
							</div>
							<textarea rows="7%" cols="68%" id="step-detail" name="detail" placeholder="내용을 입력하세요"></textarea>
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
		<div class="modal-dialog modal-update-project-dialog">
			<div class="modal-content modal-update-project-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Project 수정</h4>
				</div>
				<div class="modal-body">
					<form id="update-project-form">
						<fieldset>
							<div class="row">
								<input type="hidden" id="update-project-pid" name ="pid">
								<br>
								<div class="col-sm-6 project-setting-left">
									<input type="text" name="pname" id="update-project-pname"
										class="text ui-widget-content ui-corner-all"><br>
									<br> 
									<div class="modal-title">
										<p>방법론</p>
									</div>
										<input type="radio" id="udpate-project-methodologyid-3" name="methodologyid" value="3"
										checked><span class="method"  disabled="true"> Customizing</span> <br>
									<div>
										<!-- Customizing 설명 작성  -->
										가나다라마바사아자차카타파하 커스텀마이징 설명작성 필요
									</div>
									<br> <input type="radio" id="udpate-project-methodologyid-1" name="methodologyid" value="1"  disabled="true"><span
										class="method"> Agile</span><br>
									<div>
										<!-- Agile 설명 작성  -->
										가나다라마바사아자차카타파하 애자일 설명작성 필요
									</div>
									<br> <input type="radio" id="udpate-project-methodologyid-2" name="methodologyid" value="2"  disabled="true"><span
										class="method"> Waterfall</span><br>
									<div>
										<!-- Waterfall 설명 작성  -->
										가나다라마바사아자차카타파하 폭포수 설명작성 필요
									</div>
									<br> <br>
								</div>
								<!-- Allow form submission with keyboard without duplicating the dialog button -->
								<div class="col-sm-6 project-setting-right">
									<div class="modal-title">
										<p>기간 설정</p>
									</div>
									<br>
									<div class="col-sm-6">
										<div class="modal-title">
											<p>시작일</p>
										</div>
										<input type="text" name="sday" readonly id="update-project-sday" placeholder="Start Date" class="date sdate-img">
									</div>
									<div class="col-sm-6">
										<div class="modal-title">
											<p>종료일</p>
										</div>
										<input type="text" name="eday" readonly id ="update-project-eday" placeholder="End Date" class="date edate-img">
										<br><br>
									</div>
									<div class="modal-title">
										<p>상세설명</p>
									</div>
									<textarea rows="7%" cols="49%" name="detail" id ="update-project-detail" placeholder="내용을 입력하세요"></textarea>
								</div>
							</div>
							<br><br>
						<div align="center">
							<input type="button" id ="update-project-btn" class="btn add-btn" value="수정">&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn cancel-btn"
								data-dismiss="modal" value="취소">
						</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>	
	


<!-- Project 완료 Modal 창 -->
	<div class="modal fade" id="move-project" role="dialog">
		<div class="modal-dialog modal-delete-project-dialog">
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
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
				<!-- row 1 -->
				<div class="row">
					<div class="col-sm-10">
						<h4 class="modal-title" id="Task_Modal_tname">Task_Modal_tname</h4>
					</div>

					<div class="col-sm-2" id="star_trash">
						<span id="span_task_star"></span>&nbsp&nbsp
						<i class="fas fa-trash" id="task_trash" data-toggle="modal" data-target="#Trash_Modal"></i>&nbsp&nbsp&nbsp
						<button type="button" class="close" data-dismiss="modal" id="task_dismiss_btn">&times;</button>
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
							<select id="Task_Modal_tstatus_selectbox">
							</select>
					</div>
							
					<div class="col-sm-5">
	                    <span>시작일&nbsp:&nbsp</span>
                   		<input id="Task_Modal_sday" type="text" name="sday" placeholder="Start Date" class="date date_sday">
					</div>

					<div class="col-sm-5">
						<span>종료일&nbsp:&nbsp</span>
	                    <input id="Task_Modal_eday" type="text" name="eday" placeholder="End Date" class="date date_eday">
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
	                <div id="Task_Modal_files">파일들이 여기 잡히게 된다</div>
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
					<textarea id="Task_Modal_detail" rows="7%" cols="65%" name="detail" placeholder="내용을 입력하세요"></textarea>
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

<!-- 프로필 모달 -->
<div class="modal fade" id="profile-modal-dialog" role="dialog">
	<div class="modal-dialog profile-modal-dialog">
		<!-- Modal content-->
		<div class="modal-content" id="profile-modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">PROFILE</h4>
			</div>
			<div class="modal-body">
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
				<div class="modal-body">
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
			<div class="modal-body">

				<form id="update-step-form" method="post" onsubmit="return false;">
					<fieldset>
						<div class="modal-title">
							<p>Step명</p>
						</div>
						<input type="text" name="sname" class="update-step-name"
							placeholder="step명을 입력하세요"> <br>
						<br>
						<div class="modal-title">
							<p>책임자</p>
						</div>
						<div>
							<select name="mid" id="update-step-mgr-assignee">

							</select>
						</div>
						<br>
						<div class="modal-title">
							<p>기간 설정</p>
						</div>
						<div class="col-sm-6">
							<div class="modal-title">
								<p>시작일</p>
							</div>
							<input type="text" id="update-step-sday-id" name="sday"
								placeholder="Start Date" class="date sdate-img"><br>
						</div>
						<div class="col-sm-6">
							<div class="modal-title">
								<p>종료일</p>
							</div>
							<input type="text" id="update-step-eday-id" name="eday"
								placeholder="End Date" class="date edate-img"><br>
							<br>
							<br>
						</div>
						<div class="modal-title">
							<p>상세설명</p>
						</div>
						<textarea rows="7%" cols="68%" id="update-step-detail"
							name="detail" placeholder="내용을 입력하세요"></textarea>
						<br>
						<br> <input type="hidden" id="update-step-methodologyid"
							name="methodologyid"> <input type="hidden"
							id="update-step-sid" name="sid"> <input type="hidden"
							id="update-step-pid" name="pid"> <input type="hidden"
							id="update-step-fid" name="fid">
						<div align="center">
							<input type="button" class="btn add-btn" id="update-step-btn"
								value="수정">&nbsp;&nbsp;&nbsp; <input type="button"
								class="btn cancel-btn" data-dismiss="modal" value="취소">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 스텝 이동 모달창  -->
	
	<div class="modal fade" id="move-step" role="dialog">
		<div class="modal-dialog modal-delete-project-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="move-step-header" class="modal-title">Step 이동</h4>
				</div>
				<div class="modal-body"align="center">
				<br>
				<form id="move-step-form">
					<select id="move-step-select" name="fid">
					
					</select>
					<input type="hidden" name="sid" id="move-step-sid">
				</form>
				<br>
				</div>
				<div align="center">
					<input id="move-step-btn" type="button" class="btn delete-btn" value="확인">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn cancel-btn"
						data-dismiss="modal" value="취소">
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
				<form id="edit-profile-form">
					<fieldset>
						<div class="edit-profile-part1">
							<br>
							<div>
								<div id="edit-profile-img-content">
									<!-- <img name="image" id="edit-profile-modal-img" src=""> -->
									<input type="file" name="image" id="edit-profile-image">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div class="edit-profile-title">이름</div>
									<input type="text" name="mname" id="edit-profile-mname">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div class="edit-profile-title">이메일</div>
									<input type="text" name="mid" id="edit-profile-mid"
										readonly="readonly">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div class="edit-profile-title">인증키</div>
									<input type="text" name="apollokey" id="edit-profile-apollokey">
									<input class="btn" id="profile-apollokey-check" type="button" value="인증확인">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div class="edit-profile-title">휴대폰 번호</div>
									<input type="text" name="pnum" id="edit-profile-pnum">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div class="edit-profile-title">부서명</div>
									<input type="text" name="deptname" id="edit-profile-deptname">
								</div>
								<br>
								<div class="edit-profile-all-title">
									<div class="edit-profile-title">직위</div>
									<input type="text" name="position" id="edit-profile-position">
								</div>
								<br>
							</div>
						</div>
						<div align="center">
							<input type="button" class="btn add-btn"
								id="update-edit-profile-btn" value="수정">&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn cancel-btn" data-dismiss="modal"
								value="취소">
						</div>
					</fieldset>
				</form>
				<hr id="edit-memberinfo-hr">
				<form id="edit-pwd-form" method="post">
					<fieldset>
						<div class="edit-profile-part2">
							<h4>비밀번호 변경</h4>
							<br>
							<div class="edit-profile-title">현재 비밀번호</div>
							<input type="password" name="pwd" id="edit-profile-cpwd">
							<br>
							<div class="edit-profile-title">변경 비밀번호</div>
							<input type="password" name="pwd" id="edit-profile-upwd">
							<br>
							<div class="edit-profile-title">변경 비밀번호 확인</div>
							<input type="password" name="pwd2" id="edit-profile-upwd2">
							<br>
						</div>
						<div align="center">
							<input type="button" class="btn add-btn" id="update-pwd-btn"
								value="수정">&nbsp;&nbsp;&nbsp; <input type="button"
								class="btn cancel-btn" data-dismiss="modal" value="취소">
						</div>
					</fieldset>
				</form>
			</div>
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
		<div class="modal-dialog">
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
						<input type="button" class="btn add-btn" id="logout-btn" value="확인" onclick="location.href='logout.htm'">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn" data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>

