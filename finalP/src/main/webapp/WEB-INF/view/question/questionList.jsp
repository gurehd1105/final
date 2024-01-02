<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Question List</h1>
	
	<a href="${ctp}/insertQuestion">문의하기</a>
		
	<div style="display: table;">
		
			<div style="display: table-row;">
				<div style="display: table-cell;">No</div>&nbsp;&nbsp;
				<div style="display: table-cell;">Title</div>&nbsp;&nbsp;
				<div style="display: table-cell;">Date</div>
			</div>
		
	<c:forEach var="q" items="${ questionList }">
		
			<div style="display: table-row;">
				<div style="display: table-cell;">${ q.questionNo }</div>&nbsp;&nbsp;
				<div style="display: table-cell;">
					<a href="${ctp}/questionOne?questionNo=${ q.questionNo }">${ q.questionTitle }</a>
				</div>&nbsp;&nbsp;
				<div style="display: table-cell;">${ q.updatedate }</div>
			</div>
		
	</c:forEach>
	</div>
	
	<!-- 페이징 -->
	<c:if test="${ totalRow > rowPerPage }"> <!-- ( 전체 게시글 수 > 한 페이지 조회 개수 ) 일 때만 페이징버튼 조회 -->
		<c:if test="${ currentPage!= 1 }">
	<a href="${ctp}/questionList?currentPage=1">맨앞</a>
	<a href="${ctp}/questionList?currentPage=${currentPage-1}">이전</a>
		</c:if>
		<c:if test="${ currentPage!= lastPage }">
	<a href="${ctp}/questionList?currentPage=${currentPage+1}">다음</a>
	<a href="${ctp}/questionList?currentPage=${lastPage}">맨뒤</a>
		</c:if>
	</c:if>
</body>
</html>