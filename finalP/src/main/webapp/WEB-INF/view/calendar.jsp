<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="메인페이지" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<c:set var="body">
    <div class="container mt-5">
    	<div>
	        <el-text size="large" tag="b">예약하기</el-text>
    	</div>

        <!-- 캘린더
        <el-button-group class="space-x-4">
            <a href="${contextPath}/calendar?targetMonth=${calendarMap.targetMonth-1}&targetYear=${calendarMap.targetYear}">
                <el-button type="primary">이전달</el-button>
            </a>
            <a href="${contextPath}/calendar?targetMonth=${calendarMap.targetMonth+1}&targetYear=${calendarMap.targetYear}">
                <el-button type="primary">다음달</el-button>
            </a>
        </el-button-group>
         -->
        
        <el-calendar v-model="date">
        </el-calendar>
    </div>
</c:set>

<c:set var="script">
    data() {
        return {
            date: new Date(${calendarMap.targetYear}, ${calendarMap.targetMonth + 1})
        }
    },
    watch: {
        date(nv, pv) {
            const year = nv.getFullYear();
            const month = nv.getMonth() + 1;
            const day = nv.getDay();
            this.openPopup(year, month, day);
        }
    },
    methods: {
        openPopup(targetYear, targetMonth, targetDay) {
	        var url = "${contextPath}/reservation?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDay=" + targetDay;
	        window.open(url, "popupWindow", "width=800, height=600");
	    }
    }
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
