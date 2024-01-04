<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- question table-->
	<div style="display: table;">

		<div style="display: table-row;">
			<div style="display: table-cell;">Question No</div>
			<div style="display: table-cell;">${ questionMap.questionNo }</div>
		</div>

		<div style="display: table-row;">
			<div style="display: table-cell;">Customer Id</div>
			<div style="display: table-cell;">${ questionMap.customerId }</div>
		</div>

		<div style="display: table-row;">
			<div style="display: table-cell;">Date</div>
			<div style="display: table-cell;">${ questionMap.qUpdatedate }</div>
		</div>

		<div style="display: table-row;">
			<div style="display: table-cell;">Title</div>
			<div style="display: table-cell;">${ questionMap.questionTitle }
			</div>
		</div>

		<div style="display: table-row;">
			<div style="display: table-cell;">Content</div>
			<div style="display: table-cell;">
				<textarea readonly="readonly">${ questionMap.questionContent }</textarea>
			</div>
		</div>

	</div>

	<c:if test="${ loginCustomer.customerId == questionMap.customerId }">
		<div>
			<input type="button" value="수정" onclick="location='${ctp}/updateQuestion?questionNo=${ questionMap.questionNo }'">
			<input type="button" value="삭제" id="deleteQuestionForm">
			
			<div id="deleteQuestionPw">
				삭제를 위해 계정 Pw를 입력하세요<br>
				<input type="password" id="customerPw" placeholder="PW 입력">&nbsp;
				
				<input type="button" value="삭제하기" id="deleteQuestionAct"> 
				
				<input type="hidden" id="customerId" value="${ loginCustomer.customerId }">
				
				<input type="hidden" id="questionNo" value="${ questionMap.questionNo }">
			</div>
		</div>
	</c:if>
	<br>
	<br>
	
<!-- 관리자 insert reply table : 관리자 로그인 시 조회되도록 설정 예정 -->
<!-- 이미 답변이 있을 시 insert불가 -->
<c:if test="${ replyMap == null }">
<p>답변대기중 : 등록된 답변이 없습니다.</p>

	<form action="${ctp}/insertQuestionReply" method="post">
		<div style="display: table;">
			<div style="display: table-row;">
				<div style="display: table-cell;">작성자</div>
				<div style="display: table-cell;">관리자<!-- 추후 ID 또는 name으로 변경 -->
					<input type="hidden" value="${ loginEmployee.employeeNo }" id="employeeNo" name="employeeNo">
				</div>
			</div>
			
			<div style="display: table-row;">
				<div style="display: table-cell;"><label for="questionReplyContent">답변</label></div>
				<div style="display: table-cell;"><textarea name="questionReplyContent" id="questionReplyContent" placeholder="답변내용"></textarea></div>
			</div>
		</div>
			<input type="hidden" value="${ questionMap.questionNo }" name="questionNo" id="questionNo">
			<button type="submit">등록</button>
	</form>
</c:if>

	<c:if test="${ replyMap != null }">
		<div style="display: table;">
			<!-- 관리자 reply -->

			<div style="display: table-row;">
				<div style="display: table-cell;">최종 작성자</div>
				<div style="display: table-cell;">${ replyMap.employeeId }</div>
			</div>

			<div style="display: table-row;">
				<div style="display: table-cell;">답변</div>
				<div style="display: table-cell;">
					<textarea readonly="readonly" id="questionReplyContent">${ replyMap.questionReplyContent }</textarea>
					<input type="hidden" value="${ replyMap.questionReplyNo }" id="questionReplyNo">
				</div>
			</div>

			<div style="display: table-row;">
				<div style="display: table-cell;">최초작성일</div>
				<div style="display: table-cell;">${ replyMap.qrCreatedate }</div>
			</div>

			<div style="display: table-row;">
				<div style="display: table-cell;">최종수정일</div>
				<div style="display: table-cell;">${ replyMap.qrUpdatedate }</div>
				<!-- 관리자 로그인 시에는 ID 조회되도록 고려 중 -->
			</div>

		</div>

		<input type="button" id="updateReplyBtn" value="수정">
		<input type="hidden" id="updateReplyFinish" value="완료">

		<a
			href="${ctp}/deleteQuestionReply?questionReplyNo=${ replyMap.questionReplyNo }&questionNo=${ questionMap.questionNo }">삭제</a>
	</c:if>
	<br>
	<br>
	<br>

</body>

<script>
// 고객
	
	// 삭제 Btn 클릭 시 비밀번호 입력창 조회
	$(document).ready(function() { // 최초 페이지로드 시 deleteQuestionAct 버튼 hide()
		$('#deleteQuestionPw').hide();
	});
	
	$('#deleteQuestionForm').click(function() { // 삭제 Form 버튼 클릭 시 해당 버튼은 hide(), 로드 시 hide()한 Act 버튼 show()
		$('#deleteQuestionForm').hide();
		$('#deleteQuestionPw').show();
	});
	
	$('#deleteQuestionAct').click(function() { // Act 버튼 클릭 시
		if ($('#customerPw').val().length < 1) {
			alert('Pw를 입력하세요.');
			return;
		} else {
			$.ajax({
				url : '${ctp}/deleteQuestion',
				type : 'post',
				data : {
					customerId : $('#customerId').val(),
					customerPw : $('#customerPw').val(),
					questionNo : $('#questionNo').val(),
				},
				success : function(result) {
					if (result == 1) {
						alert('삭제가 완료되었습니다.');
						$(location).attr("href", "${ctp}/questionList");
					} else {
						alert('Pw가 일치하지 않습니다.');
					}
				},
				error : function() {
					alert("페이지 오류 : 지속적인 오류 발생 시 관리자문의 바랍니다.");
				}
			});
		}
	});

// 관리자	
	
	// reply 수정 Btn 클릭 시 즉시수정 form으로 변경 + 해당버튼 완료버튼으로 변경 
	$('#updateReplyBtn').click(function() {
		$('#questionReplyContent').attr("readonly", false);
		$('#updateReplyBtn').attr("type", "hidden");
		$('#updateReplyFinish').attr("type", "button");
	});
	
	// 수정 후 완료버튼 클릭 시	
	$('#updateReplyFinish').click(function() {
		$.ajax({
			url : '${ctp}/updateQuestionReply',
			type : 'post',
			data : {
				employeeNo0 : $('#employeeNo').val(),
				questionReplyNo0 : $('#questionReplyNo').val(),
				questionReplyContent : $('#questionReplyContent').val(),
			},
			success : function(result) {
				// 접속 성공 시 readonly 재활성화 및 완료버튼 -> 수정버튼 원복위한 페이지 새로고침
				alert("수정 완료");
				location.reload(); // 새로고침

			},
			error : function() {
				alert("페이지 오류 : 지속적인 오류 발생 시 관리자문의 바랍니다.");
			}

		});
	});
</script>
</html>