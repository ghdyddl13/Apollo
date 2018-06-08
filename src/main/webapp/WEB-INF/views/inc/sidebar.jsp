<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="my-side-nav" class="side-nav">
		<div class="text-center container-fluid">
			<a class="add-project" id="add-project" data-toggle="modal"
				data-target="#dialog-form">프로젝트 생성 <img class="add-project-img"
				src="img/if_001_01_9588.gif"></a>
		</div>

		<div>
			<a data-toggle="collapse" data-target="#working-project">진행중인
				프로젝트</a>
			<div class="side-dir collapse" id="working-project">

				<a class="side-project" data-toggle="collapse" on
					data-target="#project1"><span
					class="glyphicon glyphicon-duplicate"></span>Project1</a>
				<div class="side-dir collapse" id="project1">
					<a class="side-folder" data-toggle="collapse"
						data-target="#folder1"><span
						class="glyphicon glyphicon-folder-close"></span>Folder1</a>
					<div class="side-dir collapse" id="folder1">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>Step1</a>
					</div>
					<a class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>Step2</a> <a
						class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>Step3</a>
				</div>
				<!-- 애자일 -->
				<a class="side-project" data-toggle="collapse" data-target="#agile"><span
					class="glyphicon glyphicon-duplicate" ></span>애자일</a>
				<div class="side-dir collapse" id="agile">
					<a class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>Backlog</a> <a
						class="side-folder" data-toggle="collapse"
						data-target="#finished-sprint"><span
						class="glyphicon glyphicon-folder-close"></span>완료된 스프린트</a>
					<div class="side-dir collapse" id="finished-sprint">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>sprint01</a>
					</div>
					<a class="side-folder" data-toggle="collapse"
						data-target="#progressed-sprint"><span
						class="glyphicon glyphicon-folder-close"></span>진행중인 스프린트</a>
					<div class="side-dir collapse" id="progressed-sprint">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>sprint02</a>
					</div>
					<a class="side-folder" data-toggle="collapse"
						data-target="#next-sprint"><span
						class="glyphicon glyphicon-folder-close"></span>다음 스프린트</a>
					<div class="side-dir collapse" id="next-sprint">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>sprint03</a>
					</div>
				</div>

				<!-- 폭포수 -->
				<a class="side-project" data-toggle="collapse" data-target="#waterfall"><span
					class="glyphicon glyphicon-duplicate"></span>폭포수</a>
					<div class="side-dir collapse" id="waterfall">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>계획</a>
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>요구분석</a>
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>설계</a>
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>구현</a>
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>테스트</a>
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>유지 및 보수</a>
					</div>
			</div>
		</div>
		<div>
			<a data-toggle="collapse" data-target="#finished-project">완료 프로젝트</a>
			<div class="side-dir collapse" id="finished-project">
				<a class="side-project"><span
					class=" glyphicon glyphicon-duplicate"></span>Project2</a>
			</div>
		</div>
		<div>
			<a data-toggle="collapse" data-target="#trash-bean"><span
				class="glyphicon glyphicon-trash"></span>휴지통</a>
			<div class="side-dir collapse" id="trash-bean">
				<a class="side-project"><span
					class="glyphicon glyphicon-duplicate"></span>Project3</a>
			</div>
		</div>
	</div>

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
										<br>
										<br>
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
