<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="body">
  <el-table
    :data="branches"
    stripe
    border
  >
    <el-table-column prop="name" label="지점명" />
    <el-table-column prop="branchTel" label="전화번호" />
    <el-table-column prop="branchAddress" label="주소" />
  </el-table>
</c:set>

<c:set var="script">
      data() {
        return {
          branches: [],
        };
      },
      created() {
        axios.get('${contextPath}/branches')
          .then(response => {
            this.branches = response.data;
          })
          .catch(error => {
            console.error(error);
          });
      },
      methods: {      
      },


</c:set>
<%@ include file="/inc/user_layout.jsp" %>
