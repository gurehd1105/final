<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="멤버십 추가" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">	
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/customer/insert" method="post" enctype="multipart/form-data" id="insertForm">
	<el-form-item label="ㅋㅋ">
		<el-input readonly value=""/>
	</el-form-item>


	</el-form>
</c:set>
<c:set var="script">	
	data() {
		employee: {
			employeeNo: '${ loginEmployee.employeeNo }',
			employeeId: '${ loginEmployee.employeeId }',
		},
	},
	
	methods: {
	
	},
</c:set>