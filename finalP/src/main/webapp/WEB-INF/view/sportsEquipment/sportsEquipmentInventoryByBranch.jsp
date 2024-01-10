<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 지점별 재고 리스트" />
<c:set var="description" value="로그인계정의 소속 지점별 소유하고 있는 물품의 재고를 확인 할 수 있는 페이지" />
<c:set var="keywords" value="장비,소모품,재고" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/sportsEquipment/sportsEquipmentInventoryByBranch" method="get" id="searchSportsEquipmentOrderForm">
		<el-form-item label="검색">
				<el-input v-model="model.searchItem" name="searchItem" placeholder="검색어를 입력하세요"/>
	   	</el-form-item>
	   	<el-form-item>
				<el-button type="info" @click="resetSearchSubmit()">전체보기</el-button>
				<el-button type="primary" @click="searchSubmit(form)">검색</el-button>
	   	</el-form-item>
	</el-form>
	<!-- 재고 리스트 -->
	<el-row :gutter="20">
  		<el-col :span="8" v-for="(inventory, index) in sportsEquipmentInventory" :key="index">
    		<el-card :label="inventory.itemName" :body-style="{ padding: '15px' }">
      			<div style="padding: 14px">
        			<img :src="'/finalP/upload/sportsEquipment/' + inventory.sportsEquipmentImgFileName" class="image" style="width: 300%; height: 400px;"/>
        			<span>지점: {{ inventory.branchName }}</span><br>
        			<span>가격: {{ inventory.itemPrice }}</span><br>
        			<span>이름: {{ inventory.itemName }}</span><br>
        			<span>번호: {{ inventory.sportsEquipmentNo }}</span><br>
        			<span>재고: {{ inventory.totalQuantity }}</span><br>
        			<span>발주: {{ inventory.inventoryQuantity }}</span><br>
        			<span>폐기: {{ inventory.discartdQuantity }}</span><br>
        			<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/sportsEquipment/insertSportsEquipmentOrder" method="post" id="insertOrderForm">
  						<el-form-item label="발주수량">
    						<el-input-number v-model="model.quantity" :min="1" name="quantity" :max="100" @change="handleChange" />
 		 				</el-form-item>
  							<input type="hidden" name="sportsEquipmentNo" :value="inventory.sportsEquipmentNo">
 							<input type="hidden" name="itemPrice" :value="inventory.itemPrice">
  						<el-form-item>
    						<el-button type="primary" @click="onSubmit(form)">발주</el-button>
  						</el-form-item>
					</el-form>
					<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/sportsEquipment/insertSportsEquipmentOrder" method="post" id="insertOrderForm">
  						<el-form-item label="폐기수량">
    						<el-input-number v-model="model.quantity" :min="-inventory.totalQuantity" name="quantity"  />
 		 				</el-form-item>
  							<input type="hidden" name="sportsEquipmentNo" :value="inventory.sportsEquipmentNo">
 							<input type="hidden" name="itemPrice" :value="inventory.itemPrice">
  						<el-form-item>
    						<el-button type="primary" @click="onSubmit(form)">폐기</el-button>
  						</el-form-item>
					</el-form>
      			</div>
    		</el-card>
    		<br>
  		</el-col>
	</el-row>
   	
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
			    currentPage: 1, 
		    },
		    sportsEquipmentInventory: JSON.parse('${sportsEquipmentInventory}'), 
		    lastPage: ${lastPage} 
	  	};
	},
	
	methods: {
		searchSubmit() {
			document.getElementById('searchSportsEquipmentOrderForm').submit();
		},
		
		resetSearchSubmit() {
			location.href = `${ctp}/sportsEquipment/sportsEquipmentInventoryByBranch`;

        },
        
        onSubmit() {
			document.getElementById('insertOrderForm').submit();
		},
        	
  		changePage(page) {
    		this.currentPage = page;
    		console.log('Current Page:', this.currentPage); 
    		location.href = '${ctp}/sportsEquipment/sportsEquipmentInventoryByBranch?searchItem=${searchItem}&currentPage='+page;
  		}
	}
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>