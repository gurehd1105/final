<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/2.29.1/moment.min.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
    <el-form id="attendanceForm" action="${ctp}/insertAttendance" method="post" class="max-w-lg">
        <el-form-item>
            <el-text size="large" tag="b">출석하기</el-text>
        </el-form-item>
        <el-form-item>
            <el-input v-model="reservationNo" id="reservationNo" clearable placeholder="예약번호 입력"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="currentTime" readonly>
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
            // 출석 정보를 서버로 전송하는 비동기 요청
            axios.post('<c:url value="/insertAttendance"/>', {
                    reservationNo: this.reservationNo,
                    currentTime: this.currentTime
                })
                .then(response => {
                    console.log(response.data); // 서버 응답 로그
                    // 출석 성공 시의 로직 추가 가능
                })
                .catch(error => {
                    console.error('출석 제출 오류:', error);
                    // 오류 처리 또는 오류 메시지 표시
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

<%@ include file="/inc/admin_layout.jsp" %>
