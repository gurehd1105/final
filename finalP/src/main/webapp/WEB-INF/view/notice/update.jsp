<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="공지 수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
	action="${ctp}/notice/update" method="post" id="update">
	
	<input :value="notice.noticeNo" name="noticeNo" type="hidden">
	</input>
	
	<el-form-item label="제목">
		<el-input v-model="notice.noticeTitle" name="noticeTitle">
	</el-form-item>
	
	<el-form-item label="문의내용">
		<textarea cols="60" rows="10" name="noticeContent">{{notice.noticeContent}}</textarea>
	</el-form-item>	
	
	<el-button type="primary" @click="submit()">완료</el-button>
	
</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			notice: {
				noticeNo   : ${ notice.noticeNo },
				noticeTitle: '${ notice.noticeTitle }',
				noticeContent: '${ notice.noticeContent }',
			}			
		}
	},
	
	methods: {
		submit() {
			document.getElementById('update').submit();
		},
	},
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>