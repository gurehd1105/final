<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/review/updateReply"
			method="post" id="updateReplyAct"> 
			<el-form-item column="2">
				<input type="hidden" name="employeeNo" v-model="employeeNo" />
			</el-form-item>	
			
			<el-form-item label="작성자" column="2">
				<input readonly type="text" v-model="reply.답변자" />				
				-> &nbsp;<input readonly type="text" v-model="employeeId" /> 				
			</el-form-item>				
			
			<el-form-item column="2">
				<input type="hidden" name="reviewReplyNo" v-model="reply.no" />
				
				<input type="hidden" name="reviewNo" v-model="reply.reviewNo" />
			</el-form-item>				
			
			<el-form-item label="답변">
				<textarea rows="10" cols="160" style="resize: none;" v-model="replyContent" name="reviewReplyContent"></textarea>
			</el-form-item>
				<el-button type="primary" @click="updateReply()">등록</el-button>
		</el-form>	

</c:set>
<c:set var="script">
	data() {
			return {				
				reply: {
					no :  '${ replyMap.reviewReplyNo }',
					reviewNo :  '${ replyMap.reviewNo }',
					소속지점 : '${ replyMap.branchName }',
					답변자 : '${ replyMap.employeeId }',
					작성일 : '${ replyMap.createdate }',
					수정일 : '${ replyMap.updatedate }',
					
				},
				
					// 표로 작성하지 않을 부분 / 따로 바인딩
				replyContent : '${ replyMap.reviewReplyContent }',
				employeeNo : '1',
				employeeId : '${ loginEmployee.employeeId }',
				
			}
		},
		
	methods: {
		updateReply(){
			document.getElementById('updateReplyAct').submit();
		},
	},
</c:set>

<%@ include file="/inc/user_layout.jsp" %>