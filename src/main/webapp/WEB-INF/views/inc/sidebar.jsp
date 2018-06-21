<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >


<div id="my-side-nav" class="side-nav">
		<div class="text-center container-fluid">
			<a class="side-bar-add-project" id="side-bar-add-project" data-toggle="modal"
				data-target="#project-insert">프로젝트 생성 <img class="add-project-img"
				src="img/if_001_01_9588.gif"></a>
		</div>

		<div>
			<a data-toggle="collapse" data-target="#working-project">진행중인
				프로젝트</a>
			<div class="project-status-wrapper side-dir collapse in" id="working-project" aria-expanded="true">
		   </div>
		</div>
		<div>
			<a data-toggle="collapse" data-target="#finished-project">완료 프로젝트</a>
			<div class="project-status-wrapper side-dir collapse" id="finished-project">
				<a class="side-project"><span
					class=" glyphicon glyphicon-duplicate"></span>Project2</a>
			</div>
		</div>
		<div>
			<a data-toggle="collapse" data-target="#trash-bin"><span
				class="glyphicon glyphicon-trash"></span>휴지통</a>
			<div class="project-status-wrapper side-dir collapse" id="trash-bean">
				<a class="side-project"><span
					class="glyphicon glyphicon-duplicate"></span>Project3</a>
			</div>
		</div>

</div>