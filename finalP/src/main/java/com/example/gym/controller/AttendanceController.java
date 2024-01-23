package com.example.gym.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.AttendanceService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ReservationService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.CustomerAttendance;
import com.example.gym.vo.ProgramReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.Builder.Default;

@Controller
@RequestMapping("attendance")
public class AttendanceController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired AttendanceService attendanceService;
	@Autowired ReservationService reservationService;
	@Autowired CustomerService customerService;
	
	// 출석 조회
	@GetMapping("/list")
	public String attendanceList(HttpSession session, CustomerAttendance attendance, Model model) throws JsonProcessingException {
	        
	   List<Map<String, Object>> attendanceList = customerService.selectAllCustomer();
	   model.addAttribute("attendanceList", mapper.writeValueAsString(attendanceList));
	   System.out.println(attendanceList + "<--attendanceList");
	   return ViewRoutes.출석_조회;	   	   
	}
	
	//출석 체크
	@GetMapping("/insert")
	public String insertAttendance(Model model, CustomerAttendance attendance ) {
		Map<String, Object> paramMap = new HashMap<>();		
	    Map<String, Object> resultMap = reservationService.selectReservationList(paramMap);
	    
	    model.addAttribute("reservationList", resultMap.get("reservationList")); 	    	    
	    System.out.println(resultMap + "<--resultMap");	    
	    return ViewRoutes.출석_추가;
	}
	
	  @PostMapping("/insert")
	    @ResponseBody
	    public int insertAttendance(@RequestBody Map<String, Object> paramMap, HttpSession session ) {
		  System.out.println(paramMap + "<-- paramMap");
		  String a = (String)paramMap.get("currentTime");
		  String b = a.substring(0,10);
		  System.out.println(b + "<-- b");
		  
	    // 출석 정보 생성
		int result = 0;
		
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
		Map<String, Object> attendanceCheck = attendanceService.selectCustomerAttendanceby(loginCustomer);
		System.out.println(attendanceCheck.get("programDate") + "<-- attendanceCheck.get(\"programDate\")");
		if(b.equals(attendanceCheck.get("programDate"))){
			return result;
		}else {
		    CustomerAttendance attendance = new CustomerAttendance();
		    attendance.setProgramReservationNo(Integer.parseInt((String)paramMap.get("reservationNo")));
		    attendance.setCustomerAttendanceEnterTime((String) paramMap.get("currentTime"));
	
		    // 출석 정보 저장
		    result = attendanceService.insertAttendance(attendance);
			
		    return result;
		}
	 }
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
