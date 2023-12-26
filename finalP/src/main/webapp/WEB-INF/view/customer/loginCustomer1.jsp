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
<form action="${ctp}/loginCustomer" method="post">
	<table>
		<tr>
			<td>id</td>
			<td><input type="text" name="customerId"></td>
		</tr>
		<tr>
			<td>pw</td>
			<td><input type="text" name="customerPw"></td>
		</tr>		
	</table>
	<button type="button">로그인</button>
</form>
</body>
</html>