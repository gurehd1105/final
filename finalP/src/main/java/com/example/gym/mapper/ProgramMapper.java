package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Program;
@Mapper
public interface ProgramMapper {
	
	//program 추가
	int insert(Program program);
	
	//program 리스트 출력
	List<Map<String, Object>> selectProgramList(Map<String, Object> paramMap);
	//총 program 수
	int programCnt(Map<String, Object> paramMap);
	
	//program 수정 폼
	Map<String, Object> selectProgramOne(int programNo);
	//program 수정 액션
	int updateProgram(Map<String, Object> paramMap);
}
