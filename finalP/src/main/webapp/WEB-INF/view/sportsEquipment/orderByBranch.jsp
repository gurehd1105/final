<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비발주 리스트" />
<c:set var="description" value="지점에서 현재 발주요청, 처리 된 이력을 알 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,발주" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<h2>검색</h2>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg" 
			 action="${ctp}/sportsEquipment/orderByBranch" 
			 method="get" 
			 id="searchForm"
			 style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;"	 
	>
	
		<el-form-item label="아이템검색">
				<el-input v-model="model.searchItem" name="searchItem" placeholder="아이템을 입력하세요"/>
	   	</el-form-item>
		<el-form-item label="시작일">
				<el-input type="date" v-model="model.beginDate" name="beginDate"/>
	   	</el-form-item>
		<el-form-item label="마지막일">
				<el-input type="date" v-model="model.endDate" name="endDate" />
	   	</el-form-item>
	   	
	   	<el-form-item>
				<el-button type="info" @click="resetSearchSubmit()">전체보기</el-button>
				<el-button type="primary" @click="searchSubmit(form)">검색</el-button>
	   	</el-form-item>
	</el-form>
	

    <!-- 발주 리스트 출력 -->
    <table class="custom-table">
      	<thead>
        	<tr>
          		<th>발주/폐기</th>
		        <th>지점</th>
		        <th>이미지</th>
		        <th>아이템</th>
		        <th>가격</th>
		        <th>수량</th>
		        <th>총가격</th>
		        <th>발주일</th>
		        <th>결재일</th>
		        <th>현재상태</th>
		        <th>취소</th>
        	</tr>
      	</thead>
	    <tbody>
	    	<tr v-for="(order, i) in model.orderList" :key="i">
	        	<td>
	          		<span v-if="order.quantity > 0" style="color: blue;">발주</span>
	          		<span v-else-if="order.quantity < 0" style="color: red;">폐기</span>
	        	</td>
	        	<td>{{ order.branchName }}</td>
	        	<td>
	          		<img :src="'/upload/sportsEquipment/' + order.sportsEquipmentImgFileName" class="image" style="width: 50px; height: 50px;" />
	        	</td>
	        	<td>{{ order.itemName }}</td>
			    <td>{{ order.itemPrice }}</td>
			    <td>{{ order.quantity }}</td>
			    <td>{{ order.totalPrice }}</td>
				<td>{{ new Date(order.createdate).toLocaleString() }}</td>
	        	<td>
	          		<span v-if="order.updatedate == order.createdate">대기중</span>
	          		<span v-else>{{ new Date(order.updatedate).toLocaleString() }}</span>
	        	</td>
	        	<td>{{ order.orderStatus }}</td>
	        	<td>
					<span v-if="order.orderStatus === '대기'">
  						<el-form label-position="right" 
  								 ref="form" 
  								 label-width="150px" 
  								 status-icon class="max-w-lg" 
  								 action="${ctp}/sportsEquipment/deleteOrder" 
  								 method="post" 
  								 id="deleteOrder"
  						>
  						
	   						<el-form-item>
								<el-button type="warning" round @click="onSubmit(form)">취소</el-button>
	   					</el-form-item>
	   					<input type="hidden" name="orderNo" :value="order.orderNo">
	   					<input type="hidden" name="orderStatus" :value="order.orderStatus">
						</el-form>
					</span>
	          		<span v-else style="color: blue; font-weight: bold;">{{ order.orderStatus }} 완료</span>
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
			    searchItem: '${searchItem}', 
			    searchItem: '${beginDate}', 
			    searchItem: '${endDate}', 
			    currentPage: 1, 
			  	orderList: JSON.parse('${orderList}'),
		    },
			
		    lastPage: ${lastPage} 
	  	};
	},
	
	methods: {
		searchSubmit() {
			document.getElementById('searchForm').submit();
		},
		
		resetSearchSubmit() {
			location.href = `${ctp}/sportsEquipment/orderByBranch`;

        },
        
        onSubmit() {
			document.getElementById('deleteOrder').submit();
		},
        	
  		changePage(page) {
    		this.currentPage = page;
    		console.log('Current Page:', this.currentPage); 
    		location.href = '${ctp}/sportsEquipment/orderByBranch?searchItem=${searchItem}&beginDate=${beginDate}&endDate=${endDate}&currentPage='+page;
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