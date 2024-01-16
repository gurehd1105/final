<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 카카오 주소API CDN -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	
				<c:if test="${resultMap.customerImgOriginName != null}">
		  <div>
  <div class="demo-image__preview">
  <strong style="margin-bottom: 40%">회원 상세</strong>
    <el-image
      style="width: 300px; height: 200px; margin-left: 40%;"
      :src="url"
      :zoom-rate="1.2"
      :max-scale="7"
      :min-scale="0.2"
      :preview-src-list="srcList"
      :initial-index="4"
      fit="cover"
    />
  </div>
  </div>
				</c:if>	
		
		
		<el-descriptions :column="1" border>
		    <el-descriptions-item v-for="key of Object.keys(customer)" :label="key">{{ customer[key] }}</el-descriptions>
		</eldescriptions>
		
		<el-button type="primary" @click="updateOne()">정보수정</el-button>
		
		<el-button type="primary" @click="updatePw()">PW변경</el-button>
</c:set>

<c:set var="script">
	data() {
		return {
			customer: {
				아이디: '${resultMap.customerId}',
				이름: '${resultMap.customerName}',
				성별: '${resultMap.customerGender}',
				키: '${resultMap.customerHeight}',
				몸무게: '${resultMap.customerWeight}',
				전화번호: '${resultMap.customerPhone}',
				주소: '${resultMap.customerAddress}',
				이메일: '${resultMap.customerEmail}',		
			},
				url: '${ctp}${resultMap.customerImgOriginName}',
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