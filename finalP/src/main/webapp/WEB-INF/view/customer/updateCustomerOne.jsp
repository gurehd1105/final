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
		
			<div style="display: table;">
		<div style="display: table-row;">
			<div style="display: table-cell;">Id</div>
			<div style="display: table-cell;"><input type="text" value="${resultMap.customerId}" name="customerId" readonly></div>			
		</div>
		
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Name</div>
			<div style="display: table-cell;"><input type="text" value="${resultMap.customerName}" name="customerName"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Gender</div>
			<div style="display: table-cell;"><select name="customerGender">
				<option selected="selected" value="${resultMap.customerGender}">${resultMap.customerGender}</option>
				<option value="${resultMap.customerOtherGender}">${resultMap.customerOtherGender}</option>
			</select></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Phone</div>
			<div style="display: table-cell;"><input type="text" value="${resultMap.customerPhone}" name="customerPhone"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Height</div>
			<div style="display: table-cell;"><input type="text" value="${resultMap.customerHeight}" name="customerHeight"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Weight</div>
			<div style="display: table-cell;"><input type="text" value="${resultMap.customerWeight}" name="customerWeight"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Address</div>
			<div style="display: table-cell;"><input type="text" value="${resultMap.customerAddress}" name="customerAddress"></div>
		</div>
		
		<div style="display: table-row;">
			<div style="display: table-cell;">Email</div>
			<div style="display: table-cell;">
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
			</div>
		</div>		
	</div>
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