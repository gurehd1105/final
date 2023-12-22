package com.example.gym.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gym.service.CustomerService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {
	@Autowired private CustomerService customerService;
	
	@GetMapping("/insertCustomer")
	public String insertCustomer() {
		return "customer/insertCustomer";
	}
	
	@PostMapping("/insertCustomer")
	public String insertCustomer(Customer customer, CustomerDetail customerDetail) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customer", customer);
		paramMap.put("customerDetail", customerDetail);
		
		customerService.insertCustomer(paramMap);		
		return "customer/loginCustomer";
	}
	
	@GetMapping("/loginCustomer")
	public String loginCustomer() {
		return "customer/loginCustomer";
	}
	
	@PostMapping("/loginCustomer")
	public String loginCustomer(Model model, HttpSession session, Customer paramCustomer) {
		Customer loginCustomer = customerService.loginCustomer(paramCustomer);
		session.setAttribute("loginCustomer", loginCustomer);
		
		return "home";
	}
}
