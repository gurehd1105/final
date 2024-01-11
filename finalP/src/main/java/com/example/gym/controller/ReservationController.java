package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.BranchService;
import com.example.gym.service.CalendarService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ReservationService;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Customer;
import com.example.gym.vo.ProgramReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {	
	ObjectMapper mapper = new ObjectMapper();
	@Autowired CalendarService calendarService;
	@Autowired ReservationService reservationService;
	@Autowired CustomerService customerService;
	@Autowired BranchService branchService;
	
	// 캘린더 출력
	@GetMapping("/calendar")
	public String calendar(Model model, HttpSession session,
			 			   @RequestParam(required = false) Integer targetYear ,
			 			   @RequestParam(required = false) Integer targetMonth			 			   		 			   
			 			   ) {
		List<Branch> branchList = branchService.branch();
		
		System.out.println("접속성공");					
		Map<String, Object> calendarMap = calendarService.getCalendar(targetYear, targetMonth, session);
		model.addAttribute("calendarMap", calendarMap);
		model.addAttribute("branchList", branchList);
			
		return "reservation/calendar";
	}
	
	
	// 예약 리스트
	@GetMapping("/reservationList")
	public String reservationList(HttpSession session, Model model, Integer targetDay, Integer targetYear, Integer targetMonth,									
								 @RequestParam(defaultValue = "1") int currentPage	
							      ) throws JsonProcessingException {
		/* 
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		 if (loginCustomer == null) {
		 // 로그인이 되어있지 않으면 로그인 페이지로 리다이렉트
		    return "customer/login";
		     }
		 // 로그인한 사용자의 paymentNo 가져오기
		Map<String, Object> paymentNo = customerService.customerOne(loginCustomer);
		 	if(paymentNo == null) {		
		 	// 유효한 paymentNo가 없는 경우	
		    return "redirect:/reservationList"; 		 		
		 	}
		*/
		String targetYear2 = mapper.writeValueAsString(targetYear);
		String targetMonth2 = mapper.writeValueAsString(targetMonth);
		String targetDay2 = mapper.writeValueAsString(targetDay);
	
	
		String date = targetYear2+targetMonth2+targetDay2;
		if(targetMonth2.length()== 1){
			date = targetYear2+"0"+targetMonth2+targetDay2;
		}
		System.out.println(date +"날짜" );
		
		int rowPerPage = 5;		
		int beginRow = (currentPage - 1) * rowPerPage ;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("paymentNo", 1);
		paramMap.put("programDate", date );
		
		System.out.println(paramMap + "<-- paramMap");
		
		
		Map<String, Object> list = reservationService.selectReservationList(paramMap);
		Object reservationList = list.get("reservationList");
		
		System.out.println(reservationList +"<---reservationList");
		model.addAttribute("reservationList",mapper.writeValueAsString(reservationList));
		
		int totalRow = (int) list.get("totalRow");
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage += 1;
		}		
		model.addAttribute("targetDay", targetDay);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rowPerPage", rowPerPage);

	
	    return "reservation/reservationList";
		
	}
		
	// 예약 추가
	@GetMapping("/insertReservation")
	public String insertReservation(HttpSession session, Model model, Integer targetDay) {
		/*
		// 1. 로그인 여부 확인
	    Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
	    if (loginCustomer == null) {
	    // 로그인이 되어있지 않으면 로그인 페이지로 리다이렉트
	    return "customer/login";
	     }      
	    // 2. paymentNo 및 programDate의 존재 여부 확인
	    Integer paymentNo = (Integer) session.getAttribute("paymentNo");
	    Integer programDate = (Integer) session.getAttribute("programDate");

	    if (paymentNo == null || programDate == null) {
	    // paymentNo 또는 programDate가 없으면 다른 페이지로 리다이렉트 또는 에러 처리
	    return "redirect:/reservationList";
	    }
	    // 모든 조건을 만족하면 예약 페이지로 이동
	    model.addAttribute("loginCustomer", loginCustomer);      
		*/
		model.addAttribute("targetDay", targetDay);
		System.out.println(model);
		return "reservation/insertReservation";
		
	}
	
	@PostMapping("/insertReservation")
    public String insertReservation(ProgramReservation reservation) {
		reservationService.insertReservation(reservation);
        return "redirect:/reservationList";
    }
	

	
	
}