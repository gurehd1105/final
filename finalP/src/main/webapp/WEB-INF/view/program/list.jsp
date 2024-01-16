<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="프로그램 리스트" />
<c:set var="description" value="프로그램 목록을 보여주고 검색 할 수 있는 사이느" />
<c:set var="keywords" value="프로그램" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<h2>검색창</h2>
	<!-- 검색창 -->
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg" 
			 action="${ctp}/program/list" 
			 method="get" 
			 id="searchProgramForm"
			 style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;"
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
	<br>
	<!-- 프로그램 리스트 -->
    <table class="custom-table">
      	<thead>
        	<tr>
		        <th>번호</th>
		        <th>관리자</th>
		        <th>이름</th>
		        <th>최대인원</th>
		        <th>활성화</th>
		        <th>생성일</th>
		        <th>수정일</th>
		        <th v-if="branchLevel === 1">수정</th>
        	</tr>
      	</thead>
	    <tbody>
	    	<tr v-for="(list, i) in programList" :key="i">
	        	<td>{{ list.programNo }}</td>
			    <td>{{ list.employeeId }}</td>
			    <td>{{ list.programName }}</td>
			    <td>{{ list.maxCustomer }}</td>
			    <td>{{ list.programActive === 'Y' ? '활성화' : '비활성화' }}</td>
			    <td>{{ new Date(list.createdate).toLocaleString() }}</td>
			    <td>{{ new Date(list.updatedate).toLocaleString() }}</td>
			    <td v-if="branchLevel === 1">
       			 <button @click="updateProgram(list)">수정</button>
       			</td>
	     	</tr>
	    </tbody>
    </table>
    <br>

	<!-- 페이징 -->
	<el-row class="mb-8">
    	<el-button type="info" @click="changePage(1)" plain>처음</el-button>
    	<el-button type="info" v-for="p in lastPage" :key="p" @click="changePage(p)" plain>{{ p }}</el-button>
    	<el-button type="info" @click="changePage(lastPage)" plain>마지막</el-button>
  	</el-row>
	
</c:set>
<c:set var="script">
	data() {
	  	return {
		    model: {
			    searchWord: '${searchWord}', 
			    programActive: '${programActive}',
			    currentPage: 1, 
		    },
		    programList: JSON.parse('${programList}'),
		    lastPage: ${lastPage},
		    branchLevel : ${branchLevel}
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
        
  		changePage(page) {
    		this.currentPage = page;
    		console.log('Current Page:', this.currentPage); 
    		location.href = '${ctp}/program/list?searchWord=${searchWord}&programActive=${programActive}&currentPage='+page;
  		}
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
</style>
<%@ include file="/inc/admin_layout.jsp"%>