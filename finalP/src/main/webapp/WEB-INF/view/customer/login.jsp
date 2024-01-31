<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="로그인" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="container min-w-[400px]"
		 action="${ctp}/customer/login" method="post"  id="loginForm">
		 
		 <el-form-item label="아이디">
		 	<el-input v-model="id" name="customerId" placeholder="ID">
		 </el-form-item>
		 
		 <el-form-item label="비밀번호">
		 	<el-input v-model="pw" name="customerPw" type="password" placeholder="PASSWORD">
		 </el-form-item>
		 
		 <el-form-item>
		 	<el-button type="primary" @click="submit()">로그인</el-button>
		 	<el-button type="primary" @click="insertCustomer()">회원가입</el-button>
		 </el-form-item>
    </el-form>
</c:set>

<c:set var="script">
	data() {
		return{
			id:'test',
			pw:'1234',
		}		
	},
	methods: {
			submit(){
				document.getElementById('loginForm').submit();
			},
			
			insertCustomer(){
				location.href='${ctp}/customer/insert';
			},
		},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>