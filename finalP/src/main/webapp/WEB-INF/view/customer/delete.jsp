<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원탈퇴" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/customer/delete" method="post" id="delete">
			<el-form-item label="아이디">
				<el-input v-model="id" name="customerId" readonly />			
			</el-form-item> 
			
			<el-form-item label="비밀번호">
				<el-input type="password" v-model="pw" name="customerPw" placeholder="PASSWORD" />			
			</el-form-item> 
			
			<el-form-item label="비밀번호 확인">
				<el-input type="password" v-model="pwCk" placeholder="PASSWORD CHECK"  />			
			</el-form-item> 
			
			<el-form-item>
				<el-input type="hidden" :value="no" v-model="id" name="customerNo" />			
			</el-form-item> 
			
			<el-form-item>
				<el-button type="primary" @click="submit()">탈퇴</el-button>
			</el-form-item> 
			 
			 
	</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			no: '${ loginCustomer.customerNo }',
			id: '${ loginCustomer.customerId }',
			pw: '',
			pwCk: '',
		}
	},
	methods: {
		submit(){
			document.getElementById('delete').submit();
		},
	}

</c:set>
<%@ include file="/inc/user_layout.jsp" %>