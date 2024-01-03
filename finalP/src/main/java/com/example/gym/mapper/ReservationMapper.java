package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.ProgramReservation;

@Mapper
public interface ReservationMapper {
	// 예약목록
	List<ProgramReservation> selectReservationList(Map<String, Object>paramMap);
	
	// 예약 추가
	int insertProgramReservation(ProgramReservation programreservation);
	
}
