<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 프로젝트 생성 Modal 창 -->
	<div class="modal fade" id="dialog-form" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Create Project</h4>
				</div>
				<div class="modal-body">
					<form>
						<fieldset>
							<div class="row">
								<br>
								<div class="col-sm-6 project-setting-left">
									<div class="add-project-modal-title">
										<p>프로젝트명</p>
									</div>
									<input type="text" name="name" id="project-name"
										placeholder="프로젝트명을 입력하세요"
										class="text ui-widget-content ui-corner-all"><br>
									<br> <input type="radio" name="method" value="customizing"
										checked><span class="method"> Customizing</span> <br>
									<div>
										<!-- Customizing 설명 작성  -->
										가나다라마바사아자차카타파하 커스텀마이징 설명작성 필요
									</div>
									<br> <input type="radio" name="method" value="agile"><span
										class="method"> Agile</span><br>
									<div>
										<!-- Agile 설명 작성  -->
										가나다라마바사아자차카타파하 애자일 설명작성 필요
									</div>
									<br> <input type="radio" name="method" value="waterfall"><span
										class="method"> Waterfall</span><br>
									<div>
										<!-- Waterfall 설명 작성  -->
										가나다라마바사아자차카타파하 폭포수 설명작성 필요
									</div>
									<br> <br>
								</div>
								<!-- Allow form submission with keyboard without duplicating the dialog button -->
								<div class="col-sm-6 project-setting-right">
									<div class="add-project-modal-title">
										<p>기간 설정</p>
									</div>
									<br>
									<div class="col-sm-6">
										<div class="add-project-modal-title">
											<p>시작일</p>
										</div>
										<input type="text" placeholder="Start Date" class="date"><br>
									</div>
									<div class="col-sm-6">
										<div class="add-project-modal-title">
											<p>종료일</p>
										</div>
										<input type="text" placeholder="End Date" class="date"><br>
										<br> <br>
									</div>
									<div class="add-project-modal-title">
										<p>상세설명</p>
									</div>
									<input type="text" class="text ui-widget-content ui-corner-all"
										id="descri" placeholder="상세설명을 입력하세요">
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<div align="center">
						<input type="button" class="btn" id="add-project-btn" value="생성">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn" id="cancel-project-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Folder 생성 Modal 창 -->
	<div class="modal fade" id="add-folder" role="dialog">
		<div class="modal-dialog modal-add-folder-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Folder 생성</h4>
				</div>
				<div class="modal-body ">
					<input type="text" class="add-folder-text"
						placeholder="folder명을 입력하세요">
				</div>
				<div align="center">
					<input type="button" class="btn" id="add-btn" value="생성">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn" id="cancel-btn"
						data-dismiss="modal" value="취소">
				</div>
				<br>
			</div>
		</div>
	</div>

	<!-- 스텝 생성 모달 -->
	<div class="modal fade" id="add-step" role="dialog">
		<div class="modal-dialog modal-add-step-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Step 생성</h4>
				</div>
				<div class="modal-body">
					<div class="add-modal-title">
						<h4>Step명</h4>
					</div>
					<input type="text" class="add-step-text" placeholder="step명을 입력하세요">
					<hr id="add-step-modal-hr">
					<div class="add-modal-title">
						<h4>설정</h4>
						<br>
						<p>책임자</p>
					</div>
					<div>책임자명</div>
					<br>
					<div class="add-modal-title">
						<p>기간 설정</p>
					</div>
					<div class="col-sm-6">
						<div class="add-modal-title">
							<p>시작일</p>
						</div>
						<input type="text" placeholder="Start Date" class="date"><br>
					</div>
					<div class="col-sm-6">
						<div class="add-modal-title">
							<p>종료일</p>
						</div>
						<input type="text" placeholder="End Date" class="date"><br>
						<br> <br>
					</div>
					<div class="add-modal-title">
						<p>상세설명</p>
					</div>
					<input type="text" class="text ui-widget-content ui-corner-all"
						id="add-step-description" placeholder="상세설명을 입력하세요">
				</div>
				<div align="center">
					<input type="button" class="btn" id="add-btn" value="생성">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn" id="cancel-btn"
						data-dismiss="modal" value="취소">
				</div>
				<br>
			</div>
		</div>
	</div>