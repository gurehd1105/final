<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
</head>
<body>
   <!-- 캘린더 -->
   <div>
      ${calendarMap.targetYear}년
      ${calendarMap.targetMonth + 1}월
   </div>
   
   <table border="1">
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
                  <a href="javascript:void(0);" onclick="openModal(${d})">${d}</a>
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

   <!-- Bootstrap Modal -->
   <div class="modal fade" id="reservationModal" tabindex="-1" role="dialog" aria-labelledby="reservationModal" aria-hidden="true">
      <div class="modal-dialog modal-xl" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="reservationModal"></h5>
               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
            </div>
            <div class="modal-body">
              <!-- 예약 내용 -->
              <!-- 시간 선택 버튼 -->
              <button type="button" class="btn btn-secondary" data-toggle="modal"
              		  data-target="#timeSelectionModal">시간 선택
              </button>
   						
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
         </div>
      </div>
   </div>

  

</body>
	<script>
		function openModal(day) {
			
		// 클릭된 날짜에 대한 예약 정보	
		var url = "${contextPath}/reservation?targetYear=" + ${calendarMap.targetYear} + "&targetMonth=" + (${calendarMap.targetMonth} + 1) + "&targetDay=" + day;    
	   
		// Bootstrap 모달 창 표시
	    $('#reservationModal').modal('show');
	    
		// 모달 창의 제목을 선택된 날짜로 설정
        $('#reservationModal.modal-title').html('예약 - ' + ${calendarMap.targetYear} + '년 ' + (${calendarMap.targetMonth} + 1) + '월 ' + day + '일');
	    
	    // 모달 창에 내용 로드
	    $('#reservationModal.modal-body').load(url);
		}
</script>
</html>