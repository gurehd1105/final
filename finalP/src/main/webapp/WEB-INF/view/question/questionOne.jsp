<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="문의상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">


		<el-descriptions title="문의 상세" :column="1" border> 
		    <el-descriptions-item v-for="key of Object.keys(question)" :label="key">{{ question[key] }}</el-descriptions>
		</eldescriptions>
			<br>
		
		<el-button type="primary" @click="update(question)">수정</el-button>
		<el-button type="primary" @click="deleteForm()" id="deleteBtn">삭제</el-button>
			<span id="deleteForm" style="display:none; ">
			
			<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/question/delete" method="post" id="deleteAct">	
		 	<el-form-item>
				<input type="hidden" name="customerId" v-model="question.customerId">
			</el-form-item>
			
			<el-form-item>
				<input type="hidden" name="questionNo" v-model="question.questionNo">
			</el-form-item>
			
			<el-form-item label="PW">
				<input type="password" name="customerPw" placeholder="PW 입력"/><el-button type="primary" @click="deleteAct()">삭제</el-button>
			</el-form-item>
			
				
			</el-form>
			
			</span>
			<br>
			<br>
			
			<br>
			<br>
	<textarea readonly  rows="20" cols="170" style="resize: none; ">{{ questionContent }}</textarea>
		<br>
		
		<!-- 답변 내용 -->
	<c:if test="${ replyMap != null }">
		<el-descriptions title="문의 답변" :column="1" border> 
		    <el-descriptions-item v-for="key of Object.keys(reply)" :label="key">{{ reply[key] }}</el-descriptions>
		    	<br>
		    <el-button type="primary" @click="updateReply()">수정</el-button>
			<el-button type="primary" @click="deleteReply()">삭제</el-button>
		</eldescriptions>
	</c:if>
	
	
		<!-- 답변 없을 시 표시 -->
	<c:if test="${ replyMap == null }">
		<p>답변이 등록되지 않았습니다. 조금만 기다려주세요.</p>
	</c:if>
	
	<c:if test="${ replyMap == null  }">
		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/question/insertReply" method="post" id="insertReplyAct">
	    <el-form-item label="답변자">
	        <el-input v-model="employee.employeeId" readonly />
	    </el-form-item>
	    
	    <el-form-item>
	        <el-input type="hidden" name="employeeNo" v-model="employee.employeeNo" />
	    </el-form-item>
	    
	    <el-form-item label="답변내용">
	        <el-input name="questionReplyContent" v-model="employee.replyContent" />
	    </el-form-item>
	    
	    <el-form-item>
	        <el-input type="hidden" name="questionNo" v-model="employee.questionNo" />
	    </el-form-item>
	    
	    	<el-button type="primary" @click="insertReply()">등록</el-button>
	</c:if>
</c:set>

<c:set var="script">
	
	data() {
		return{
			question: {
				questionNo: '${ questionMap.questionNo }',
				customerId: '${ questionMap.customerId }',
				updatedate: '${ questionMap.updatedate }',
				questionTitle: '${ questionMap.questionTitle }', 				
			},
			questionContent: '${ questionMap.questionContent }',
			
			reply: {
				replyNo: '${ replyMap.questionReplyNo }' ,
				employeeId: '${ replyMap.employeeId }' ,
				replyContent: '${ replyMap.questionReplyContent }',
				updatedate:  '${ replyMap.updatedate }',
				
			},
			employee: {
				employeeNo: '${ loginEmployee.employeeNo }',
				employeeId: '${ loginEmployee.employeeId }',
				replyContent: '',
				questionNo: '${ questionMap.questionNo }',
			},
			
			
			
		}
	},
	
	methods: {
		update(){
			location.href = '${ctp}/question/update?questionNo=${ questionMap.questionNo }';
		},	
		updateReply(){
			location.href = '${ctp}/question/updateReply?questionNo=${ questionMap.questionNo }';
		},
		insertReply(){
			document.getElementById('insertReplyAct').submit();
		},
		
		deleteReply(){
			location.href = '${ctp}/question/deleteReply?questionNo=${ replyMap.questionNo }';
		},
		
		deleteForm(){
			document.getElementById('deleteForm').style.display = "flex";
			document.getElementById('deleteBtn').style.display = "none";
		},
		
		deleteAct(){
			document.getElementById('deleteAct').submit();			
		},
	},
	
</c:set>



<%@ include file="/inc/user_layout.jsp" %>