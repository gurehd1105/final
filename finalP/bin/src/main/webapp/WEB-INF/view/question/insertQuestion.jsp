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
			<td>Title</td>
			<td><input type="text" name="questionTitle"></td>
		</tr>
		
		<tr>
			<td>Content</td>
			<td><textarea name="questionContent"></textarea></td>
		</tr>
	</table>
		<button type="submit">등록</button>
</form>
</body>
</html>