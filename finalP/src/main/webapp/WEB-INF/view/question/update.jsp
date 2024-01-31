<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="문의수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
	action="${ctp}/question/update" method="post" id="update">
	
	<el-form-item label="아이디">
		<el-input readonly v-model="question.customerId">
	</el-form-item>
	
	<el-form-item label="문의번호">
		<el-input readonly v-model="question.questionNo" name="questionNo">
	</el-form-item>
	
	<el-form-item label="제목">
		<el-input v-model="question.questionTitle" name="questionTitle">
	</el-form-item>
	
	<el-form-item label="문의내용">
		<textarea cols="60" rows="10" name="questionContent">{{question.questionContent}}</textarea>
	</el-form-item>	
	
	<el-button type="primary" @click="submit()">완료</el-button>
	
</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			question: {
				questionNo: '${ questionMap.questionNo }',
				questionTitle: '${ questionMap.questionTitle }',
				questionContent: '${ questionMap.questionContent }',
				customerId: '${ questionMap.customerId }',			
			}			
		}
	},
	
	methods: {
		submit() {
			document.getElementById('update').submit();
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>