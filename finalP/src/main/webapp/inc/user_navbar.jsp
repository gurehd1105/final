<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="ctp" value="${pageContext.request.contextPath}"/>    
<div id="header" v-cloak>
  <el-header class="!p-0">
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      style="background-color:#c6e2ff"
    >
      <el-menu-item index="1">홈</el-menu-item>
      <el-menu-item index="2" >예약</el-menu-item>
      <el-menu-item index="3" >로그인</el-menu-item> 
      
    </el-menu>
  </el-header>
</div>
<script>
  const header = Vue.createApp({
    data() {
      return {
        activeIndex: '1', // 메뉴 초기 선택 항목
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        // 메뉴 선택 시 처리할 내용
        if(key == '1'){
        	location.href = '${ctp}/home';        
        }else if(key == '2'){
        	location.href = '${ctp}/calendar';
        }else if(key == '3'){
        	location.href = '${ctp}/customer/login';

        }
      },
    },
  });
  header.use(ElementPlus, {
    locale: ElementPlusLocaleKo,
  });
  header.mount("#header");
</script>
