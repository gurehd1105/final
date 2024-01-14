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
				<th>생성일</th>
				<th>수정일</th>
				<th>수정/삭제</th>
			</tr>
		</thead>
		<tbody v-for="(membership, i) in membershipList" :key="i">
			<tr>
				<th>{{ i+1 }}</th>
				<th>{{ membership.membershipName }}</th>
			 	<th>{{ membership.membershipPrice }}</th>
				<th>{{ new Date(membership.createdate).toLocaleDateString() }}</th>
				<th>{{ membership.createdate == membership.updatedate ? "-" : new Date(membership.updatedate).toLocaleDateString() }}</th>
				<th colspan="2">
					<el-button type="primary" @click="update(membership.membershipNo)">수정</el-button>
					<el-button type="primary" @click="delete(membership.membershipNo)">삭제</el-button>
				</th>
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
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
