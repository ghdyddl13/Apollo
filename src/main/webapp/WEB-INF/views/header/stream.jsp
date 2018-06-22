<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
$(function() {	
	$("#pidselect").change(function(evt){
		var curpid = $(this).val()
		$.ajax({
			url:"selectstream.htm",
			type: 'POST',
			data:{pid:$(this).val()},
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$("#pidselect").val(curpid).prop("selected", true);
			}
		})
	});
	
	
});
			
			
</script>

<div class= "stream-main-container">
<select class="form-control" id="pidselect" name="book_no" style="width: 150px">
	<c:forEach var="pidlist" items="${pidlist}">
    	<option value="${pidlist.pid}">${pidlist.pname}</option>
    </c:forEach>
</select>
<div class = "stream_main">



<c:forEach var="streamlist" items="${streamlist}">

<div>
	${streamlist.cmtid}<br>
	${streamlist.tname}<br>
	${streamlist.cmtmtime}<br>
	${streamlist.mname} : ${streamlist.comments}<br>
</div>
</c:forEach>

</div>
</div>