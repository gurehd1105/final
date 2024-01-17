<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원가입" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${ctp}/customer/insert" method="post" enctype="multipart/form-data" id="insertForm">
		 
			<el-form-item label="사진">
		<el-upload 
		   class="avatar-uploader"
		   action="/file/upload/customer"
		   :show-file-list="false"
		   :on-success="handleSuccess"
		 >
		   <img v-if="customer.customerImg" :src="customer.customerImg" class="avatar"/>		   	
		   <el-icon v-else class="avatar-uploader-icon" ><Plus /><span>(선택)</span></el-icon>
		 </el-upload>
		 <input v-if="customer.customerImg" type="hidden" :value="customer.customerImg" name="customerImg"/>
				 </el-form-item>
		  
	    <el-form-item label="아이디">
	        <el-input v-model="customer.id" id="checkId" placeholder="ID"><template #append>
        <el-button @click="duplication()">중복확인</el-button>
      </template></el-input>
	    </el-form-item>   
	    
	    <el-form-item label="비밀번호">
	        <el-input v-model="customer.pw" type="password" show-password name="customerPw" placeholder="PASSWORD"/>
	    </el-form-item>
	    
	    <el-form-item label="비밀번호 확인">
	        <el-input v-model="customer.pwChk" type="password" show-password placeholder="PASSWORD CHECK"/>
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
	        <el-input v-model="customer.address.postCode" id="postCode" disabled>
		      <template #append>
		        <el-button @click="openPostCode()">
		        	우편번호 찾기
		        </el-button>
		      </template>
	        </el-input>
	    </el-form-item>
	    
	    <el-form-item label="주소">
			<el-input v-model="customer.address.address" placeholder="ADDRESS"/>
	    </el-form-item>
	    
	    <el-form-item label="상세주소">
			<el-input v-model="customer.address.detailAddr" id="detailAddr" placeholder="ADDRESS"/>
	    </el-form-item>
	    
	    <el-form-item label="참고주소">
			<el-input v-model="customer.address.extraAddr" placeholder="ADDRESS"/>
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
	      	<el-button type="primary" @click="onSubmit(form)">회원가입</el-button>
	    </el-form-item>
	    
	    <el-form-item>
	        <el-input type="hidden" id="customerId" name="customerId"/>
	    </el-form-item>
	    
	     <el-form-item>
	    	  <el-input type="hidden" name="customerEmail" :value="customer.customerEmailId + '@' +customer.customerEmailJuso">
	    </el-input>	    
	    
	    <el-form-item>
			<el-input type="hidden" id="address" :value="customer.address.address + ' ' + customer.address.detailAddr + customer.address.extraAddr" name="address"/>
	    </el-form-item>
	</el-form>
</c:set>

<c:set var="script">
	data() {
	    return {
	    	customer: {
	    		id: '',
	    		pw: '',
	    		pwChk: '',
	    		name: '',
	    		gender: '',
	    		phone: '',
	    		height: 170,
	    		weight: 70,
	    		address: {
	    			postCode: '',
	    			
	    		},
	    		customerEmailId: '',
	    		customerEmailJuso: '',
	    		customerImg: '',
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
	watch: {
		
	},
	methods: {
		validCheck() {
			return true;
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

					// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
					if (data.userSelectedType === 'R') {
						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== ''
								&& /[동|로|가]$/g.test(data.bname)) {
							extraAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== ''
								&& data.apartment === 'Y') {
							extraAddr += (extraAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraAddr !== '') {
							extraAddr = ' (' + extraAddr + ')';
						}

					}
					
					// 데이터 바인딩
					self.customer.address = {
						postCode: data.zonecode,
						address: addr,
						detailAddress: '',
						extraAddr: extraAddr,
					}
				}
			}).open();
		},
		
		onSubmit() {	<!-- submit 및 계정정보 유효성검사 -->
		const finalId = document.getElementById('customerId').value;
		const address = document.getElementById('address').value;
		const detailAddr = document.getElementById('detailAddr').value;
		const postCode = document.getElementById('postCode').value;
			if(finalId.length < 1){
				alert('ID 중복을 확인해주세요.');
			} else if(this.customer.pw.length < 4 || this.customer.phone.length < 4
			 || this.customer.customerEmailId.length < 4 || this.customer.customerEmailJuso.length < 4 ){
				alert('이름 외 모든 란은 4글자 이상 입력해주세요.');
			} else if(this.customer.name.length < 2) {
				alert('이름은 2글자 이상 입력해주세요.');
			} else if(this.customer.gender.length < 1) {
				alert('성별을 선택해주세요.');
			} else if(this.customer.pw != this.customer.pwChk) {
				alert('비밀번호 확인 란이 정확하지 않습니다.');
			} else if(detailAddr.length < 1 || postCode.length < 1) {
				alert('우편번호 및 상세주소를 입력해주세요.');
			} else {
				document.getElementById('insertForm').submit();
			}		
		},
		
		duplication(){<!-- 중복확인 -->
			if(this.customer.id.length < 4){
				alert('이름 외 모든 란은 4글자 이상 입력해주세요.');
				document.getElementById('customerId').value = '';
			} else{
				const self = this;
				const customer = {
					customerId: this.customer.id,
				};
				axios.post('${ctp}/customer/idCheck', customer)
				.then((res) => {
					if(res.data == 0){
						alert('사용가능한 ID입니다.');
						document.getElementById('customerId').value = '';
						document.getElementById('customerId').value = this.customer.id;
					} else {
						alert('중복 ID입니다.');
						document.getElementById('customerId').value = '';
					}
				}).catch((res) => {
					alert('error');
				})	
			}		
		},
		
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