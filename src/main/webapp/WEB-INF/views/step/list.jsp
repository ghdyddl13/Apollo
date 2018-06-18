<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/list.js"></script>
    
<!DOCTYPE html >

<h3>리스트</h3>
<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>

<a id ="testmodal_btn" data-toggle="modal" data-target="testmodal"><div id='t10'>테스트 tid 10</div></a>

<!-- task 수정/삭제 Modal -->
	<div class="modal fade" id="testmodal">
		<div class="modal-dialog modal-add-project-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Task Test</h4>
				</div>
				
				<div class="modal-body ">
					<div id="task_test_area">테스트입니다 없어지고 디비 데이터 떠야할 디브</div>
					<div id="db_area"></div>
				</div>
				
				<div align="center">
					<input type="button" class="btn" id="cancel-btn"
						data-dismiss="modal" value="돌아가기">
				</div>
				<br>
			</div>
		</div>
	</div>
