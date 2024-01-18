package com.example.gym.util;

public class ViewRoutes {
	public static final String 홈 = "/home";
	// Customer 관련 path
	public static final String 사용자_로그인 = "/customer/login";
	public static final String 사용자_추가 = "/customer/insert";
	public static final String 사용자_삭제 = "/customer/delete";
	public static final String 사용자_정보_확인 = "/customer/customerOne";
	public static final String 사용자_정보_수정 = "/customer/updateOne";
	public static final String 사용자_암호_확인 = "/customer/updateOneForPw";
	public static final String 사용자_암호_변경 = "/customer/updatePw";
	// Branch 관련 path
	public static final String 지점_목록 = "/branch/list";
	public static final String 지점_추가 = "/branch/insert";
	public static final String 지점_정보_수정 = "/branch/update";
	// Attendance 관련 path
	public static final String 출석_조회 = "/attendance/attendanceList";
	public static final String 출석_추가 = "/attendance/insertAttendance";
	// Employee 관련 path
	public static final String 직원_로그인 = "/employee/login";
	public static final String 직원_추가 = "/employee/insert";
	public static final String 직원_삭제 = "/employee/delete";
	public static final String 직원_목록 = "/employee/list";
	public static final String 직원_정보_확인 = "/employee/employeeOne";
	public static final String 직원__정보_수정 = "/employee/updateOne";
	public static final String 직원_암호_확인 = "/employee/updateOneForPw";
	public static final String 직원_암호_변경 = "/employee/updatePw";
	// Membership 관련 path
	public static final String 회원권_추가 = "/membership/insert";
	public static final String 회원권_조회 = "/membership/list";
	public static final String 회원권_수정 = "/membership/update";
	// Notice 관련 path 
	public static final String 공지사항_추가 = "/notice/insert";
	public static final String 공지사항_목록 = "/notice/list";
	public static final String 공지사항_수정 = "/notice/update";
	public static final String 공지사항_상세보기 = "/notice/noticeOne";
	// Payment 관련 path
	public static final String 결제정보_조회 = "/payment/list";
	// Program 관련 path
	public static final String 프로그램_추가 = "/program/insert";
	public static final String 프로그램_목록 = "/program/list";
	public static final String 프로그램_수정 = "/program/update";
	// Question 관련 path
	public static final String 문의사항_추가 = "/question/insert";
	public static final String 문의사항_목록 = "/question/list";
	public static final String 문의사항_상세보기 = "/question/questionOne";
	public static final String 문의사항_수정 = "/question/update";
	public static final String 문의사항_답변_수정 = "/question/updateReply";
	// Reservation 관련 path
	public static final String 예약_달력 = "/reservation/calendar";
	public static final String 예약_프로그램_추가 = "/reservation/insertProgramDate";
	public static final String 예약_추가 = "/reservation/insertReservation";
	public static final String 예약_프로그램_조회 = "/reservation/programList";
	public static final String 예약_조회 = "/reservation/reservationList";
	// Review 관련 path
	public static final String 후기_추가 = "/review/insert";
	public static final String 후기_목록 = "/review/list";
	public static final String 후기_상세보기 = "/review/reviewOne";
	public static final String 후기_수정 = "/review/update";
	public static final String 후기_댓글_수정 = "/review/updateReply";
	// SportsEquipment 관련 path
	public static final String 소모품_추가= "/sportsEquipment/insert";
	public static final String 소모품_수정= "/sportsEquipment/update";
	public static final String 소모품_목록 = "/sportsEquipment/list";
	public static final String 소모품_상세보기= "/sportsEquipment/sportsEquipmentOne";
	public static final String 소모품_조회_지점= "/sportsEquipment/inventoryByBranch";
	public static final String 소모품_조회_본점= "/sportsEquipment/inventoryByHead";
	public static final String 소모품_발주_지점= "/sportsEquipment/orderByBranch";
	public static final String 소모품_발주_본점= "/sportsEquipment/orderByHead";
}
