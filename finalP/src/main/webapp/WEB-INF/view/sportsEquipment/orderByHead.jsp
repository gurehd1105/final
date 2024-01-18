<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비발주 리스트" />
<c:set var="description" value="본사에서 현재 발주요청, 처리 된 이력을 알 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,발주" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<h2>검색</h2>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg" 
			 action="${ctp}/sportsEquipment/orderByHead" 
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
		        <th>승인/거부</th>

        	</tr>
      	</thead>
	    <tbody>
	    	<tr v-for="(order, i) in list" :key="i">
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
	        	<td :style="{ backgroundColor: order.orderStatus === '승인' ? 'blue' : 'red', color: 'white' }">{{ order.orderStatus }}</td>
	        	<td>
					<span v-if="order.orderStatus === '대기'">
  						<el-form label-position="right" 
  								 ref="form" 
  								 label-width="150px" 
  								 status-icon class="max-w-lg" 
  								 action="${ctp}/sportsEquipment/updateOrder"
  								 method="post" 
  						 		 id="updateOrder1"
  						 >
  						 
	   						<el-form-item>
								<el-button type="success" plain round @click="onSubmit1(form)">승인</el-button>
	   						</el-form-item>
	   					<input type="hidden" name="orderNo" :value="order.orderNo">
	   					<input type="hidden" name="orderStatus" value="승인">
						</el-form>
					</span>
					
					<span v-if="order.orderStatus === '대기'">
  						<el-form label-position="right" 
  								 ref="form" 
  								 label-width="150px" 
  								 status-icon class="max-w-lg" 
  								 action="${ctp}/sportsEquipment/updateOrder" 
  								 method="post" 
  								 id="updateOrder2"
  						>
	   						<el-form-item>
								<el-button type="danger" plain round @click="onSubmit2(form)">거부</el-button>
	   						</el-form-item>
	   					<input type="hidden" name="orderNo" :value="order.orderNo">
	   					<input type="hidden" name="orderStatus" value="거부">
						</el-form>
					</span>
					
	          		<span v-else style="color: blue; font-weight: bold;" >{{ order.orderStatus }} 완료</span>
	        	</td>
	     	</tr>
	    </tbody>
    </table>	
 	<br>
 	
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
		const model = JSON.parse('${result}');
	  	return {
		    model: {
			    query: model.param.query ?? '', 
			    searchBranch: model.param.searchBranch ?? '',
			    beginDate: model.param.beginDate ?? '',
			    endDate: model.param.endDate ?? '',
		    },
		    
		    list: model.list, 
		    rowPerPage: model.param.rowPerPage,
		    totalCount: model.param.totalCount,
			pageNum: model.param.pageNum,
			query: model.param.query,
			searchBranch: model.param.searchBranch,
			beginDate: model.param.beginDate,
			endDate: model.param.endDate,
	  	};
	},
	
	methods: {
		searchSubmit() {
			document.getElementById('searchForm').submit();
		},
		
		resetSearchSubmit() {
			location.href = `${ctp}/sportsEquipment/orderByHead`;

        },
        
        onSubmit1() {
			document.getElementById('updateOrder1').submit();
		},
		
        onSubmit2() {
			document.getElementById('updateOrder2').submit();
		},
        	
  	    loadPage(pageNum) {
      		const param = new URLSearchParams();
      		param.set('pageNum', pageNum);
      		param.set('query', this.query);
      		param.set('searchBranch', this.searchBranch);
      		param.set('beginDate', this.beginDate);
      		param.set('endDate', this.endDate);

			location.href = '/sportsEquipment/orderByHead?' + param.toString();
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