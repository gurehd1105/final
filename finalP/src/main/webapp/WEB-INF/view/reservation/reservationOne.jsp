<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

    <el-descriptions title="${targetYear}년 ${targetMonth+1}월 ${targetDay}일" :column="1" border>
        <el-descriptions-item v-for="key of Object.keys(reservation)" :label="key">{{ reservation[key] }}</el-descriptions-item>
    </el-descriptions>

    <el-button type="primary" @click="reservation()">예약하기</el-button>

</c:set>

<c:set var="script">
    data() {
        return {
            customer: {
                payDate: '${reservationList.paymentDate}',
                branchName: '${reservationList.branchName}',
            },
        };
    },
    methods: {
        reservation() {
            location.href = '${ctp}/insertReservation';
        }
    }
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
