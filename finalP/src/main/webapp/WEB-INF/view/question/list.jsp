<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="문의작성" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">


	<el-button type="primary" @click="insert()">문의하기</el-button>

		
		
	<el-table :data="questionList" class="w-fit" @row-click="rowClick">
   		<el-table-column prop="questionNo" label="No" ></el-table-column>
	    <el-table-column prop="questionTitle" label="제목" ></el-table-column>
	    <el-table-column prop="customerId" label="작성자" ></el-table-column>
  </el-table>
		<!-- 페이징 네비게이션 -->
    <div class="flex justify-center">
      <el-pagination layout="prev, pager, next" 
      	:page-size="rowPerPage" 
		v-model:current-page="pageNum" 
		:total="totalCount"
		@change="loadPage" />
    </div>
</c:set>

<c:set var="script">
	
	data() {
		return {			
			questionList: JSON.parse('${ questionList }'),
			pageNum: ${page.pageNum},
			rowPerPage: ${page.rowPerPage },
			totalCount: ${page.totalCount},
			totalPage: ${page.totalPage },
		}
	},
	
	methods: {
		insert(){
			location.href = '${ctp}/question/insert';
		},
		questionOne(no){
			location.href = '${ctp}/question/questionOne?questionNo=' + no;
		},
		
		loadPage(pageNum) {
      	const param = new URLSearchParams();
      	param.set('pageNum', this.pageNum);
      	param.set('rowPerPage', this.rowPerPage);
      	
		location.href = '/question/list?' + param.toString();
      },
      
      
	},
 
</c:set>
<%@ include file="/inc/user_layout.jsp" %>