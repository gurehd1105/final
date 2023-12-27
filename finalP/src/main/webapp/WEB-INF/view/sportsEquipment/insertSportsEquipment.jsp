<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="장비리스트페이지" />
<c:set var="description" value="현재 발주 할 수 있는 장비의 리스트를 확인 할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,발주,폐기" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
<c:set var="body">
	<div>
		<h2>sportsEquipment Insert</h2>
	</div>
   <form method="post" action="${pageContext.request.contextPath}/insertSportsEquipment" enctype="multipart/form-data">
      <div>
         이름 : <input type="text" name="itemName">
      </div>
      <div>
         가격 : <input type="number" name="itemPrice">
      </div>
      <div>
         이미지 : <input type="file" name="sportsEquipmentImg" multiple>
      </div>
      <div>
         <button type="submit">입력</button>
      </div>
   </form>
</c:set>
</body>
</html>

<%@ include file="/inc/admin_header.jsp"%>