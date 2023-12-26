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
<h1>updateCustomerPw</h1>
<form action="${ctp}/updateCustomerPw" method="post">
	<table>
		<tr>
			<td>Id</td>
			<td>${loginCustomer.customerId}</td>
		</tr>
		
		<tr>
			<td>Pw</td>
			<td><input type="password" name="customerPw"></td>
		</tr>
		
		<tr>
			<td>변경Pw</td>
			<td><input type="password" name="customerNewPw"></td>
		</tr>
		
		<tr>
			<td>변경Pw확인</td>
			<td><input type="password"></td>
		</tr>
	</table>
		<button type="submit">변경</button>
</form>
</body>
</html>