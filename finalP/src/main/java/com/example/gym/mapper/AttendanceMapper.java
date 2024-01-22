package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.CustomerAttendance;
import com.example.gym.vo.ProgramReservation;

@Mapper
public interface AttendanceMapper {
	// 출석 정보 조회
	List<Map<String, Object>> selectCustomerAttendance(CustomerAttendance attendance);
	
	// 출석 체크
	int insertAttendance(CustomerAttendance attendance);
	
	// 출석체크 조건
	Map<String, Object> selectCustomerAttendanceby(Map<String, Object> paramMap);
	
	// 출석확인 
	List<Map<String, Object>> selectAttendance(int customerNo);
}
