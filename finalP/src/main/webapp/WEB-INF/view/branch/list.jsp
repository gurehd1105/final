<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="지점 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />

<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
		<div id="app">
  <h1>지점 목록</h1>
  <ul>
    <li v-for="branch in branchs" :key="branch">
      <h3>{{ branch.branchName}}</h3>
      <p>{{ branch.branchTEl }}</p>
    </li>
  </ul>
</div>
</c:set>

<c:set var="script">

        const app = Vue.crveateApp({});
        
        app.use(ElementPlus);

        app.mount("#app");
</c:set>

<%@ include file="/inc/admin_layout.jsp"%>