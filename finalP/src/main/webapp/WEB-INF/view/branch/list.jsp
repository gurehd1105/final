<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="지점 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<c:set var="body">
<style>
.container {
  width: 800px;
  margin: 0 auto;
}
</style>
<template>
  <div class="container">
    <h1>지점 목록</h1>
	   <el-table
	  :data="branchList"
	  style="width: 100%">
	  <el-table-column
	    prop="branchName"
	    label="지점명" />
	  <el-table-column
	    prop="branchAddress"
	    label="주소" />
	  <el-table-column
	    prop="branchTel"
	    label="전화번호" />
	</el-table>

    <el-pagination
      :current-page="currentPage"
      :total="totalCount"
      @current-change="handleCurrentChange"
      layout="prev, pager, next" />
  </div>
  console.log(branchList);
</template>
</c:set>
<c:set var="script">
		export default {
  data() {
    return {
      branchList: JSON.parse(branchList),
      currentPage: 1,
      totalCount: 0,
    };
  },
  mounted() {
    this.totalCount = this.branchList.length;
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
  },
};
	
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>