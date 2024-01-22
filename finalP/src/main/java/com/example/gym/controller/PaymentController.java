package com.example.gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.PaymentService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Page;
import com.example.gym.vo.Payment;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("payment")
public class PaymentController extends DefaultController{
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/insert")
	@ResponseBody
	public int insert(@RequestBody Payment payment, HttpSession session) {
		Map<String, Object> loginCustomer = (Map)session.getAttribute("loginCustomer");
		payment.setCustomerNo((int) loginCustomer.get("customerNo"));
		
		int result = 0;
		result = paymentService.insert(payment);		
		return result;
	}
	@PostMapping("/delete")
	@ResponseBody
	public int delete(@RequestBody Payment payment) {
		int result = 0;
		result = paymentService.delete(payment);
		
		return result;
	}
	
	@GetMapping("/list")
	public String select(Map<String, Object> paramMap, @RequestParam(defaultValue = "") String customerName,  Model model, Page page) {


		page.setRowPerPage(5);
	
		paramMap.put("beginRow", page.getBeginRow());
		paramMap.put("rowPerPage", page.getRowPerPage());
		paramMap.put("customerName", customerName);
		page.setTotalCount(paymentService.countOfPayment(paramMap));
		model.addAttribute("page", page);		
		
		List<Map<String, Object>> paymentList = paymentService.select(paramMap);
		log.info("customerName: " + customerName);
		log.info((paymentList.size() == 0 || paymentList == null) ? "출력값 없음" : "출력 성공");
		model.addAttribute("paymentList", toJson(paymentList));
		model.addAttribute("page", page);
		model.addAttribute("customerName", customerName);
		return ViewRoutes.결제정보_조회;
	}
}


















