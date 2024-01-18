package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.ProgramDate;

@Mapper
public interface ProgramDateMapper {
	// 관리자 프로그램 예약 가능 정보 조회
	List<Map<String, Object>> programDateList(ProgramDate programDate);
	   
	// 관리자 프로그램 예약 날짜 입력
	int insertProgramDate(ProgramDate programDate);
	   
	// 관리자 프로그램 예약 날짜 수정
	int updateProgramDate(ProgramDate programDate);
}
