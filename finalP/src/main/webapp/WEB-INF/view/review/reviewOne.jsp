<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<el-descriptions title="" :column="2" border>
		<el-descriptions-item v-for="key of Object.keys(review)" :label="key">{{ review[key] }}</el-descriptions-item>
	</el-descriptions>
		<br>		
	<el-button type="primary" @click="updateForm()">수정</el-button>
	<el-button type="primary" @click="deleteForm()" id="deleteBtn">삭제</el-button>
		<br>

		
	<span id="deleteForm" style="display:none; "> 
			<!-- 삭제 시 고객PW 입력 form / 삭제버튼 클릭 시 보여지도록 구성 -->
		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/question/delete"
			method="post" id="deleteAct"> 
			
			<el-form-item>
				<input type="hidden" name="customerId" v-model="review.customerId">
			</el-form-item> 
			
			<el-form-item> 
				<input type="hidden" name="questionNo"	v-model="customerNo"> 
			</el-form-item> 
			
			<el-form-item label="PW">
				<input type="password" name="customerPw" placeholder="PW 입력" /> 
				<el-button type="primary" @click="deleteAct()">삭제</el-button>
		 </el-form-item> 
			
		</el-form>
	</span>
	<textarea readonly rows="10" cols="160" style="resize: none;"> {{ reviewContent }}</textarea>
	
	<c:if test="${ replyMap != null }"><!-- 답변 있을 시 답변표시 -->
		<el-descriptions title="리뷰 답변" column="2" border>
			<el-descriptions-item v-for:="key of Object.keys(reply)" :label="key">{{ reply[key] }}</el-descriptions-item>
		</el-descriptions>
			<el-button type="primary" @click="updateReply(reviewNo)">수정</el-button>
			<el-button type="primary" @click="deleteReply(reviewNo)">삭제</el-button>
		<textarea readonly rows="10" cols="160" style="resize: none;">{{ replyContent }}</textarea>
	
	
	</c:if>
	
	<c:if test="${ replyMap == null }">
		<p>답변이 등록되지 않았습니다. 조금만 기다려주세요.</p>
	</c:if>
	
	
	<!-- 답변 없을 시 입력Form 표시 -> 직원이 소속한, 본인 지점에 대한 리뷰에서만 조회 예정 -->
		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/review/insertReply"
			method="post" id="insertReplyAct"> 
			<el-form-item column="2">
				<input type="hidden" name="reviewNo" v-model="reviewNo" />
				<input type="hidden" name="employeeNo" v-model="employeeNo" />
			</el-form-item>	
			
			<el-form-item label="작성자">
				<input readonly  v-model="employeeId" />
			</el-form-item>	
			
			<el-form-item label="답변">
				<textarea rows="10" cols="160" style="resize: none;" name="reviewReplyContent"></textarea>
			</el-form-item>
				<el-button type="primary" @click="insertReply()">등록</el-button>
		</el-form>	
	
	
	
</c:set>

<c:set var="script">
	

	data() {
		return {
			review: {
				고객번호 : '${ reviewMap.customerNo }',
				아이디 : '${ reviewMap.customerId }',
				제목 : '${ reviewMap.reviewTitle }',
				방문일자 : '${ reviewMap.customerAttendanceDate }',
				지점번호 : '${ reviewMap.branchNo }',
				지점명 : '${ reviewMap.branchName }',				
				작성일 : '${ reviewMap.createdate }',
				수정일: '${ reviewMap.updatedate }',			
			},					
			reply: {
				소속지점 : '${ replyMap.branchName }',
				답변자 : '${ replyMap.employeeId }',
				작성일 : '${ replyMap.createdate }',
				수정일 : '${ replyMap.updatedate }',
			},
			
				<!-- 표로 작성하지 않을 부분 / 따로 바인딩 -->	
			reviewNo: '${ reviewMap.reviewNo }',				
			reviewContent : '${ reviewMap.reviewContent }',
			replyContent : '${ replyMap.reviewReplyContent }',
			employeeNo : '${ loginEmployee.employeeNo }',
			employeeId : '${ loginEmployee.employeeId }',
			
		}
	},
	
	methods: {
		updateForm(){
			location.href = '${ctp}/review/update?reviewNo=${ reviewMap.reviewNo }';
		},
		deleteForm(){
			document.getElementById('deleteForm').style.display = "flex";
			document.getElementById('deleteBtn').style.display = "none";
		},		
		deleteAct(){
			document.getElementById('deleteAct').submit();
		},
		insertReply(){
			document.getElementById('insertReplyAct').submit();
		},
		updateReply(no){
			location.href = "${ctp}/review/updateReply?reviewNo=" + no;
		},
		deleteReply(no){
			location.href = "${ctp}/review/deleteReply?reviewNo=" + no;
		},
	},
	
</c:set>

<%@ include file="/inc/user_layout.jsp" %>