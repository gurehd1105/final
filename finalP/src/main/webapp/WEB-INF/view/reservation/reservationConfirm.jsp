<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="예약 확인" />
<c:set var="body">
    <div>
	    <h2>${param.targetYear}년 ${param.targetMonth}월 ${param.targetDay}일" 예약이 완료되었습니다!</h2>
	    <p>지점: ${selectBranch}</p>
  	</div>
</c:set>
<c:set var="script"></c:set>
<%@ include file="/inc/admin_layout.jsp" %>
