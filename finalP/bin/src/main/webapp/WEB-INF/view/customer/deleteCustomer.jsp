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
	<form action="${ctp}/deleteCustomer" method="post">
		<div style="display: table;">
			<div style="display: table-row;">
				<div style="display: table-cell;"> Id </div>
				<div style="display: table-cell;">
					<input type="hidden" value="${loginCustomer.customerNo}" name="customerNo">
					<input type="text" readonly value="${loginCustomer.customerId}" name="customerId">
				</div>
			</div>
			
			<div style="display: table-row;">
				<div style="display: table-cell;"> Pw </div>
				<div style="display: table-cell;"><input type="password" name="customerPw"></div>
			</div>
			
			<div style="display: table-row;">
				<div style="display: table-cell;"> Pw Ck </div>
				<div style="display: table-cell;"><input type="password"></div>
			</div>
			
			
		</div>
			<button type="submit">탈퇴</button>
	</form>
</body>
</html>