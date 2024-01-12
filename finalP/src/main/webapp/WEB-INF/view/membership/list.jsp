<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="멤버십 리스트" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	<table>
		<thead>
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
				<td>{{ i+1 }}</td>
				<td>{{ (membership.membershipName }}</td>
			 	<td>{{ membership.membershipPrice }}</td>
				<td>{{ new Date(membership.createdate).toLocaleDateString() }}</td>
				<td>{{ membership.createdate == membership.updatedate ? "-" : new Date(membership.updatedate).toLocaleDateString() }}</td>
				<td colspan="2">
					<el-button type="primary" @click="update(membership.membershipNo)">수정</el-button>
					<el-button type="primary" @click="delete()">삭제</el-button>
				</td>
			</tr>
		</tbody>			
	</table>
</c:set>
<c:set var="script">
	data() {
		return {
			membershipList : '${ membershipList }',
		}
	},
	methods: {
		update(no){
			location.href = '${ctp}/membership/update?membershipNo='+no;
		},
		
		delete(){
		
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
