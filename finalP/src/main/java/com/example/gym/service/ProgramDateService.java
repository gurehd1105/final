package com.example.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.ProgramDateMapper;
import com.example.gym.mapper.ReservationMapper;
import com.example.gym.vo.ProgramDate;

@Service
@Transactional
public class ProgramDateService {
	@Autowired
	private ProgramDateMapper programDateMapper;
	
	
	// 프로그램 진행 날짜 조회
	public List<Map<String, Object>> programDateList(ProgramDate programDate){
		return programDateMapper.programDateList(programDate);
	}
			
	// 프로그램 진행 날짜 추가
	public int insertProgramDate(ProgramDate programDate) {
		return programDateMapper.insertProgramDate(programDate);
		}
		
	// 프로그램 진행 날짜 수정
	public int updateProgramDate(ProgramDate programDate) {
		return programDateMapper.updateProgramDate(programDate);
		}
	
	// 프로그램 진행 날짜	 삭제
	public int deleteProgramDate(ProgramDate programDate) {
		return programDateMapper.deleteProgramDate(programDate);
	}
		
}
