<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="PW확인" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

	<el-form action="${ctp}/customer/updateOneForm" ref="form" id="update"
		label-position="right" label-width="150px" status-icon method="post"> 
		
	<el-form-item label="아이디">
		<el-input readonly name = "customerId" v-model="id" /> 
	</el-form-item> 
	
	<el-form-item label="비밀번호">
		<el-input type="password" name="customerPw" v-model="pw" placeholder="PASSWORD"/> 
	</el-form-item> 
	
	<el-form-item>
		<el-input type="hidden" name="customerNo" v-model="no"/> 
	</el-form-item> 
	
	<el-form-item>
		<el-button type="primary" @click="submit()">확인</el-button> 
	</el-form-item> 
	
	</el-form>
</c:set>

<c:set var="script">
	data() {
		return {
			no: '${loginCustomer.customerNo}',
			id: '${loginCustomer.customerId}',
			pw: '',
		}
	},
	
	
	methods: {
		submit(){
			const self = this;
			const customer = {
				customerNo: this.no,
				customerId: this.id,
				customerPw: this.pw,
			};
			axios.post('${ctp}/customer/pwCheck', customer)
			.then((res) => {
				if(res.data){
					document.getElementById('update').submit();
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
	},
	
</c:set>
<%@ include file="/inc/user_layout.jsp" %>