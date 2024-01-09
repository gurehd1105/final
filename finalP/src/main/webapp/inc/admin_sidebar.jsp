<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="side" class="bg-gray-50" v-cloak>
	<el-aside width="200px">
      <el-scrollbar class="h-[calc(100vh-60px)]">
        <el-menu :default-openeds="['1', '3']">
          <el-sub-menu index="1">
            <template #title>
              Navigator One
            </template>
            <el-menu-item-group>
              <template #title>Group 1</template>
              <el-menu-item index="1-1">Option 1</el-menu-item>
              <el-menu-item index="1-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="1-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title>Option4</template>
              <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              Navigator Two
            </template>
            <el-menu-item-group>
              <template #title>Group 1</template>
              <el-menu-item index="2-1">Option 1</el-menu-item>
              <el-menu-item index="2-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="2-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="2-4">
              <template #title>Option 4</template>
              <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-sub-menu index="3">
           	<template #title>
              스포츠 장비
            </template>
            <el-menu-item-group>
              <template #title>본사</template>
              <el-menu-item index="3-1">
              		<a href="${pageContext.request.contextPath}/sportsEquipment/insertSportsEquipment">장비리스트 추가</a>	
              </el-menu-item>
              <el-menu-item index="3-2">
              		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByHead">발주내역</a>
              </el-menu-item>
              <el-menu-item index="3-3">
              		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByHead">재고</a>
              </el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="지점">
              <el-menu-item index="3-4">
              		<a href="${pageContext.request.contextPath}/sportsEquipment/SportsEquipmentList">장비리스트</a>
              </el-menu-item>
              <el-menu-item index="3-5">
              		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentOrderListByBranch">발주내역(지점:부산점)</a>
              </el-menu-item>
              <el-menu-item index="3-6">
              		<a href="${pageContext.request.contextPath}/sportsEquipment/sportsEquipmentInventoryByBranch">재고(지점:부산점)</a>
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