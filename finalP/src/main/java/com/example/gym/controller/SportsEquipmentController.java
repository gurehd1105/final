package com.example.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.service.SportsEquipmentService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SportsEquipmentController {
	@Autowired SportsEquipmentService sportsEquipmentService;
	
	@GetMapping("insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session) {
		return "sportsEquipment/insertSportsEquipment";
	}
	
	@PostMapping("insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session,
	                                    @RequestParam String itemName,
	                                    @RequestParam int itemPrice,
	                                    @RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList) {
	    String path = session.getServletContext().getRealPath("/upload/sportsEquipment");
	    sportsEquipmentService.insertSportsEquipmentService(session, path, itemName, itemPrice, sportsEquipmentImgList);

	    return "sportsEquipment/sportsEquipmentList";
	}
}