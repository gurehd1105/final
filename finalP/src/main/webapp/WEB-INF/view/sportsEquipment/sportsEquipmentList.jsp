<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>sportsEquipment List</h2>
	</div>
	<div>
		<form method="get" action="${pageContext.request.contextPath}/SportsEquipmentList" enctype="multipart/form-data">
	      <div>
	         <input type="text" name="searchWord">
	      </div>
	      <div>
	         <button type="submit">검색</button>
	         <a href="${pageContext.request.contextPath}/SportsEquipmentList?searchWord=">전체보기</a>
	      </div>
   		</form>
   	</div>
   	<div>
   		lastPage : ${lastPage}
   	</div>
   	<div>
   		<c:forEach var="equipment" items="${sportsEquipmentList}">
   			이름 : ${equipment.itemName }<br>
   			가격 : ${equipment.itemPrice }<br>
   			관리자 : ${equipment.employeeId }<br>
   			등록일 : ${equipment.createdate }<br>
   			이미지<br>
   			<img src="${pageContext.request.contextPath}/upload/sportsEquipment/${equipment.sportsEquipmentImgFileName }" width="100" height="100"><br>
   		</c:forEach>
   	</div>
   	<!-- 페이징 -->
   	<div>
		<a href="${pageContext.request.contextPath}/SportsEquipmentList?currentPage=1&searchWord=${searchWord}">처음</a>
		<c:forEach var="p" begin="1" end="${lastPage}">
			<a href="${pageContext.request.contextPath}/SportsEquipmentList?currentPage=${p}&searchWord=${searchWord}">${p}</a>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/SportsEquipmentList?currentPage=${lastPage}&searchWord=${searchWord}">마지막</a>
    </div>
</body>
</html>