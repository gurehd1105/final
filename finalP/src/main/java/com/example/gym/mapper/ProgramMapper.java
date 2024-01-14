package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Program;
@Mapper
public interface ProgramMapper {

	int insertProgram(Program program);
	
	int deleteProgram(Program program);
	
	int updateProgram(Program program);
	
	//program 리스트 출력
	List<Map<String, Object>> selectProgramList(Map<String, Object> paramMap);
	//총 program 수
	int programCnt(Map<String, Object> paramMap);
	
	Map<String, Object> selectProgramOne(int programNo);
}
