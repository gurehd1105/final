package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Branch;
import com.example.gym.vo.ProgramReservation;

@Mapper
public interface ReservationMapper {
   // 캘린더 출력
    List<Map<String,Object>> selectCalendarList(Map<String, Object> paramMap);
   
   // 예약목록 출력 
    List<Map<String,Object>>selectReservationList(Map<String, Object> paramMap);
   
   // 예약 추가
   int insertReservation(ProgramReservation reservation);

   // 예약삭제
   int deleteReservation(ProgramReservation reservation);
   
   
}