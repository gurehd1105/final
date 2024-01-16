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
	<el-form-item label="제목">
		<el-input v-model="branch.branchTel" name="branchTel">
	</el-form-item>
	
	<el-form-item label="우편번호">
        <el-input v-model="branch.address.postCode" disabled>
	      <template #append>
	        <el-button @click="openPostCode()">
	        	우편번호 찾기
	        </el-button>
	      </template>
        </el-input>
    </el-form-item>
	    
    <el-form-item label="주소">
		<el-input v-model="branch.address.address" name="address1" placeholder="ADDRESS"/>
    </el-form-item>
    
    <el-form-item label="상세주소">
		<el-input v-model="branch.address.detailAddr" name="address2" placeholder="ADDRESS"/>
    </el-form-item>
    
    <el-form-item label="참고주소">
		<el-input v-model="branch.address.extraAddr" name="address3" placeholder="ADDRESS"/>
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
            address: {
                postCode: '',
                address: '',
                detailAddress: '',
                extraAddr: '',
            },  
        },      
    }
},
	methods: {
    submit() {
			document.getElementById('update').submit();
		},
  	openPostCode() {
        const self = this;
        new daum.Postcode({
            oncomplete: function(data) {
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
				self.model.address = {
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
<%@ include file="/inc/admin_layout.jsp" %>