<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="예약 확인" />
<c:set var="body">
    <div>
      <el-button type="primary" @click="goReservationApp">예약하기</el-button>
    </div>
</c:set>

<c:set var="script">

      methods: {
        goReservationApp() {
          window.location.href = "${contextPath}/insertReservation";
        },
      }

 
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
