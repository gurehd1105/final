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
<div style="display: table;">
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerId"> Id </label></div>
		<div style="display: table-cell;"><input type="text" id="customerId" name="customerId" placeholder="ID를 입력하세요"></div>
	</div>
	
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerPw"> Pw </label></div>
		<div style="display: table-cell;"><input type="password" id="customerPw" name="customerPw" placeholder="PW를 입력하세요"></div>
	</div>
	
</div>
	<button type="submit">로그인</button>
	<input type="button" onclick="location='${ctp}/insertCustomer'" value="회원가입">
</form>
</body>
</html>



