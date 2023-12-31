<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="PW수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/insertQuestion" method="post" id="insert">
		
		<el-form-item label="작성자" rowspan="2">
			<el-input v-model="customerId" readonly/>			
		</el-form-item>
		
		<el-form-item label="제목">
			<el-input type="text" v-model="customerNo" name="customerNo"/>
		</el-form-item>
		
		<el-form-item label="제목">
			<el-input v-model="questionTitle" name="questionTitle"/>
		</el-form-item>
		
		<el-form-item label="문의내용">
			<textarea v-model="questionContent" name="questionContent"></textarea>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="submit(form)">등록</el-button>
		</el-form-item>
		
</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			customerNo: '${ loginCustomer.customerNo }',
			customerId: '${ loginCustomer.customerId }',
			questionTitle: '',
			questionContent: '',			
		}
	},
	
	methods: {
		submit(){
			document.getElementById('insert').submit();
		}
	},
</c:set>	
		
<%@ include file="/inc/admin_layout.jsp" %>