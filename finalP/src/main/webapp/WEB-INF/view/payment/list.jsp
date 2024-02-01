<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="결제자목록" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

			<p>결제 목록 </p>
			<span><el-input  style="width: 150px;" v-model="customerName" placeholder="고객명"/></span>
				<el-button type="primary" @click="select(customerName)">조회</el-button>
				
			
		<el-table :data="paymentList" style="width: 100%" @row-click="rowClick">
		    <el-table-column prop="paymentNo" label="결제번호" ></el-table-column>
		     <el-table-column prop="paymentDate" label="결제일자" ></el-table-column>
		    <el-table-column prop="customerId" label="고객 ID" ></el-table-column>
		    <el-table-column prop="customerName" label="고객명" ></el-table-column>
		    <el-table-column prop="membershipName" label="이용권 이름" ></el-table-column>
		     <el-table-column prop="membershipMonth" label="이용권 기간(개월)" ></el-table-column>
		     <el-table-column prop="membershipPrice" label="결제 가격" ></el-table-column>
		     <el-table-column v-if="isEmployee" label="내역삭제(결제취소)" >
		     	 <template #default="scope">
		     	 	<el-button type="danger" @click="remove(scope.row)">취소</el-button>
		     	 </template>
		     </el-table-column>
		</el-table>
		
		
 <!-- 페이징 네비게이션 -->
	<%@ include file="/inc/pagination.jsp" %>
    
</c:set>
<c:set var="script">
	data(){
		return{
			paymentList: JSON.parse('${ paymentList }'),
			pageNum: ${page.pageNum},
			rowPerPage: ${page.rowPerPage },
			totalCount: ${page.totalCount},
			totalPage: ${page.totalPage},
			isEmployee: '${loginEmployee}',
			customerName: '${customerName}',
		}
	},
	methods: {
		select(name){
			location.href='${ctp}/payment/list?customerName='+name;
		},
		
		remove(row){
			if(confirm('결제내역 삭제(승인취소) 후 되돌릴 수 없습니다. 해당 결제내역을 취소하시겠습니까?')){
				const self = this;
				const payment = {
					paymentNo: row.paymentNo,
				};
				axios.post('${ctp}/payment/delete', payment)
				.then((res) => {
					if(res.data == 1){
						alert('취소가 완료되었습니다.');
						location.href='${ctp}/payment/list';
					}
				}).catch((res) => {
					alert('error');
				})
			}
		},
		
		loadPage(pageNum) {	<!-- 페이징함수 -->
	      	const param = new URLSearchParams();
	      	param.set('pageNum', this.pageNum);
	      	param.set('rowPerPage', this.rowPerPage);    
	      	param.set('customerName', this.customerName)
			location.href = '${ctp}/payment/list?'+param.toString();
      	},
	},
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>