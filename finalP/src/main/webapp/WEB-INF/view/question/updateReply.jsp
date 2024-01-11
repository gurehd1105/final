<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="문의상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/question/updateReply" method="post" id="updateReplyAct">
		 <el-form-item label="작성자">
		 	<input readonly v-model="reply.employeeId"> ---> <input readonly v-model="employeeId">
		 </el-form-item>
		 
		 <el-form-item>
		 	<input type="hidden" v-model="employeeNo" name="employeeNo">
		 </el-form-item>
		 
		<el-form-item label="답변 내용">
			<textarea rows="20" cols="170" name="questionReplyContent" style="resize: none;">{{ replyContent }}</textarea>
		</el-form-item>
		
		<el-form-item>
		 	<input type="hidden" v-model="reply.questionNo" name="questionNo">
		 </el-form-item>
		 
		 <el-form-item>
		 	<input type="hidden" v-model="reply.replyNo" name="questionReplyNo">
		 </el-form-item>
		 
		 	<el-button type="primary" @click="updateReply()">완료</el-button>
	</el-form>
		
		<br>
			

</c:set>
<c:set var="script">
	data() {
		return {
			reply: {
				replyNo: '${ replyMap.questionReplyNo }',
				employeeId: '${ replyMap.employeeId }',
				questionNo: '${ replyMap.questionNo }',			
			},
			replyContent: '${ replyMap.questionReplyContent }',
			
			employeeId: '${ loginEmployee.employeeId }',
			employeeNo: '${ loginEmployee.employeeNo }',
		}
	},
	
	methods: {
		updateReply(){
			document.getElementById('updateReplyAct').submit();
		},
	},

</c:set>


<%@ include file="/inc/user_layout.jsp" %>