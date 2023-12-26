<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${ctp}/insertCustomer" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>Id</td>
			<td><input type="text" name="customerId"></td>			
		<tr>
		
		<tr>
			<td>Pw</td>
			<td><input type="password" name="customerPw"></td>
		<tr>
		<tr>
			<td>Pw Ck</td>
			<td><input type="password"></td>
		<tr>
		
		<tr>
			<td>Name</td>
			<td><input type="text" name="customerName"></td>
		<tr>
		
		<tr>
			<td>Gender</td>
			<td><input type="text" name="customerGender"></td>
		<tr>
		
		<tr>
			<td>Phone</td>
			<td><input type="text" name="customerPhone"></td>
		<tr>
		
		<tr>
			<td>Height</td>
			<td><input type="text" name="customerHeight"></td>
		<tr>
		
		<tr>
			<td>Weight</td>
			<td><input type="text" name="customerWeight"></td>
		<tr>
		
		<tr>
			<td>Address</td>
			<td><input type="text" name="customerAddress"></td>
		<tr>
		
		<tr>
			<td>Email</td>
			<td><input type="text" name="customerEmail"></td>
		<tr>
		
		<tr>
			<td>Img</td>
			<td><input type="file" name="customerImg"></td>
		<tr>
	</table>
	<button type="reset">초기화</button>
	<button type="button">회원가입</button>
</form>
</body>
</html>