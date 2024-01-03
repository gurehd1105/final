<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 지점별 재고 리스트" />
<c:set var="description" value="현재 지점별 소유하고 있는 물품의 재고를 확인 할 수 있는 페이지" />
<c:set var="keywords" value="장비,소모품,재고" />
<c:set var="body">
	<div>
		<a href="${pageContext.request.contextPath}/sportsEquipment/SportsEquipmentList" style="border: 1px solid #ccc;">장비리스트(지점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" style="border: 1px solid #ccc;">장비리스트 추가(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch" style="border: 1px solid #ccc;">발주내역(지점:부산점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByHead" style="border: 1px solid #ccc;">발주내역(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead" style="border: 1px solid #ccc;">재고(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByBranch" style="border: 1px solid #ccc;">재고(지점:부산점)</a>	
	</div>
	<br>
   	<div>
   	<br>
	<div>
		<h2>재고목록(본사)</h2>
	</div>
	<br>
	<div>
		<form method="get" action="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead">
	      	<div style="border: 1px solid #ccc;">
	 			<label for="searchBranch">지점검색 :</label>
	         	<input type="text" id="searchBranch" name="searchBranch" value="${searchBranch}" placeholder="지점을 입력하세요">
	 			<label for="searchItem">아이템검색 :</label>
	         	<input type="text" id="searchItem" name="searchItem" value="${searchItem}" placeholder="아이템을 입력하세요">
	      	</div>
	        <button type="submit" style="border: 1px solid #ccc;">검색</button>
   		</form>
	    <a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead" style="border: 1px solid #ccc;">전체보기</a>	         
   	</div>
    <div>
   		<c:forEach var="inventory" items="${sportsEquipmentInventory}">
   			<div style="border: 1px solid #ccc;">
   				지점 : ${inventory.branchName }<br>
   				이름 : ${inventory.itemName }<br>
   				재고 : ${inventory.totalQuantity }<br>
   				발주 : ${inventory.inventoryQuantity }<br>
   				폐기 : ${inventory.discartdQuantity }<br>
   			</div>
   		</c:forEach>
   	</div>

   	<!-- 페이징 -->
   	<div style="border: 1px solid #ccc;">
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead?currentPage=1&searchBranch=${searchBranch}&searchItem=${searchItem}">처음</a>
		<c:forEach var="p" begin="1" end="${lastPage}">
			<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead?currentPage=${p}&ssearchBranch=${searchBranch}&searchItem=${searchItem}">${p}</a>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead?currentPage=${lastPage}&searchBranch=${searchBranch}&searchItem=${searchItem}">마지막</a>
    </div>
</div>
</c:set>
<c:set var="script">
	{

	
	};
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>