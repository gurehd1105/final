<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 지점별 재고 리스트" />
<c:set var="description" value="로그인계정의 소속 지점별 소유하고 있는 물품의 재고를 확인 할 수 있는 페이지" />
<c:set var="keywords" value="장비,소모품,재고" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<h2>검색</h2>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg" 
			 action="${ctp}/sportsEquipment/inventoryByBranch"
			 method="get" 
			 id="searchForm"
			 style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;"
	>
	
		<el-form-item label="검색">
				<el-input v-model="model.query" name="query" placeholder="검색어를 입력하세요"/>
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
		        <th>발주하기</th>
		        <th>폐기하기</th>
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
			    <td>
			        <el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" 
			        	action="${ctp}/sportsEquipment/insertOrder" method="post" :id="'insertOrderForm1' + i">
  						<el-form-item label="발주수량">
    						<el-input-number v-model="model.quantity1[i]" :min="0" name="quantity" :max="100" />
 		 				</el-form-item>
  							<input type="hidden" name="sportsEquipmentNo" :value="inventory.sportsEquipmentNo">
 							<input type="hidden" name="itemPrice" :value="inventory.itemPrice">
  						<el-form-item>
    						<el-button type="success" round @click="onSubmit1(i)">발주</el-button>
  						</el-form-item>
					</el-form>
			    </td>
			    <td>
			    	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			    		 action="${ctp}/sportsEquipment/insertOrder" method="post" :id="'insertOrderForm2' + i">
  						<el-form-item label="폐기수량">
    						<el-input-number v-model="model.quantity2[i]" :min="-inventory.totalQuantity" :max="0" name="quantity"  />
 		 				</el-form-item>
  							<input type="hidden" name="sportsEquipmentNo" :value="inventory.sportsEquipmentNo">
 							<input type="hidden" name="itemPrice" :value="0">
  						<el-form-item>
    						<el-button type="danger" round @click="onSubmit2(i)">폐기</el-button>
  						</el-form-item>
					</el-form> 
			    </td>
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
			    quantity1: [], 
			    quantity2: [], 
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
			location.href = `${ctp}/sportsEquipment/inventoryByBranch`;

        },
        
        
    onSubmit1(i) {
        const form = document.getElementById('insertOrderForm1'+i);
        
        console.log('onSubmit1 Parameters:', form);
        console.log('i:', i);
        console.log('model.quantity1[i]:', this.model.quantity1[i]);
        
        if (form) {
             form.submit();
        } else {
            console.error('Form not found.');
        }
    },

    onSubmit2(i) {
        const form = document.getElementById('insertOrderForm2'+i);
        
        console.log('i:', i);
        console.log('onSubmit2 Parameters:', form);
        
        if (form) {
             form.submit();
        } else {
            console.error('Form not found.');
        }
    },
	        	
  	 loadPage(pageNum) {
      	const param = new URLSearchParams();
      	param.set('pageNum', pageNum);
      	param.set('query', this.query);

		location.href = '/sportsEquipment/inventoryByBranch?' + param.toString();
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