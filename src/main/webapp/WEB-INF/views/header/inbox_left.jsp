<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

				
				<center><h3 style="color:#717171;">TODAY</h3><hr class="inbox-hr"></center>	
				<c:forEach var="commentlist" items="${cmtlist}" varStatus="status">
				<c:choose>
					<c:when test="${fn:substring(commentlist.cmtmtime,0,10) eq today}">
						<div class="inbox-list">
						<div class ="Task_RUD_Modal" id="t${commentlist.tid}" style="cursor: pointer;">
							<div class ="yyTask_RUD_Modal">
							<span class="inbox-span1"><b>${commentlist.tname}</b></span></div>
							<span class="inbox-span2">
							
							<span style="margin-right: 5px; font-size: 15px;">&#60;&nbsp;</span>
							<c:choose>
								<c:when test="${commentlist.methodologyid eq 1}">
									<img style="width: 11px; height: 11px;" src="img/waterfallicon.png">
								</c:when>
								<c:when test="${commentlist.methodologyid eq 2}">
									<img style="width: 11px; height: 11px;" src="img/agileicon.png">
								</c:when>
								<c:otherwise>
									<img style="width: 11px; height: 11px;" src="img/customicon.png">
								</c:otherwise>
							</c:choose>
							&nbsp;${commentlist.pname}</span>
							<span class="inbox-span3">
							<c:choose>
								<c:when test="${inbox eq 'archive'}">
									<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
									<c:if test="${commentlist.newcheck eq 0}">
										<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
									</c:if>
								
									<span class="archiveupdate2" style="cursor: pointer;">
									<img src="img/archive2.png" width="15px" height="15px">
									<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
									</span>
								</c:when>
								<c:otherwise>
								<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
									<c:if test="${commentlist.newcheck eq 0}">
										<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
									</c:if>
									
									<span class="archiveupdate" style="cursor: pointer;">
									<img src="img/archive.png" width="15px" height="15px">
									<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
									</span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;&nbsp;${commentlist.cmtmtime}</span>
							<div class="inbox-div4"><img style="border-radius: 50%;" src='displayImage.htm?image=${commentlist.image}' width="30px" height="30px">&nbsp;&nbsp;&nbsp;
							<c:if test="${commentlist.cmtkind eq 0}">${commentlist.mname} : </c:if>${commentlist.comments}</div>
						</div>
						</div>
						<hr class="inbox-hr">
					</c:when>
					<c:otherwise>
						<c:if test="${fn:substring(cmtlist[status.index-1].cmtmtime,0,10) ne fn:substring(commentlist.cmtmtime,0,10)}">
							<center>
								<h3 style="color:#717171;">${fn:substring(commentlist.cmtmtime,0,10)}</h3>
								<hr class="inbox-hr">
							</center>
						</c:if>
						<div class="inbox-list"><div class ="Task_RUD_Modal" id="t${commentlist.tid}" style="cursor: pointer;">
							<div class ="yyTask_RUD_Modal"><div><span class="inbox-span1"><b>${commentlist.tname}</b></span></div></div>
							<span class="inbox-span2" >
							<span style="margin-right: 5px; font-size: 15px;">&#60;&nbsp;</span> 
							<c:choose>
								<c:when test="${commentlist.methodologyid eq 1}">
									<img style="width: 11px; height: 11px;" src="img/waterfallicon.png">
								</c:when>
								<c:when test="${commentlist.methodologyid eq 2}">
									<img style="width: 11px; height: 13px;" src="img/agileicon.png">
								</c:when>
								<c:otherwise>
									<img style="width: 11px; height: 13px;" src="img/customicon.png">
								</c:otherwise>
							</c:choose>
							&nbsp;${commentlist.pname}</span> 
							<span class="inbox-span3">
							<c:choose>
								<c:when test="${inbox eq 'archive'}">
								<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
									<c:if test="${commentlist.newcheck eq 0}">
										<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
									</c:if>
									<span class="archiveupdate2" style="cursor: pointer;">
									<img src="img/archive2.png" width="15px" height="15px">
									<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
									</span>
								</c:when>
								<c:otherwise>
								<input class="inbox_mid2" type="hidden" value="${commentlist.mid2}"> 
									<c:if test="${commentlist.newcheck eq 0}">
										<span class = "inbox_newcheck" style="color: rgba(211, 0, 68, 0.74);">NEW!</span>&nbsp;&nbsp;&nbsp;
									</c:if>
									<span class="archiveupdate" style="cursor: pointer;">
									<img src="img/archive.png" width="15px" height="15px">
									<input type="hidden" class = "cmtid" value="${commentlist.cmtid}">
									</span>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;&nbsp;${commentlist.cmtmtime}</span>
							<div class="inbox-div4"><img style="border-radius: 50%;" src='displayImage.htm?image=${commentlist.image}' width="30px" height="30px">&nbsp;&nbsp;&nbsp;${commentlist.mname} : ${commentlist.comments}</div>
						</div>
						</div>
						<hr class="inbox-hr">
					</c:otherwise>
				</c:choose>
				</c:forEach>