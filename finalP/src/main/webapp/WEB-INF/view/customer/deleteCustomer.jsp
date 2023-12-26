<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctp}/deleteCustomer?customerNo=${loginCustomer.customerNo}" method="post">
		<table>
			<tr>
				<td>Id</td>
				<td><input type="text" readonly value="${loginCustomer.customerId}" name="customerId"></td>
			</tr>
			
			<tr>
				<td>Pw</td>
				<td><input type="password" name="customerPw"></td>
			</tr>
			
			<tr>
				<td>Pw Ck</td>
				<td><input type="password"></td>
			</tr>
			
			
		</table>
			<button type="submit">탈퇴</button>
	</form>
</body>
</html>