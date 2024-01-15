<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="멤버십 수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">	

	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/membership/update" method="post" enctype="multipart/form-data" id="updateForm">
	<el-form-item label="작업자">
		<el-input readonly v-model="employee.employeeId"/>		
	</el-form-item>
	
	<el-form-item label="상품명">
		<el-input readonly name="membershipName" :value="membership.membershipMonth +'개월'"/>
	</el-form-item>
	
	<el-form-item label="이용기간(개월 수)">
		<el-input-number v-model="membership.membershipMonth" name="membershipMonth" min="1"/>
	</el-form-item>
	
	<el-form-item label="가격">
		<el-input-number :step="1000" v-model="membership.membershipPrice" name="membershipPrice" min="10000"/>
	</el-form-item>
	
	<el-form-item>
		<el-input type="hidden" v-model="employee.employeeNo" name="employeeNo"/>
	</el-form-item>
	
	<el-form-item>
		<el-input type="hidden" v-model="membership.membershipNo" name="membershipNo"/>
	</el-form-item>
	
	
		<el-button type="primary" @click="submit()">완료</el-button>
	</el-form>


</c:set>
<c:set var="script">
	data(){
		return {
			membership: JSON.parse('${membership}'),	
			employee: {
				employeeNo: '${ loginEmployee.employeeNo }',
				employeeId: '${ loginEmployee.employeeId }',
			},
		}
	},
	
	methods: {
		submit(){
			document.getElementById('updateForm').submit();
		},
	},
</c:set>

<%@ include file="/inc/user_layout.jsp" %>