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
	<table>		<!-- question -->
	
		<tr>
			<td>Question No</td>
			<td>${ questionMap.questionNo }</td>
		</tr>
		
		<tr>
			<td>Customer Id</td>
			<td>${ questionMap.customerId }</td>
		</tr>
		
		<tr>
			<td>Date</td>
			<td>${ questionMap.qUpdatedate }</td>
		</tr>
		
		<tr>
			<td>Title</td>
			<td>
				${ questionMap.questionTitle }
			</td>
		</tr>
		
		<tr>
			<td>Content</td>
			<td><textarea readonly="readonly">${ questionMap.questionContent }</textarea></td>
		</tr>
		
	</table>
		<a href="${ctp}/updateQuestion?questionNo=${ questionMap.questionNo }">수정</a>
		<a href="${ctp}/deleteQuestion?questionNo=${ questionMap.questionNo }">삭제</a>

	<br>
	<br>

	<table>		<!-- 관리자 reply -->
	
		<tr>
			<td>Employee</td>
			<td>관리자</td>	
		</tr>
		
		<tr>
			<td>Reply</td>
			<td><textarea readonly="readonly">${ replyMap.questionReplyContent }</textarea></td>	
		</tr>
		
		<tr>
			<td>Date</td>
			<td>${ replyMap. qrCreatedate }</td>	<!-- 관리자 로그인 시에는 ID 조회되도록 고려 중 -->
		</tr>

	</table>
	<!-- <a href="">수정</a> -->
	<a href="${ctp}/deleteQuestionReply?questionReplyNo=${ replyMap.questionReplyNo }&questionNo=${ questionMap.questionNo }">삭제</a>

	<br>
	<br>
	<br>
	
	
<!-- 관리자 insert reply : 관리자 로그인 시 조회되도록 설정 예정 -->
<form action="${ctp}/insertQuestionReply" method="post">
	<table>
		<tr>
			<td>Employee Id(무조건 비공개)</td>
			<td>관리자<%-- <input type="hidden" value="${ loginEmployee.employeeNo }" name="employeeNo"> --%></td>
		</tr>
		
		<tr>
			<td>Content</td>
			<td><textarea name="questionReplyContent"></textarea></td>
		</tr>
	</table>
		<input type="hidden" value="${ questionMap.questionNo }" name="questionNo">
		<button type="submit">등록</button>
</form>

</body>
</html>