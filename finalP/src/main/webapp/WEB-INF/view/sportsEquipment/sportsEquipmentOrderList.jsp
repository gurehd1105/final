<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비발주 리스트" />
<c:set var="description" value="현재 발주요청, 처리 된 이력을 알 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,발주" />
<c:set var="body">
	<div>
		<a href="${pageContext.request.contextPath}/sportsEquipment/SportsEquipmentList" style="border: 1px solid #ccc;">장비리스트(지점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment" style="border: 1px solid #ccc;">장비리스트 추가(본점)</a>
		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderList" style="border: 1px solid #ccc;">발주내역(본점)</a>
	</div>
	
	<div>
		<h2>발주 리스트(본점)</h2>
	</div>
	<br>
	<div>
		<form method="get" action="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderList">
	      	<div style="border: 1px solid #ccc;">
	 			<label for="searchBranch">지점검색 :</label>
	         	<input type="text" id="searchBranch" name="searchBranch" value="${searchBranch}" placeholder="지점을 입력하세요">
	 			<label for="searchItem">아이템검색 :</label>
	         	<input type="text" id="searchItem" name="searchItem" value="${searchItem}" placeholder="아이템을 입력하세요">
	      	</div>
	      	<div>
	      		<label for="beginDate">검색할 시작일</label>
	         	<input type="date" id="beginDate" name="beginDate" value="${beginDate}" >
	      		<label for="endDate">검색할 마지막일</label>
	         	<input type="date" id="endDate" name="endDate" value="${endDate}" >
	      	</div>
	         	<button type="submit" style="border: 1px solid #ccc;">검색</button>
   		</form>
	    <a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderList" style="border: 1px solid #ccc;">전체보기</a>	         
   	</div>
    <div>
   		<table style="border: 1px solid #ccc;">
   			<thead>
   				<tr>
   					<th>발주번호</th>
   					<th>지점</th>
   					<th>아이템</th>
   					<th>가격</th>
   					<th>수량</th>
   					<th>총가격</th>
   					<th>발주일</th>
   					<th>현재상태</th>
   					<th>이미지</th>
   				</tr>
   			</thead>
   			<tbody>
   				<c:forEach var="equipmentOrder" items="${sportsEquipmentOrderList}">
   					<tr>
   						<td>${equipmentOrder.orderNo }</td>
   						<td>${equipmentOrder.branchName }</td>
   						<td>${equipmentOrder.itemName }</td>
   						<td>${equipmentOrder.itemPrice }</td>
   						<td>${equipmentOrder.quantity }</td>
   						<td>${equipmentOrder.totalPrice }</td>
   						<td>${equipmentOrder.createdate }</td>
   						<td>${equipmentOrder.orderStatus }</td>
   						<td><img src="${pageContext.request.contextPath}/upload/sportsEquipment/${equipmentOrder.sportsEquipmentImgFileName}" width="50" height="50"></td>
   					</tr>
   				</c:forEach>
   			</tbody>
   		</table>
   	</div>

</c:set>
<c:set var="script">
	{
		
	
	};
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>