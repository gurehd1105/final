<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비발주 리스트" />
<c:set var="description" value="지점에서 현재 발주요청, 처리 된 이력을 알 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,발주" />
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
		<h2>발주 리스트(지점:부산점)</h2>
	</div>
	<br>
	<div>
		<form method="get" action="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch">
	      	<div style="border: 1px solid #ccc;">
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
	    <a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch" style="border: 1px solid #ccc;">전체보기</a>	         
   	</div>
    <div>
   		<table style="border: 1px solid #ccc;">
   			<thead>
   				<tr>
   					<th>발주/페기</th>
   					<th>지점</th>
   					<th>이미지</th>
   					<th>아이템</th>
   					<th>가격</th>
   					<th>수량</th>
   					<th>총가격</th>
   					<th>발주일</th>
   					<th>결재일</th>
   					<th>현재상태</th>
   					<th>취소</th>
   				</tr>
   			</thead>
   			<tbody>
   				<c:forEach var="equipmentOrder" items="${sportsEquipmentOrderList}">
   					<tr>
   						<td>
   							<c:if test="${equipmentOrder.quantity > 0}">
   								발주
   							</c:if>
   							<c:if test="${equipmentOrder.quantity < 0}">
   								폐기
   							</c:if>
   						</td>
   						<td>${equipmentOrder.branchName }</td>
   						<td><img src="${pageContext.request.contextPath}/upload/sportsEquipment/${equipmentOrder.sportsEquipmentImgFileName}" width="50" height="50"></td>
   						<td>${equipmentOrder.itemName }</td>
   						<td>${equipmentOrder.itemPrice }</td>
   						<td>${equipmentOrder.quantity }</td>
   						<td>${equipmentOrder.totalPrice }</td>
   						<td>${equipmentOrder.createdate }</td>
   						<td>
   							<c:if test="${equipmentOrder.updatedate == equipmentOrder.createdate}">
   								대기중
   							</c:if>
   							<c:if test="${equipmentOrder.updatedate != equipmentOrder.createdate}">
   								${equipmentOrder.updatedate }
   							</c:if>
   						</td>
   						<td>${equipmentOrder.orderStatus }</td>
   						<td>
   							<c:if test="${equipmentOrder.orderStatus== '대기' }">
	   						    <form action="${pageContext.request.contextPath}/sportsEquipment/deleteSportsEquipmentOrder" method="post">
	                    			<input type="hidden" name="orderNo" value="${equipmentOrder.orderNo}">
	                    			<input type="hidden" name="orderStatus" value="${equipmentOrder.orderStatus }">
	                    			<button type="submit" style="border: 1px solid #ccc;">취소</button>
	                			</form>
   							</c:if>
   							<c:if test="${equipmentOrder.orderStatus != '대기' }">
   								본사승인완료
   							</c:if>
   						</td>
   					</tr>
   				</c:forEach>
   			</tbody>
   		</table>
   		<!-- 페이징 -->
      	<div style="border: 1px solid #ccc;">
      		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch?currentPage=1&searchItem=${searchItem}&beginDate=${beginDate}&endDate=${endDate}">처음</a>
      			<c:forEach var="p" begin="1" end="${lastPage}">
         			<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch?currentPage=${p}&searchItem=${searchItem}&beginDate=${beginDate}&endDate=${endDate}">${p}</a>
      			</c:forEach>
      		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch?currentPage=${lastPage}&searchItem=${searchItem}&beginDate=${beginDate}&endDate=${endDate}">마지막</a>
    	</div>
   	</div>

</c:set>
<c:set var="script">
	{
		
	
	};
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>