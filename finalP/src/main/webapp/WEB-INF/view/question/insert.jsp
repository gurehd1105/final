<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="문의작성" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/question/insert" method="post" id="insert">
		
		<el-form-item label="작성자">
			<el-input v-model="customerId" readonly/>				
		</el-form-item>
		
		<el-form-item>
			<el-input type="hidden" name="customerNo" v-model="customerNo"/>		
		</el-form-item>
		
		<el-form-item label="제목">
			<el-input v-model="questionTitle" name="questionTitle"/>
		</el-form-item>
			<br>
		<el-form-item label="문의내용">
			<textarea cols="50" rows="15" style="resize: none;" v-model="questionContent" name="questionContent"></textarea>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="submit()">등록</el-button>
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
			if(this.questionTitle.length < 5 || this.questionContent.length < 10){
				alert('제목 및 내용은 각 5자 , 10자 이상 입력해주세요.');
			} else {
				document.getElementById('insert').submit();
			}
		}
	},
</c:set>	
		
<%@ include file="/inc/user_layout.jsp" %>