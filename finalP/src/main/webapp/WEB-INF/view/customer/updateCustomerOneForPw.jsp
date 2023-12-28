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
<h1>PW 확인</h1>
<form action="${ctp}/updateCustomerOneForm" method="post">
	<div>Pw : <input type="password" name="customerPw"></div>
		<button type="submit">확인</button>
</form>
</body>
</html>