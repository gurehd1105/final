package com.example.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gym.service.EmployeeService;
import com.example.gym.vo.Employee;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	//로그아웃
   @GetMapping("/logout")
   public String logout(HttpSession session) {
      session.invalidate();   
      return "employee/login";
      
   }
   
   //직원 추가폼
 	@GetMapping("/insertEmployee")
 	public String insertEmployee() {
 		System.out.print("add");
 		return "employee/insertEmployee";
 	}
 	
 	//회원가입 액션
	@PostMapping("/insertEmployee")
	public String insertEmployee(Employee employee) {
		
	//Service호출
	employeeService.insertEmployee(employee);
	return "redirect:/home";
	}
 	
	@GetMapping("/login")
	public String login(HttpSession session , Model model) {
		// 로그인 전에만 가능
		if(session.getAttribute("loginEmployee") != null) {
			return "redirect:/home";
		}
		return "employee/login";
		
	}
	@PostMapping("/login")
	public String login(HttpSession session , Employee paramEmployee) {
	 	Employee loginEmployee = employeeService.loginEmployee(paramEmployee);
	 	if (loginEmployee == null) {
	 		//로그인 실패
	 		return "redirect:/login";
	 	}
	 	session.setAttribute("loginEmployee", loginEmployee);
	 	
		return "redirect:/home";
	}
}
