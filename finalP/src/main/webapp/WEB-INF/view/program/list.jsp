<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="프로그램 리스트" />
<c:set var="description" value="프로그램 목록을 보여주고 검색 할 수 있는 사이느" />
<c:set var="keywords" value="프로그램" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
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
    <table class="custom-table">
      	<thead>
        	<tr>
		        <th>번호</th>
		        <th>관리자</th>
		        <th>이름</th>
		        <th>내용</th>
		        <th>최대인원</th>
		        <th>활성화</th>
		        <th>생성일</th>
		        <th>수정일</th>
		        <th v-if="<%= session.getAttribute("loginEmployee") != null %> && branchLevel === 1">
		        	수정
		        </th>
        	</tr>
      	</thead>
	    <tbody>
	    	<tr v-for="(list, i) in programList" :key="i">
	        	<td>{{ list.programNo }}</td>
			    <td>{{ list.employeeId }}</td>
			    <td>{{ list.programName }}</td>
			    <td >{{ list.programContent }}</td>
			    <td>{{ list.maxCustomer }}</td>
			    <td>{{ list.programActive === 'Y' ? '활성화' : '비활성화' }}</td>
			    <td>{{ new Date(list.createdate).toLocaleString() }}</td>
			    <td>{{ new Date(list.updatedate).toLocaleString() }}</td>
			    <td v-if="<%= session.getAttribute("loginEmployee") != null %> && branchLevel === 1">
       			 <button @click="updateProgram(list)">수정</button>
       			</td>
	     	</tr>
	    </tbody>
    </table>
    <br>
	<%@ include file="/inc/pagination.jsp" %>
	
</c:set>
<c:set var="script">
	data() {
	  	return {
		    model: {
			    searchWord: '${searchWord}', 
			    programActive: '${programActive}', 
		    },
		    programList: JSON.parse('${programList}'),
		    pageNum: ${page.pageNum},
       		rowPerPage: ${page.rowPerPage },
        	totalCount: ${page.totalCount},
        	totalPage: ${page.totalPage },
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
        
      	loadPage(pageNum) {
      		const param = new URLSearchParams();
      		param.set('pageNum', this.pageNum);
      		param.set('searchWord', this.model.searchWord);
      		param.set('programActive', this.model.programActive);
      	
			location.href = '/program/list?' + param.toString();
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