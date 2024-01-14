<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 관리 및 리스트" />
<c:set var="description" value="현재 발주 할 수 있는 장비를 추가할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,수정,삭제,추가" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	
	<!-- 장비 추가 폼 -->
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/sportsEquipment/insertSportsEquipment" enctype="multipart/form-data" method="post" id="insertSportsEquipment">
		<el-form-item label="이름">
			<el-input type="text" v-model="model.itemName" name="itemName" placeholder="이름을 입력하세요." />			
		</el-form-item> 

		<el-form-item label="가격">
			<el-input type="number" v-model="model.itemPrice" name="itemPrice" :min="0" placeholder="가격을 입력하세요." />			
		</el-form-item> 
	
		<el-form-item label="이미지">
			<el-input type="file" v-model="model.sportsEquipmentImg" name="sportsEquipmentImg" multiple  />			
		</el-form-item> 
	
		<el-form-item>
			<el-button type="primary" @click="onSubmit(form)">입력</el-button>
		</el-form-item> 
	</el-form>
	
	<template>
		<el-alert v-if="showErrorAlert" title="전부 다 입력하세요" type="error" />
	</template>	

	<!-- 검색창 -->
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/sportsEquipment/insertSportsEquipment" method="get" enctype="multipart/form-data" id="searchSportsEquipmentForm">
	   	<el-form-item label="상태">
			<el-radio-group v-model="model.equipmentActive" name="equipmentActive" class="ml-4" >
				<el-radio label="Y">주문가능</el-radio>
				<el-radio label="N">품절</el-radio>
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
   	
	<!-- 장비 리스트 -->
	<el-row :gutter="20">
  		<el-col :span="8" v-for="(equipment, index) in sportsEquipmentList" :key="index">
    		<el-card :label="equipment.itemName" :body-style="{ padding: '15px' }">
      			<div style="padding: 14px">
        			<img :src="'/upload/sportsEquipment/' + equipment.sportsEquipmentImgFileName" class="image" style="width: 300%; height: 400px;"/>
        			<span>상품명: {{ equipment.itemName }}</span><br>
        			<span>가격: {{ equipment.itemPrice }}원</span><br>
        			<span>상태: {{ equipment.equipmentActive === 'Y' ? '주문가능' : '품절' }}</span><br>
        			<br>
          			<el-button type="primary" @click="updateSportsEquipment(equipment.sportsEquipmentNo)">수정</el-button>
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
			 showErrorAlert: false,
			model: {
			    searchWord: '${searchWord}', 
			   	equipmentActive: '${equipmentActive}',
			    currentPage: 1, 
		    	itemName: '',
		    	itemPrice: '',
		    	sportsEquipmentImg: '',
		    	
		    },
		    
		    sportsEquipmentList: JSON.parse('${sportsEquipmentList}'), 
		    lastPage: ${lastPage},
	  	};
	},
	methods: {
			searchSubmit() {
				document.getElementById('searchSportsEquipmentForm').submit();
			},
			
			resetSearchSubmit() {
				location.href = `${ctp}/sportsEquipment/insertSportsEquipment`;

        	},
        	
        	chekForm(){
        		console.log('validateAndSubmit 메서드 실행');
    			this.$refs.form.validate((valid) => {
      			if (valid) {
        			this.onSubmit();
      			} else {
        			this.showErrorAlert = true;
        			console.log('true 변경');
      			}
    		});
        	
        	},
        	
			onSubmit(){
				console.log('onSubmit 메서드 실행');
				document.getElementById('insertSportsEquipment').submit();
			},
			
			updateSportsEquipment(sportsEquipmentNo){
				location.href = '${ctp}/sportsEquipment/updateSportsEquipment?sportsEquipmentNo='+sportsEquipmentNo;
			},
					
			changePage(page) {
    			this.currentPage = page;
    			console.log('Current Page:', this.currentPage); 
    			location.href = '${ctp}/sportsEquipment/insertSportsEquipment?searchWord=${searchWord}&equipmentActive=${equipmentActive}&currentPage='+page;
  			}
	
	}
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>