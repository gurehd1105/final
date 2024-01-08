package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gym.service.CustomerService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerForm;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController { 
	@Autowired private CustomerService customerService;
  	// login (로그인) Form
  @GetMapping("/loginCustomer")
  public String loginCustomer(HttpSession session) {
	  // id 유효성검사
	Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	if(loginCustomer != null) {
		return "home";
	}
	
    return "customer/loginCustomer";
  }

  	// login 후 Act -> session 세팅 후 home.jsp로 이동
  @PostMapping("/loginCustomer")
  public String loginCustomer(HttpSession session, Customer customer) {
    Customer loginCustomer = customerService.loginCustomer(customer);
    if(loginCustomer != null) {	// 등록된 ID가 있을 시    	
    	 session.setAttribute("loginCustomer", loginCustomer);
    	 return "home";
    	 
	    } else { // 정보 없을 시
	    	log.info(customer.getCustomerId() + " / " + customer.getCustomerPw() + "  <-- login 실패");
	    	return "redirect:/loginCustomer";
	    }
    }   

  	// insert (회원가입) Form
  @GetMapping("/insertCustomer")
  public String insertCustomer(HttpSession session) {
	  // id 유효성검사
	Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	if(loginCustomer != null) {
		return "home";
	}
    return "customer/insertCustomer";
  }

	// insert (회원가입) Act
	@PostMapping("/insertCustomer")
	public String insertCustomer(HttpSession session, CustomerForm customerForm, String address1, String address2,
			String address3) {
		log.info(customerForm.getCustomerId() + "  <- customerId");
		String path = session.getServletContext().getRealPath("/upload/customer");

		customerForm.setCustomerAddress(address1 + " " + address2 + address3);

		int result = customerService.insertCustomer(customerForm, path);
		if (result == 1) { // 가입 완
			return "customer/loginCustomer";
		} else { // 예외발생
			return "customer/insertCustomer";
		}
	}

  	// delete (탈퇴) update(customerActive : Y -> N), delete(customerImg , customerDetail)
  @GetMapping("/deleteCustomer")
  public String deleteCustomer(HttpSession session, Model model) { // 탈퇴화면 아이디정보 표기위한 세션 전달
	  // id 유효성검사
	Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	if(loginCustomer == null) {
		return "customer/loginCustomer";
	}
	
    model.addAttribute("loginCustomer" , loginCustomer);
    return "customer/deleteCustomer";
  }
  
  
  	// delete (탈퇴) Act
  @PostMapping("/deleteCustomer")
  public String deleteCustomer(Customer customer, HttpSession session) {
	 Customer loginCustomer = (Customer)session.getAttribute("loginCustomer"); 
	 customer.setCustomerNo(loginCustomer.getCustomerNo());
	 int result = customerService.deleteCustomer(customer);
	 
	 if(result==1) {	// 탈퇴 완 --> login 창으로 이동
		 session.invalidate();
		 return "customer/loginCustomer";
	 } else {	// 예외발생
		 return "customer/deleteCustomer";
	 }
    
  }
  
  	// 마이페이지
  @GetMapping("/customerOne")
  public String customerOne(HttpSession session, Model model) {
	  // id 유효성검사
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			return "customer/loginCustomer";
		}
		
	  
	  Map<String, Object> resultMap = customerService.customerOne(loginCustomer);
	  model.addAttribute("resultMap", resultMap);
	  
	  return "customer/customerOne";
  }  
  
  	// 내정보 수정 Form
  // 접속 전 PW 확인	
  @GetMapping("/updateCustomerOneForPw")
  public String customerOneForCheckPw(HttpSession session, Model model) {
	  // id 유효성검사
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			return "customer/loginCustomer";
		}
	  model.addAttribute("loginCustomer", loginCustomer);
	  return "customer/updateCustomerOneForPw";
  }
  // PW확인 후 Form 접속	
  @PostMapping("/updateCustomerOneForm")
  public String updateCustomerOne(HttpSession session, Model model, String customerPw) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  loginCustomer.setCustomerPw(customerPw);
	  Customer checkCustomer = customerService.loginCustomer(loginCustomer);
	  if(checkCustomer == null) { // PW 확인 불일치 --> PW 확인 페이지로 return
		  log.info("PW 불일치, 접속실패");
		  return "customer/updateCustomerOneForPw";
	  } else {
		  log.info("PW 일치, 접속성공");
		  
	  Map<String, Object> resultMap = customerService.customerOne(loginCustomer);
	  
	  
	  	// 성별 option 값 표기
	  String customerGender = (String)resultMap.get("customerGender");
	  String customerOtherGender = null;
	  if(customerGender.equals("남")) {
		  customerOtherGender = "여";
	  } else {
		  customerOtherGender = "남";
	  }
	  resultMap.put("customerOtherGender", customerOtherGender);
	  
	  String emailId = ((String)resultMap.get("customerEmail")).substring(0,((String)resultMap.get("customerEmail")).lastIndexOf("@"));
	  String emailJuso = ((String)resultMap.get("customerEmail")).substring(((String)resultMap.get("customerEmail")).lastIndexOf("@")+1);	  
	  resultMap.put("emailId", emailId);
	  resultMap.put("emailJuso", emailJuso);
	  
	  model.addAttribute("resultMap", resultMap);
	  
	  return "customer/updateCustomerOne";
	  }
  }  
  
  	// 내정보 수정 Act
  @PostMapping("/updateCustomerOne")
  public String updateCustomerOne(HttpSession session, CustomerForm customerForm,		  						
		  						String address1, String address2, String address3) {
	  String path = session.getServletContext().getRealPath("/upload/customer");
	  
	  customerForm.setCustomerAddress(address1 + " " + address2 + address3);

	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  customerService.updateCustomerOne(path, customerForm, loginCustomer.getCustomerNo());	// 반환값 없음 (void)
	  return "redirect:/customerOne";
  }
  
  	// PW 수정 Form
  @GetMapping("/updateCustomerPw")
  public String updateCustomerPw(HttpSession session, Model model) {	// ID값 표기 위한 세션 세팅
	  // id 유효성검사
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			return "customer/loginCustomer";
		}
		
	  model.addAttribute("loginCustomer", loginCustomer);
	  return "customer/updateCustomerPw";
  }
  	// PW 수정 Act
  @PostMapping("/updateCustomerPw")
  public String updateCustomerPw(HttpSession session, Customer customer, String customerNewPw) {
	  
	  
	  int result = customerService.updateCustomerPw(customer, customerNewPw);
	  if(result > 0) {	// PW 수정 완 --> 재로그인
		  session.invalidate();
		  return "customer/loginCustomer";
	  } else {			// 수정 실패 현재 페이지로 return
		  return "customer/updateCustomerPw";
	  }
  }
  
  	// logout -> login.jsp로 이동
  @GetMapping("/logoutCustomer")
  public String logoutCustomer(HttpSession session) {
    session.invalidate();
    return "customer/loginCustomer";
  }
}
