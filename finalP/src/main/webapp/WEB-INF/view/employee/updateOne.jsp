<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="직원 정보수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" 
			action="${ctp}/employee/updateOne" method="post"  id="updateOne" enctype="multipart/form-data">
			
	    <el-form-item label="아이디">
	        <el-input v-model="employee.id" name="employeeId" placeholder="ID"/>
	    </el-form-item>

	    <el-form-item label="이름">
	        <el-input v-model="employee.name" name="employeeName" placeholder="NAME"/>
	    </el-form-item>
	    
	    <el-form-item label="성별">
		    <el-radio-group v-model="employee.gender" name="employeeGender" class="ml-4" >
		      <el-radio label="남">남자</el-radio>
		      <el-radio label="여">여자</el-radio>
		    </el-radio-group>
	    </el-form-item>
	    
	    <el-form-item label="연락처">
	        <el-input v-model="employee.phone" name="employeePhone" placeholder="PHONE"/>
	    </el-form-item>
	    
	    <el-form-item label="이메일">
	    	<el-col :span="14">
		        <el-input v-model="customer.customerEmailId" placeholder="EMAILID"/>
	        </el-col>
	        <el-col :span="2" class="text-center">@</el-col>
	        <el-col :span="8">
	        	<el-autocomplete
			        v-model="customer.customerEmailJuso"
			        :fetch-suggestions="getSuggestion"
			        clearable
			        class="inline-input w-full"
			        placeholder="EMAIL ADDRESS"
			      />
	        </el-col>	      
	    </el-form-item>
	    <el-form-item>
	    	  <el-input type="hidden" name="employeeEmail" :value="employee.employeeEmailId + '@' +employee.employeeEmailJuso">
	    </el-input>
	    
	    <el-form-item label="사진(선택)">
	    	<el-input type="file" name="employeeImg" v-model="employee.employeeImg" id="employeeImg">
	    </el-form-item>
	   
	    <el-form-item>
	      	<el-button type="primary" @click="onSubmit()">완료</el-button>
	    </el-form-item>

	</el-form>
</c:set>
<c:set var="script">
		data() {
			return{
	    	customer: {
	    		id: '${ resultMap.employeeId }',
	    		name: '${ resultMap.employeeName }',
	    		gender: '${ resultMap.employeeGender }',
	    		phone: '${ resultMap.employeePhone }',
	    		employeeEmailId: '${ resultMap.emailId }',
	    		employeeEmailJuso: '${ resultMap.emailJuso }',
	    		employeeImg: '${ resultMap.employeeImg }',
	    	},
	    	emailSuggestion: [
	    		'naver.com',
	    		'gmail.com',
	    		'hanmail.net',
	    		'nate.com',
	    		'daum.net',
	    		'icloud.com'
	    	]
	    }		
		},
	
		methods: {
		validCheck() {
			return true;
		},
		onSubmit() {
			document.getElementById('updateOne').submit();
		},
		getSuggestion(query, cb) {
			const result = this.emailSuggestion.filter(x => x.indexOf(query) !== -1);
			cb(result.map(x => { return { value: x } }));
			console.log(query, result);
		},
	}
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>