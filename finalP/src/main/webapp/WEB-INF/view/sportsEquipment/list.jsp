<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="스포츠 장비 리스트" />
<c:set var="description" value="현재 발주 할 수 있는 스포츠 장비의 리스트를 보여주는 사이트" />
<c:set var="keywords" value="장비,소모품,발주,폐기" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<h2>검색</h2>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg" 
			 action="${ctp}/sportsEquipment/list" 
			 method="get" 
			 enctype="multipart/form-data" 
	  		 id="searchForm"
			 style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;"
	>
	   	<el-form-item label="상태">
			<el-radio-group v-model="model.active" name="active" class="ml-4" >
				<el-radio label="Y">주문가능</el-radio>
				<el-radio label="N">품절</el-radio>
			</el-radio-group>
	   	</el-form-item>
		<el-form-item label="검색">
			<el-input v-model="model.query" name="query" placeholder="검색어를 입력하세요"/>
	   	</el-form-item>
	   	<el-form-item>
				<el-button type="info" @click="resetSearchSubmit()">전체보기</el-button>
				<el-button type="primary" @click="searchSubmit(form)">검색</el-button>
	   	</el-form-item>
	</el-form>
	   
	<br>
	<!-- 장비 리스트 -->
	<el-row :gutter="20">
  		<el-col :span="8" v-for="(equipment, index) in list" :key="index">
    		<el-card :label="equipment.itemName" :body-style="{ padding: '15px' }">
      			<div style="padding: 14px">
        			<img :src="'/upload/sportsEquipment/' + equipment.sportsEquipmentImgFileName" class="image" style="width: 300%; height: 400px;"/>
        			<span>상품명: {{ equipment.itemName }}</span><br>
        			<span>가격: {{ equipment.itemPrice }}원</span><br>
        			<span>상태: {{ equipment.equipmentActive === 'Y' ? '주문가능' : '품절' }}</span><br>
        			<br>
          			<el-button type="success" @click="sportsEquipmentOne(equipment.sportsEquipmentNo)">발주</el-button>
      			</div>
    		</el-card>
    		<br>
  		</el-col>
	</el-row>
    <!-- 페이징 네비게이션 -->
	<%@ include file="/inc/pagination.jsp" %>
	
</c:set>
<c:set var="script">
	data() {
		const model = JSON.parse('${result}');
	  	return {
		    model: {
			    query: model.param.query ?? '', 
			    active: model.param.active ?? '',
		    },
		    
		    list: model.list, 
		    rowPerPage: model.param.rowPerPage,
		    totalCount: model.param.totalCount,
			pageNum: model.param.pageNum,
			query: model.param.query,
			active: model.param.active,
	  	};
	},
	
	methods: {
		searchSubmit() {
			document.getElementById('searchForm').submit();
		},
		
		resetSearchSubmit() {
			location.href = `${ctp}/sportsEquipment/list`;

        },
        
        sportsEquipmentOne(sportsEquipmentNo){
			location.href = '${ctp}/sportsEquipment/sportsEquipmentOne?sportsEquipmentNo='+sportsEquipmentNo;
		},
		
  		
  	    loadPage(pageNum) {
      		const param = new URLSearchParams();
      		param.set('pageNum', pageNum);
      		param.set('active', this.active);
      		param.set('query', this.query);

			location.href = '/sportsEquipment/list?' + param.toString();
      	},
	}
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>