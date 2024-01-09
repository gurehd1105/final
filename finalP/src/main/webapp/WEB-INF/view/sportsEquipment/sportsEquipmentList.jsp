<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="스포츠 장비 리스트" />
<c:set var="description" value="현재 발주 할 수 있는 스포츠 장비의 리스트를 보여주는 사이트" />
<c:set var="keywords" value="장비,소모품,발주,폐기" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<div>
		<!-- 검색창 -->
		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" action="${ctp}/sportsEquipment/SportsEquipmentList" method="get" enctype="multipart/form-data" id="searchSportsEquipmentForm">
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
	    		<el-button type="info" @click="resetSearchSubmit(form)">전체보기</el-button>
	    		<el-button type="primary" @click="searchSubmit(form)">검색</el-button>
		   	</el-form-item>
	    </el-form>
	    
	    <!-- 장비 리스트 -->
		<el-descriptions title="장비 리스트" :column="1" border>
		  <el-descriptions-item v-for="(equipment, index) in sportsEquipmentList" :label="equipment.itemName">
		    <el-card :body-style="{ padding: '0px' }">
		      <!-- 장비 정보 표시 -->
		      <img :src="`${ctp}/upload/sportsEquipment/${equipment.sportsEquipmentImgFileName}`" class="image" />
		      <div style="padding: 14px">
		        <span>{{ equipment.itemName }}</span>
		        <span>{{ equipment.itemPrice }}</span>
		        <span>{{ equipment.equipmentActive }}</span>
		        <div class="bottom">
		          <el-button type="primary" @click="sportsEquipmentOne(equipment.sportsEquipmentNo)">발주</el-button>
		        </div>
		      </div>
		    </el-card>
		  </el-descriptions-item>
		</el-descriptions>
		
		<!-- 페이징 부분 -->
		<div style="border: 1px solid #ccc;">
		  <a @click="changePage(1)">처음</a>
		  <a v-for="p in lastPage" :key="p" @click="changePage(p)">{{ p }}</a>
		  <a @click="changePage(lastPage)">마지막</a>
		</div>
</c:set>
<c:set var="script">
	data() {
	  	return {
		    model: {
			    searchWord: '${searchWord}', 
			    equipmentActive: '${equipmentActive}', 
		    },
		    sportsEquipmentList: JSON.parse('${sportsEquipmentList}'), 
		    currentPage: 1, 
		    lastPage: ${lastPage} 
	  	};
	},
	methods: {
		searchSubmit() {
			document.getElementById('searchSportsEquipmentForm').submit();
		},
		
		resetSearchSubmit() {
          this.model.searchEquipment = '';
          this.model.equipmentActive = '';

          document.getElementById('searchSportsEquipmentForm').submit();
        },
        
        sportsEqquipmentOne(sportsEquipmentNo){
			location.href = '${ctp}/sportsEquipment/sportsEquipmentOne?sportsEquipmentNo=${equipment.sportsEquipmentNo }';
		},
		
		changePage(page) {
     		this.currentPage = page;
      		location.href = '${ctp}/sportsEquipment/sportsEquipmentList?searchWord=${searchWord}&equipmentActive=${equipmentActive}&currentPage=+page';
    	}
	}
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>