package com.example.gym.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.service.SportsEquipmentService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.SearchResult;
import com.example.gym.vo.SportsEquipmentSearchParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("employee/sportsEquipment")
public class SportsEquipmentController extends DefaultController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	SportsEquipmentService sportsEquipmentService;
	@Autowired
	SearchResult result;

	@ModelAttribute("searchParam")
	public SportsEquipmentSearchParam defaultSearchParam() {
		return SportsEquipmentSearchParam.builder().build();
	}

//----------------------------------------- SportsEquipment -------------------------------------

	// SportsEquipment 추가 폼 & 장비,소모품 리스트 (본사직원만 접근 가능)
	@GetMapping("/insert")
	public String insert(HttpSession session, Model model, 
						@ModelAttribute("searchParam") SportsEquipmentSearchParam param)
			throws JsonProcessingException {

		param.setRowPerPage(6);
		var result = sportsEquipmentService.list(param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_추가;
	}

	// SportsEquipment 추가 액션 (본사직원만 접근 가능)
	@PostMapping("/insert")
	public String insert(HttpSession session, Model model, @RequestParam String itemName, @RequestParam int itemPrice,
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param,
			@RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList) throws JsonProcessingException {

		// 파일업로드 경로 설정
		String path = session.getServletContext().getRealPath("upload/sportsEquipment");

		// service 호출
		sportsEquipmentService.insert(session, path, itemName, itemPrice, sportsEquipmentImgList);

		param.setRowPerPage(6);
		var result = sportsEquipmentService.list(param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_추가;
	}

	// SportsEquipment 장비,소모품 리스트 (직원)
	@GetMapping("/list")
	public String list(HttpSession session, Model model,
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param) throws JsonProcessingException {
		param.setRowPerPage(6);
		var result = sportsEquipmentService.list(param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_목록;
	}

	// SportsEquipment 수정 폼 (본사직원만 접근 가능)
	@GetMapping("/update")
	public String update(HttpSession session, Model model, @RequestParam int sportsEquipmentNo)
			throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.one(session, sportsEquipmentNo);
		
	    // map이 null이면 예외 던지기
		  if (map == null || map.get("one") == null ) {
		        throw new NoSuchElementException("해당 장비를 찾을 수 없습니다.");
		    }
		  
		// jsp에서 출력할 model
		model.addAttribute("one", map.get("one"));
		model.addAttribute("imgList", toJson(map.get("imgList")));

		return ViewRoutes.소모품_수정;
	}

	// SportsEquipment 수정 액션 (본사직원만 접근 가능)
	@PostMapping("/update")
	public String update(HttpSession session, Model model, @RequestParam String itemName,
			@RequestParam int sportsEquipmentNo, @RequestParam int itemPrice, @RequestParam String equipmentActive) {

		// service 호출
		sportsEquipmentNo = sportsEquipmentService.update(session, sportsEquipmentNo, itemName, itemPrice,
				equipmentActive);

		// 디버깅
		log.info("redirect:sportsEquipmentNo : {}", sportsEquipmentNo);

		return String.format("redirect:%s?sportsEquipmentNo=%s", ViewRoutes.소모품_수정, sportsEquipmentNo);
	}

	// SportsEquipmentImg 개별 삭제 액션 (본사직원만 접근 가능)
	@PostMapping("/deleteImg")
	public String deleteImg(HttpSession session, @RequestParam int sportsEquipmentImgNo,
			@RequestParam int sportsEquipmentNo, @RequestParam String sportsEquipmentImgFileName) {

		log.info("개별 삭제할 sportsEquipmentImgFileName : {}", sportsEquipmentImgFileName);

		// service 호출
		sportsEquipmentNo = sportsEquipmentService.deleteImg(session, sportsEquipmentNo, sportsEquipmentImgNo,
				sportsEquipmentImgFileName);

		// 디버깅
		log.info("redirect:sportsEquipmentNo : {}", sportsEquipmentNo);

		return String.format("redirect:%s?sportsEquipmentNo=%s", ViewRoutes.소모품_수정, sportsEquipmentNo);
	}

	// SportsEquipmentImg 개별 추가 액션 (본사직원만 접근 가능)
	@PostMapping("/insertImg")
	public String insertImg(HttpSession session, @RequestParam int sportsEquipmentNo,
			@RequestParam("img") MultipartFile[] imgList) {

		// 파일업로드 경로 설정
		String path = session.getServletContext().getRealPath("/upload/sportsEquipment");

		// 디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);

		// service 호출
		sportsEquipmentNo = sportsEquipmentService.insertImg(session, path, sportsEquipmentNo, imgList);

		// 디버깅
		log.info("redirect:sportsEquipmentNo : {}", sportsEquipmentNo);

		return String.format("redirect:%s?sportsEquipmentNo=%s", ViewRoutes.소모품_수정, sportsEquipmentNo);
	}
	

	// SportsEquipment 상세보기 및 발주 폼
	@GetMapping("/sportsEquipmentOne")
	public String sportsEquipmentOne(HttpSession session, Model model, @RequestParam int sportsEquipmentNo)
			throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.one(session, sportsEquipmentNo);
		
		// jsp에서 출력할 model
		int branchLevel = 0;
		// 지점직원이라면 직원이 속해 있는 지점의 재고 출력
		if (branchLevel != 1) {
			model.addAttribute("inventory", toJson(map.get("inventory")));

		}
		// jsp에서 출력할 model
		model.addAttribute("one", map.get("one"));
		model.addAttribute("imgList", toJson(map.get("imgList")));
		
	    // map이 null이면 예외 던지기
		  if (map == null || map.get("one") == null ) {
		        throw new NoSuchElementException("해당 장비를 찾을 수 없습니다.");
		    }
		return ViewRoutes.소모품_상세보기;
	}

//----------------------------------------- SportsEquipmentOrder -------------------------------------

	// SportsEquipmentOrder 추가 액션 (지점직원만 접근 가능)
	@PostMapping("/insertOrder")
	public String insertOrder(HttpSession session, @RequestParam int sportsEquipmentNo, @RequestParam int quantity,
			@RequestParam int itemPrice) {

		// 디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);

		// service 호출
		sportsEquipmentService.insertOrder(session, sportsEquipmentNo, quantity, itemPrice);
		
		return Redirect(ViewRoutes.소모품_발주_지점);
	}

	// SportsEquipmentOrderByHead 리스트 (본사직원만 접근 가능)
	@GetMapping("/orderByHead")
	public String orderByHead(HttpSession session, Model model, 
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param) throws JsonProcessingException {
		param.setRowPerPage(10);
		// service 호출
		var result = sportsEquipmentService.orderByHead(param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_발주_본점;
	}

	// SportsEquipmentOrderByBranch 리스트 (지점직원만 접근 가능)
	@GetMapping("/orderByBranch")
	public String orderByBranch(HttpSession session, Model model, 
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param) throws JsonProcessingException {
		param.setRowPerPage(10);
		// service 호출
		var result = sportsEquipmentService.orderByBranch(session, param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_발주_지점;
	}

	// SportsEquipmentOrder 수정 액션 (본사직원만 접근 가능)
	@PostMapping("/updateOrder")
	public String updateOrder(HttpSession session, @RequestParam int orderNo, @RequestParam String orderStatus) {

		// 디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);

		// service 호출
		sportsEquipmentService.updateOrder(session, orderNo, orderStatus);

		return Redirect(ViewRoutes.소모품_발주_본점);
	}

	// SportsEquipmentOrder 삭제 액션 (지점직원만 접근 가능)
	@PostMapping("/deleteOrder")
	public String deleteOrder(HttpSession session, @RequestParam int orderNo, @RequestParam String orderStatus) {
		// 디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);

		// service 호출
		sportsEquipmentService.deleteOrder(session, orderNo, orderStatus);

		return Redirect(ViewRoutes.소모품_발주_지점);
	}

//----------------------------------------- SportsEquipmentInventory -------------------------------------


	// SportsEquipmentInventoryByHead 리스트 (본사직원만 접근 가능)
	@GetMapping("/inventoryByHead")
	public String inventoryByHead(HttpSession session, Model model, 
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param) throws JsonProcessingException {
		
		param.setRowPerPage(10);
		// service 호출
		var result = sportsEquipmentService.inventoryHead(param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_조회_본점;

	}

	// SportsEquipmentInventoryByBranch 리스트 (지점직원만 접근 가능)
	@GetMapping("/inventoryByBranch")
	public String inventoryByBranch(HttpSession session, Model model, 
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param) throws JsonProcessingException {
		
		param.setRowPerPage(10);
		// service 호출
		var result = sportsEquipmentService.inventoryBranch(session, param);

		model.addAttribute("result", toJson(result));

		return ViewRoutes.소모품_조회_지점;

	}
}