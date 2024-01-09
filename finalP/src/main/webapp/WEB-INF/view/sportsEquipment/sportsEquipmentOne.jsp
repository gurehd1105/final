<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 상세보기 및 발주" />
<c:set var="description" value="스포츠 장비의 이름, 가격, 상태를 확인하고 수량을 선택해 발주 할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,상세보기,발주" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
<c:set var="totalPrice" value="'\${model.quantity * itemPrice}'" />

	<el-descriptions
	    class="margin-top"
	    title="장비 상세보기 및 발주"
	    :column="3"
	    :size="size"
	    border
	  >    
	    <el-descriptions-item>
	      	<template #label>
	        	<div class="cell-item">
	          		관리자
	        	</div>
	      	</template>
	      	{{employeeId}}
	    </el-descriptions-item>
	    
	    <el-descriptions-item>
	      	<template #label>
	        	<div class="cell-item">
	          		장비
	        	</div>
	      	</template>
	      	{{itemName}}
	    </el-descriptions-item>
	    
	    <el-descriptions-item>
	      	<template #label>
	        	<div class="cell-item">
	          		가격
	        	</div>
	      	</template>
	      	{{itemPrice}}원
	      	{{sportsEquipment.sportsEquipmentNo}}번
	    </el-descriptions-item>
	    
	    <el-descriptions-item>
	      	<template #label>
	        	<div class="cell-item">
	          		상태
	        	</div>
	      	</template>
	      	{{equipmentActive === 'Y' ? '주문가능' : '품절' }}
	    </el-descriptions-item>
	    
	    <el-descriptions-item>
	      	<template #label>
	        	<div class="cell-item">
	          		등록일
	        	</div>
	      	</template>
	      	{{equipmentCreatedate}}
	    </el-descriptions-item>
	    
	    <el-descriptions-item>
	      	<template #label>
	        	<div class="cell-item">
	          		수정일
	        	</div>
	      	</template>
	      	{{equipmentUpdatedate}}
	    </el-descriptions-item>
	    
	    <el-descriptions-item :column="1">
	      	<template #label>
	        	<div class="cell-item">
	          		이미지
	        	</div>
	      	</template>
	      	<div class="image-list">
				<el-image
	    			v-for="(img, index) in sportsEquipmentImgList"
	    			:key="index"
	    			:src="'/finalP/upload/sportsEquipment/' + img.sportsEquipmentImgFileName" 
	    			style="max-width: 300px; max-height: 300px; margin-right: 10px;" 
	    			fit="cover"
	  			></el-image>
			</div>
	    </el-descriptions-item>
	</el-descriptions>
	
	<br>
	<div>
		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/sportsEquipment/insertSportsEquipmentOrder" method="post" id="insertOrderForm">
		  	<el-input-number v-model="quantity" :min="1" name="quantity" :max="100" @change="handleChange" />
		  	<el-input type="hidden" name="sportsEquipmentNo" :value="sportsEquipment.sportsEquipmentNo">
		 	<el-input type="hidden" name="itemPrice" :value="sportsEquipment.itemPrice">
 		</el-form>
 		<br>
  		<el-button type="primary" @click="onSubmit(form)">발주</el-button>
	</div>



</c:set>
<c:set var="script">
	data() {
	  	return {
	  			model: {
	    			quantity: '',
	    			totalPrice:'',
	    		},
	    		
	    		sportsEquipment:{
				    sportsEquipmentNo: '${sportsEquipmentNo}', 
				},		     
				    employeeId: '${employeeId}',
				    itemName: '${itemName}',
				    itemPrice: '${itemPrice}',
				    equipmentActive: '${equipmentActive}',
				    equipmentCreatedate: '${equipmentCreatedate}',
				    equipmentUpdatedate: '${equipmentUpdatedate}',
				    sportsEquipmentImgList: JSON.parse('${sportsEquipmentImgList}'),		     
				    sportsEquipmentInventory: JSON.parse('${sportsEquipmentInventory}'),
	  	};
	},
	methods: {
	
		handleChange() {
    		this.model.totalPrice = this.model.quantity * this.itemPrice;
    		console.log('Total Price:', this.model.totalPrice);
		},
		
		onSubmit() {
			document.getElementById('insertOrderForm').submit();
		},
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp"%>