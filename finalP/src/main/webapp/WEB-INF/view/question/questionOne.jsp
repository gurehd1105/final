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
<form action="${ctp}/deleteQuestion">
	<table>
		<tr>
			<td>Customer Id</td>
			<td>${ resultMap.customerId }</td>
		</tr>
		
		<tr>
			<td>Date</td>
			<td>${ resultMap.qUpdatedate }</td>
		</tr>
		
		<tr>
			<td>Title</td>
			<td>
				<input type="hidden" name="questionNo" value="${ resultMap.questionNo }">
				${ resultMap.questionTitle }
			</td>
		</tr>
		
		<tr>
			<td>Content</td>
			<td><textarea readonly="readonly">${ resultMap.questionContent }</textarea></td>
		</tr>
	</table>
		<button type="submit">삭제</button>
</form>	
</body>
</html>