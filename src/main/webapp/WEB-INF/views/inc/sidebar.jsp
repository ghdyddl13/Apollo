<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<input type="hidden" id="currentPage" value="<%=session.getAttribute("location")%>">
<div id="my-side-nav" class="side-nav">
		<div class="text-center container-fluid">
			<div class="side-bar-add-project" id="side-bar-add-project" data-toggle="modal"
				data-target="#project-insert">New Project<img class="add-project-img"
				src="img/if_001_01_9588.gif"></div>
		</div>

		<div>
			<div class="side-pstatus">
			<i data-toggle="collapse" data-target="#working-project" class="side-dir-arrow fas fa-angle-down"></i>
			<b>Working Projects</b></div>
			<div class="project-status-wrapper side-dir collapse in" id="working-project" aria-expanded="true">
		   </div>
		</div>
		<div>
			<div  class="side-pstatus">
			<i data-toggle="collapse" data-target="#finished-project" class="side-dir-arrow fas fa-angle-right"></i>
			<b>Completed Projects</b></div>
			<div class="project-status-wrapper side-dir collapse" id="finished-project">
				
			</div>
		</div>
		<div>
			<div class="side-pstatus">
				<i data-toggle="collapse" data-target="#trash-bin" class=" side-dir-arrow fas fa-angle-right"></i>
	
				<i class="far fa-trash-alt"></i>
				&nbsp;&nbsp;
				<b>Recycle Bin</b>
			</div>
			<div class="project-status-wrapper side-dir collapse" id="trash-bin">
				
			</div>
		</div>

</div>