<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- 페이징 네비게이션 -->
    <div class="flex justify-center">
      <el-pagination layout="prev, pager, next" 
      	:page-size="rowPerPage" 
		v-model:current-page="pageNum" 
		:total="totalCount"
		@change="loadPage" />
    </div>
