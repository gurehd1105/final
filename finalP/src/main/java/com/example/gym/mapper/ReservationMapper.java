package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Branch;
import com.example.gym.vo.CustomerAttendance;
import com.example.gym.vo.Program;
import com.example.gym.vo.ProgramDate;
import com.example.gym.vo.ProgramReservation;

@Mapper
public interface ReservationMapper {
   // 캘린더 출력
    List<Map<String,Object>> selectCalendarList(Map<String, Object> paramMap);
   
   // 예약목록 출력 
   List<Map<String,Object>>selectReservationList(Map<String, Object> paramMap);
 
   //페이징 위한 전체 수량
   int reservationCount(); 
   //프로그램 이름
   List<Map<String, Object>>selectProgram(ProgramDate programDate); 
   
   //프로그램 예약 가능 정보
   List<ProgramDate> selectProgramDates(int program_no);
   
   // 예약 추가
   int insertReservation(ProgramReservation reservation);
   
   // 예약삭제
   int deleteReservation(ProgramReservation reservation);
  
}
   
   
   
