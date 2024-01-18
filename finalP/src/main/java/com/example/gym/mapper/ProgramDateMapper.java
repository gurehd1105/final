package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.ProgramDate;

@Mapper
public interface ProgramDateMapper {
	// 프로그램 진행 날짜 조회
	List<Map<String, Object>> programDateList(ProgramDate programDate);
	   
	// 프로그램 진행 날짜 입력
	int insertProgramDate(ProgramDate programDate);
	   
	//  프로그램 진행 날짜 수정
	int updateProgramDate(ProgramDate programDate);
	
	//  프로그램 진행 날짜 삭제
	int deleteProgramDate(ProgramDate programDate);
}
