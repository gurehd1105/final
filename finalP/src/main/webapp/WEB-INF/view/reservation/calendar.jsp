<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="예약" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<c:set var="body">
    <div class="container mt-5">
        <div>
            <el-text size="large" tag="b">예약하기</el-text>
        </div>
        <el-calendar v-model="date">
            
        </el-calendar>
    </div>
</c:set>

<c:set var="script">
    data() {
        return {
            date: new Date(${calendarMap.targetYear}, ${calendarMap.targetMonth}, ${calendarMap.targetDay})
        }
    },
    watch: {
        date(nv, pv) {
            const year = nv.getFullYear();
            const month = nv.getMonth() + 1;
            const day = nv.getDate();
            this.openPage(year, month, day);
        }
    },
    methods: {
        selectDate(type) {
            if (type === 'prev-month') {
                this.date = new Date(this.date.getFullYear(), this.date.getMonth() - 1);
            } else if (type === 'today') {
                this.date = new Date();
            } else if (type === 'next-month') {
                this.date = new Date(this.date.getFullYear(), this.date.getMonth() + 1);
            }
        },
       openPage(targetYear, targetMonth, targetDay) {
          location.href = "${ctp}/reservation/list?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDay=" + targetDay;          
        }
    }
</c:set>

<%@ include file="/inc/user_layout.jsp" %>