<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
	
});
</script>

<!-- 프로젝트 생성 Modal 창 -->
	<div class="modal fade" id="dialog-form123" role="dialog">
		<div class="modal-dialog modal-add-project-dialog">
			<div class="modal-content modal-add-project-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Project 생성</h4>
				</div>
				<div class="modal-body">
				<!-- 추후 actuon=""information.htm" 으로 변경 예정 -->
					<form action="insertproject.htm" method="post">
						<fieldset>
							<div class="row">
								<br>
								<div class="col-sm-6 project-setting-left">
									<input type="text" name="pname" id="add-project-name"
										placeholder="프로젝트명을 입력하세요"
										class="text ui-widget-content ui-corner-all"><br>
									<br> 
									<div class="modal-title">
										<p>방법론</p>
									</div>
										<input type="radio" name="methodologyid" value="3"
										checked><span class="method"> Customizing</span> <br>
									<div>
										<!-- Customizing 설명 작성  -->
										가나다라마바사아자차카타파하 커스텀마이징 설명작성 필요
									</div>
									<br> <input type="radio" name="methodologyid" value="2"><span
										class="method"> Agile</span><br>
									<div>
										<!-- Agile 설명 작성  -->
										가나다라마바사아자차카타파하 애자일 설명작성 필요
									</div>
									<br> <input type="radio" name="methodologyid" value="1"><span
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
										<input type="text" name="sday" placeholder="Start Date" class="date date-img">
									</div>
									<div class="col-sm-6">
										<div class="modal-title">
											<p>종료일</p>
										</div>
										<input type="text" name="eday" placeholder="End Date" class="date date-img">
										<br><br>
									</div>
									<div class="modal-title">
										<p>상세설명</p>
									</div>
									<textarea rows="7%" cols="49%" name="detail" placeholder="내용을 입력하세요"></textarea>
								</div>
							</div>
							<!-- 아래는 임시로 작성해둔 것임 -->
							<input type="hidden" name="mid" value="${mid}">
		
						<div align="center">
							<input type="submit" class="btn add-btn" value="생성">&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn cancel-btn"
								data-dismiss="modal" value="취소">
						</div>
						<br>
						</fieldset>
					</form>

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
				<div class="modal-body">
					<input type="text" class="add-folder-text"
						placeholder="folder명을 입력하세요">
					<div align="center">
						<input type="submit" class="btn add-btn" value="생성">&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn cancel-btn"
							data-dismiss="modal" value="취소">
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>

	<!-- 스텝 생성 Modal 창 -->
	<div class="modal fade" id="step-add-modal" role="dialog">
		<div class="modal-dialog modal-add-step-dialog">
			<!-- Modal content-->
			<div class="modal-content modal-add-step-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Step 생성</h4>
				</div>
				<div class="modal-body">
					<form method="post">
						<fieldset>
							<div class="modal-title">
								<p>Step명</p>
							</div>
							<input type="text" name="sname" class="add-step-text"
								placeholder="step명을 입력하세요">
							<br><br>
							<div class="modal-title">
								<p>책임자</p>
							</div>
							<div>
								<select>
									<option value=""></option>
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
								<input type="text" name="sday" placeholder="Start Date"
									class="date date-img"><br>
							</div>
							<div class="col-sm-6">
								<div class="modal-title">
									<p>종료일</p>
								</div>
								<input type="text" name="eday" placeholder="End Date"
									class="date date-img"><br><br><br>
							</div>
							<div class="modal-title">
								<p>상세설명</p>
							</div>
							<textarea rows="7%" cols="68%" name="detail" placeholder="내용을 입력하세요"></textarea>
								<br><br>
							<div align="center">
								<input type="submit" class="btn add-btn" value="생성">&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn cancel-btn"
									data-dismiss="modal" value="취소">
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
					<form>
						<fieldset>
							<div class="row">
								<br>
								<div class="col-sm-6 project-setting-left">
									<input type="text" name="pname" id="add-project-name"
										placeholder="프로젝트명을 입력하세요"
										class="text ui-widget-content ui-corner-all"><br>
									<br> 
									<div class="modal-title">
										<p>방법론</p>
									</div>
										<input type="radio" name="methodologyid" value="customizing"
										checked><span class="method"> Customizing</span> <br>
									<div>
										<!-- Customizing 설명 작성  -->
										가나다라마바사아자차카타파하 커스텀마이징 설명작성 필요
									</div>
									<br> <input type="radio" name="methodologyid" value="agile"><span
										class="method"> Agile</span><br>
									<div>
										<!-- Agile 설명 작성  -->
										가나다라마바사아자차카타파하 애자일 설명작성 필요
									</div>
									<br> <input type="radio" name="methodologyid" value="waterfall"><span
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
										<input type="text" name="sday" placeholder="Start Date" class="date date-img">
									</div>
									<div class="col-sm-6">
										<div class="modal-title">
											<p>종료일</p>
										</div>
										<input type="text" name="eday" placeholder="End Date" class="date date-img">
										<br><br>
									</div>
									<div class="modal-title">
										<p>상세설명</p>
									</div>
									<textarea rows="7%" cols="49%" name="detail" placeholder="내용을 입력하세요"></textarea>
								</div>
							</div>
							<br><br>
						<div align="center">
							<input type="submit" class="btn add-btn" value="수정">&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn cancel-btn"
								data-dismiss="modal" value="취소">
						</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>	
	
	<!-- Project 삭제 Modal 창 -->
	<div class="modal fade" id="delete-project" role="dialog">
		<div class="modal-dialog modal-delete-project-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Project 삭제</h4>
				</div>
				<div class="modal-body">
					<p>해당 프로젝트를 삭제하시겠습니까?</p>
					<p>프로젝트 삭제시 14일동안 휴지통에 보관됩니다.</p>
				</div>
				<div align="center">
					<input type="submit" class="btn delete-btn" value="삭제">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn cancel-btn"
						data-dismiss="modal" value="취소">
				</div>
				<br>
			</div>
		</div>
	</div>	
