<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- question table-->
	<el-descriptions title="문의 상세" :column="1" border>
		    <el-descriptions-item v-for="key of Object.keys(question)" :label="key">{{ question[key] }}</el-descriptions>
		</eldescriptions>
		<br>
	<textarea readonly  rows="20" cols="170" :class style="resize: none; ">{{ questionContent }}</textarea>
	
</c:set>

<c:set var="script">
	data() {
		return{
			question: {
				questionNo: '${ questionMap.questionNo }',
				customerId: '${ questionMap.customerId }',
				updatedate: '${ questionMap.qUpdatedate }',
				questionTitle: '${ questionMap.questionTitle }', 				
			},
			questionContent: '${ questionMap.questionContent }',
		}
	},
</c:set>
</html>


<%@ include file="/inc/user_layout.jsp" %>