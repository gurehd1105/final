<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>update CustomerOne</h1>
	
			
			<div><input type="hidden" value="" name="">${resultMap.customerImgFileName}</div><!-- 프로필사진/임시 -->
		
	
		<table>
		<tr>
			<td>Id</td>
			<td><input type="text" value="${resultMap.customerId}" name="customerId"></td>			
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
</body>
</html>