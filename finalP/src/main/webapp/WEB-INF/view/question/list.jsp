<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="문의목록" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">


	<el-button type="primary" @click="insert()">문의하기</el-button>
	
			<el-table :data="questionList">
			<el-table-column prop="questionList.questionNo" label="문의번호" width="180" />
			<el-table-column prop="questionList.questionTitle" label="작성일" width="180" />		
		    <el-table-column prop="questionList.updatedate" label="제목" />		    
		</el-table>
		
	
	 
		<li v-for="question in questionList">
			<el-div :value="questionNo">{{ question.questionNo }}</el-div>
			<a href="${ctp}/question/questionOne">{{ question.questionTitle }}</a>
			{{ question.updatedate }}
		</li>
  	 

</c:set>

<c:set var="script">
	
	data() {
		return {			
			questionList: JSON.parse('${ questionList }'),
			
		}
	},
	
	methods: {
		insert(){
			location.href = '${ctp}/question/insert';
		},
	},
 
</c:set>
<%@ include file="/inc/user_layout.jsp" %>