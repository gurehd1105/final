<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="직원 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
  <div id="app" class="space-y-4">
    <div class="flex flex-row justify-between">
	    <el-text size="large" tag="b">직원 목록</el-text>
    	
	    <el-select
		  v-model="rowPerPage"
		  class="m-2"
		  placeholder="페이지당 개수"
		  size="large"
		  style="width: 240px"
		>
		  <el-option
		    v-for="item in options"
		    :key="item.value"
		    :label="item.label"
		    :value="item.value"
		  />
		</el-select>
    </div>
   	
	<el-table :data="employees" border class="w-fit" @row-click="rowClick">
	    <el-table-column prop="employeeNo" label="사번"></el-table-column>
	    <el-table-column prop="employeeId" label="ID"></el-table-column>
	    <el-table-column prop="employeeName" label="이름" width="180"></el-table-column>
	    <el-table-column prop="employeePhone" label="연락처" width="180"></el-table-column>
	    <el-table-column prop="employeeGender" label="성별" width="180"></el-table-column>
	    <el-table-column prop="employeeEmail" label="이메일" width="180"></el-table-column>
	    <el-table-column prop="branchName" label="소속지점" width="180"></el-table-column>
	    <el-table-column prop="createdate" label="입사일" width="180"></el-table-column>
	</el-table>
    
	
    <!-- 페이징 네비게이션 -->
    <div class="flex justify-center">
      <el-pagination layout="prev, pager, next" 
      	:page-size="rowPerPage" 
		v-model:current-page="pageNum" 
		:total="totalCount"
		@change="loadPage" />
    </div>
  </div>
</c:set>

<c:set var="script">
    data() {
      return {
        employees: JSON.parse('${employeeList}'),
        options: [
        	{ value: 3, label: '3개씩 보기' },
        	{ value: 5, label: '5개씩 보기' },
        	{ value: 10, label: '10개씩 보기' },
        	{ value: 20, label: '20개씩 보기' },
        ],
        pageNum: ${page.pageNum},
        rowPerPage: ${page.rowPerPage },
        totalCount: ${page.totalCount},
        totalPage: ${page.totalPage },
        isEmployee: <%= session.getAttribute("loginEmployee") != null %>,
      };
    },
    methods: {

    }
</c:set>

<%-- Check if loginEmployee session attribute exists --%>
<%
    Object loginEmployee = session.getAttribute("loginEmployee");
    if (loginEmployee != null) {
%>
    <%@ include file="/inc/admin_layout.jsp" %>
<%
    } else {
%>
    <%@ include file="/inc/user_layout.jsp" %>
<%
    }
%>
