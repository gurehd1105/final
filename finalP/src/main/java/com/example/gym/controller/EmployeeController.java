package com.example.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.EmployeeService;
import com.example.gym.vo.Employee;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	// 로그인 폼
	@GetMapping("/employeeLogin")
	public String employeeLogin() {
		return "employee/login";
	}
	
	// 로그인 엑션
	@PostMapping("/employee/employeeLogin")
	public String employeeLogin(HttpSession session, Employee employee) {
		
		log.debug("getLoginEmployee", "Controller employee", employee.toString());
		Employee loginEmployee = employeeService.getLoginEmployee(employee);
		
		// 로그인성공시 세션에 정보담기
		if(loginEmployee!= null) {
			session.setAttribute("loginEmployee", loginEmployee);
		}
		
		return "redirect:/EmployeeLogin";
	}
	// 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		log.debug("logout", "Controller logout", "logout");
		return "redirect:/home";
	}
		
	// 관리자 목록 리스트
	@GetMapping("/employee/employeeList")
	public String employeeList(Model model) {
		List<Employee> employeeList = employeeService.getEmployeeList();
		log.debug("getEmployeeList", "Controller employeeList", employeeList.toString());
	
	model.addAttribute("employeeList", employeeList);
	return "employee/employeeList";
	}

	// 관리자 삭제
	@GetMapping("/employee/deleteEmployee")
	public String deleteEmployee(String employeeId) {
		int deleteRow = employeeService.getdeleteEmployee(employeeId);
		log.debug("deleteEmployee", "Controller deleteRow", deleteRow);
	return "redirect:/employee/employeeList";
	}

	// 관리자 입력 폼
	@GetMapping("/employee/insertEmployee")
	public String insertEmployee() {
		return "employee/insertEmployee";
	}
	// 관리자 입력 엑션
	@PostMapping("/employee/insertEmployee")
	public String insertEmployee(Employee employee) {
		employeeService.insertEmployee(Employee);
		log.debug("insertEmployee", "Controller employee", employee.toString());
	return "redirect:/employee/employeeList";
	}

	// 관리자 수정 폼
	@GetMapping("/employee/updateEmployee")
	public String updateEmployee(Model model,
									@RequestParam(value="employeeId", required = true) String employeeId) {
	Employee employeeOne = employeeService.getEmployeeOne(employeeId);
	log.debug(employeeId);
	model.addAttribute("employeeOne", employeeOne);
	return "employee/updateEmployee";
	}
	// 관리자 수정 엑션
	@PostMapping("/employee/updateEmployee")
	public String updateEmployee(Employee employee) {
		log.debug("updateEmployee", "Controller Employee", employee.toString());
	employeeService.updateEmployee(employee);
	return "redirect:/employee/employeeList";
		}
	
   
}