<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="메인페이지" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />

<c:set var="body">
	<el-calendar v-model="date" />
</c:set>

<c:set var="script">
	data() {
	    return {
	    	date: new Date()
	    }
	},
	watch: {
		date(newValue, prevValue) {
			this.log(newValue, prevValue);
		}
	},
	methods: {
		log(args) {
			console.log(args);
		}
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>