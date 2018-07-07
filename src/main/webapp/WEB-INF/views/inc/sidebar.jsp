<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="toDay" class="java.util.Date" />
<!DOCTYPE html >

<div id="my-side-nav" class="side-nav">
	<div id="side-bar-add-project" data-toggle="modal"
				data-target="#project-insert">
	 	<b>New Project&nbsp;&nbsp;</b>
	 	<i  class="fas fa-plus-circle"></i>
	</div>
	<div class="side-body">
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
	<div>		
	 	 <c:set var="m" value="${memberdto}"></c:set>
			<c:choose>
				<c:when test="${m.apollokey eq null}">
					<div class="side-freetrial">
	  				<fmt:parseDate var="rday14"  value="${m.rday}" pattern="yyyy-MM-dd"/>
	    			<fmt:parseNumber value="${toDay.time/(1000*60*60*24)}" var="rday0Date" integerOnly="true"/>
	   				<fmt:parseNumber value="${rday14.time/(1000*60*60*24)+15}" var="rday14Date" integerOnly="true"/>
					<span class = "side-freetrial-span">free trial <h id="freetrial-h"><strong>${rday14Date - rday0Date}</strong></h> days left</span>
					<hr id="freetrial-hr">	
				</div>	
				</c:when>
				<c:when test="${m.apollokey ne null}">
					<div class="side-freetrial">
					<span class = "side-freetrial-span">Hello Apollo!</span>
					<hr id="freetrial-hr">	
				</div>
				</c:when>
			</c:choose>
	</div>
	
</div>