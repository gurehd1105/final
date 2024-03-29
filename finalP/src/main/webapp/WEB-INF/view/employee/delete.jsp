<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원탈퇴" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/employee/delete" method="post" id="delete">
			 <el-form-item label="사번">
				<el-input v-model="no" name="employeeNo" type="hidden" />			
			</el-form-item> 
			<el-form-item label="아이디">
				<el-input v-model="id" name="employeeId" readonly />			
			</el-form-item> 
			
			<el-form-item label="비밀번호">
				<el-input type="password" v-model="pw" name="employeePw" placeholder="PASSWORD" />			
			</el-form-item> 
			
			<el-form-item label="비밀번호 확인">
				<el-input type="password" v-model="pwCk" placeholder="PASSWORD CHECK"  />			
			</el-form-item> 
			
			<el-form-item>
				<el-button type="primary" @click="submit()">삭제</el-button>
			</el-form-item> 
			 
			 
	</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			no: '${ loginEmployee.employeeNo }',
			id: '${ loginEmployee.employeeId }',
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
<%@ include file="/inc/admin_layout.jsp" %>