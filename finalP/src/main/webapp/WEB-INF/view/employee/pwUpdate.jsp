<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="PW수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

	<el-form action="${ctp}/employee/pwUpdate" ref="form" id="pwUpdate"
		label-position="right" label-width="150px" status-icon method="post"> 
	
		<el-form-item label="아이디">
			<el-input readonly name="employeeId" v-model="id"/>
		</el-form-item>
		
		<el-form-item label="현재PW">
			<el-input type="password" name="employeePw" v-model="pw" placeholder="PASSWORD"/>
		</el-form-item>
		
		<el-form-item label="변경PW">
			<el-input type="password" name="employeeNewPw" v-model="newPw" placeholder="NEW PASSWORD"/>
		</el-form-item>
		
		<el-form-item label="변경PW 확인">
			<el-input type="password" v-model="newPwCk" placeholder="NEW PASSWORD CHECK"/>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="validateAndSubmit()">완료</el-button>
		</el-form-item>
		
</el-form>

</c:set>


<c:set var="script">
	data() {
		return {
			id: '${loginEmployee.employeeId}',
			pw: '',
			newPw: '',
			newPwCk: '',			
		}
	},
	methods: {
		validateAndSubmit(){
			if (this.newPw !== this.newPwCk) {
				alert("입력한 새로운 암호와 암호 확인이 일치하지 않습니다. 다시 확인해주세요.");
			} else {
				document.getElementById('pwUpdate').submit();
			}
		},
	},
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>