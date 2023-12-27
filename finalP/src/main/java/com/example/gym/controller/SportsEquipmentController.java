package com.example.gym.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//SportsEquipment 추가 폼
	@GetMapping("insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session) {
//		//session 유효성 검사
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		return "sportsEquipment/insertSportsEquipment";
	}  

	//SportsEquipment 추가 액션
	@PostMapping("insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session,
										Model model,
	                                    @RequestParam String itemName,
	                                    @RequestParam int itemPrice,
										@RequestParam(defaultValue = "1") int currentPage,
										@RequestParam(defaultValue = "") String searchWord,
	                                    @RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList) {
//		//session 유효성 검사
//		if(session.getAttribute("") == null) {
//			return "";
//		}

		//파일업로드 경로 설정
	    String path = session.getServletContext().getRealPath("/upload/sportsEquipment");
	    
	    //service 호출
	    sportsEquipmentService.insertSportsEquipmentService(session, path, itemName, itemPrice, sportsEquipmentImgList);
	  	Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentByPageService(session, currentPage, searchWord);
	  		
	  	//jsp에서 출력할 model
	  	model.addAttribute("sportsEquipmentList", map.get("sportsEquipmentList"));
	  	model.addAttribute("lastPage", map.get("lastPage"));
	  	model.addAttribute("searchWord", map.get("searchWord"));

		return "sportsEquipment/insertSportsEquipment";
	}
	
	@GetMapping("SportsEquipmentList")
	public String selectSportsEquipmentList(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String searchWord) {
//		//session 유효성 검사
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentByPageService(session, currentPage, searchWord);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentList", map.get("sportsEquipmentList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchWord", map.get("searchWord"));
		
		return "sportsEquipment/insertSportsEquipment";
	}
	
	//SportsEquipment 수정 폼
	@GetMapping("updateSportsEquipment")
	public String updateSportsEquipment(HttpSession session,
										Model model,
										@RequestParam int sportsEquipmentNo) {
		//service 호출
		Map<String,Object> map = sportsEquipmentService.updateSportsEquipmentService(session, sportsEquipmentNo);
		
		//jsp에서 출력할 model
		model.addAttribute("employeeId", map.get("employeeId"));
		model.addAttribute("itemName", map.get("itemName"));
		model.addAttribute("itemPrice", map.get("itemPrice"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("equipmentCreatedate", map.get("equipmentCreatedate"));
		model.addAttribute("equipmentUpdatedate", map.get("equipmentUpdatedate"));
		
		return "sportsEquipment/updateSportsEquipment";
	}  
	
}