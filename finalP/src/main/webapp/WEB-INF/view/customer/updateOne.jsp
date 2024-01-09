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
			<el-input-number v-model="customer.height" controls-position="right" name="customerHeight"/>
	    </el-form-item>
	    
	    <el-form-item label="몸무게">
			<el-input-number v-model="customer.weight" controls-position="right" name="customerWeight"/>
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
			<el-input v-model="customer.address.address" name="address1" placeholder="ADDRESS"/>
	    </el-form-item>
	    
	    <el-form-item label="상세주소">
			<el-input v-model="customer.address.detailAddr" name="address2" placeholder="ADDRESS"/>
	    </el-form-item>
	    
	    <el-form-item label="참고주소">
			<el-input v-model="customer.address.extraAddr" name="address3" placeholder="ADDRESS"/>
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
	    
	    <el-form-item label="사진(선택)">
	    	<el-input type="file" name="customerImg" v-model="customer.customerImg" id="customerImg">
	    </el-form-item>
	   
	    <el-form-item>
	      	<el-button type="primary" @click="onSubmit(form)">수정</el-button>
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
	    		},
	    		customerEmailId: '${ resultMap.emailId }',
	    		customerEmailJuso: '${ resultMap.emailJuso }',
	    		customerImg: '${ resultMap.customerImg }',
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
		}
	}
</c:set>
<%@ include file="/inc/user_layout.jsp" %>