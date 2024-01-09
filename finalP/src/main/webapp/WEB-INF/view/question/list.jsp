<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">


	<el-button type="primary" @click="insert()">문의하기</el-button>
	
		
		<li v-for="item in items">
			{{ question.questionNo }}
		</li>
  	

</c:set>

<c:set var="script">
	data() {
		return {
			questionList: [
				{ questionNo: '${ questionList.questionNo }',				
				{ questionTitle: '${ questionList.questionTitle }',
				{ updatedate: '${ questionList.updatedate }',
			],
		}
	},
	
	methods: {
		insert(){
			location.href = '${ctp}/question/insert';
		},
	},

</c:set>
<%@ include file="/inc/user_layout.jsp" %>