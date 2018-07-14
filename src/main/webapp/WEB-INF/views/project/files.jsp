<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<div class="main-body-container">
  <div class="main-body-onepannel">
    <div class="main-body-onepannel-container">
      <div class="main-body-onepannel-header">
		<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include>
        <div class="main-body-onepannel-header-bottom">
        </div>
      </div>
      <div class="main-body-onepannel-body">
		<div class="files-Table-div" align="center">
			<table class="table file-table">
				<thead class="file-thead">	
					<tr>
						<th class="col-sm-1 file-table-tr-th-num">번호</th>
						<th	class="col-sm-8 file-table-tr-th-fname">File명</th>
						<th	class="col-sm-3 file-table-tr-th-tname">관련 Task</th>
					</tr>
				</thead>
				<tbody id="file-tbody">
					<c:forEach var="f" items="${f}">
					<c:set var="count" value="${count + 1}" />
					<tr class="file-table-tr-td">
				
					<td class="file-table-tr-td-count">${count}</td>
					<td class="file-table-tr-td-fname"><a href="taskFileDownload.htm?filename=${f.filename}"><strong>${f.filename}</strong></a>
					<div id="${f.fileid}" class="file-table-tr-td-delete" data-toggle="modal" data-target="#file_delete_modal">
					<span id="${f.filename}" class="file-deleteicon glyphicon glyphicon-remove"></span>
					</div></td>
					<td class="file-table-tr-td-tname">${f.tname}&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp; <h class="file-table-tr-td-sname">${f.sname}</h></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
					<div id="project-page-table-no-data">
						<div id="proejct-page-table-img-content">
							<img src="img/rocket.png" id="proejct-page-table-img">
						</div>
						<div id="project-page-table-no-data-content">NO DATA!</div>
					</div>
				</div><!--END OF FILE TABLE  -->
      </div>
    </div>
  </div>
</div>
