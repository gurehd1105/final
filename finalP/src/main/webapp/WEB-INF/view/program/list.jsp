<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="프로그램 리스트" />
<c:set var="description" value="프로그램 목록을 보여주고 검색 할 수 있는 사이느" />
<c:set var="keywords" value="프로그램" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<div v-if="isEmployee">
	   <div class="flex justify-start" class="w-auto">
	      <!-- 검색창 -->
	      <el-form label-position="right" 
	            ref="form" 
	            label-width="150px" 
	            status-icon 
	            class="w-96" 
	            action="${ctp}/program/list" 
	            method="get" 
	            id="searchProgramForm"
	      > 
	  
			<el-form-item label="상태">
				<el-radio-group v-model="model.programActive" name="programActive" class="ml-4" >
		               <el-radio label="Y">활성화</el-radio>
		               <el-radio label="N">비활성화</el-radio>
		        </el-radio-group>
		      </el-form-item>
	         <el-form-item label="검색">
	                <el-input v-model="model.searchWord" name="searchWord" placeholder="검색어를 입력하세요"/>
	            </el-form-item>
	            
	            <el-form-item>
	                <el-button type="info" @click="resetSearchSubmit()">전체보기</el-button>
	                <el-button type="primary" @click="searchSubmit(form)">검색</el-button>
	            </el-form-item>
	      </el-form>
	   </div>   
	   <br>
	   <!-- 프로그램 리스트 -->
		<p>자세한 내용을 확인하려면 프로그램을 클릭하세요.</p>
	    <el-table
	        :data="list"
	        :key="loading"
	        class="w-full"
	        align="center"
	        border
	        @row-click="handleRowClick"
	    >   
	
			<el-table-column width="180" prop="programNo" label="번호"></el-table-column>
			<el-table-column width="180" prop="programName" label="이름"></el-table-column>
			<el-table-column width="180" prop="employeeId" label="관리자"></el-table-column>
			<el-table-column width="180" prop="maxCustomer" label="최대인원"></el-table-column>
	        <el-table-column label="활성화">
				<template #default="scope">
				    <el-tag class="ml-2" :type="scope.row.programActive === 'Y' ? 'success' : 'danger'">
				        {{ scope.row.programActive === 'Y' ? '활성화' : '비활성화' }}
				    </el-tag>
				</template>
	        </el-table-column>
	        <el-table-column label="생성일">
	            <template #default="scope">
	               <span>{{ moment(scope.row.createdate).format('yyyy-MM-DD') }}</span>
	            </template>
	        </el-table-column>
	        <el-table-column label="수정일">
	            <template #default="scope">
	               <span>{{ moment(scope.row.updagedate).format('yyyy-MM-DD') }}</span>
	            </template>
	        </el-table-column>
	        <el-table-column fixed="right" label="수정" width="220">
			  <template #default="scope">
			    <el-button plain type="primary" @click="move(scope.row, 'update')" size="small">수정</el-button>
			  </template>
			</el-table-column>
	    </el-table>
	 </div>   

 
	<div v-else>
		    <el-table
	        :data="list"
	        :key="loading"
	        class="w-full"
	        align="center"
	        border
	        @row-click="handleRowClick"
	    >   
	    	<el-table-column width="180" prop="programName" label="이름"></el-table-column>
			<el-table-column width="180" prop="employeeId" label="관리자"></el-table-column>
			<el-table-column width="180" prop="maxCustomer" label="최대인원"></el-table-column>
	        <el-table-column label="예약">
				<template #default="scope">
				    <el-tag class="ml-2" :type="scope.row.programActive === 'Y' ? 'success' : 'danger'">
				        {{ scope.row.programActive === 'Y' ? '예약가능' : '예약불가' }}
				    </el-tag>
				</template>
	        </el-table-column>
	</div>

   <el-dialog v-model="dialogVisible" title="프로그램 내용" width="50%" draggable>
	    <span>{{ selectedProgram.programContent }}</span>
	    <template #footer>
	      <span class="dialog-footer">
	        <el-button @click="dialogVisible = false">닫기</el-button>
	      </span>
    	</template>
    </el-dialog>
    
    
     
   <%@ include file="/inc/pagination.jsp" %>
</c:set>

<c:set var="script">
   
   data() {
        return {
      
          model: {
             searchWord: '${searchWord}', 
             programActive: '${programActive}', 
          },
      	  dialogVisible: false, 
      	  selectedProgram: {}, 
      	  loading: false,
          list: JSON.parse('${programList}'),
          pageNum: ${page.pageNum},
          rowPerPage: ${page.rowPerPage },
          totalCount: ${page.totalCount},
          totalPage: ${page.totalPage },
          branchLevel : ${branchLevel},
		  isEmployee: <%= session.getAttribute("loginEmployee") != null %>,

        };
   },
   methods: {
      searchSubmit() {
         document.getElementById('searchProgramForm').submit();
      },
      
      resetSearchSubmit() {
         location.href = `${ctp}/program/list`;
        },
        
        updateProgram(list){
           location.href = '${ctp}/program/update?programNo=' + list.programNo;
        },
        
        move(row, path) {
		if (row.programNo != null) {
		   	location.href = '${ctp}/program/update?programNo=' + row.programNo;
			}
		},
        
        loadPage(pageNum) {
            const param = new URLSearchParams();
            param.set('pageNum', this.pageNum);
            param.set('searchWord', this.model.searchWord);
            param.set('programActive', this.model.programActive);
         
         location.href = '/program/list?' + param.toString();
         },
         
         openDialog(index) {
          this.dialogVisible[index] ='true'
        },
        
        closeDialog(index) {
          this.dialogVisible[index] ='false'
        },
        
        moment(date) {
            return moment(date);
        },
        
        handleRowClick(row, event, column) {
      		this.selectedProgram = row;
      		this.dialogVisible = true;
    	},
   }
</c:set>

<style>
  .custom-table {
    border: 1px solid #ccc;
    width: 100%;
    border-collapse: collapse;
  }

  .custom-table th, .custom-table td {
    padding: 10px;
    text-align: center;
  }

  .custom-table th {
    background-color: #f0f0f0; 
  }

  .custom-table img {
    width: 50px;
    height: 50px;
  }

 .custom-table button {
    border: 1px solid #ccc;
   padding: 5px 10px;
    text-align: center; 
 }
 
 .dialog-footer button:first-child {
  margin-right: 10px;
  }
</style>
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
