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
<form action="${ctp}/insertQuestion" method="post">
	<div style="display: table;">
		<div style="display: table-row;">
			<div style="display: table-cell;">Customer Id</div>
			<div style="display: table-cell;">
				<input type="hidden" name="customerNo" value="${loginCustomer.customerNo}">
				${loginCustomer.customerId}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;"><label for="questionTitle">Title</label></div>
			<div style="display: table-cell;"><input type="text" id="questionTitle" name="questionTitle" placeholder="제목"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;"><label for="questionContent">Content</label></div>
			<div style="display: table-cell;"><textarea id="questionContent" name="questionContent" placeholder="문의내용"></textarea></div>
		</div>
	</div>
		<button type="submit">등록</button>
</form>
</body>
</html>