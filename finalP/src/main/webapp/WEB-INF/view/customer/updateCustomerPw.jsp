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
	<div style="display: table;">
		<div style="display: table-row;">
			<div style="display: table-cell;"> Id </div>
			<div style="display: table-cell;">${loginCustomer.customerId}</div>
		</div>
			
		<div style="display: table-row;">
			<div style="display: table-cell;"> <label for="customerPw"> Pw </label> </div>
			<div style="display: table-cell;"><input type="password" id="customerPw" name="customerPw" placeholder="현재 PW"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;"><label for="customerNewPw"> 변경Pw </label></div>
			<div style="display: table-cell;"><input type="password" id="customerNewPw" name="customerNewPw" placeholder="변경 PW"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;"><label for="pwck"> 변경Pw확인 </label></div>
			<div style="display: table-cell;"><input type="password" id="pwck" placeholder="변경 PW 확인"></div>
		</div>
	</div>
		<button type="submit">변경</button>
</form>
</body>
</html>