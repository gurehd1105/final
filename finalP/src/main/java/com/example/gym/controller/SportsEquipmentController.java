package com.example.gym.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.service.SportsEquipmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("sportsEquipment")
public class SportsEquipmentController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired SportsEquipmentService sportsEquipmentService;
	
//----------------------------------------- SportsEquipment -------------------------------------
	
	//SportsEquipment 추가 폼 & 장비,소모품 리스트 (본사직원만 접근 가능)
	@GetMapping("/insertSportsEquipment")
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
	@PostMapping("/insertSportsEquipment")
	public String insertSportsEquipment(HttpSession session,
										Model model,
	                                    @RequestParam String itemName,
	                                    @RequestParam int itemPrice,
										@RequestParam(defaultValue = "1") int currentPage,
										@RequestParam(defaultValue = "") String searchWord,
										@RequestParam(defaultValue = "") String equipmentActive,
	                                    @RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList) throws JsonProcessingException {
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
	  	
	  	model.addAttribute("sportsEquipmentList", mapper.writeValueAsString(map.get("sportsEquipmentList")));
	  	model.addAttribute("lastPage", map.get("lastPage"));
	  	model.addAttribute("searchWord", map.get("searchWord"));

		return "sportsEquipment/insertSportsEquipment";
	}
	
	//SportsEquipment 장비,소모품 리스트 (직원)
	@GetMapping("/SportsEquipmentList")
	public String selectSportsEquipmentList(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String equipmentActive,
											@RequestParam(defaultValue = "") String searchWord) throws JsonProcessingException {
//		//session 유효성 검사(직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentByPageService(session, currentPage, equipmentActive, searchWord);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentList", mapper.writeValueAsString(map.get("sportsEquipmentList")));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("searchWord", map.get("searchWord"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		
		return "sportsEquipment/sportsEquipmentList";
	}
	
	//SportsEquipment 수정 폼 (본사직원만 접근 가능)
	@GetMapping("/updateSportsEquipment")
	public String updateSportsEquipment(HttpSession session,
										Model model,
										@RequestParam int sportsEquipmentNo) {
		
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.sportsEquipmentOneService(session, sportsEquipmentNo);
		
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
	@PostMapping("/updateSportsEquipment")
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
	@PostMapping("/deleteOneSportsEquipmentImg")
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
	@PostMapping("/insertOneSportsEquipmentImg")
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
	
	//SportsEquipment 상세보기 및 발주 폼
	@GetMapping("/sportsEquipmentOne")
	public String sportsEquipmentOne(HttpSession session,
										Model model,
										@RequestParam int sportsEquipmentNo) throws JsonProcessingException {
		
//		//session 유효성 검사 (직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.sportsEquipmentOneService(session, sportsEquipmentNo);
		
		//jsp에서 출력할 model
		int branchLevel = 0;
		//지점직원이라면 직원이 속해 있는 지점의 재고 출력
		if(branchLevel != 1) {
			//model.addAttribute("sportsEquipmentInventory", map.get("sportsEquipmentInventory"));
			model.addAttribute("sportsEquipmentInventory", mapper.writeValueAsString(map.get("sportsEquipmentInventory")));
		}	
		model.addAttribute("sportsEquipmentNo", map.get("sportsEquipmentNo"));
		model.addAttribute("employeeId", map.get("employeeId"));
		model.addAttribute("itemName", map.get("itemName"));
		model.addAttribute("itemPrice", map.get("itemPrice"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("equipmentCreatedate", map.get("equipmentCreatedate"));
		model.addAttribute("equipmentUpdatedate", map.get("equipmentUpdatedate"));
		//model.addAttribute("sportsEquipmentImgList", map.get("sportsEquipmentImgList"));
		model.addAttribute("sportsEquipmentImgList", mapper.writeValueAsString(map.get("sportsEquipmentImgList")));
		
		return "sportsEquipment/sportsEquipmentOne";
	}  
	
//----------------------------------------- SportsEquipmentOrder -------------------------------------
	
	//SportsEquipmentOrder 추가 액션 (지점직원만 접근 가능)
	@PostMapping("/insertSportsEquipmentOrder")
	public String insertSportsEquipmentOrder(HttpSession session,
	                                    		@RequestParam int sportsEquipmentNo,
	                                    		@RequestParam int quantity,
	                                    		@RequestParam int itemPrice) {

		//		//session 유효성 검사 (지점직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}

		//디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);
	    
	    //service 호출
		sportsEquipmentService.insertSportsEquipmentOrderService(session, sportsEquipmentNo, quantity, itemPrice);
	  	
		return "redirect:/sportsEquipment/sportsEquipmentOrderListByBranch";
	}

	//SportsEquipmentOrderByHead 리스트 (본사직원만 접근 가능)
	@GetMapping("/sportsEquipmentOrderListByHead")
	public String sportsEquipmentOrderListByHead(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String searchBranch,
											@RequestParam(defaultValue = "") String searchItem,
											@RequestParam(defaultValue = "") String beginDate,
											@RequestParam(defaultValue = "") String endDate) {
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
	
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentOrderByHeadService(session, currentPage, searchBranch, searchItem, beginDate, endDate);
		
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentOrderList", map.get("sportsEquipmentOrderList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchBranch", map.get("searchBranch"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("searchItem", map.get("searchItem"));
		model.addAttribute("beginDate", map.get("beginDate"));
		model.addAttribute("endDate", map.get("endDate"));
		
		return "sportsEquipment/sportsEquipmentOrderListByHead";
	}  

	
	//SportsEquipmentOrderByBranch 리스트 (지점직원만 접근 가능)
	@GetMapping("/sportsEquipmentOrderListByBranch")
	public String sportsEquipmentOrderListByBranch(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String searchItem,
											@RequestParam(defaultValue = "") String beginDate,
											@RequestParam(defaultValue = "") String endDate) {
//		//session 유효성 검사 (지점직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
	
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentOrderByBranchService(session, currentPage, searchItem, beginDate, endDate);
		
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentOrderList", map.get("sportsEquipmentOrderList"));
		model.addAttribute("searchBranch", map.get("searchBranch"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("searchItem", map.get("searchItem"));
		model.addAttribute("beginDate", map.get("beginDate"));
		model.addAttribute("endDate", map.get("endDate"));
		
		return "sportsEquipment/sportsEquipmentOrderListByBranch";
	}
	
	//SportsEquipmentOrder 수정 액션 (본사직원만 접근 가능)
	@PostMapping("/updateSportsEquipmentOrder")
	public String updateSportsEquipmentOrder(HttpSession session,
	                                    		@RequestParam int orderNo,
	                                    		@RequestParam String orderStatus) {

		//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}

		//디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);
	    
	    //service 호출
		sportsEquipmentService.updateSportsEquipmentOrderService(session, orderNo, orderStatus);
		
		return "redirect:/sportsEquipment/sportsEquipmentOrderListByHead";
	}
	
	//SportsEquipmentOrder 삭제 액션 (지점직원만 접근 가능)
	@PostMapping("/deleteSportsEquipmentOrder")
	public String deleteSportsEquipmentOrder(HttpSession session,
	                                    		@RequestParam int orderNo,
	                                    		@RequestParam String orderStatus) {

		//		//session 유효성 검사 (지점직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}

		//디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);
	    
	    //service 호출
		sportsEquipmentService.deleteSportsEquipmentOrderService(session, orderNo, orderStatus);
		
		return "redirect:/sportsEquipment/sportsEquipmentOrderListByBranch";
	}

//----------------------------------------- SportsEquipmentInventory -------------------------------------
	
	//SportsEquipmentInventoryByHead 리스트 (본사직원만 접근 가능)
	@GetMapping("/sportsEquipmentInventoryByHead")
	public String sportsEquipmentInventoryByHead(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String searchBranch,
											@RequestParam(defaultValue = "") String searchItem) {
//		//session 유효성 검사 (본사직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
	
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentInventoryHeadService(session, currentPage, searchBranch, searchItem);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentInventory", map.get("sportsEquipmentInventory"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchItem", map.get("searchItem"));
		model.addAttribute("searchBranch", map.get("searchBranch"));
		
		return "sportsEquipment/sportsEquipmentInventoryByHead";
	} 

	//SportsEquipmentInventoryByBranch 리스트 (지점직원만 접근 가능)
	@GetMapping("/sportsEquipmentInventoryByBranch")
	public String sportsEquipmentInventoryByHead(HttpSession session,
											Model model,
											@RequestParam(defaultValue = "1") int currentPage,
											@RequestParam(defaultValue = "") String searchItem) {
//		//session 유효성 검사 (지점직원)
//		if(session.getAttribute("") == null) {
//			return "";
//		}
		
		//service 호출
		Map<String,Object> map = sportsEquipmentService.selectSportsEquipmentInventoryBranchService(session, currentPage, searchItem);
		
		//jsp에서 출력할 model
		model.addAttribute("sportsEquipmentInventory", map.get("sportsEquipmentInventory"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchItem", map.get("searchItem"));
		
		return "sportsEquipment/sportsEquipmentInventoryByBranch";
	} 
}