<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<c:set var="title" value="출석" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
    <el-form id="attendanceForm" action="${ctp}/attendance/insert" method="post" class="max-w-lg">
        <el-form-item>
            <el-text size="large" tag="b">출석하기</el-text>
        </el-form-item>
        <el-form-item>
            <el-input v-model="reservationNo" id="reservationNo" clearable placeholder="예약번호 입력"></el-input>
        </el-form-item>
        
        <el-form-item>
            <el-input v-model="currentTime" name="customerAttendanceEnterTime" readonly>
                {{ formattedTime }}
            </el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="insert">출석하기</el-button>
        </el-form-item>
    </el-form>
</c:set>

<c:set var="script">
    data() {
        return {
            reservationNo: '',
            currentTime: moment().format('YYYY-MM-DD HH:mm:ss'),
        }
    },
    computed: {
        formattedTime() {
            return moment(this.currentTime).format('YYYY-MM-DD HH:mm:ss');
        }
    },
    methods: {
    insert() {
    	const self = this;
    	const paramMap = {
    		reservationNo: this.reservationNo,
        	currentTime: this.currentTime.toString(),
    	};
    	
        // 출석 정보를 서버로 전송하는 비동기 요청
        axios.post(`${ctp}/attendance/insert`, paramMap)
            .then((res) => {
                if (res.data == 1) {
                    // 출석 성공
                    alert('출석이 완료되었습니다.');
                    // 페이지 리디렉션
                    location.href = `${ctp}/attendance/list`;
                } else {
                    // 출석 실패
                    alert('출석에 실패했습니다.');
                     console.log(paramMap);
                }
            })
            .catch((res) => {
                // 오류 처리
                console.log(paramMap);
                alert('출석 처리 중 오류가 발생했습니다.');
            });
    }
},
    mounted() {
        // 1초마다 현재 시간을 업데이트
        setInterval(() => {
            this.currentTime = moment().format('YYYY-MM-DD HH:mm:ss');
        }, 1000);
    }
</c:set>

<%@ include file="/inc/user_layout.jsp" %>
