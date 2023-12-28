package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.CalendarService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalendarController {	
	@Autowired CalendarService calendarService;
	
	@GetMapping("/calendar")
	public String calendar(Model model, HttpSession session,
			 			   @RequestParam(required = false) Integer targetYear ,
			 			   @RequestParam(required = false) Integer targetMonth) {
							
		Map<String, Object> calendarMap = calendarService.getCalendar(targetYear, targetMonth, session);
		model.addAttribute("calendarMap", calendarMap);
			
		return "calendar";

	}
	
}
