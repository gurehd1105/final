package com.example.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.ReservationMapper;
import com.example.gym.vo.Branch;
import com.example.gym.vo.ProgramReservation;

@Service
@Transactional

public class ReservationService {
   @Autowired  private ReservationMapper reservationMapper;
  
   // 예약 목록
   public List<Map<String, Object>> selectReservationList(Map<String, Object> paramMap) {
       return reservationMapper.selectReservationList(paramMap);
   }

   // 예약 추가
   public int insertReservation(ProgramReservation reservation) {
      int row = reservationMapper.insertReservation(reservation);
      return row;   
   }
   // 지점 목록
   public List<Branch> branchList() {
		return	reservationMapper.branchList();
	}
     
   // 예약 삭제
   public int deleteReservation(ProgramReservation reservation) {
      int row = reservationMapper.deleteReservation(reservation);
      return row;
      
   }
   
}