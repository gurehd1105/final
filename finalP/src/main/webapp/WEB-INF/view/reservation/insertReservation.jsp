<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
	<h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일</h1>
    <el-select v-model="selectBranch" id="insert" clearable placeholder="지점 선택">
        <el-option v-for="(branch, b) in branchList" :key="b" 
        		   :label="branch.branchName" :value="branch.branchNo" >          
        </el-option> 
    </el-select>
    <br>
    <el-button type="primary" @click="onSubmit">예약하기</el-button>
</c:set>

<c:set var="script">
    data() {
    	return{
    		selectBranch:'',
    		branchList:JSON.parse('${branchList}'),
    	}
    },
    methods: {
        onSubmit() {
            // 예약 정보를 담을 객체
            const reservationData = {
                selectBranch: this.selectBranch,            
            };

            // Ajax 요청을 사용하여 서버에 예약 정보 전송
            axios.post('${ctp}/insertReservation', reservationData)
                .then(response => {
                    // 서버에서 정상적으로 응답을 받았을 때 처리
                    console.log(response.data);
                    // 예약 완료 후 리다이렉트 등 필요한 동작 수행
                    location.href = '${ctp}/calendar';
                }) 
        }
    },
</c:set>

<%@ include file="/inc/user_layout.jsp"%>
