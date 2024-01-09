<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
	action="${ctp}/question/update" method="post" id="update">
	<el-form-item label="아이디">
		<el-input readonly v-model="customerId">
	</el-form-item>
		<table>				
			
			<tr>
				<td>Customer Id</td>
				<td>${ questionMap.customerId }</td>
			</tr>
			
			<tr>
				<td><label for="questionTitle">Title</label></td>
				<td><input type="text" value="${ questionMap.questionTitle }" id="questionTitle" name="questionTitle"></td>
			</tr>
			
			<tr>
				<td><label for="questionContent">Content</label></td>
				<td><textarea id="questionContent" name="questionContent">${ questionMap.questionContent }</textarea></td>
			</tr>
			
		</table>
				<input type="hidden" value="${ questionMap.questionNo }" name="questionNo">
	<button type="submit">수정</button>
</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			customerId: ${ loginCustomer.customerId },
			
		}
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>