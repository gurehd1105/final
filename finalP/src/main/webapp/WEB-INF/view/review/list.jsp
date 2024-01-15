<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰목록" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<el-button type="primary" @click="insert()">리뷰작성</el-button>
	<el-table :data="reviewList" class="w-fit" @row-click="rowClick" class-name="cursor-pointer">
	    <el-table-column prop="reviewNo" label="No"></el-table-column>
	    <el-table-column prop="branchName" label="지점명" ></el-table-column>
	    	<el-table-column prop="programName" label="프로그램명" ></el-table-column>	
	    <el-table-column prop="reviewTitle" label="제목" ></el-table-column>
	    <el-table-column prop="customerId" label="작성자" ></el-table-column>
		<c:if test="${ loginEmployee != null }">

		    <el-table-column label="수정/삭제" >
	     		<template #default="scope">
		    		<el-button type="primary" @click="remove(scope.row)">삭제</el-button> 
		    	</template>
		    </el-table-column>
		</c:if>
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
			reviewList: JSON.parse('${reviewList}'),
			pageNum: ${page.pageNum},
			rowPerPage: ${page.rowPerPage },
			totalCount: ${page.totalCount},
			totalPage: ${page.totalPage },
		}
	},
	
	methods: {
		insert() {
			location.href='${ctp}/review/insert';
		},
				
		loadPage(pageNum) {
      	const param = new URLSearchParams();
      	param.set('pageNum', this.pageNum);
      	param.set('rowPerPage', this.rowPerPage);      	
		location.href = '/review/list?' + param.toString();
      },
      rowClick(row, column){
      	console.log('Row.data:',row, column);
      	if (column.property) {
      	  location.href='${ctp}/review/reviewOne?reviewNo=' + row.reviewNo;
      	}
      },
	  remove(row) {
         if(confirm('해당 게시글을 강제 삭제하시겠습니까?')){
            const reviewNo = row.reviewNo;  <!-- 여기서 reviewNo를 추출합니다 -->
            axios.post('${ctp}/review/delete', { reviewNo: reviewNo })
            .then((res) => {
               if(res.data == 1){
                  alert('삭제가 완료되었습니다.');
                  location.reload();
               }
            }).catch((error) => {
               alert('삭제 중 에러가 발생했습니다.');
               console.error(error);
            })
         }
      },  
   },
</c:set>
<%@ include file="/inc/user_layout.jsp" %>