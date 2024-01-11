<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>


<c:set var="body">
	<h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일 예약</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>결제일</th>		
			<th>지점명</th>		
		</tr>
		<tbody v-for="(reservation, r) in  reservationList" :key="r">
			<tr>
			<th>{{reservation.programReservationNo}}</th>
			<th>{{new Date(reservation.paymentDate).toLocaleString()}}</th>		
			<th>{{reservation.branchName}}</th>		
			</tr>			
		</tbody>		    
    </table>
	<br>
	<el-button type="primary" @click="reservation()">예약하기</el-button>
</c:set>

<c:set var="script">
    data() {
		return {			
			reservationList: JSON.parse('${reservationList}'),
			
		}
	},
	
	methods: {
        reservation() {        
            location.href = '${ctp}/insertReservation';
        }
    },
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
