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
	<div style="display: table;">
			
	<div style="display: table-row;">
		<div style="display: table-cell;"> Id </div>	 
		<div style="display: table-cell;"><input type="text" name="customerId"></div>		
	</div>		
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Pw </div>
		<div style="display: table-cell;"><input type="password" name="customerPw"></div>
	</div>	
		
	
	<div style="display: table-row;">
		<div style="display: table-cell;"> Pw Ck </div>
		<div style="display: table-cell;"><input type="password"></div>	
	</div>	
	
	<div style="display: table-row;">
		<div style="display: table-cell;"> Name </div>
		<div style="display: table-cell;"><input type="text" name="customerName"></div>	
	</div>
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Gender </div>
		<div style="display: table-cell;"><select name="customerGender">
			<option selected="selected">선택</option>
			<option value="남">남</option>
			<option value="여">여</option>
		</select></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Phone </div>
		<div style="display: table-cell;"><input type="text" name="customerPhone"></div>	
	</div>
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Height </div>
		<div style="display: table-cell;"><input type="text" name="customerHeight"></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Weight </div>
		<div style="display: table-cell;"><input type="text" name="customerWeight"></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Address </div>
		<div style="display: table-cell;"><input type="text" name="customerAddress"></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Email </div>
		<div style="display: table-cell;">
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
		</div>	
	</div>		
		
	<div style="display: table-row;">
		<div style="display: table-cell;"> Img(선택) </div>
		<div style="display: table-cell;"><input type="file" name="customerImg"></div>	
	</div>	

	<button type="reset">초기화</button>  <button type="submit">회원가입</button>


	</div>
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
