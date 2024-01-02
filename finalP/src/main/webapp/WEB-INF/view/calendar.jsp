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
        <h1>예약하기</h1>

        <!-- 캘린더 -->
        <div class="mt-3">
            <a href="${contextPath}/calendar?targetMonth=${calendarMap.targetMonth-1}&targetYear=${calendarMap.targetYear}">
                <button type="button" class="btn btn-primary">이전달</button>
            </a>
            <a href="${contextPath}/calendar?targetMonth=${calendarMap.targetMonth+1}&targetYear=${calendarMap.targetYear}">
                <button type="button" class="btn btn-primary">다음달</button>
            </a>
        </div>
        <div class="mt-2">
            ${calendarMap.targetYear}년
            ${calendarMap.targetMonth + 1}월
        </div>
        
        <el-calendar v-model="date">
        </el-calendar>

        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th scope="col">일</th>
                    <th scope="col">월</th>
                    <th scope="col">화</th>
                    <th scope="col">수</th>
                    <th scope="col">목</th>
                    <th scope="col">금</th>
                    <th scope="col">토</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <c:forEach var="i" begin="1" end="${calendarMap.totalTd}">
                        <c:set var="d" value="${i - calendarMap.beginBlank}"/>
                        <td>
                            <c:if test="${d < 1 || d > calendarMap.lastDate}">
                                &nbsp;
                            </c:if>
                            <c:if test="${!(d < 1 || d > calendarMap.lastDate)}">
                                <a href="javascript:void(0);" onclick="openPopup(${calendarMap.targetYear}, ${calendarMap.targetMonth+1}, ${d})">${d}</a>
                                <c:forEach var="s" items="${scheduleList}">
                                    <c:if test="${d == s.day}">
                                        <div>${s.cnt}</div>
                                        <div>${s.memo}</div>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </td>
                        <c:if test="${i < calendarMap.totalTd && i % 7 == 0}">
                            </tr><tr>
                        </c:if>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
    </div>
</c:set>

<script>
    function openPopup(targetYear, targetMonth, targetDay) {
        var url = "${contextPath}/reservation?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDay=" + targetDay;
        window.open(url, "popupWindow", "width=800, height=600");
    }
</script>

<c:set var="script">
    {
        data() {
            return {
                date: new Date()
            }
        },
        watch: {
            date(newValue, prevValue) {
                this.log(newValue, prevValue);
            }
        },
        methods: {
            log(args) {
                console.log(args);
            }
        }
    };
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
