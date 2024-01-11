<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
	<h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일</h1>
    <el-select v-model="selectBranch" clearable placeholder="지점 선택">
        <el-option v-for="(branch, b) in branchList" :key="b" 
        		   :label="branch.branchName" :value="branch.branchNo" >          
        </el-option> 
    </el-select>
    <br>
    <el-button type="primary" @click="insert">예약하기</el-button>
</c:set>

<c:set var="script">
    data() {
    	return{
    		selectBranch:'',
    		branchList:JSON.parse('${branchList}'),
    	}
 
    },
    methods: {
        insert() {
        	location.href = '${ctp}/reservationList';
         		
        }
    },
</c:set>

<%@ include file="/inc/user_layout.jsp"%>
