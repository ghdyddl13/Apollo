<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:forEach var="streamlist" items="${streamlist}">
<div>
	${streamlist.cmtid}<br>
	${streamlist.tname}<br>
	${streamlist.pname}<br>
	${streamlist.cmtmtime}<br>
	${streamlist.mname} : ${streamlist.comments}<br>
</div>
</c:forEach>
