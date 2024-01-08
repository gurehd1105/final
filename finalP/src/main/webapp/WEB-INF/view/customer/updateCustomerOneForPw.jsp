<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="PW확인" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

	<el-form action="${ctp}/updateCustomerOneForm" ref="form" id="updateForm"
		label-position="right" label-width="150px" status-icon method="post"> 
		
	<el-form-item label="아이디">
		<el-input readonly v-model="id" /> 
	</el-form-item> 
	
	<el-form-item label="비밀번호">
		<el-input type="password" name="customerPw" v-model="pw"/> 
	</el-form-item> 
	
	<el-form-item>
		<el-button type="primary" @click="submit(form)">확인</el-button> 
	</el-form-item> 
	
	</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			id: '${loginCustomer.customerId}',
			pw: '',
		}
	},
	methods: {
		submit(){
			document.getElementById('updateForm').submit();
		},
	},
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>