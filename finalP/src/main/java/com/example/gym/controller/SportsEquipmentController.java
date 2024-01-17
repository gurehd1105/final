package com.example.gym.controller;

import java.util.Map;

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
import com.example.gym.vo.SearchParam;
import com.example.gym.vo.SportsEquipmentSearchParam;
import com.example.gym.vo.SportsEquipmentSearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("sportsEquipment")
public class SportsEquipmentController extends DefaultController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	SportsEquipmentService sportsEquipmentService;
	@Autowired
	SportsEquipmentSearchResult result;

	@ModelAttribute("searchParam")
	public SportsEquipmentSearchParam defaultSearchParam() {
		return SportsEquipmentSearchParam.builder().build();
	}

//----------------------------------------- SportsEquipment -------------------------------------

	// SportsEquipment 추가 폼 & 장비,소모품 리스트 (본사직원만 접근 가능)
	@GetMapping("/insert")
	public String insert(HttpSession session, Model model, @RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "") String equipmentActive, @RequestParam(defaultValue = "") String searchWord)
			throws JsonProcessingException {

//		//본사직원 확인
//		Employee loginSession = (Employee) session.getAttribute("loginEmployee");
//		int branchLevel = loginSession.getBranchLevel();
//		if(branchLevel != 1) {
//			//지점 장비 리스트로 리턴
//			return "redirect:/sportsEquipment/SportsEquipmentList";
//		}
//		
		// service 호출
		result = sportsEquipmentService.list(session, currentPage, equipmentActive, searchWord);

		// jsp에서 출력할 model
		model.addAttribute("list", toJson(result.getList()));
		model.addAttribute("lastPage", result.getLastPage());
		model.addAttribute("searchWord", result.getSearchWord());
		model.addAttribute("equipmentActive", result.getEquipmentActive());

		return "sportsEquipment/insert";
	}

	// SportsEquipment 추가 액션 (본사직원만 접근 가능)
	@PostMapping("/insert")
	public String insert(HttpSession session, Model model, @RequestParam String itemName, @RequestParam int itemPrice,
			@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "") String searchWord,
			@RequestParam(defaultValue = "") String equipmentActive,
			@RequestParam("sportsEquipmentImg") MultipartFile[] sportsEquipmentImgList) throws JsonProcessingException {

		// 파일업로드 경로 설정
		String path = session.getServletContext().getRealPath("upload/sportsEquipment");

		// service 호출
		sportsEquipmentService.insert(session, path, itemName, itemPrice, sportsEquipmentImgList);

		result = sportsEquipmentService.list(session, currentPage, equipmentActive, searchWord);

		System.out.println(result + "<--result");

		// jsp에서 출력할 model
		model.addAttribute("list", toJson(result.getList()));
		model.addAttribute("lastPage", result.getLastPage());
		model.addAttribute("searchWord", result.getSearchWord());
		model.addAttribute("equipmentActive", result.getEquipmentActive());

		return "sportsEquipment/insert";
	}

	// SportsEquipment 장비,소모품 리스트 (직원)
	@GetMapping("/list")
	public String list(HttpSession session, Model model,
			@ModelAttribute("searchParam") SportsEquipmentSearchParam param) throws JsonProcessingException {
		param.setRowPerPage(5);
		var result = sportsEquipmentService.list(param);

		model.addAttribute("result", toJson(result));

		return "sportsEquipment/list";
	}

	// SportsEquipment 수정 폼 (본사직원만 접근 가능)
	@GetMapping("/update")
	public String update(HttpSession session, Model model, @RequestParam int sportsEquipmentNo)
			throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.one(session, sportsEquipmentNo);

		// jsp에서 출력할 model
		model.addAttribute("sportsEquipmentNo", map.get("sportsEquipmentNo"));
		model.addAttribute("employeeId", map.get("employeeId"));
		model.addAttribute("itemName", map.get("itemName"));
		model.addAttribute("itemPrice", map.get("itemPrice"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("equipmentCreatedate", map.get("equipmentCreatedate"));
		model.addAttribute("equipmentUpdatedate", map.get("equipmentUpdatedate"));
		model.addAttribute("imgList", toJson(map.get("imgList")));

		return "sportsEquipment/update";
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

		return "redirect:/sportsEquipment/update?sportsEquipmentNo=" + sportsEquipmentNo;
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

		return "redirect:/sportsEquipment/update?sportsEquipmentNo=" + sportsEquipmentNo;
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

		return "redirect:/sportsEquipment/update?sportsEquipmentNo=" + sportsEquipmentNo;
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
		model.addAttribute("sportsEquipmentNo", map.get("sportsEquipmentNo"));
		model.addAttribute("employeeId", map.get("employeeId"));
		model.addAttribute("itemName", map.get("itemName"));
		model.addAttribute("itemPrice", map.get("itemPrice"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("equipmentCreatedate", map.get("equipmentCreatedate"));
		model.addAttribute("equipmentUpdatedate", map.get("equipmentUpdatedate"));
		model.addAttribute("imgList", toJson(map.get("sportsEquipmentImgList")));

		return "sportsEquipment/sportsEquipmentOne";
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

		return "redirect:/sportsEquipment/orderByBranch";
	}

	// SportsEquipmentOrderByHead 리스트 (본사직원만 접근 가능)
	@GetMapping("/orderByHead")
	public String orderByHead(HttpSession session, Model model, @RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "") String searchBranch, @RequestParam(defaultValue = "") String searchItem,
			@RequestParam(defaultValue = "") String beginDate, @RequestParam(defaultValue = "") String endDate)
			throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.orderByHead(session, currentPage, searchBranch, searchItem,
				beginDate, endDate);

		// jsp에서 출력할 model
		model.addAttribute("orderList", toJson(map.get("orderList")));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchBranch", map.get("searchBranch"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("searchItem", map.get("searchItem"));
		model.addAttribute("beginDate", map.get("beginDate"));
		model.addAttribute("endDate", map.get("endDate"));

		return "sportsEquipment/orderByHead";
	}

	// SportsEquipmentOrderByBranch 리스트 (지점직원만 접근 가능)
	@GetMapping("/orderByBranch")
	public String orderByBranch(HttpSession session, Model model, @RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "") String searchItem, @RequestParam(defaultValue = "") String beginDate,
			@RequestParam(defaultValue = "") String endDate) throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.orderByBranch(session, currentPage, searchItem, beginDate,
				endDate);

		// jsp에서 출력할 model
		model.addAttribute("orderList", toJson(map.get("orderList")));
		model.addAttribute("searchBranch", map.get("searchBranch"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("equipmentActive", map.get("equipmentActive"));
		model.addAttribute("searchItem", map.get("searchItem"));
		model.addAttribute("beginDate", map.get("beginDate"));
		model.addAttribute("endDate", map.get("endDate"));

		return "sportsEquipment/orderByBranch";
	}

	// SportsEquipmentOrder 수정 액션 (본사직원만 접근 가능)
	@PostMapping("/updateOrder")
	public String updateOrder(HttpSession session, @RequestParam int orderNo, @RequestParam String orderStatus) {

		// 디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);

		// service 호출
		sportsEquipmentService.updateOrder(session, orderNo, orderStatus);

		return "redirect:/sportsEquipment/orderByHead";
	}

	// SportsEquipmentOrder 삭제 액션 (지점직원만 접근 가능)
	@PostMapping("/deleteOrder")
	public String deleteOrder(HttpSession session, @RequestParam int orderNo, @RequestParam String orderStatus) {
		// 디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);

		// service 호출
		sportsEquipmentService.deleteOrder(session, orderNo, orderStatus);

		return "redirect:/sportsEquipment/orderByBranch";
	}

//----------------------------------------- SportsEquipmentInventory -------------------------------------

	// SportsEquipmentInventoryByHead 리스트 (본사직원만 접근 가능)
	@GetMapping("/inventoryByHead")
	public String inventoryByHead(HttpSession session, Model model, @RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "") String searchBranch, @RequestParam(defaultValue = "") String searchItem)
			throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.inventoryHead(session, currentPage, searchBranch, searchItem);

		// jsp에서 출력할 model
		model.addAttribute("inventoryList", toJson(map.get("inventoryList")));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchItem", map.get("searchItem"));
		model.addAttribute("searchBranch", map.get("searchBranch"));

		return "sportsEquipment/inventoryByHead";
	}

	// SportsEquipmentInventoryByBranch 리스트 (지점직원만 접근 가능)
	@GetMapping("/inventoryByBranch")
	public String inventoryByHead(HttpSession session, Model model, @RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "") String searchItem) throws JsonProcessingException {

		// service 호출
		Map<String, Object> map = sportsEquipmentService.inventoryBranch(session, currentPage, searchItem);

		// jsp에서 출력할 model
		model.addAttribute("inventoryList", toJson(map.get("inventoryList")));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchItem", map.get("searchItem"));

		return "sportsEquipment/inventoryByBranch";
	}
}