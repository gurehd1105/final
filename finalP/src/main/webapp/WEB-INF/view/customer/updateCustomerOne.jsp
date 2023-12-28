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
<h1>update CustomerOne</h1>
<form action="${ctp}/updateCustomerOne" method="post" enctype="multipart/form-data">			
			<div><img src="${resultMap.customerImgFileName}"></div><!-- 프로필사진/임시 -->
		미선택 시 프로필 사진 없음<br><input type="file" name="customerImg"> 
			<table>
		<tr>
			<td>Id</td>
			<td><input type="text" value="${resultMap.customerId}" name="customerId" readonly></td>			
		<tr>
		
		
		<tr>
			<td>Name</td>
			<td><input type="text" value="${resultMap.customerName}" name="customerName"></td>
		<tr>
		
		<tr>
			<td>Gender</td>
			<td><input type="text" value="${resultMap.customerGender}" name="customerGender"></td>
		<tr>
		
		<tr>
			<td>Phone</td>
			<td><input type="text" value="${resultMap.customerPhone}" name="customerPhone"></td>
		<tr>
		
		<tr>
			<td>Height</td>
			<td><input type="text" value="${resultMap.customerHeight}" name="customerHeight"></td>
		<tr>
		
		<tr>
			<td>Weight</td>
			<td><input type="text" value="${resultMap.customerWeight}" name="customerWeight"></td>
		<tr>
		
		<tr>
			<td>Address</td>
			<td><input type="text" value="${resultMap.customerAddress}" name="customerAddress"></td>
		<tr>
		
		<tr>
			<td>Email</td>
			<td><input type="text" value="${resultMap.customerEmail}" name="customerEmail"></td>
		<tr>		
	</table>
	<button type="submit">저장</button>
</form>
</body>
</html>