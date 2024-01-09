<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="예약 확인" />
<c:set var="body">
    <h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일 예약정보</h1>  
    	<el-table :data="tableData" >    	
    		<el-table-column prop="program" label="예약종목"/>
    		<el-table-column prop="selectBranch" label="예약지점"/>
    	</el-table>
    	
    <div>
      <a href="${contextPath}/insertReservation?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${targetDay}">
      	<el-button type="primary">예약하기</el-button>
      </a>
    </div>

</c:set>
	 
<c:set var="script">
	
 
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
