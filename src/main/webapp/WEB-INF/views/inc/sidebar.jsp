<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<input type="hidden" id="currentPage" value="<%=session.getAttribute("location")%>">
<div id="my-side-nav" class="side-nav">
		<div id="side-bar-add-project" data-toggle="modal"
					data-target="#project-insert">
		 	<b>New Project(보류)&nbsp;&nbsp;</b>
		 	<i  class="fas fa-plus-circle"></i>
		</div>
		
		<div>
			<div class="side-pstatus">
				<i data-toggle="collapse" data-target="#working-project" class="side-dir-arrow fas fa-angle-down"></i>
				<b>Working Projects</b>
				
			</div>
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