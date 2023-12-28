<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
			<td><select name="customerGender">
				<option selected="selected" value="${resultMap.customerGender}">${resultMap.customerGender}</option>
				<option value="${resultMap.customerOtherGender}">${resultMap.customerOtherGender}</option>
			</select></td> 
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
			<td>
					<input type="text" value="${resultMap.emailId}" name="customerEmailId">@
					<input type="text" value="${resultMap.emailJuso}" name="customerEmailJuso" id="selfJuso">
				<select id="autoJuso" name="customerEmailAutoJuso">
					<option selected="selected" value="">직접 입력</option>
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="nate.com">nate.com</option>
					<option value="kakao.com">kakao.com</option>
					<option value="icloud.com">icloud.com</option>
				</select>
			</td>
		<tr>		
	</table>
	<button type="submit">저장</button>
</form>
</body>
<script>

	 $('#autoJuso').click(function() {
		if($('#autoJuso').val() == ""){
			$("#selfJuso").removeAttr("disabled"); 
		} else {
			$("#selfJuso").attr("disabled",true); 
		}
	 });
 
</script>
</html>