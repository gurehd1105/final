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
	
	//SportsEquipment 추가 폼 & 장비,소모품 리스트 (본사직원만 접근 가능)
	@GetMapping("/sportsEquipment/insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session,
										Model model,
										@RequestParam(defaultValue = "1") int currentPage,
										@RequestParam(defaultValue = "") String equipmentActive,
										@RequestParam(defaultValue = "") String searchWord) {
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentByPageService(session, currentPage, equipmentActive, searchWord);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentList", map.get("sportsEquipmentList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchWord", map.get("searchWord"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		
		return "sportsEquipment/insertSportsEquipment";
	}  

	//SportsEquipment 추가 액션 (본사직원만 접근 가능)
	@PostMapping("/sportsEquipment/insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session,
										Model model,
	                                    @RequestParam String itemName,
	                                    @RequestParam int itemPrice,
										@RequestParam(defaultValue = "1") int currentPage,
										@RequestParam(defaultValue = "") String searchWord,
										@RequestParam(defaultValue = "") String equipmentActive,
	                                    @RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList) {
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}

		//파일업로드 경로 설정
	    String path = session.getServletContext().getRealPath("/upload/sportsEquipment");
	    
	    //service 호출
	    sportsEquipmentService.insertSportsEquipmentService(session, path, itemName, itemPrice, sportsEquipmentImgList);
	  	Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentByPageService(session, currentPage, equipmentActive, searchWord);
	  		
	  	//jsp에서 출력할 model
	  	model.addAttribute("sportsEquipmentList", map.get("sportsEquipmentList"));
	  	model.addAttribute("lastPage", map.get("lastPage"));
	  	model.addAttribute("searchWord", map.get("searchWord"));

		return "sportsEquipment/insertSportsEquipment";
	}
	
	//SportsEquipment 장비,소모품 리스트 (직원)
	@GetMapping("/sportsEquipment/SportsEquipmentList")
	public String selectSportsEquipmentList(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String equipmentActive,
											@RequestParam(defaultValue = "") String searchWord) {
//		//session 유효성 검사(직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentByPageService(session, currentPage, equipmentActive, searchWord);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentList", map.get("sportsEquipmentList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchWord", map.get("searchWord"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		
		return "sportsEquipment/sportsEquipmentList";
	}
	
	//SportsEquipment 수정 폼 (본사직원만 접근 가능)
	@GetMapping("/sportsEquipment/updateSportsEquipment")
	public String updateSportsEquipment(HttpSession session,
										Model model,
										@RequestParam int sportsEquipmentNo) {
		
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.updateSportsEquipmentService(session, sportsEquipmentNo);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentNo", map.get("sportsEquipmentNo"));
		model.addAttribute("employeeId", map.get("employeeId"));
		model.addAttribute("itemName", map.get("itemName"));
		model.addAttribute("itemPrice", map.get("itemPrice"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("equipmentCreatedate", map.get("equipmentCreatedate"));
		model.addAttribute("equipmentUpdatedate", map.get("equipmentUpdatedate"));
		model.addAttribute("sportsEquipmentImgList", map.get("sportsEquipmentImgList"));
		
		return "sportsEquipment/updateSportsEquipment";
	}  
	
	//SportsEquipment 수정 액션 (본사직원만 접근 가능)
	@PostMapping("/sportsEquipment/updateSportsEquipment")
	public String updateSportsEquipment(HttpSession session,
										Model model,
	                                    @RequestParam String itemName,
	                                    @RequestParam int sportsEquipmentNo,
	                                    @RequestParam int itemPrice,
	                                    @RequestParam String equipmentActive) {
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}

	    
	    //service 호출
		sportsEquipmentNo = sportsEquipmentService.updateSportsEquipmentService(session, sportsEquipmentNo, itemName, itemPrice, equipmentActive);
	  	
		//디버깅
		log.info("redirect:sportsEquipmentNo : {}", sportsEquipmentNo);

		return "redirect:/sportsEquipment/updateSportsEquipment?sportsEquipmentNo="+sportsEquipmentNo;
	}
	
	//SportsEquipmentImg 개별 삭제 액션 (본사직원만 접근 가능)
	@PostMapping("/sportsEquipment/deleteOneSportsEquipmentImg")
	public String deleteOneSportsEquipmentImg(HttpSession session,
	                                    		@RequestParam int sportsEquipmentImgNo,
	                                    		@RequestParam int sportsEquipmentNo,
	                                    		@RequestParam String sportsEquipmentImgFileName ) {
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}

		log.info("개별 삭제할 sportsEquipmentImgFileName : {}", sportsEquipmentImgFileName);
	    
	    //service 호출
		sportsEquipmentNo = sportsEquipmentService.deleteOneSportsEquipmentImgService(session, sportsEquipmentNo, sportsEquipmentImgNo, sportsEquipmentImgFileName);
	  	
		//디버깅
		log.info("redirect:sportsEquipmentNo : {}", sportsEquipmentNo);

		return "redirect:/sportsEquipment/updateSportsEquipment?sportsEquipmentNo="+sportsEquipmentNo;
	}
	
	//SportsEquipmentImg 개별 추가 액션 (본사직원만 접근 가능)
	@PostMapping("/sportsEquipment/insertOneSportsEquipmentImg")
	public String insertOneSportsEquipmentImg(HttpSession session,
	                                    		@RequestParam int sportsEquipmentNo,
	                                    		@RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList ) {

		//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		//파일업로드 경로 설정
	    String path = session.getServletContext().getRealPath("/upload/sportsEquipment");
	    
		//디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);
	    
	    //service 호출
		sportsEquipmentNo = sportsEquipmentService.insertOneSportsEquipmentImgService(session, path, sportsEquipmentNo, sportsEquipmentImgList);
	  	
		//디버깅
		log.info("redirect:sportsEquipmentNo : {}", sportsEquipmentNo);

		return "redirect:/sportsEquipment/updateSportsEquipment?sportsEquipmentNo="+sportsEquipmentNo;
	}	
	
}