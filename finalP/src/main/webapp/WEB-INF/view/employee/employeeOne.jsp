<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="직원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

		<el-descriptions title="직원 정보" :column="1" border>
		    <el-descriptions-item v-for="key of Object.keys(employee)" :label="key">{{ employee[key] }}</el-descriptions>
		</eldescriptions>
		
		<el-button type="primary" @click="updateOne()">정보수정</el-button>
		
		<el-button type="primary" @click="updatePw()">PW변경</el-button>
</c:set>

<c:set var="script">
	data() {
		return {
			employee: {
				소속지점: '${resultMap.branchName}',
				사번: '${resultMap.employeeNo}',
				이름: '${resultMap.employeeName}',
				성별: '${resultMap.employeeGender}',
				연락처: '${resultMap.employeePhone}',
				이메일: '${resultMap.employeeEmail}',
				입사일: '${resultMap.createdate}',
			},
		}
	},
	methods: {
		updateOne(){
			location.href = '${ctp}/employee/updateOneForPw';
		},
		updatePw(){
			location.href = '${ctp}/employee/updatePw';
		}
	}

</c:set>


<%@ include file="/inc/admin_layout.jsp" %>