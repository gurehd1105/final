<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="회원탈퇴" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<el-table :data="customerList" style="width: 100%">
		<el-table-column prop="customerNo" label="No" ></el-table-column>
		<el-table-column prop="customerId" label="ID" ></el-table-column>
		<el-table-column prop="customerActive" label="Active" ></el-table-column>
		<el-table-column prop="customerName" label="Name" ></el-table-column>
		<el-table-column prop="customerGender" label="Gender" ></el-table-column>
		<el-table-column prop="customerHeight" label="Height" ></el-table-column>
		<el-table-column prop="customerWeight" label="Weight" ></el-table-column>
		<el-table-column prop="customerPhone" label="Phone" ></el-table-column>
		<el-table-column prop="customerAddress" label="Address" ></el-table-column>
		<el-table-column prop="customerEamil" label="Email" ></el-table-column>
		<el-table-column prop="createdate" label="Createdate" ></el-table-column>
		<el-table-column>
			<el-button type="success" @click="">뭔가를 보기</el-button>
		</el-table-column>
	</el-table>
</c:set>
<c:set var="script">
	data() {
		return {
			customerList: JSON.parse('${customerList}'),
		}
	},
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>