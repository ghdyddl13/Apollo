<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<div class="main-body-onepannel-header-top">
	<div class="main-body-onepannel-header-top-title">
		<div class="main-body-onepannel-header-top-title-text" id="s${stepinfo.sid}">${stepinfo.sname}</div>
	</div>
	<input id ="current-sid" type ="hidden" value=<%=session.getAttribute("sid")%> >
	<div class="main-body-onepannel-header-top-selectors">
		<span class="main-body-onepannel-header-top-selector" id ="list-page" > 
			<span class="main-body-onepannel-header-top-selector-text" >list</span>
		</span> 
		<span class="main-body-onepannel-header-top-selector" id ="board-page">
			<span class="main-body-onepannel-header-top-selector-text">board</span>
		</span> 
		<span class="main-body-onepannel-header-top-selector" id ="timeline-page">
			<span class="main-body-onepannel-header-top-selector-text">timeline</span>
		</span>
	</div>
</div>