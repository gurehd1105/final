<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	
				<c:if test="${resultMap.customerImgFileName != null}">
			<div><img src="${ctp}/upload/customer/${ resultMap.customerImgFileName }"></div><!-- 프로필사진/임시 -->
				</c:if>	
		
		<el-descriptions title="회원 정보" :column="1" border>
		    <el-descriptions-item v-for="key of Object.keys(customer)" :label="key">{{ customer[key] }}</el-descriptions>
		</eldescriptions>
		
		<el-button type="primary" @click="updateOne()">정보수정</el-button>
		
		<el-button type="primary" @click="updatePw()">PW변경</el-button>
</c:set>

<c:set var="script">
	data() {
		return {
			customer: {
				id: '${resultMap.customerId}',
				name: '${resultMap.customerName}',
				gender: '${resultMap.customerGender}',
				heitht: '${resultMap.customerHeight}',
				weight: '${resultMap.customerWeight}',
				phone: '${resultMap.customerPhone}',
				address: '${resultMap.customerAddress}',
				email: '${resultMap.customerEmail}',
			},
		}
	},
	methods: {
		updateOne(){
			location.href = '${ctp}/customer/updateOneForPw';
		},
		updatePw(){
			location.href = '${ctp}/customer/updatePw';
		}
	}

</c:set>


<%@ include file="/inc/user_layout.jsp" %>