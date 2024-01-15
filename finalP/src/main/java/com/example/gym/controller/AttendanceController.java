package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.AttendanceService;
import com.example.gym.service.ReservationService;
import com.example.gym.vo.CustomerAttendance;
import com.example.gym.vo.ProgramReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder.Default;

@Controller
public class AttendanceController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired AttendanceService attendanceService;
	@Autowired ReservationService reservationService;

	@GetMapping("/attendanceList")
	public String attendanceList(CustomerAttendance attendance, Model model) throws JsonProcessingException {
   
	   List<Map<String, Object>> attendanceList = attendanceService.selectCustomerAttendance(attendance);
	   model.addAttribute("attendanceList", mapper.writeValueAsString(attendanceList));
	   System.out.println(attendanceList + "<--attendanceList");
	   return "attendance/attendanceList";
	   
	   
	}
	@GetMapping("/insertAttendance")
	public String insertAttendance(Model model, CustomerAttendance attendance ) {
		Map<String, Object> paramMap = new HashMap<>();
		
	    Map<String, Object> resultMap = reservationService.selectReservationList(paramMap);
	    model.addAttribute("reservationList", resultMap.get("reservationList")); 
	    System.out.println(resultMap + "<--resultMap");

	    return "attendance/insertAttendance";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
