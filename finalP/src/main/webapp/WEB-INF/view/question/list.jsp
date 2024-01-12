<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="문의작성" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">


	<el-button type="primary" @click="insert()">문의하기</el-button>

		
		<table>
			<thead>
				<tr>
					<th>문의번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>수정일</th>
				</tr>
			</thead>
							
			<tbody v-for="(question, i) in questionList" :key="i">
				<tr>
					<th>{{ question.questionNo }}</th>
					<th @click="questionOne(question.questionNo)">{{ question.questionTitle }}</th>
					<th>{{ question.createdate }}</th>
					<th>{{ question.createdate == question.updatedate ? "-" : question.updatedate}}</th>
				</tr>
			</tbody>
		</table>
		
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
		questionOne(no){
			location.href = '${ctp}/question/questionOne?questionNo=' + no;
		},
	},
 
</c:set>
<%@ include file="/inc/user_layout.jsp" %>