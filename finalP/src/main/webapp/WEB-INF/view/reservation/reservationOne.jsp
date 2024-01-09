<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="예약 확인" />
<c:set var="body">
    	<el-descriptions :column="2" title="${targetYear}년 ${targetMonth+1}월 ${targetDay}일 예약정보" border>
	      <el-descriptions-item v-for="key of Object.keys(program_reservation)" :label="key" >{{program_reservation[key]}}</el-descriptions-item>
	    </el-descriptions>
    <br>	
    <div>
     	<el-button type="primary" @click="reservation()">예약하기</el-button>
    </div>

</c:set>

<c:set var="script">
	data() {
        return {
            program_reservation: {
                결제날짜: '${reservationList}', 
                지점명: '${reservationList}',
            },
        };
    },
    watch: {
      
    },
    
    methods: {   
    	reservation(){
			location.href = '${contextPath}/insertReservation'    
    }
 
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
