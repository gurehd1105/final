<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reservation calendar</title>
<!-- Import style -->
    <link
      rel="stylesheet"
      href="https://unpkg.com/element-plus/dist/index.css"
    />
    <!-- Import Vue 3 -->
    <script src="https://unpkg.com/vue@3"></script>
    <!-- Import component library -->
    <script src="https://unpkg.com/element-plus"></script>
</head>
<body>
<!--  
	<!-- 예약 캘린더 -->
  	<div id="app">
  	  <!-- 캘린더 출력 -->
      <el-calendar  v-model="currentDate" @change="handleDateChange"></el-calendar>
    </div>

    <script>
      // Vue 3 인스턴스 생성
      const app = Vue.createApp({
    	// 화면에 보여질 데이터
    	data() {
          return {
        	  currentDate: new Date(),      
          };
        },
        // 뷰페이지에 들어갈 데이터
        methods: {
        	handleDateChange(date) {
        		// 선택된 날짜를 콘솔에 출력
           	 	console.log('Selected Date:', date);
          },          	
        },
      });

      // Element Plus를 사용하기 위한것
      app.use(ElementPlus);

      // Vue 인스턴스를 마운트
      app.mount("#app");
    </script>        
</body>
</html>