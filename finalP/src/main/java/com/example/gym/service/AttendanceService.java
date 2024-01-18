package com.example.gym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.AttendanceMapper;
import com.example.gym.vo.CustomerAttendance;
import com.example.gym.vo.ProgramReservation;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class AttendanceService {
	@Autowired private AttendanceMapper attendanceMapper;
	// 출석 조회
	 public List<Map<String, Object>> selectCustomerAttendance(CustomerAttendance attendance) {
	     return attendanceMapper.selectCustomerAttendance(attendance);

	 }
	// 출석체크
	 public int insertAttendance(CustomerAttendance attendance) {
		return attendanceMapper.insertAttendance(attendance);
		 
	 }
	//출석확인
	 public List<Map<String, Object>> selectAttendance(int customerNo){
		 List<Map<String, Object>> attendanceList = attendanceMapper.selectAttendance(customerNo);
		 return attendanceList;
		}
	 
}
