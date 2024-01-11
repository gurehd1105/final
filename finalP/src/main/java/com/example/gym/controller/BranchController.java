package com.example.gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.BranchService;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("branch")
public class BranchController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private BranchService branchService;

	// 지점 목록 조회.
	@GetMapping("/list")
	public String branchList(HttpSession session, @RequestParam(defaultValue = "1") int currentPage, Model model)
			throws JsonProcessingException {
		
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");

		if (loginEmployee != null) {
			model.addAttribute("loginEmployee", loginEmployee);
		}

		if (loginCustomer != null) {
			model.addAttribute("loginCustomer", loginCustomer);
		}

		int rowPerPage = 5;
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		Map<String, Object> resultMap = branchService.branchList(paramMap);
		Object branchList = resultMap.get("branchList");
		model.addAttribute("branchList", mapper.writeValueAsString(branchList)); // branchList 출력 완
		log.info(branchList.toString());
		// 페이징
		int totalRow = (int) resultMap.get("totalRow");
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage += 1;
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rowPerPage", rowPerPage);
		return "branch/list";
	}

	@GetMapping("insert")
	public String insert() {
		return "branch/insert";
	}

	@PostMapping("insert")
	public String insert(Branch branch, String address1, String address2, String address3) {
		Map<String, Object> map = new HashMap<>();
		map.put("branchName", branch.getBranchName());
		map.put("branchTel", branch.getBranchTel());
		
		// 주소 합치기
		String fullAddress = address1 + " " + address2 + " " + address3;
		map.put("branchAddress", fullAddress);
		log.info(fullAddress);
		int insertRow = branchService.insertBranch(map);
		if (insertRow == 1) {
			return "redirect:/branch/list"; // "Redirect:"에서 오타 수정
		} else {
			return "branch/insert";
		}
	}

}
