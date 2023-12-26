<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>