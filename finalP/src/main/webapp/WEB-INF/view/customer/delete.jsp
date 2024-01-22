<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="회원탈퇴" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/customer/delete" method="post" id="delete">
			<el-form-item label="아이디">
				<el-input v-model="id" name="customerId" readonly />			
			</el-form-item> 
			
			<el-form-item label="비밀번호">
				<el-input type="password" v-model="pw" name="customerPw" placeholder="PASSWORD" />			
			</el-form-item> 
			
			<el-form-item label="비밀번호 확인">
				<el-input type="password" v-model="pwCk" placeholder="PASSWORD CHECK"  />			
			</el-form-item> 
			
			<el-form-item>
				<el-input type="hidden" :value="no" v-model="id" name="customerNo" />			
			</el-form-item> 
			
			<el-form-item>
				<el-button type="primary" @click="submit()">탈퇴</el-button>
			</el-form-item> 
			 
			 
	</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			no: '${ loginCustomer.customerNo }',
			id: '${ loginCustomer.customerId }',
			pw: '',
			pwCk: '',
		}
	},
	methods: {
		submit(){
			const self = this;
			const customer = {
				customerId: this.id,
				customerPw: this.pw,
			};
			axios.post('${ctp}/customer/pwCheck', customer)
			.then((res) => {
				if(res.data){
					if(this.pw != this.pwCk){
						self.$notify({
						  title: 'PW 오류',
						  message: 'PW 확인이 일치하지 않습니다.',
						  type: 'error',
						})
					} else {
						if(confirm('정말로 탈퇴하시겠습니까?')){
						alert('탈퇴가 완료되었습니다.');
						document.getElementById('delete').submit();
						}
					}
					
				} else {
					self.$notify({
					  title: 'PW 오류',
					  message: '비밀번호가 일치하지 않습니다.',
					  type: 'error',
					})
				}
			}).catch((res) => {
				self.$notify({
				  title: '페이지 오류',
				  message: '잠시 후 시도해주세요.',
				  type: 'error',
				})	
			})	
			
		},
	}

</c:set>
<%@ include file="/inc/user_layout.jsp" %>