package com.example.gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.PaymentService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Payment;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment")
public class PaymentController extends DefaultController{
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/insert")
	@ResponseBody
	public int insert(@RequestBody Payment payment, HttpSession session) {
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		payment.setCustomerNo(loginCustomer.getCustomerNo());
		
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
	
	@PostMapping("/list")
	public String select(Map<String, Object> paramMap, Model model) {
		List<Map<String, Object>> paymentList = paymentService.select(paramMap);
		model.addAttribute("paymentList", toJson(paymentList));
		return "";
	}
}
