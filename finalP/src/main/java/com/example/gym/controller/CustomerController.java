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
    if(loginCustomer != null) {
	    if(loginCustomer.getCustomerActive().equals("Y")) { // 로그인 성공
	    	 session.setAttribute("loginCustomer", loginCustomer);
	    	 return "home";
	    } else { // 정보 있으나 탈퇴회원
	    	return "redirect:/loginCustomer";
	    }
    } else { // 회원정보 불일치 -> 정보없음
    	return "redirect:/loginCustomer";
    }
  }

  // insert (회원가입) Form
  @GetMapping("/insertCustomer")
  public String insertCustomer() {
    return "customer/insertCustomer";
  }

  // insert (회원가입) Act
  @PostMapping("/insertCustomer")
  public String insertCustomer(CustomerForm customerForm, String customerEmailId, 
		  						String customerEmailJuso, HttpSession session) {
	String path = session.getServletContext().getRealPath("/customerImg");
	customerForm.setCustomerEmail(customerEmailId+"@"+customerEmailJuso);
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
  public String deleteCustomer(String customerId, String customerPw, int customerNo) {
	 Customer paramCustomer = new Customer();
	 paramCustomer.setCustomerId(customerId);
	 paramCustomer.setCustomerPw(customerPw);
	 paramCustomer.setCustomerNo(customerNo);
	 customerService.deleteCustomer(paramCustomer);
    return "customer/loginCustomer";
  }
  
  // 마이페이지
  @GetMapping("/customerOne")
  public String customerOne(HttpSession session, Model model) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  Map<String, Object> resultMap = customerService.customerOne(loginCustomer);
	  model.addAttribute("resultMap", resultMap);
	  
	  return "customer/customerOne";
  }  
  
  // 내정보 수정 Form
  @GetMapping("/updateCustomerOneForPw")
  public String customerOneForCheckPw() {
	  return "customer/updateCustomerOneForPw";
  }
  @PostMapping("/updateCustomerOneForm")
  public String updateCustomerOne(HttpSession session, Model model, String customerPw) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  loginCustomer.setCustomerPw(customerPw);
	  Customer checkCustomer = customerService.loginCustomer(loginCustomer);
	  if(checkCustomer == null) {
		  return "customer/updateCustomerOneForPw";
	  } else {
	  Map<String, Object> resultMap = customerService.customerOne(loginCustomer);
	  model.addAttribute("resultMap", resultMap);
	  
	  return "customer/updateCustomerOne";
	  }
  }  
  // 내정보 수정 Act
  @PostMapping("/updateCustomerOne")
  public String updateCustomerOne(HttpSession session, CustomerForm customerForm) {
	  String path = session.getServletContext().getRealPath("/customerImg");
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  customerService.updateCustomerOne(path, customerForm, loginCustomer.getCustomerNo());
	  return "redirect:/customerOne";
  }
  
  // Pw 수정 Form
  @GetMapping("/updateCustomerPw")
  public String updateCustomerPw(HttpSession session, Model model) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  model.addAttribute("loginCustomer", loginCustomer);
	  return "customer/updateCustomerPw";
  }
  // Pw 수정 Act
  @PostMapping("/updateCustomerPw")
  public String updateCustomerPw(HttpSession session, String customerPw, String customerNewPw) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  loginCustomer.setCustomerPw(customerPw);
	  
	  int result = customerService.updateCustomerPw(loginCustomer, customerNewPw);
	  if(result==0) {
		  return "customer/updateCustomerPw";
	  } else {
	  session.invalidate();
	  return "customer/loginCustomer";
	  }
  }
  
  // logout -> login.jsp로 이동
  @GetMapping("/logoutCustomer")
  public String logoutCustomer(HttpSession session) {
    session.invalidate();
    return "customer/loginCustomer";
  }
}
