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
	
	// insert (회원가입) Form
	@GetMapping("/insertCustomer")
	public String insertCustomer() {
		return "customer/insertCustomer";
	}
	
	
	// insert (회원가입) Act
	@PostMapping("/insertCustomer")
	public String insertCustomer(Customer customer, CustomerDetail customerDetail) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customer", customer);
		paramMap.put("customerDetail", customerDetail);
		System.err.println(paramMap);	// 매개값 디버깅
		
		customerService.insertCustomer(paramMap);		
		return "customer/loginCustomer";
	}
	
	// delete (탈퇴) update(customerActive : Y -> N), delete(customerImg , customerDetail)
	// 완료 후 loginForm으로 이동
	public String deleteCustomer(Customer customer) {
		customerService.deleteCustomer(customer);
		return "customer/loginCustomer";
	}
	
	// login (로그인) Form
	@GetMapping("/loginCustomer")
	public String loginCustomer() {
		return "customer/loginCustomer";
	}
	
	
	// login 후 Act -> home.jsp로 이동
	@PostMapping("/loginCustomer")
	public String loginCustomer(Model model, HttpSession session, Customer paramCustomer) {
		Customer loginCustomer = customerService.loginCustomer(paramCustomer);
		session.setAttribute("loginCustomer", loginCustomer);
		
		return "home";
	}
	
	
	// logout -> login.jsp로 이동
	@GetMapping("/logoutCustomer")
	public String logoutCustomer(HttpSession session) {
		session.invalidate();
		return "customer/loginCustomer";
	}
}
