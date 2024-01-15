<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="멤버십 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<table>
		<thead style="font-size: 20px;">
			<tr>
				<th>No</th>
				<th>상품명</th>
				<th>가격</th>
			<c:if test="${ loginEmployee != null}">
				<th>생성일</th>
				<th>수정일</th>
			
				<th>수정/삭제</th>
			</c:if>
			<c:if test="${ loginCustomer != null}">
				<th>결제</th>
			</c:if>
			</tr>
		</thead>
		<tbody v-for="(membership, i) in membershipList" :key="i">
			<tr>
				<th>{{ i+1 }}</th>
				<th>{{ membership.membershipName }}</th>
			 	<th>{{ membership.membershipPrice }}</th>
			 <c:if test="${ loginEmployee != null}">
				<th>{{ new Date(membership.createdate).toLocaleDateString() }}</th>
				<th>{{ membership.createdate == membership.updatedate ? "-" : new Date(membership.updatedate).toLocaleDateString() }}</th>
				<th colspan="2">
					<el-button type="primary" @click="update(membership.membershipNo)">수정</el-button>
					<el-button type="primary" @click="delete(membership.membershipNo)">삭제</el-button>
				</th>
			</c:if>
			<c:if test="${ loginCustomer != null}">
				<th><el-button type="primary" @click="insertPayment(membership)">결제하기</el-button></th>
			</c:if>
			</tr>
		</tbody>			
	</table>
</c:set>
<c:set var="script">
	data() {
		return {
			membershipList : JSON.parse('${ membershipList }'),

		}
	},
	methods: {
		update(no){
			location.href = '${ctp}/membership/update?membershipNo='+no;
		},
		
		delete(no){
			location.href = '${ctp}/membership/delete?membershipNo='+no;
		},
		
		insertPayment(membership){
			if(confirm(membership.membershipName + ' 상품을 결제하시겠습니까?')){
				const self = this;
				const payment = {
					membershipNo : membership.membershipNo,
				};
				
				axios.post('${ctp}/payment/insert', payment)
				.then((res) => {
					if(res.data == 1){
						alert('결제가 완료되었습니다 !');
					}
				}).catch((res) => {
					alert('error');
				})
			}
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
