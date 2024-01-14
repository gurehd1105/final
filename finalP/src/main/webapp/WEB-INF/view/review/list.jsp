<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰목록" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

		<el-button type="primary" @click="insert()">리뷰작성</el-button>
	<table>
		<thead style="font-size: 20px;">
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>지점명</th>
				<th>프로그램</th>
				<th>작성일</th>
				<th>수정일</th>
			</tr>
		</thead>
		<tbody v-for="(review,i) in reviewList" :key="i">
			<tr>
				<th>{{ i+1 }}</th>
				<th @click="reviewOne(review.reviewNo)">{{ review.reviewTitle }}</th>
				<th>{{ review.branchName }}</th>
				<th>{{ review.programName }}</th>
				<th>{{ new Date(review.createdate).toLocaleDateString() }}</th>
				<th>{{ review.createdate == review.updatedate ? "-" : new Date(review.updatedate).toLocaleDateString() }}</th>
			</tr>
		</tbody>
	</table>

</c:set>
<c:set var="script">
	data() {
		return {
			reviewList: JSON.parse('${reviewList}'),
		}
	},
	
	methods: {
		insert() {
			location.href='${ctp}/review/insert';
		},
		reviewOne(no){
			location.href='${ctp}/review/reviewOne?reviewNo=' + no;
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>