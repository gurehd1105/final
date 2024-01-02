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
	
		<div style="display: table;">
		<div style="display: table-row;">
			<div style="display: table-cell;">Id</div>
			<div style="display: table-cell;">${resultMap.customerId}</div>			
		</div>
		
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Name</div>
			<div style="display: table-cell;">${resultMap.customerName}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Gender</div>
			<div style="display: table-cell;">${resultMap.customerGender}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Phone</div>
			<div style="display: table-cell;">${resultMap.customerPhone}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Height</div>
			<div style="display: table-cell;">${resultMap.customerHeight}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Weight</div>
			<div style="display: table-cell;">${resultMap.customerWeight}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Address</div>
			<div style="display: table-cell;">${resultMap.customerAddress}</div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Email</div>
			<div style="display: table-cell;">${resultMap.customerEmail}</div>
		</div>		
	</div>
		<a href="${ctp}/updateCustomerPw">PW 수정</a>
		<a href="${ctp}/updateCustomerOneForPw">정보 수정</a>
</body>
</html>