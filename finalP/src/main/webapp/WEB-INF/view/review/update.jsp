<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/review/update" method="post" id="update">
		
		<el-form-item label="작성자">
			<el-input v-model="review.아이디" readonly/>				
		</el-form-item>
		
		<el-form-item>
			<el-input type="hidden" v-model="reviewNo" name="reviewNo"/>
		</el-form-item>
		
		<el-form-item label="제목">
			<el-input v-model="review.제목" name="reviewTitle"/>
		</el-form-item>
			<br>
		<el-form-item label="문의내용">
			<textarea cols="50" rows="15" style="resize: none;" v-model="reviewContent" name="reviewContent"></textarea>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="submit()">등록</el-button>
		</el-form-item>
		
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
				// 표로 작성하지 않을 부분 / 따로 바인딩
			reviewNo: '${ reviewMap.reviewNo }',				
			reviewContent : '${ reviewMap.reviewContent }',	
		}
	},
	
	methods: {
		submit(){
			document.getElementById('update').submit();
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>