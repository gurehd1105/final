<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
      <h1>출석 조회</h1>
        <table>
        <tr>
            <th>번호</th>
            <th>예약번호</th>            
            <th>출석시간</th>        
        </tr>
        
        <tbody v-for="(attendance, a) in  attendanceList" :key="a">
            <tr>
                <th>{{a+1}}</th>
                <th>{{attendance.programReservationNo}}</th>        
                <th>{{attendance.customerAttendanceEnterTime == null ? "미출석" 
                    : new Date(attendance.customerAttendanceEnterTime).toLocaleDateString() + " "
                      + new Date(attendance.customerAttendanceEnterTime).toLocaleTimeString()}}
                </th>
 
                
            </tr>            
        </tbody>            
    </table>

</c:set>

<c:set var="script">
      data() {
                return {
                    attendanceList: JSON.parse('${attendanceList}'),
                };
            },
            methods: {
               
            },

</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
