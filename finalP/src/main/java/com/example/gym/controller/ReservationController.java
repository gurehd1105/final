package com.example.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
	@GetMapping("/reservation")
	public String reservation() {
		
		return "reservation";	
	}
	
	@PostMapping("/reservation")
	public String confirmReservation() {
		return "redirect:/confirmReservation";
			
	}
	
	
}
