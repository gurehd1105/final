<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
    <h1>${targetYear}년 ${targetMonth + 1}월 ${targetDay}일 예약</h1>
    	
    <table>
        <div>
            <label for="selectBranch">지점선택</label>
            <select id="selectBranch" class="form-select form-select-lg">
                <option>선택</option>
                <option value=2>서울</option>
                <option>대전</option>
                <option>부산</option>
            </select>
        </div>
    </table>
    <button type="button" id="confirm">확인</button>

    <script>
        $(document).ready(function () {
            // 클릭한 해당 날짜데이터
            var urlParams = new URLSearchParams(window.location.search);
            var targetYear = urlParams.get('targetYear');
            var targetMonth = urlParams.get('targetMonth');
            var targetDay = urlParams.get('targetDay');

            // 확인버튼 클릭처리
            $('#confirm').click(function () {
                var selectedBranch = $('#selectBranch').val();

                // 지점, 시간 둘중 하나라도 비활성화시 경고메시지 
                if (selectedBranch == '선택') {
                    alert('지점을 선택하세요.');
                } else {                  
                    console.log('선택 완료:', selectedBranch);                                      
                    alert('예약이 완료되었습니다.');                    
                    window.close();
                }
            });
        });
    </script>
</body>
</html>
