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
		<div style="display: table-cell;"><label for="customerId"> Id 확인 </label></div>	 
		<div style="display: table-cell;">
			<input type="text" id="idCk">&nbsp;<button type="button" id="idCkBtn">중복확인</button>
			<span id="idCkMsg"></span>
		</div>		
	</div>		
	
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerPw"> Id </label></div>
		<div style="display: table-cell;"><input type="text" id="customerId" name="customerId" readonly="readonly"></div>
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerPw"> Pw </label></div>
		<div style="display: table-cell;"><input type="password" id="customerPw" name="customerPw"></div>
	</div>	
		
	
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="pwCk"> Pw Ck </label></div>
		<div style="display: table-cell;"><input type="password" id="pwCk" ></div>	
	</div>	
	
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerName"> Name </label></div>
		<div style="display: table-cell;"><input type="text" id="customerName" name="customerName"></div>	
	</div>
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerGender"> Gender </label></div>
		<div style="display: table-cell;">
			<input type="radio" value="남" name="customerGender">남
			<input type="radio"	value="여" name="customerGender">여
		</div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerPhone"> Phone </label></div>
		<div style="display: table-cell;"><input type="text" id="customerPhone" name="customerPhone"></div>	
	</div>
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerHeight"> Height </label></div>
		<div style="display: table-cell;"><input type="text" id="customerHeight" name="customerHeight"></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerWeight"> Weight </label></div>
		<div style="display: table-cell;"><input type="text" id="customerWeight" name="customerWeight"></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerAddress"> Address </label></div>
		<div style="display: table-cell;"><input type="text" id="customerAddress" name="customerAddress"></div>	
	</div>	
		
	<div style="display: table-row;">
		<div style="display: table-cell;"><label for="customerEmail"> Email </label></div>
		<div style="display: table-cell;">
			<input type="text" name="customerEmailId" id="customerEmail" >@
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
		<div style="display: table-cell;"><label for="customerImg"> Img(선택) </label></div>
		<div style="display: table-cell;"><input type="file" id="customerImg" name="customerImg"></div>	
	</div>	

	<button type="reset">초기화</button>  <button type="submit">회원가입</button>


	</div>
</form>
</body>
<script>

	$('#idCkBtn').click(function() {
		if($('#idCk').val().length < 4){
			console.log($('#idCk').val());
			$('#idCkMsg').html('4글자 이상 입력해주세요');
		} else {
		$.ajax({
			url: '${ctp}/idCheck',
			type: 'get',
			data: {
				customerId: $('#idCk').val()
			},
			success: function(result) {
				if(result == 1){
					$('#idCkMsg').html('중복 ID입니다');
					$('#customerId').val('');
				} else {
					$('#idCkMsg').html('사용 가능한 ID입니다.');
					$('#customerId').val($('#idCk').val());
				}
			},
				error: function(){
					alert("요청실패");
				}
			})
		}
	});

	 $('#autoJuso').click(function() {
			if($('#autoJuso').val() == ""){
				$("#selfJuso").attr("disabled",false); 
			} else {
				$("#selfJuso").val("");
				$("#selfJuso").attr("disabled",true); 
			}
		 });
 
</script>
</html>
