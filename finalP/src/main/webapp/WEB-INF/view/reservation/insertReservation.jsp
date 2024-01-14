<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
    <h1>${targetYear}년 ${targetMonth}월 ${targetDay}일</h1>
    <form id="reservationForm" action="${ctp}/insertReservation" method="post">

        <el-select v-model="selectBranch" id="selectBranch" clearable placeholder="지점 선택">
    		<el-option v-for="(branch, b) in branchList" :key="b" :label="branch.branchName" :value="branch.branchNo">          
    		</el-option>
        </el-select>
   
        <el-select v-model="selectProgram" id="selectProgram" clearable placeholder="프로그램 선택">
            <el-option v-for="(program, p) in programList" :key="p" 
                       :label="program.programName" :value="program.programName">
            </el-option> 
        </el-select>
        <el-button type="primary" @click="insert">예약하기</el-button>
    </form>
    
</c:set>

<c:set var="script">
    data() {
        console.log('Program List:', this.programList);

        return {         
            selectBranch: '',
            branchList: JSON.parse('${branchList}'),           
            selectProgram: '',
            programList: JSON.parse('${programList}'),
        }
    },
    methods: {
        insert() {
        	 console.log('Selected Branch:', this.selectBranch);
            // 예약 정보를 서버로 전송하는 코드
            const branchNo = parseInt(this.selectBranch);
            const reservationData = {
                branchNo: branchNo,
                programName: this.selectProgram,
                targetYear: ${targetYear},
                targetMonth: ${targetMonth},
                targetDay: ${targetDay},
            };

            // 실제로는 Ajax 또는 fetch를 사용하여 서버로 데이터를 전송합니다.
            fetch('${ctp}/insertReservation', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reservationData),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Server Response:', data);
                // 여기서 필요한 UI 업데이트 등을 수행할 수 있습니다.
            })
            .catch(error => {
                console.error('Error:', error);
            });
        },
    },
</c:set>

<%@ include file="/inc/user_layout.jsp"%>
