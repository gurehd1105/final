<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 수정" />
<c:set var="description" value="스포츠 장비의 이름, 가격, 상태를 수정할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,수정" />
<c:set var="body">
	<div>
		<a href="${pageContext.request.contextPath}/sportsEquipment/SportsEquipmentList" style="border: 1px solid #ccc;">장비리스트(지점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" style="border: 1px solid #ccc;">장비리스트 추가(본점)</a>
				<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderList" style="border: 1px solid #ccc;">발주내역(본점)</a>
	</div>
	<div>
		<h2>장비 수정 하기</h2>
	</div>
	<br>
	<form method="post" action="${pageContext.request.contextPath}/sportsEquipment/updateSportsEquipment" >
		<input type="hidden" name="sportsEquipmentNo" value="${sportsEquipmentNo }">
		<div style="border: 1px solid #ccc;">
		    <label for="itemName">이름 :</label>
		    <input type="text" id="itemName" name="itemName" value="${itemName }" >
		</div>
		<div style="border: 1px solid #ccc;">
		    <label for="itemPrice">가격 :</label>
		    <input type="number" id="itemPrice" name="itemPrice" value="${itemPrice }" >
		</div>
		<div style="border: 1px solid #ccc;">
 			<label for="equipmentActive">상태 :</label>
            주문 가능: <input type="radio" id="equipmentActive1" name="equipmentActive" value="Y" ${equipmentActive == 'Y' ? 'checked' : ''}>
			품절: <input type="radio" id="equipmentActive2" name="equipmentActive" value="N" ${equipmentActive == 'N' ? 'checked' : ''}>
		</div>
		<div style="border: 1px solid #ccc;">
		    <label for="employeeId">관리자 :</label>
		    <input type="text" id="employeeId" name="employeeId" value="${employeeId }" readonly="readonly">
		</div>
		<div style="border: 1px solid #ccc;">
		    <label for="equipmentCreatedate">등록일 :</label>
		    <input type="text" id="equipmentCreatedate" name="equipmentCreatedate" value="${equipmentCreatedate }" readonly="readonly" >
		</div>
		<div style="border: 1px solid #ccc;">
		    <label for="equipmentUpdatedate">수정일 :</label>
		    <input type="text" id="equipmentUpdatedate" name="equipmentUpdatedate" value="${equipmentUpdatedate }" readonly="readonly" >
		</div>
      	<div>
         	<button type="submit" style="border: 1px solid #ccc;">수정</button>
      	</div>
   	</form>
	<c:forEach var="img" items="${sportsEquipmentImgList }">
	   	<form method="post" action="${pageContext.request.contextPath}/sportsEquipment/deleteOneSportsEquipmentImg">
			<div style="border: 1px solid #ccc;">
			    <label for="sportsEquipmentImgFileName">이미지 :</label>
				<input type="hidden" id="sportsEquipmentImgNo" name="sportsEquipmentImgNo" value="${img.sportsEquipmentImgNo }" >
				<input type="hidden" id="sportsEquipmentNo" name="sportsEquipmentNo" value="${sportsEquipmentNo }">
				<input type="hidden" id="sportsEquipmentImgFileName" name="sportsEquipmentImgFileName" value="${img.sportsEquipmentImgFileName }">
				<img src="${pageContext.request.contextPath}/upload/sportsEquipment/${img.sportsEquipmentImgFileName }" width="100" height="100">
				<button type="submit" style="border: 1px solid #ccc;">개별삭제</button>
			</div>
		</form>
	</c:forEach>
	<form method="post" action="${pageContext.request.contextPath}/sportsEquipment/insertOneSportsEquipmentImg" enctype="multipart/form-data">
		<div style="border: 1px solid #ccc;">
			<input type="hidden" id="sportsEquipmentNo" name="sportsEquipmentNo" value="${sportsEquipmentNo }" >
		    <label for="sportsEquipmentImg">이미지추가 : </label>
		    <input type="file" id="sportsEquipmentImg" name="sportsEquipmentImg" multiple>
		</div>
		<div>
         	<button type="submit" style="border: 1px solid #ccc;">추가</button>
      </div>
	</form>
   	<div>
   	<br>
</div>
</c:set>
<c:set var="script">

</c:set>

<script>
 //체크박스 하나만 체크되도록하는 스크립트 추가 예정
</script>

<%@ include file="/inc/admin_layout.jsp"%>