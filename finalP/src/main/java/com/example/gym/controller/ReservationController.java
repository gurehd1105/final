package com.example.gym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.CalendarService;
import com.example.gym.service.ReservationService;
import com.example.gym.vo.Branch;
import com.example.gym.vo.ProgramReservation;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {	
	@Autowired CalendarService calendarService;
	@Autowired ReservationService reservationService;
	
	// 캘린더 출력
	@GetMapping("/calendar")
	public String calendar(Model model, HttpSession session,
			 			   @RequestParam(required = false) Integer targetYear ,
			 			   @RequestParam(required = false) Integer targetMonth			 			   		 			   
			 			   ) {
							
		Map<String, Object> calendarMap = calendarService.getCalendar(targetYear, targetMonth, session);
		model.addAttribute("calendarMap", calendarMap);
			
		return "reservation/calendar";
	}
	// 예약 상세보기
		@GetMapping("/reservationOne")
		public String reservationOne(Model model, @RequestParam Map<String, Object> paramMap, Integer targetDay) {
			Map<String,Object> rList = new HashMap<>();
			rList.put("paymentNo", paramMap.get("paymentNo"));
			rList.put("programDateNo", paramMap.get("programDateNo"));
			rList.put("branchNo", paramMap.get("branchNo"));
			
			List<Map<String, Object>> reservationList = reservationService.selectReservationList(rList);
	        model.addAttribute("reservationList", reservationList);
	        model.addAttribute("targetDay", targetDay);
		    return "reservation/reservationOne";
			
		}
		
	


	// 예약 추가
	@GetMapping("/insertReservation")
	public String insertReservation(Model model, Integer targetDay) {	
	    model.addAttribute("targetDay", targetDay);
		return "reservation/insertReservation";
		
	}
	
	@PostMapping("/insertReservation")
    public String insertReservation(ProgramReservation reservation) {
		reservationService.insertReservation(reservation);
        return "redirect:/reservationOne";
    }
	
	
	
	
	
	
}