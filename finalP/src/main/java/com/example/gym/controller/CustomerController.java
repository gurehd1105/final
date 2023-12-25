package com.example.gym.controller;

import com.example.gym.service.CustomerService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

  // login 후 Act -> home.jsp로 이동
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
  public String insertCustomer(Customer customer,CustomerDetail customerDetail) {
	customerService.insertCustomer(customer, customerDetail);
	
		return "customer/loginCustomer";	
  }

  // delete (탈퇴) update(customerActive : Y -> N), delete(customerImg , customerDetail)
  // 완료 후 loginForm으로 이동
  @GetMapping("/deleteCustomer")
  public String deleteCustomer(Customer customer) {
    customerService.deleteCustomer(customer);
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
