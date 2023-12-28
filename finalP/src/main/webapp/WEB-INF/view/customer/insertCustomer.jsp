<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
			<td><select name="customerGender">
				<option selected="selected">선택</option>
				<option value="남">남</option>
				<option value="여">여</option>
			</select></td>
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
			<td>	
					<input type="text" name="customerEmailId">@
					<input type="text" name="customerEmailJuso" id="selfJuso">
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
		
		<tr>
			<td>Img</td>
			<td><input type="file" name="customerImg"></td>
		<tr>
	</table>
	<button type="reset">초기화</button>
	<button type="submit">회원가입</button>
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
