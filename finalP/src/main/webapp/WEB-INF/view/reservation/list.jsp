<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://unpkg.com/element-plus/dist/index.css">
<script src="https://unpkg.com/element-plus/dist/index.full.js"></script>

<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="예약목록" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
    <h1>${targetYear}년 ${targetMonth}월 ${targetDay}일 예약</h1>
    <el-table :data="reservationList"> 
    	<el-table-column prop="paymentDate" label="결제일" width="150" :formatter="formatDate" ></el-table-column>
        <el-table-column prop="branchName" label="지점명"></el-table-column>
        <el-table-column prop="programName" label="프로그램"></el-table-column>
        <el-table-column prop="programReservationNo" label="예약번호" width="150"></el-table-column>
        <el-table-column prop="programDate" label="예약일자" width="150" :formatter="formatDate" ></el-table-column>
        <el-table-column label="삭제">
            <template #default="scope">
                <el-button type="danger" @click="deleteReservation(scope.row.programReservationNo, targetYear, targetMonth, targetDay)">삭제</el-button>
            </template>
        </el-table-column>
    </el-table>
    <br>
    <el-button type="primary" @click="reservation()" style="float: right;">예약하기</el-button>
</c:set>

<c:set var="script">
    data() {
        return {        
            programList: JSON.parse('${programList}'),   
            reservationList: JSON.parse('${reservationList}'),   
            targetYear:'${targetYear}', 
            targetMonth:'${targetMonth}', 
            targetDay:'${targetDay}',  
        };
      
    },
    
    methods: {
        reservation() {    
            if(${ loginCustomer.paymentActive != '사용중' }){
              alert('멤버십 이용권 구매 후 이용해주세요.');
            }else {
               location.href = '${ctp}/reservation/insert?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${targetDay}';
            }     
          
        },        
        deleteReservation(no, targetYear, targetMonth, targetDay) {
		    if (confirm("이 예약을 삭제하시겠습니까?")) {
		        axios.post('${ctp}/reservation/delete', {
		            programReservationNo: no}, {
		            	params: {
		                targetYear: targetYear,
		                targetMonth: targetMonth,
		                targetDay: targetDay
		            	}
		        })
		        .then(response => {
		            console.log(response.data);
		            alert('예약이 삭제되었습니다.');
		            // 삭제 성공 후의 동작 
		            location.reload(); // 현재 페이지 새로고침
		        })
		        .catch(error => {
		            console.error(error);
		            alert('출석을 한 예약은 삭제가 불가능 합니다.');
		           
		        });
		    }
		},
        formatDate(row, column, cellValue) {
       		// 진행일 열은 시간을 표시하지 않고 날짜만 표시         
            return new Date(cellValue).toLocaleDateString();
     
        },
        
    },
</c:set>

<%@ include file="/inc/user_layout.jsp" %>
