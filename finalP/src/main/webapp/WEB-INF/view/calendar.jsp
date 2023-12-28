<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="메인페이지" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<c:set var="body">
    <h1>예약하기</h1>
    <!-- 캘린더 -->
    <div>
        <a href="${contextPath}/calendar?targetMonth=${calendarMap.targetMonth-1}&targetYear=${calendarMap.targetYear}">
            <button type="button">이전달</button>
        </a>
        <a href="${contextPath}/calendar?targetMonth=${calendarMap.targetMonth+1}&targetYear=${calendarMap.targetYear}">
            <button type="button">다음달</button>
        </a>
    </div>
    <div>
        ${calendarMap.targetYear}년
        ${calendarMap.targetMonth + 1}월
    </div>

    <table border="1" class="col-md-6">
        <tr>
            <td>일</td>
            <td>월</td>
            <td>화</td>
            <td>수</td>
            <td>목</td>
            <td>금</td>
            <td>토</td>
        </tr>
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

                    <!-- 한 행 당 7열 -->
                    <c:if test="${i < calendarMap.totalTd && i % 7 == 0}">
                        </tr><tr>
                    </c:if>

                </td>
            </c:forEach>
        </tr>
    </table>  
</c:set>
	
    <script>
        function openPopup(targetYear, targetMonth, targetDay) {
            var url = "${contextPath}/reservation?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDay=" + targetDay;
            window.open(url, "popupWindow", "width=500, height=300");
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



