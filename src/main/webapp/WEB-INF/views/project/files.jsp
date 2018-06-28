<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<div class="container-fluid">
<h4>파일페이지</h4>
<jsp:include page="/WEB-INF/views/inc/projectInsideHeader.jsp"></jsp:include> 
<script type="text/javascript">

	$(document).on("mouseenter",".file-table-tr-td-fname",function() {//마우스 호버 하면 checkbox가 보임
		console.log("mouseenter")
	  $(this).children(".file-table-tr-td-delete").children(".file-deleteicon").css("visibility","visible");
	}).on("mouseleave",".file-table-tr-td-fname",function() {//마우스 호버 아웃 하면 checkbox가 다시 안보이게 함
		console.log("mouseleave")
	  $(this).children(".file-table-tr-td-delete").children(".file-deleteicon").css("visibility","hidden");
	})
	
	$(document).on("click", ".file-deleteicon", function(){
		var fileid = $(this).attr('id');
	 	$('#starbucks').attr('value', fileid) 
	})
	
   $(document).on("click", "#file_delete_btn", function(){
    	 	
		var fileid = $('#starbucks').attr('value');
		 $.ajax({
             url : "filesDeleteByFileId.htm",
             data : {
                	  fileid : fileid
                   },
             success:function(data){
            	$('#file_delete_dismiss_btn').click();
                $("#main-box").empty();
                $("#main-box").append(data);
                /* doDraggable(); */
             }  
          })
		
	})  
	
	 
	
	
	
</script>
<style>

	.file-table{
		width: 100%;
		margin: auto;
		border: 3px solid #d9d9d9;
	}
	.file-table-tr-th{
		background-color: #e6e6e6;
		height: 45px;
		text-align: center;
		font-size: 18px;
	
	}
	.file-table-tr-td{
		height: 40px;
		text-align: center;
		font-size:14px;
		
	}
	 .file-table-tr-td:hover{
		background-color:#f5f5f5;
	} 
	
	.file-table-tr-th-num{
		text-align: center;
	}
	.file-table-tr-th-fname{
		text-align: center;
	}
	.file-table-tr-th-tname{
		text-align: center;
	}
	.file-table-tr-td-sname{
		color: lightgray;"
	}
	.file-table-tr-td-delete{
		float: right;
		margin-right: 20px;
    	 visibility: hidden; 
	}
</style>


<div class="files_Table">

	<table class="file-table">
			<tr class="file-table-tr-th">
				<th class="col-sm-1 file-table-tr-th-num">번호</th>
				<th	class="col-sm-8 file-table-tr-th-fname">File명</th>
				<th	class="col-sm-3 file-table-tr-th-tname">관련 Task</th>
			</tr>
			<c:forEach var="f" items="${f}">
			<c:set var="count" value="${count + 1}" />
			<tr class="file-table-tr-td">
		
			<td>${count}</td>
			<td class="file-table-tr-td-fname">${f.filename}
			<div id="${f.fileid}" class="file-table-tr-td-delete" data-toggle="modal" data-target="#file_delete_modal">
			<span id="${f.fileid}" class="file-deleteicon glyphicon glyphicon-remove"></span>
			</div></td>
			<td>${f.tname}&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp; <h class="file-table-tr-td-sname">${f.sname}</h></td>
			</tr>
			</c:forEach>
		<tbody>
		</tbody>
	</table>

</div>
</div>  