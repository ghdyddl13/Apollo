<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >


<div id="my-side-nav" class="side-nav">
		<div class="text-center container-fluid">
			<a class="side-bar-add-project" id="side-bar-add-project" data-toggle="modal"
				data-target="#project-insert-form">프로젝트 생성 <img class="add-project-img"
				src="img/if_001_01_9588.gif"></a>
		</div>

		<div>
			<a data-toggle="collapse" data-target="#working-project">진행중인
				프로젝트</a>
			<div class="side-dir collapse" id="working-project">
				<a   class="side-project"><span  data-toggle="collapse"
					 data-target="#project1"
					class="glyphicon glyphicon-duplicate"></span>Project1</a>
				<div class="side-dir collapse" id="project1">
					<a class="side-folder" ><span data-toggle="collapse" data-target="#folder1"
						class="glyphicon glyphicon-folder-close"></span>Folder1</a>
					<div class="side-dir collapse" id="folder1">
						<a id="3" class="side-step step1"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>Step1</a>
					</div>
					<a class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>Step2</a> <a
						class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>Step3</a>
				</div>
				<!-- 애자일 -->
				<a class="side-project" ><span data-toggle="collapse" data-target="#agile"
					class="glyphicon glyphicon-duplicate"></span>애자일</a>
				<div class="side-dir collapse" id="agile">
					<a class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>Backlog</a> <a
						class="side-folder" ><span data-toggle="collapse"
						data-target="#finished-sprint"
						class="glyphicon glyphicon-folder-close"></span>완료된 스프린트</a>
					<div class="side-dir collapse" id="finished-sprint">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>sprint01</a>
					</div>
					<a class="side-folder" ><span data-toggle="collapse"
						data-target="#progressed-sprint"
						class="glyphicon glyphicon-folder-close"></span>진행중인 스프린트</a>
					<div class="side-dir collapse" id="progressed-sprint">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>sprint02</a>
					</div>
					<a class="side-folder" ><span data-toggle="collapse"
						data-target="#next-sprint"
						class="glyphicon glyphicon-folder-close"></span>다음 스프린트</a>
					<div class="side-dir collapse" id="next-sprint">
						<a class="side-step"><span
							class="glyphicon glyphicon glyphicon-list-alt"></span>sprint03</a>
					</div>
				</div>

				<!-- 폭포수 -->
				<a class="side-project" ><span data-toggle="collapse" data-target="#waterfall"
					class="glyphicon glyphicon-duplicate"></span>폭포수</a>
				<div class="side-dir collapse" id="waterfall">
					<a class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>계획</a> <a
						class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>요구분석</a> <a
						class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>설계</a> <a
						class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>구현</a> <a
						class="side-step"><span
						class="glyphicon glyphicon glyphicon-list-alt"></span>테스트</a> <a
						class="side-step"><span
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