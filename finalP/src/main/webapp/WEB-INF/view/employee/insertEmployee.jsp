<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Form</title>
    <!-- Vue 3 CDN 추가 -->
    <script src="https://unpkg.com/vue@next"></script>
    <!-- Element Plus CDN 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/element-plus/lib/theme-chalk/index.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/element-plus/lib/index.full.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="app">
        <el-form :model="formData" label-width="80px">
            <el-form-item label="ID">
                <el-input v-model="formData.employeeId" placeholder="Enter ID"></el-input>
            </el-form-item>
            <el-form-item label="Password">
                <el-input v-model="formData.employeePw" placeholder="Enter Password" type="password"></el-input>
            </el-form-item>
        </el-form>
    </div>
    
    <script>
        const app = Vue.createApp({
            data() {
                return {
                    formData: {
                    	employeeId: '',
                    	employeePw: ''
                    }
                };
            }
        });

        app.mount('#app');
    </script>
</body>
</html>