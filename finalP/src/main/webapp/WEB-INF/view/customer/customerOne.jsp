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
	<h1>customer One</h1>
	
				<c:if test="${resultMap.customerImgFileName != null}">
			<div><img src="${resultMap.customerImgFileName}"></div><!-- 프로필사진/임시 -->
				</c:if>
	
		<table>
		<tr>
			<td>Id</td>
			<td>${resultMap.customerId}</td>			
		<tr>
		
		
		<tr>
			<td>Name</td>
			<td>${resultMap.customerName}</td>
		<tr>
		
		<tr>
			<td>Gender</td>
			<td>${resultMap.customerGender}</td>
		<tr>
		
		<tr>
			<td>Phone</td>
			<td>${resultMap.customerPhone}</td>
		<tr>
		
		<tr>
			<td>Height</td>
			<td>${resultMap.customerHeight}</td>
		<tr>
		
		<tr>
			<td>Weight</td>
			<td>${resultMap.customerWeight}</td>
		<tr>
		
		<tr>
			<td>Address</td>
			<td>${resultMap.customerAddress}</td>
		<tr>
		
		<tr>
			<td>Email</td>
			<td>${resultMap.customerEmail}</td>
		<tr>		
	</table>
		<a href="${ctp}/updateCustomerPw">PW 수정</a>
		<a href="${ctp}/updateCustomerOneForPw">정보 수정</a>
</body>
</html>