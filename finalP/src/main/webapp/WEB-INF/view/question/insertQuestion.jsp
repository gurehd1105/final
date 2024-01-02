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
<form action="${ctp}/insertQuestion" method="post">
	<table>
		<tr>
			<td>Customer Id</td>
			<td>
				<input type="hidden" name="customerNo" value="${loginCustomer.customerNo}">
				${loginCustomer.customerId}</td>
		</tr>
		
		<tr>
			<td><label for="questionTitle">Title</label></td>
			<td><input type="text" id="questionTitle" name="questionTitle" placeholder="제목"></td>
		</tr>
		
		<tr>
			<td><label for="questionContent">Content</label></td>
			<td><textarea id="questionContent" name="questionContent" placeholder="문의내용"></textarea></td>
		</tr>
	</table>
		<button type="submit">등록</button>
</form>
</body>
</html>