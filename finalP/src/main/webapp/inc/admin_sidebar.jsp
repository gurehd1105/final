<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="side" class="bg-gray-50">
	<el-aside width="200px">
     <el-scrollbar class="h-[calc(100vh-60px)]">
       <el-menu :default-openeds="['1', '3']">
     <el-sub-menu index="1">
       <template #title>
           관리
       </template>
       <el-sub-menu index="1-1">
           <template #title>지점 관리</template>
           <el-menu-item index="1-1-1">
            <a href="${ctp}/branch/insert">지점 추가</a>
           </el-menu-item> 
           <el-menu-item index="1-1-2">
             <a href="${ctp}/branch/update">지점 정보 수정</a>
           </el-menu-item> 
           <el-menu-item index="1-1-3">
        	 <a href="${ctp}/branch/delete">지점 삭제</a>
           </el-menu-item>
           <el-menu-item index="1-1-4">
      		   <a href="${ctp}/branch/list">지점 목록 조회</a>
           </el-menu-item>
       </el-sub-menu>
       <el-sub-menu index="1-2">
           <template #title>직원 관리</template>
           <el-menu-item index="1-2-1">
               <a href="${ctp}/employee/insert">직원 추가</a>
           </el-menu-item> 
           <el-menu-item index="1-2-2">
               <a href="${ctp}/employee/update">직원 정보 수정</a>
           </el-menu-item>
           <el-menu-item index="1-2-3">
               <a href="${ctp}/employee/list">직원 목록 조회</a>
           </el-menu-item>
       </el-sub-menu>
       <el-sub-menu index="1-3">
           <template #title>공지 관리</template>
           <el-menu-item index="1-3-1">
               <a href="${ctp}/notice/insert">공지 추가</a>
           </el-menu-item> 
           <el-menu-item index="1-3-2">
               <a href="${ctp}/notice/update">공지 수정</a>
           </el-menu-item>
           <el-menu-item index="1-3-3">
               <a href="${ctp}/notice/list">공지 목록 조회</a>
           </el-menu-item>
            <el-menu-item index="1-3-4">
               <a href="${ctp}/notice/list">공지 삭제</a>
           </el-menu-item>
       </el-sub-menu>
   </el-sub-menu>
   <el-sub-menu index="2">
       <template #title>
           프로그램
       </template>
           <el-menu-item index="2-1">
            <a href="${ctp}/program/list">프로그램</a>
           </el-menu-item> 
   </el-sub-menu>
         <el-sub-menu index="3">
           <template #title>
             예시 페이지
           </template>
           <el-menu-item-group>
             <template #title>Group 1</template>
             <el-menu-item index="3-1">Option 1</el-menu-item>
             <el-menu-item index="3-2">Option 2</el-menu-item>
           </el-menu-item-group>
           <el-menu-item-group title="Group 2">
             <el-menu-item index="3-3">Option 3</el-menu-item>
           </el-menu-item-group>
           <el-sub-menu index="3-4">
             <template #title>Option 4</template>
             <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
           </el-sub-menu>
         </el-sub-menu>
         <el-sub-menu index="4">
          	<template #title>
             스포츠 장비
           </template>
           <el-menu-item-group>
             <template #title>본사</template>
             <el-menu-item index="4-1">
             		<a href="${ctp}/sportsEquipment/insertSportsEquipment">장비리스트 추가</a>	
             </el-menu-item>
             <el-menu-item index="4-2">
             		<a href="${ctp}/sportsEquipment/sportsEquipmentOrderListByHead">발주내역</a>
             </el-menu-item>
             <el-menu-item index="4-3">
             		<a href="${ctp}/sportsEquipment/sportsEquipmentInventoryByHead">재고</a>
             </el-menu-item>
           </el-menu-item-group>
           <el-menu-item-group title="지점">
             <el-menu-item index="4-4">
             		<a href="${ctp}/sportsEquipment/SportsEquipmentList">장비리스트</a>
             </el-menu-item>
             <el-menu-item index="4-5">
             		<a href="${ctp}/sportsEquipment/sportsEquipmentOrderListByBranch">발주내역(지점:부산점)</a>
             </el-menu-item>
             <el-menu-item index="4-6">
             		<a href="${ctp}/sportsEquipment/sportsEquipmentInventoryByBranch">재고(지점:부산점)</a>
             </el-menu-item>
           </el-menu-item-group>
           </el-menu-item-group>
           </el-sub-menu>
         </el-sub-menu>
       </el-menu>
     </el-scrollbar>
   </el-aside>
</div>
<script>
	const side = Vue.createApp({
		data() {
			return {
			}
		}
	});

	side.use(ElementPlus, {
	  locale: ElementPlusLocaleKo,
	});
	side.mount("#side");
</script>