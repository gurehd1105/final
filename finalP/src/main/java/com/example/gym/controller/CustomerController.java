package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gym.service.CustomerService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;
import com.example.gym.vo.CustomerForm;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {
  @Autowired
  private CustomerService customerService;
  
  // login (로그인) Form
  @GetMapping("/loginCustomer")
  public String loginCustomer() {
    return "customer/loginCustomer";
  }

  // login 후 Act -> session 세팅 후 home.jsp로 이동
  @PostMapping("/loginCustomer")
  public String loginCustomer(Model model, HttpSession session, Customer customer) {
    Customer loginCustomer = customerService.loginCustomer(customer);
    
    session.setAttribute("loginCustomer", loginCustomer);

    return "home";
  }

  // insert (회원가입) Form
  @GetMapping("/insertCustomer")
  public String insertCustomer() {
    return "customer/insertCustomer";
  }

  // insert (회원가입) Act
  @PostMapping("/insertCustomer")
  public String insertCustomer(CustomerForm customerForm, HttpSession session) {
	String path = session.getServletContext().getRealPath("/customerImg");
	customerService.insertCustomer(customerForm, path);
	
		return "customer/loginCustomer";	
  }

  // delete (탈퇴) update(customerActive : Y -> N), delete(customerImg , customerDetail) 
  @GetMapping("/deleteCustomer")
  public String deleteCustomer(HttpSession session, Model model) { // 탈퇴화면 아이디정보 표기위한 세션 전달
	Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
    model.addAttribute("loginCustomer" , loginCustomer);
    return "customer/deleteCustomer";
  }
  
  
  	//완료 후 loginForm으로 이동  
  @PostMapping("/deleteCustomer")
  public String deleteCustomer(String customerId, String CustomerPw) {
	 Customer paramCustomer = new Customer();
	 paramCustomer.setCustomerId(customerId);
	 paramCustomer.setCustomerPw(CustomerPw);
	 customerService.deleteCustomer(paramCustomer);
    return "customer/loginCustomer";
  }
  
  
  @GetMapping("/customerOne")
  public String customerOne(Customer customer, HttpSession session, Model model) {
	  customer = (Customer)session.getAttribute("loginCustomer");
	  Map<String, Object> resultMap = customerService.customerOne(customer);
	  model.addAttribute("resultMap", resultMap);
	  
	  return "customer/customerOne";
  }
  
  
  
  
  
  
  
  @GetMapping("/updateCustomerOne")
  public String updateCustomerOne(Customer customer, HttpSession session, Model model) {
	 
	  
	  return "customer/updateCustomerOne";
  }
  
  
  @PostMapping("/updateCUstomerOne")
  public String updateCustomerOne(CustomerDetail customerDetail) {
	  
	  return "redirect:/updateCustomerOne";
  }
  
  // logout -> login.jsp로 이동
  @GetMapping("/logoutCustomer")
  public String logoutCustomer(HttpSession session) {
    session.invalidate();
    return "customer/loginCustomer";
  }
}
