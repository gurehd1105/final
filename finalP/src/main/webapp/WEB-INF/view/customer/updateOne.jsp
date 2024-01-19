<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="정보수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" 
			action="${ctp}/customer/updateOne" method="post"  id="updateOne" enctype="multipart/form-data">
			
			<el-form-item>	
		 <el-upload
		    class="avatar-uploader"
		    action="/file/upload/customer"
		    :show-file-list="false"
		    :on-success="handleSuccess"
		  >
		    <img v-if="customer.customerImg" :src="customer.customerImg" class="avatar" />
		    <el-icon v-else class="avatar-uploader-icon"><Plus /><span>+</span></el-icon>
		  </el-upload> 
		 <input v-if="customer.customerImg" type="hidden" :value="customer.customerImg" name="customerImg"/>
		 
		  </el-form-item>
			
	    <el-form-item label="아이디">
	        <el-input v-model="customer.id" name="customerId" placeholder="ID"/>
	    </el-form-item>

	    <el-form-item label="이름">
	        <el-input v-model="customer.name" name="customerName" placeholder="NAME"/>
	    </el-form-item>
	    
	    <el-form-item label="성별">
		    <el-radio-group v-model="customer.gender" name="customerGender" class="ml-4" >
		      <el-radio label="남">남자</el-radio>
		      <el-radio label="여">여자</el-radio>
		    </el-radio-group>
	    </el-form-item>
	    
	    <el-form-item label="연락처">
	        <el-input v-model="customer.phone" name="customerPhone" placeholder="PHONE"/>
	    </el-form-item>
	    
	    <el-form-item label="키">
			<el-input-number v-model="customer.height" controls-position="right" name="customerHeight" max="250"/>
	    </el-form-item>
	    
	    <el-form-item label="몸무게">
			<el-input-number v-model="customer.weight" controls-position="right" name="customerWeight" max="150"/>
	    </el-form-item>
	    
	    <el-form-item label="우편번호">
	        <el-input v-model="customer.address.postCode" disabled>
		      <template #append>
		        <el-button @click="openPostCode()">
		        	우편번호 찾기
		        </el-button>
		      </template>
	        </el-input>
	    </el-form-item>
	    
	    <el-form-item label="주소">
			<el-input v-model="customer.address.address" name="customerAddress" placeholder="ADDRESS"/>
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
	    	  <el-input type="hidden" name="customerEmail" :value="customer.customerEmailId + '@' +customer.customerEmailJuso">
	    </el-input>
	   
	    <el-form-item>
	      	<el-button type="primary" @click="onSubmit()">완료</el-button>
	    </el-form-item>

	</el-form>
</c:set>
<c:set var="script">
		data() {
			return{
	    	customer: {
	    		id: '${ resultMap.customerId }',
	    		name: '${ resultMap.customerName }',
	    		gender: '${ resultMap.customerGender }',
	    		phone: '${ resultMap.customerPhone }',
	    		height: '${ resultMap.customerHeight }',
	    		weight: '${ resultMap.customerWeight }',
	    		address: {
	    			postCode: '',
	    			address:'${ resultMap.customerAddress }',
	    		},
	    		
	    		customerEmailId: '${ resultMap.emailId }',
	    		customerEmailJuso: '${ resultMap.emailJuso }',
	    		customerImg: '${ resultMap.customerImgOriginName }',
	    	},
	    	emailSuggestion: [
	    		'naver.com',
	    		'gmail.com',
	    		'hanmail.net',
	    		'nate.com',
	    		'daum.net',
	    		'icloud.com'
	    	],
	    	url: '',
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
		handleSuccess(response, uploadFile) {
			this.customer.customerImg = '${ctp}/upload/customer/' + response;
		},
			
		openPostCode() {
			const self = this;
			new daum.Postcode(
			{
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 각 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var addr = ''; // 주소 변수
					var extraAddr = ''; // 참고항목 변수

					//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						addr = data.roadAddress;
					} else { // 사용자가 지번 주소를 선택했을 경우(J)
						addr = data.jibunAddress;
					}
		
					// 데이터 바인딩
					self.customer.address = {
						postCode: data.zonecode,
						address: addr,						
					}
				}
			}).open();
		}
	}
</c:set>



<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
  margin-left: 40%;
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>

<%@ include file="/inc/user_layout.jsp" %>