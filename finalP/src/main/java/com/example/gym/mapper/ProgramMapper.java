package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import com.example.gym.vo.Program;

public interface ProgramMapper {

	int insertProgram(Program program);
	
	int deleteProgram(Program program);
	
	int updateProgram(Program program);
	
	List<Program> selectProgramList();
	
	Map<String, Object> selectProgramOne(Program program);
}
