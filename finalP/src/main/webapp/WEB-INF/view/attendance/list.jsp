<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="출석목록" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
      <h1>출석 조회</h1>
       <el-table :data="attendanceList" style="width: 100%">
            <el-table-column label="번호"type="index" width="60"></el-table-column>
            <el-table-column label="예약번호" prop="programReservationNo"></el-table-column>
            <el-table-column label="출석시간" prop="customerAttendanceEnterTime" :formatter="formatDate"></el-table-column>
        </el-table>

</c:set>

<c:set var="script">
      data() {
          return {
             attendanceList: JSON.parse('${attendanceList}'),
                }
            },
      methods: {
          formatDate(row, column, cellValue) {
	      // 진행일 열은 시간을 표시하지 않고 날짜만 표시
	      if (column.property === 'programDate') {
	                return new Date(cellValue).toLocaleDateString();
	            }
	            return new Date(cellValue).toLocaleString();
        },
               
            },

</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
