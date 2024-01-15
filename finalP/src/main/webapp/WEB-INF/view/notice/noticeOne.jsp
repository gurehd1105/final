<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="공지 상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

		<el-descriptions title="공지사항 상세보기" :column="1" border>
		    <el-descriptions-item v-for="key of Object.keys(notice)" :label="key">{{ notice[key] }}</el-descriptions>
		</el-descriptions>
</c:set>

<c:set var="script">
	data() {
		return {
			notice: {
				공지등록번호: '${noticeOne.noticeNo}',
				작성자: '${noticeOne.employeeName}',
				제목: '${noticeOne.noticeTitle}',
				내용: '${noticeOne.noticeContent}',
				공지등록일: '${noticeOne.createdate}',
			},
		}
	},
	methods: {
	}

</c:set>


<%@ include file="/inc/admin_layout.jsp" %>