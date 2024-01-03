<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 상세보기 및 발주" />
<c:set var="description" value="스포츠 장비의 이름, 가격, 상태를 확인하고 수량을 선택해 발주 할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,상세보기,발주" />
<c:set var="body">
	<div>
		<a href="${pageContext.request.contextPath}/sportsEquipment/SportsEquipmentList" style="border: 1px solid #ccc;">장비리스트(지점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" style="border: 1px solid #ccc;">장비리스트 추가(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch" style="border: 1px solid #ccc;">발주내역(지점:부산점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByHead" style="border: 1px solid #ccc;">발주내역(본점)</a>
	</div>
	<div>
		<h2>장비 발주 하기</h2>
	</div>
	<br>
	<form method="post" action="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipmentOrder" >
		<input type="hidden" name="sportsEquipmentNo" value="${sportsEquipmentNo }">
		<div style="border: 1px solid #ccc;">
		    이름 : ${itemName }
		</div>
		<div style="border: 1px solid #ccc;">
		    가격 :
		    <input type="number" name="itemPrice" value="${itemPrice }">
		</div>
		<div style="border: 1px solid #ccc;">
				상태 :
				<c:if test="${equipmentActive == 'Y'}">주문가능</c:if>
				<c:if test="${equipmentActive == 'N'}">품절</c:if>
		</div>
		<div style="border: 1px solid #ccc;">
			관리자 :${employeeId }
		</div>
		<div style="border: 1px solid #ccc;">
				등록일 : ${equipmentCreatedate }
		</div>
		<div style="border: 1px solid #ccc;">
				수정일 :${equipmentUpdatedate }
		</div>
			<div style="border: 1px solid #ccc;">
			<label for="sportsEquipmentImgFileName">이미지 :</label>
				<c:forEach var="img" items="${sportsEquipmentImgList }">
					<img src="${pageContext.request.contextPath}/upload/sportsEquipment/${img.sportsEquipmentImgFileName }" width="100" height="100">
				</c:forEach>
			</div>
      	<c:if test="${equipmentActive == 'Y'}"> <!-- 품절이 아닐 경우만 발주 버튼 출력 -->
      		<div style="border: 1px solid #ccc;">
      			<label>수량을 선택하세요:  <input type="number" name="quantity" min="1" max="10" value="1"> </label>
         		<button type="submit" style="border: 1px solid #ccc;"> 주문</button>
      		</div>
        </c:if>
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