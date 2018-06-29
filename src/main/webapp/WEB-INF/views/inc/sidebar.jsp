<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<input type="hidden" id="currentPage" value="<%=session.getAttribute("location")%>">
<div id="my-side-nav" class="side-nav">
		<div class="text-center container-fluid">
			<a class="side-bar-add-project" id="side-bar-add-project" data-toggle="modal"
				data-target="#project-insert"><b>New Project</b> <i class="fas fa-plus-circle" id="side-new-project-btn"></i></a>
		</div>

		<div>
			<a class="side-pstatus">
			<i data-toggle="collapse" data-target="#working-project" class="side-dir-arrow fas fa-angle-down"></i>
			<b>Working Projects</b></a>
			<div class="project-status-wrapper side-dir collapse in" id="working-project" aria-expanded="true">
		   </div>
		</div>
		<div>
			<a  class="side-pstatus">
			<i data-toggle="collapse" data-target="#finished-project" class="side-dir-arrow fas fa-angle-right"></i>
			<b>Completed Projects</b></a>
			<div class="project-status-wrapper side-dir collapse" id="finished-project">
				<a class="side-project"><span
					class=" glyphicon glyphicon-duplicate"></span>Project2</a>
			</div>
		</div>
		<div>
			<a class="side-pstatus">
				<i data-toggle="collapse" data-target="#trash-bin" class=" side-dir-arrow fas fa-angle-right"></i>
				<b>Recycle Bin&nbsp;&nbsp;&nbsp;</b>
				<i class="far fa-trash-alt"></i>
			</a>
			<div class="project-status-wrapper side-dir collapse" id="trash-bin">
				<a class="side-project"><span
					class="glyphicon glyphicon-duplicate"></span>Project3</a>
			</div>
		</div>

</div>