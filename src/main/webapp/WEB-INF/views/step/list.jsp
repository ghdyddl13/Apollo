<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/list.js"></script>
    
<!DOCTYPE html >

<h3>리스트</h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>

<a class ="Task_RUD_Modal" data-toggle="modal" data-target="#Task_RUD_Modal"><div id="t10">테스트 tid 10</div></a>

<!-- Task 수정/삭제 Modal 창 -->
	<div class="modal fade modal-task-dialog" id="Task_RUD_Modal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<!-- row 1 -->
				<div class="row">
					<div class="col-sm-6">
						<h4 class="modal-title" id="Task_Modal_tname">Task_Modal_tname</h4>
					</div>
					<div class="col-sm-6" id="star_trash">
						<i class="fas fa-star"></i>&nbsp;&nbsp;
						<i class="fas fa-trash"></i>&nbsp;&nbsp;&nbsp;
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					
					<div class="col-sm-12" id="Task_Modal_snames">
					<br>여기는 소속된 스텝들이 뜨는 공간입니다&nbsp;<i class="fas fa-plus-circle"></i>
					</div>
				</div>
				<!-- end row 1 -->
				<hr>	
				<!-- row 2 -->
				<div class="row">
					<div class="col-sm-3" id="Task_Modal_tstatus">
							<select>
							<option>New</option>
							<option>Development</option>
							<option>Testing</option>
							<option>Reopened</option>
							<option>Ready</option>
							<option>Completed</option>
							<option>On hold</option>
							<option>Cancelled</option>
							</select>
					</div>
					<div class="col-sm-9" id="Task_Modal_assignee">여기는 담당자들이 뜨는 공간입니다&nbsp;<i class="fas fa-plus-circle"></i></div>
				</div>
				<!-- end row 2 -->
				<hr>
				<!-- end modal-header -->

				<div class="modal-body">
					<!-- row 1 -->
					<div class="row">
                	<div class="col-sm-12 modal-title">기간 설정</div><br /><br />

					<div class="col-sm-6">
	                    <div class="modal-title">
	                    <p>시작일</p>
	                    </div>
                   		<input id="Task_Modal_sday" type="text" name="sday" placeholder="Start Date" class="date date-img">
					</div>

					<div class="col-sm-6">
	                    <div class="modal-title">
	                      <p>종료일</p>
	                    </div>
	                    <input id="Task_Modal_eday" type="text" name="eday" placeholder="End Date" class="date date-img">
	                    <br><br>
					</div>
					</div>
					<!-- end row 1 -->

                <hr />
					<div class="modal-title">파일 업로드</div><br />
	                <div id="Task_Modal_files">파일들이 여기 잡히게 된다</div>
	                <br>
	                <input id="Task_Modal_add_file_btn" type="button" value="파일추가"><br /><br />
                <hr>

				<div class="modal-title">Sub Task</div><br />
				 <div id="Task_Modal_subtasks">subtask들이 들어가게 된다</div><br>
					<input type="text" name="pname" id="add_sub_task"
							placeholder="Sub Task 입력"
							class="text ui-widget-content ui-corner-all">
					<input type="button" value="Sub Task 추가">
                <hr />
                
				<div class="modal-title">
					<p>상세설명</p>
					<textarea id="Task_Modal_detail" rows="7%" cols="49%" name="detail" placeholder="내용을 입력하세요"></textarea>
				</div>
                <hr />
					<div class="modal-title">Comment</div><br />
					<div id="Task_Modal_comments">여기는 코멘트 들이 추가되는 공간입니다
					<br>
					<br>
					<br>
					</div>
					<input type="text" placeholder="코멘트 입력.." class="">
					<input id="Task_Modal_enter_comments" type="button" value="전송">
			 	</div>
			 	<!-- end modal-body -->
				</div>
			</div>
		</div>
	</div>

