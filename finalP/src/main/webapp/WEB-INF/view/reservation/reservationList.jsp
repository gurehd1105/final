<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
    <h1>${targetYear}년 ${targetMonth}월 ${targetDay}일 예약</h1>
    <table>
        <tr>
            <th>NO</th>
            <th>결제일</th>        
            <th>지점명</th> 
            <th>프로그램</th>
            <th>예약번호</th> 
            <th>삭제</th>        
        </tr>
        
        <tbody v-for="(reservation, r) in  reservationList" :key="r">
            <tr>
                <th>{{r+1}}</th>
                <th>{{new Date(reservation.paymentDate).toLocaleDateString()}}</th>        
                <th>{{reservation.branchName}}</th>
                <th>{{reservation.programName}}</th>
                <th>{{reservation.programReservationNo}}</th>    
                <th><el-button type="danger" @click="deleteReservation(reservation.programReservationNo,targetYear,targetMonth,targetDay)">삭제</el-button></th>  
            </tr>            
        </tbody>            
    </table>
    <br>
    <el-button type="primary" @click="reservation()">예약하기</el-button>
</c:set>

<c:set var="script">
    data() {
        return {        
        	programList: JSON.parse('${programList}'),   
            reservationList: JSON.parse('${reservationList}'),   
            targetYear:'${targetYear}', 
            targetMonth:'${targetMonth}', 
            targetDay:'${targetDay}',  
        };
      
    },
    
    methods: {
        reservation() {      
            location.href = '${ctp}/insertReservation?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${targetDay}';
        },
        
        deleteReservation(no,targetYear,targetMonth,targetDay ) {
                if (confirm("이 예약을 삭제하시겠습니까?")) {
				location.href = '${ctp}/deleteReservationList?programReservationNo=' + no + '&targetYear=' + ${targetYear} + '&targetMonth=' + ${targetMonth} + '&targetDay=' + ${targetDay};
            }
        }
    },
</c:set>

<%@ include file="/inc/user_layout.jsp" %>
