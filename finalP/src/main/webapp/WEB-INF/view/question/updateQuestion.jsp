<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${ctp}/updateQuestion" method="post">
		<table>				
			
			<tr>
				<td>Customer Id</td>
				<td>${ questionMap.customerId }</td>
			</tr>
			
			<tr>
				<td><label for="questionTitle">Title</label></td>
				<td><input type="text" value="${ questionMap.questionTitle }" id="questionTitle" name="questionTitle"></td>
			</tr>
			
			<tr>
				<td><label for="questionContent">Content</label></td>
				<td><textarea id="questionContent" name="questionContent">${ questionMap.questionContent }</textarea></td>
			</tr>
			
		</table>
				<input type="hidden" value="${ questionMap.questionNo }" name="questionNo">
	<button type="submit">수정</button>
</form>
</body>
</html>