<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰입력" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/review/insert" method="post" id="insert">
		
		<el-form-item label="작성자">
			<el-input v-model="customerId" readonly/>				
		</el-form-item>
		
		<el-form-item>
			<el-input type="hidden" name="customerAttendanceNo" v-model="customerAttendanceNo"/>
		</el-form-item>
		
		<el-form-item label="제목">
			<el-input v-model="reviewTitle" name="reviewTitle"/>
		</el-form-item>
			<br>
		<el-form-item label="문의내용">
			<textarea cols="50" rows="15" style="resize: none;" v-model="reviewContent" name="reviewContent"></textarea>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="submit()">등록</el-button>
		</el-form-item>
		
</el-form>
	
</c:set>

<c:set var="script">
	data() {
		return {
			customerId: '${ loginCustomer.customerId }',
			reviewTitle: '',
			reviewContent: '',
			customerAttendanceNo: '',
		}
	},
	
	methods: {
		submit() {
			document.getElementById('insert').submit();
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>