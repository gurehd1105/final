<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="문의내용" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">

<p style="text-align: center; font-size: 30px;">문의내용</p>
	<el-button type="primary" @click="insert()">문의하기</el-button>

	<el-table :data="questionList" @row-click="rowClick"> 
		<el-table-column prop="questionNo" label="No"></el-table-column> 
		<el-table-column prop="questionTitle" label="제목"></el-table-column> 
		<el-table-column prop="customerId" label="작성자"></el-table-column>
	</el-table>
	
	
	<!-- 페이징 네비게이션 -->
	<div class="flex justify-center">
		<el-pagination layout="prev, pager, next" :page-size="rowPerPage"
			v-model:current-page="pageNum" :total="totalCount" @change="loadPage" />
	</div>
</c:set>

<c:set var="script">
	
	data() {
		return {			
			questionList: JSON.parse('${ questionList }'),	// 리스트
			pageNum: ${page.pageNum},						// 페이징
			rowPerPage: ${page.rowPerPage },
			totalCount: ${page.totalCount},
			totalPage: ${page.totalPage },
		}
	},
	
	methods: {
		insert(){	// 추가
			if(${ loginCustomer != null }){
				location.href = '${ctp}/question/insert';
			} else {
				alert('로그인 후 이용해주세요.');
				location.href ='${ctp}/customer/login';
			}
			
		},
		
		rowClick(row, column){	// 상세보기
	      	console.log('Row.data:',row, column);
	      	if (column.property) {
	      	  location.href='${ctp}/question/questionOne?questionNo=' + row.questionNo;
	      	}
	      },
		
		loadPage(pageNum) {	// 페이징
      	const param = new URLSearchParams();
      	param.set('pageNum', this.pageNum);
      	param.set('rowPerPage', this.rowPerPage);
      	
		location.href = '/question/list?' + param.toString();
      },     
	},
 
</c:set>
<%@ include file="/inc/user_layout.jsp"%>