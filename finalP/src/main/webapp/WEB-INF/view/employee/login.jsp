<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="직원 로그인" />
<c:set var="description" value="헬스장 직원 로그인을 위한 페이지" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />

<c:set var="body">
	<el-form :model="formData" label-width="80px" id="loginForm">
	    <el-form-item label="ID">
	        <el-input v-model="formData.employeeId" placeholder="Enter ID" ></el-input>
	    </el-form-item>
	    <el-form-item label="Password">
	        <el-input v-model="formData.employeePw" placeholder="Enter Password" type="password"></el-input>
	    </el-form-item>
	    
	    <el-form-item>
      		<el-button type="primary" @click="submit">로그인</el-button>
	    </el-form-item>
	</el-form>
</c:set>

<c:set var="script">
	data() {
	    return {
	        formData: {
	        	employeeId: 'admin',
	        	employeePw: '1234'
	        }
	    };
	},
	methods: {
		submit() {
			const data = this.formData;
			axios.post('', { ...data } ).then((res) => {
				const isSuccess = res.data === 'success';
				if (isSuccess) {
					location.href = '/employee/mypage';
				} else {
					alert('로그인 실패');
				}
			});
		}
	}
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>