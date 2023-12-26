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
		<form method="get" action="${pageContext.request.contextPath}/insertSportsEquipment" enctype="multipart/form-data">
	      <div>
	         <input type="text" name="searchWord">
	      </div>
	      <div>
	         <button type="submit">검색</button>
	      </div>
   		</form>
   	</div>
   	<div>
   		lastPage : ${lastPage}
   	</div>
   	<div>
   		<c:forEach var="equipment" items="${sportsEquipmentList}">
   			${equipment.itemName }<br>
   			${equipment.itemPrice }<br>
   		</c:forEach>
   	</div>
</body>
</html>