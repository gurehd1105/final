<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 지점별 재고 리스트" />
<c:set var="description" value="현재 지점별 소유하고 있는 물품의 재고를 확인 할 수 있는 페이지" />
<c:set var="keywords" value="장비,소모품,재고" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<h2>검색</h2>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px"
			 status-icon class="max-w-lg" 
			 action="${ctp}/sportsEquipment/inventoryByHead" 
			 method="get" 
			 id="searchForm"
			 style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;"
	>
	
		<el-form-item label="지점검색">
				<el-input v-model="model.searchBranch" name="searchBranch" placeholder="지점을 입력하세요"/>
	   	</el-form-item>
		<el-form-item label="아이템검색">
				<el-input v-model="model.query" name="query" placeholder="아이템을 입력하세요"/>
	   	</el-form-item>
	   	<el-form-item>
				<el-button type="info" @click="resetSearchSubmit()">전체보기</el-button>
				<el-button type="primary" @click="searchSubmit(form)">검색</el-button>
	   	</el-form-item>
	</el-form>
	
	<!-- 재고 리스트 출력 -->
    <table class="custom-table">
      	<thead>
        	<tr>
		        <th>지점</th>
		        <th>이미지</th>
		        <th>아이템</th>
		        <th>발주</th>
		        <th>폐기</th>
		        <th>재고</th>
        	</tr>
      	</thead>
	    <tbody>
	    	<tr v-for="(inventory, i) in list" :key="i">
	        	<td>{{ inventory.branchName }}</td>
	        	<td>
	          		<img :src="'/upload/sportsEquipment/' + inventory.sportsEquipmentImgFileName" class="image" style="width: 100px; height: 100px;" />
	        	</td>
	        	<td>{{ inventory.itemName }}</td>
			    <td>{{ inventory.inventoryQuantity }}</td>
			    <td>{{ inventory.discartdQuantity }}</td>
			    <td>{{ inventory.totalQuantity }}</td>
	     	</tr>
	    </tbody>
    </table>
    <br>
			
    <!-- 페이징 네비게이션 -->
	<%@ include file="/inc/pagination.jsp" %>
  	
</c:set>
<c:set var="script">
	data() {
		const model = JSON.parse('${result}');
	  	return {
		    model: {
			    query: model.param.query ?? '', 
			    searchBranch: model.param.searchBranch ?? '',

		    },
		    
		    list: model.list, 
		    rowPerPage: model.param.rowPerPage,
		    totalCount: model.param.totalCount,
			pageNum: model.param.pageNum,
			query: model.param.query,
			searchBranch: model.param.searchBranch,
	  	};
	},

	methods: {
		searchSubmit() {
			document.getElementById('searchForm').submit();
		},
		
		resetSearchSubmit() {
			location.href = `${ctp}/sportsEquipment/inventoryByHead`;
        },
            	
  	    loadPage(pageNum) {
      		const param = new URLSearchParams();
      		param.set('pageNum', pageNum);
      		param.set('query', this.query);
      		param.set('searchBranch', this.searchBranch);

			location.href = '/sportsEquipment/inventoryByHead?' + param.toString();
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

<%@ include file="/inc/admin_layout.jsp"%>