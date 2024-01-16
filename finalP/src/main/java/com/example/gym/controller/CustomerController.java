package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.filter.CustomerLoginFilter;
import com.example.gym.service.CustomerService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerForm;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	

	// login (로그인) Form
	@GetMapping("/login")
	public String loginCustomer(HttpSession session) {
		// id 유효성검사
		Map<String,Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
		if (loginCustomer != null) {
			return "home";
		}
 
		return "customer/login";
	}

	// login 후 Act -> session 세팅 후 home.jsp로 이동
	@PostMapping("/login")
	public String loginCustomer(HttpSession session, Customer customer) {
		Map<String,Object> loginCustomer = customerService.loginCustomer(customer);
		if (loginCustomer != null) { // 등록된 ID가 있을 시
			session.setAttribute("loginCustomer", loginCustomer);
			return "home";

		} else { // 정보 없을 시
			log.info(customer.getCustomerId() + " / " + customer.getCustomerPw() + "  <-- login 실패");
			return "redirect:login";
		}
	}
	// insert (회원가입) Form
	@GetMapping("/insert")
	public String insertCustomer(HttpSession session) {
		// id 유효성검사
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		if (loginCustomer != null) {
			return "home";
		}
		return "customer/insert";
	}
	@GetMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestBody Customer customer) {
		int result = 0;
		boolean check = customerService.checkId(customer) == null;
		if (!check) { // id 중복
			log.info(customer.getCustomerId() + " / 중복 ID");
			result = 1;
		}
		return result;
	}

	// insert (회원가입) Act
	@PostMapping("/insert")
	public String insertCustomer(HttpSession session, CustomerForm customerForm,
								String address1, String address2, String address3) {
		customerForm.setCustomerAddress(address1 + " " + address2 + address3);
		
		int result = customerService.insertCustomer(customerForm); 
		
		if (result == 1) {// 가입 완 		  
			return "customer/login"; 
		} else { // 예외발생
			return "customer/insert";
		}		
	}

	// delete (탈퇴) update(customerActive : Y -> N), delete(customerImg ,
	// customerDetail)
	@GetMapping("/delete")
	public String deleteCustomer(HttpSession session, Model model) {
		return "customer/delete";
	}

	// delete (탈퇴) Act
	@PostMapping("/delete")
	public String deleteCustomer(String customerId, String customerPw, int customerNo, HttpSession session) {
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(customerId);
		paramCustomer.setCustomerPw(customerPw);
		paramCustomer.setCustomerNo(customerNo);
		int result = customerService.deleteCustomer(paramCustomer);

		if (result == 1) { // 탈퇴 완 --> login 창으로 이동
			session.invalidate();
			return "customer/login";
		} else { // 예외발생
			return "customer/delete";
		}

	}

	// 마이페이지
	@GetMapping("/customerOne")
	public String customerOne(HttpSession session, Model model) {
		// id 유효성검사
		Map<String,Object> loginCustomer = (Map) session.getAttribute("loginCustomer");

		Map<String, Object> resultMap = customerService.customerOne((int) loginCustomer.get("customerNo"));
		model.addAttribute("resultMap", resultMap);

		return "customer/customerOne";
	}

	// 내정보 수정 Form
	// 접속 전 PW 확인
	@GetMapping("/updateOneForPw")
	public String customerOneForCheckPw(HttpSession session) {

		return "customer/updateOneForPw";
	}

	// PW확인 후 Form 접속
	@PostMapping("/updateOneForm")
	public String updateCustomerOne(HttpSession session, Model model, Customer customer) {
			boolean check = customerService.loginCustomer(customer) == null;
		if (check) { // PW 확인 불일치 --> PW 확인 페이지로 return
			log.info("PW 불일치, 접속실패");
			return "customer/updateOneForPw";
		} else {
			log.info("PW 일치, 접속성공");

			Map<String, Object> resultMap = customerService.customerOne(customer.getCustomerNo());

			// Email 값 표기
			String customerEmail = (String) resultMap.get("customerEmail");
			String customerEmailId = customerEmail.substring(0, customerEmail.lastIndexOf("@"));
			String customerEmailJuso = customerEmail.substring(customerEmail.lastIndexOf("@") + 1);
			resultMap.put("emailId", customerEmailId);
			resultMap.put("emailJuso", customerEmailJuso);

			

			model.addAttribute("resultMap", resultMap);

			return "customer/updateOne";
		}
	}

	// 내정보 수정 Act
	@PostMapping("/updateOne")
	public String updateCustomerOne(HttpSession session, CustomerForm customerForm, 
									String address1, String address2, String address3) {

		customerForm.setCustomerAddress(address1 + " " + address2 + address3);

		Map<String,Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
		customerService.updateCustomerOne(customerForm, (int) loginCustomer.get("customerNo"));
		return "redirect:customerOne";
	}

	// PW 수정 Form
	@GetMapping("/updatePw")
	public String updateCustomerPw(HttpSession session, Model model) { // ID값 표기 위한 세션 세팅
		return "customer/updatePw";
	}

	// PW 수정 Act
	@PostMapping("/updatePw")
	public String updateCustomerPw(HttpSession session, Customer customer, String customerNewPw) {
		
		int result = customerService.updateCustomerPw(customer, customerNewPw);
		if (result > 0) { // PW 수정 완 --> 재로그인
			session.invalidate();

			return "customer/login";
		} else { // 수정 실패 현재 페이지로 return
			return "customer/updatePw";
		}
	}

	// logout -> login.jsp로 이동
	@GetMapping("/logout")
	public String logoutCustomer(HttpSession session) {
		session.invalidate();
		return "customer/login";
	}
}
