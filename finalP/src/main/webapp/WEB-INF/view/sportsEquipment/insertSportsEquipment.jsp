<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 관리 및 리스트" />
<c:set var="description" value="현재 발주 할 수 있는 장비를 추가할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,수정,삭제,추가" />
<c:set var="body">
	<div>
		<a href="${pageContext.request.contextPath}/sportsEquipment/SportsEquipmentList" style="border: 1px solid #ccc;">장비리스트(지점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" style="border: 1px solid #ccc;">장비리스트 추가(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch" style="border: 1px solid #ccc;">발주내역(지점:부산점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByHead" style="border: 1px solid #ccc;">발주내역(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead" style="border: 1px solid #ccc;">재고(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByBranch" style="border: 1px solid #ccc;">재고(지점:부산점)</a>
	</div>
	<div>
		<h2>장비 추가 하기</h2>
	</div>
	<br>
	<form method="post" action="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" enctype="multipart/form-data">
		<div style="border: 1px solid #ccc;">
		    <label for="itemName">이름 :</label>
		    <input type="text" id="itemName" name="itemName" placeholder="이름을 입력하세요.">
		</div>
		<div style="border: 1px solid #ccc;">
		    <label for="itemPrice">가격 :</label>
		    <input type="number" id="itemPrice" name="itemPrice" placeholder="가격을 입력하세요.">
		</div>
		<div style="border: 1px solid #ccc;">
		    <label for="sportsEquipmentImg">이미지 :</label>
		    <input type="file" id="sportsEquipmentImg" name="sportsEquipmentImg" multiple>
		</div>
      <div>
         <button type="submit" style="border: 1px solid #ccc;">입력</button>
      </div>
   	</form>
   	<div>
   	<br>
	<div>
		<h2>장비 리스트</h2>
	</div>
	<br>
	<div>
		<form method="get" action="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" enctype="multipart/form-data">
			<div style="border: 1px solid #ccc;">
    			<label for="equipmentActive">상태 :</label>
    			주문 가능: <input type="radio" id="equipmentActive1" name="equipmentActive" value="Y"
           		<c:if test="${equipmentActive == 'Y'}">checked</c:if>>
    			품절: <input type="radio" id="equipmentActive2" name="equipmentActive" value="N"
           		<c:if test="${equipmentActive == 'N'}">checked</c:if>>
			</div>
	      	<div style="border: 1px solid #ccc;">
	 			<label for="searchWord">검색 :</label>
	         	<input type="text" id="searchWord" name="searchWord" value="${searchword}" placeholder="검색어를 입력하세요.">
	         	<button type="submit" style="border: 1px solid #ccc;">검색</button>
	         	<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment?searchWord=" style="border: 1px solid #ccc;">전체보기</a>	         
	      	</div>
   		</form>
   	</div>
    <div>
   		<c:forEach var="equipment" items="${sportsEquipmentList}">
   			<div style="border: 1px solid #ccc;">
   				이름 : ${equipment.itemName }<br>
   				가격 : ${equipment.itemPrice }<br>
   				상태 : <c:if test="${equipment.equipmentActive == 'Y' }"> 재고있음</c:if>
   					  <c:if test="${equipment.equipmentActive == 'N' }"> 품절</c:if><br>
   				이미지<br>
   				<img src="${pageContext.request.contextPath}/upload/sportsEquipment/${equipment.sportsEquipmentImgFileName }" width="100" height="100"><br>
   				<a href="${pageContext.request.contextPath}/sportsEquipment/updateSportsEquipment?sportsEquipmentNo=${equipment.sportsEquipmentNo}">수정</a>
   			
   			</div>
   		</c:forEach>
   	</div>

   	<!-- 페이징 -->
   	<div style="border: 1px solid #ccc;">
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment?currentPage=1&searchWord=${searchWord}&equipmentActive=${equipmentActive}">처음</a>
		<c:forEach var="p" begin="1" end="${lastPage}">
			<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment?currentPage=${p}&searchWord=${searchWord}&equipmentActive=${equipmentActive}">${p}</a>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment?currentPage=${lastPage}&searchWord=${searchWord}&equipmentActive=${equipmentActive}">마지막</a>
    </div>
</div>
</c:set>
<c:set var="script">
	{

	
	};
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>