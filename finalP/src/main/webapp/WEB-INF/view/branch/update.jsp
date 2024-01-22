<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="지점 정보 수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
	action="${ctp}/branch/update" method="post" id="update">
	
	<input :value="branch.branchNo" name="branchNo" type="hidden">
	</input>
	
	<el-form-item label="제목">
		<el-input v-model="branch.branchName" name="branchName">
	</el-form-item>
	<el-form-item label="전화번호">
		<el-input v-model="branch.branchTel" name="branchTel">
	</el-form-item>
	
	<el-form-item label="현주소">
		<el-input v-model="branch.branchAddress" name="branchAddress" disabled>
	</el-form-item>
	
	<el-form-item label="우편번호">
        <el-input v-model="branch.address.postCode" id="postCode" disabled>
	      <template #append>
	        <el-button @click="openPostCode()">
	        	우편번호 찾기
	        </el-button>
	      </template>
        </el-input>
    </el-form-item>
	    
    <el-form-item label="주소">
		<el-input v-model="branch.address.address" name="branchAddress" id="address" placeholder="ADDRESS"/>
    </el-form-item>
    
	<el-button type="primary" @click="submit()">완료</el-button>
	
</el-form>
</c:set>

<c:set var="script">
	data() {
    return {
        branch: {
                branchNo: ${branch.branchNo}, 
                branchName: '${branch.branchName}', 
                branchTel: '${branch.branchTel}', 
                branchAddress: '${branch.branchAddress}', 
                address: {
                    postCode: '${branch.address.postCode}',  <!-- 기존 주소 정보 로드  --> 
                    address: '${branch.address.branchAddress}',  <!-- 기존 주소 정보 로드  --> 
                },
        },      
    }
},
	methods: {
    submit() {
			document.getElementById('update').submit();
		},
  	openPostCode() {
			this.branch.address.address = '';
			document.querySelector('#address').disabled = true;
			const self = this;
			new daum.Postcode(
			{
				oncomplete : function(data) {
					document.querySelector('#address').disabled = false;
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
					self.branch.address = {
						postCode: data.zonecode,
						address: addr,						
					}
				}
			}).open();
		}
	}
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>