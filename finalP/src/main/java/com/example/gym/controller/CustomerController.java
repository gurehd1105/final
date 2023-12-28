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


@Controller
public class CustomerController { // 프로세스 진행 시 세션 필요한 경우 제외 대부분 세션 유효성 검사 X , 추후 필터링 기능 시도 예정
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
    if(loginCustomer != null) {	// 등록된 ID가 있을 시 
	    if(loginCustomer.getCustomerActive().equals("Y")) { // 활성화 계정 --> 로그인 성공
	    	 session.setAttribute("loginCustomer", loginCustomer);
	    	 return "home";
	    } else { // 정보 있으나 비활성화 계정(탈퇴회원) --> 로그인 페이지로 return
	    	return "redirect:/loginCustomer";
	    }
    } else { // 정보없음(ID or PW 불일치) --> 로그인 페이지로 return
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
		  						String customerEmailJuso, String customerEmailAutoJuso,
		  						HttpSession session) {
	String path = session.getServletContext().getRealPath("/upload/customer");
	
	if(customerEmailAutoJuso.equals("")) { // 선택한 이메일이 없다면 직접 입력한 이메일주소로 등록
		customerForm.setCustomerEmail(customerEmailId+"@"+customerEmailJuso);
	} else {								// 선택한 이메일이 있다면 해당 이메일주소로 등록
		customerForm.setCustomerEmail(customerEmailId+"@"+customerEmailAutoJuso);
	}	
		// 입력 이메일값 디버깅
	System.out.println(customerForm.getCustomerEmail() + " <-- Email");
	
	
	int result = customerService.insertCustomer(customerForm, path);
	if(result==1) { // 가입 완
		return "customer/loginCustomer";
	} else {		// 예외발생
		return "customer/insertCustomer";
	}
  }

  	// delete (탈퇴) update(customerActive : Y -> N), delete(customerImg , customerDetail) 
  @GetMapping("/deleteCustomer")
  public String deleteCustomer(HttpSession session, Model model) { // 탈퇴화면 아이디정보 표기위한 세션 전달
	Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
    model.addAttribute("loginCustomer" , loginCustomer);
    return "customer/deleteCustomer";
  }
  
  
  	// delete (탈퇴) Act
  @PostMapping("/deleteCustomer")
  public String deleteCustomer(String customerId, String customerPw, int customerNo) {
	 Customer paramCustomer = new Customer();
	 paramCustomer.setCustomerId(customerId);
	 paramCustomer.setCustomerPw(customerPw);
	 paramCustomer.setCustomerNo(customerNo);
	 int result = customerService.deleteCustomer(paramCustomer);
	 
	 if(result==1) {	// 탈퇴 완 --> login 창으로 이동
		 return "customer/loginCustomer";
	 } else {	// 예외발생
		 return "customer/deleteCustomer";
	 }
    
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
  // 접속 전 PW 확인
  @GetMapping("/updateCustomerOneForPw")
  public String customerOneForCheckPw() {
	  return "customer/updateCustomerOneForPw";
  }
  // PW확인 후 Form 접속
  @PostMapping("/updateCustomerOneForm")
  public String updateCustomerOne(HttpSession session, Model model, String customerPw) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  loginCustomer.setCustomerPw(customerPw);
	  Customer checkCustomer = customerService.loginCustomer(loginCustomer);
	  if(checkCustomer == null) { // PW 확인 불일치 --> PW 확인 페이지로 return
		  return "customer/updateCustomerOneForPw";
	  } else {
	  Map<String, Object> resultMap = customerService.customerOne(loginCustomer);
	  
	  	// Email 값 표기
	  String customerEmail = (String) resultMap.get("customerEmail");
	  String customerEmailId = customerEmail.substring(0,customerEmail.lastIndexOf("@"));
	  String customerEmailJuso = customerEmail.substring(customerEmail.lastIndexOf("@")+1);
	  resultMap.put("emailId", customerEmailId);
	  resultMap.put("emailJuso", customerEmailJuso);
	  
	  	// 성별 option 값 표기
	  String customerGender = (String)resultMap.get("customerGender");
	  String customerOtherGender = null;
	  if(customerGender.equals("남")) {
		  customerOtherGender = "여";
	  } else {
		  customerOtherGender = "남";
	  }
	  resultMap.put("customerOtherGender", customerOtherGender);
	  
	  
	  model.addAttribute("resultMap", resultMap);
	  
	  return "customer/updateCustomerOne";
	  }
  }  
  
  	// 내정보 수정 Act
  @PostMapping("/updateCustomerOne")
  public String updateCustomerOne(HttpSession session, CustomerForm customerForm,
		  		String customerEmailId, String customerEmailJuso, String customerEmailAutoJuso) {
	  String path = session.getServletContext().getRealPath("/upload/customer");
	  
	  if(customerEmailAutoJuso.equals("")) { // 선택한 이메일이 없다면 직접 입력한 이메일주소로 등록
			customerForm.setCustomerEmail(customerEmailId+"@"+customerEmailJuso);
		} else {							// 선택한 이메일이 있다면 해당 이메일주소로 등록
			customerForm.setCustomerEmail(customerEmailId+"@"+customerEmailAutoJuso);
		}	
			// 입력 이메일값 디버깅
		System.out.println(customerForm.getCustomerEmail() + " <-- Email");
		
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  customerService.updateCustomerOne(path, customerForm, loginCustomer.getCustomerNo());	// 반환값 없음 (void)
	  return "redirect:/customerOne";
  }
  
  	// PW 수정 Form
  @GetMapping("/updateCustomerPw")
  public String updateCustomerPw(HttpSession session, Model model) {	// ID값 표기 위한 세션 세팅
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  model.addAttribute("loginCustomer", loginCustomer);
	  return "customer/updateCustomerPw";
  }
  	// PW 수정 Act
  @PostMapping("/updateCustomerPw")
  public String updateCustomerPw(HttpSession session, String customerPw, String customerNewPw) {
	  Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
	  loginCustomer.setCustomerPw(customerPw);
	  
	  int result = customerService.updateCustomerPw(loginCustomer, customerNewPw);
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
