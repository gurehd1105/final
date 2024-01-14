<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="공지사항 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
  <div id="app">
    <h1>공지사항 리스트</h1>
    <ul>
      <li v-for="(notice, index) in notices" :key="index">
        <h3>{{ notice.noticeTitle }}</h3>
        <p>{{ notice.noticeContent }}</p>
      </li>
    </ul>
    
    <!-- 페이징 네비게이션 -->
    <div class="pagination">
      <button v-if="pageNum > 0" @click="loadPage(pageNum - 1)">이전</button>
      <span>페이지 {{ pageNum + 1 }}</span>
      <button v-if="nextFlag" @click="loadPage(pageNum + 1)">다음</button>
    </div>
  </div>
</c:set>

<c:set var="script">
  <script>
    var app = new Vue({
      el: '#app',
      data() {
        return {
          notices: JSON.parse('${noticeList}'),
          pageNum: JSON.parse('${pageNum}'),
          nextFlag: JSON.parse('${nextFlag}'),
          totalCount: JSON.parse('${totalCount}')
        };
      },
      methods: {
        loadPage(newPageNum) {
          // 새 페이지 번호로 데이터를 로드하는 로직 구현
          // 예: 서버에 새 페이지 데이터 요청
        }
      }
    });
  </script>
</c:set>

<%@ include file="/inc/admin_layout.jsp"%>
