<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="멤버십 수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">	
	
		<h3>ㅋㅋ</h3>
	<table>
		<tr>
			<th>작업자</th>
			<td>{{ loginEmplyee.employeeId }}</td>
		</tr>
		
		<tr>
			<th>상품명</th>
			<td><el-input name="membershipName" v-model="membership.membershipName"/></td>
		</tr>
		
		<tr>
			<th>이용기간(개월 수)</th>
			<td><el-input-number name="membershipMonth" v-model="membership.membershipMonth" /></td>
		</tr>
		
		<tr>
			<th>가격</th> 
			<td><el-input-number name="membershipPrice" v-model="membership.membershipPrice"/></td>
		</tr>
	</table>


</c:set>
<c:set var="script">
	data(){
		return {
			membership: JSON.parse('${membership}'),			
		}
	},
</c:set>