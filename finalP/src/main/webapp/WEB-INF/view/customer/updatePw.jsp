<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="PW수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

	<el-form action="${ctp}/customer/updatePw" ref="form" id="updatePw"
		label-position="right" label-width="150px" status-icon method="post"> 
	
		<el-form-item label="아이디">
			<el-input readonly name="customerId" v-model="id"/>
		</el-form-item>
		
		<el-form-item label="현재PW">
			<el-input type="password" name="customerPw" v-model="pw" placeholder="PASSWORD"/>
		</el-form-item>
		
		<el-form-item label="변경PW">
			<el-input type="password" name="customerNewPw" v-model="newPw" placeholder="NEW PASSWORD"/>
		</el-form-item>
		
		<el-form-item label="변경PW 확인">
			<el-input type="password" v-model="newPwCk" placeholder="NEW PASSWORD CHECK"/>
		</el-form-item>
		
		<el-form-item>
			<el-input type="hidden" v-model="no" name="customerNo"/>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="submit()">완료</el-button>
		</el-form-item>
		
</el-form>

</c:set>


<c:set var="script">
	data() {
		return {
			no: '${loginCustomer.customerNo}',
			id: '${loginCustomer.customerId}',
			pw: '',
			newPw: '',
			newPwCk: '',			
		}
	},
	methods: {
		submit(){
			document.getElementById('updatePw').submit();
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>