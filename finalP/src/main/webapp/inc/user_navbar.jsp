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
      <el-menu-item index="2" >로그인</el-menu-item> 
      <el-menu-item index="3" >멤버십 상품</el-menu-item>
       <el-menu-item index="4" >프로그램</el-menu-item>
      <el-menu-item index="5" >문의하기</el-menu-item>
      <el-menu-item index="6" >리뷰보기</el-menu-item>
       <el-menu-item index="7" >공지사항</el-menu-item>
      
	<c:if test="${ loginCustomer != null }">
	  <el-menu-item index="8" >예약</el-menu-item>	  
	  <el-menu-item index="9" >내정보</el-menu-item>
	</c:if>
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
        	location.href = '${ctp}/customer/login';
        }else if(key == '3'){
        	location.href = '${ctp}/membership/list';
        }else if(key == '4'){
        	location.href = '${ctp}/program/list';
        }else if(key == '5'){
        	location.href = '${ctp}/question/list';
        }else if(key == '6'){
        	location.href = '${ctp}/review/list';
        }else if(key == '7'){
        	location.href = '${ctp}/review/list';
        }else if(key == '8'){
        	location.href = '${ctp}/calendar';
        }else if(key == '9'){
        	location.href = '${ctp}/customer/customerOne';
        }
      },
    },
  });
  header.use(ElementPlus, {
    locale: ElementPlusLocaleKo,
  });
  header.mount("#header");
</script>
