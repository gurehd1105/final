<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<!-- 주소 kakao API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
			<div style="display: table-cell;">
				<input type="text" id="sample6_postcode" placeholder="우편번호" readonly="readonly">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" readonly="readonly"><br>
				<input type="text" id="sample6_address" placeholder="주소" readonly="readonly"  name="address1"><br>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소"  name="address2">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목" readonly="readonly"  name="address3">
			</div>
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
		
		<div style="display: table-row;">
			<div style="display: table-cell;"></div>
			
			<div style="display: table-cell;">
				<button type="reset">초기화</button>&nbsp;<button type="submit">가입하기</button>
			</div>
		</div>
	
	</div>
	
</form>
</body>
<script>
	/* 
	
		customerId 제외 , 전체 유효성검사 필요
	
	*/
	

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
					alert("페이지 오류");
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
 
	 
	 // 주소 API 설정
	  function sample6_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("sample6_extraAddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("sample6_extraAddress").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample6_postcode').value = data.zonecode;
	                document.getElementById("sample6_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample6_detailAddress").focus();
	            }
	        }).open();
	    }
</script>
</html>
