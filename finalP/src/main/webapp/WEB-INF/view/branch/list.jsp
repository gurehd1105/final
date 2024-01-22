<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="지점 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
  <div id="app" class="space-y-4">
    <div class="flex flex-row justify-between">
	    <el-text size="large" tag="b">지점 리스트</el-text>
    	
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
   	
	<el-table :data="branchs" border class="w-fit" @row-click="rowClick">
	    <el-table-column prop="branchName" label="지점명"></el-table-column>
	    <el-table-column prop="branchTel" label="연락처" width="180"></el-table-column>
	    <el-table-column prop="branchAddress" label="지점주소" width="180"></el-table-column>
	    <el-table-column prop="createdate" label="개점일" width="180"></el-table-column>
		<el-table-column fixed="right" label="도구" width="220">
		  <template #default="scope">
		    <el-button plain type="primary" v-if="isEmployee" @click="move(scope.row, 'update')" size="small">수정</el-button>
		    <el-button plain type="danger" v-if="isEmployee" @click="remove(scope.row,'delete')" size="small">삭제</el-button>
		  </template>
		</el-table-column>
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
	    branchs: JSON.parse('${branchList}'),
	    options: [
	      { value: 3, label: '3개씩 보기' },
	      { value: 5, label: '5개씩 보기' },
	      { value: 10, label: '10개씩 보기' },
	      { value: 20, label: '20개씩 보기' }
	    ],
	    pageNum: ${page.pageNum},
	    rowPerPage: ${page.rowPerPage},
	    totalCount: ${page.totalCount},
	    totalPage: ${page.totalPage},
	    isEmployee: <%= session.getAttribute("loginEmployee") != null %>
	  };
	},
	methods: {
	  loadPage() {
	    const param = new URLSearchParams();
	    param.set('pageNum', this.pageNum);
	    param.set('rowPerPage', this.rowPerPage);
	
	    location.href = '/branch/list?' + param.toString();
	  },
	  remove(row,path) {
	    if (confirm("정말 삭제하시겠습니까?")) {
	      // 사용자가 "예"를 누르면 삭제 작업을 진행
	 	  	location.href = ['/branch', path, row.branchNo].join('/');
	    } else {
	      // 사용자가 "아니요"를 누르면 삭제 작업 취소
	      console.log("삭제가 취소되었습니다.");
	    }
	  },
	  rowClick(row) {
	    console.log('rowClick >>> ', row);
	  },
	  move(row, path) {
	    if (row.branchNo != null) {
	      location.href = ['/branch', path, row.branchNo].join('/');
	    }
	  }
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