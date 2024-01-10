package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.BranchService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.EmployeeService;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Employee;
import com.example.gym.vo.EmployeeForm;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	private CustomerService customerService;
	@Autowired
	private BranchService branchService;
	// 로그인 폼
	@GetMapping("login")
	public String employeeLogin(HttpSession session) {
		// 로그인 되어 있을 경우 home으로
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee != null) {
			return "home";
		}
		return "employee/login";
	}

	// 로그인 엑션
	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<String> employeeLogin(HttpSession session, @RequestBody Employee employee) {
		Employee loginEmployee = employeeService.loginEmployee(employee);
		session.setAttribute("loginEmployee", loginEmployee);

		return ResponseEntity.ok(loginEmployee == null ? "fail" : "success");
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "employee/login";
	}

	// 직원 입력 폼
	@GetMapping("insert")
	public String insertEmployee(HttpSession session , Model model) {
		List<Branch> branches = branchService.branchList();
		log.info(branches.toString());
        model.addAttribute("branches", branches);
		return "employee/insert";
	}

	// 직원 입력 엑션
	@PostMapping("insert")
	@ResponseBody
	public ResponseEntity<?> insertEmployee(HttpSession session, @RequestBody EmployeeForm employeeForm
			, Model model) {
		log.info(employeeForm.toString());
		String path = session.getServletContext().getRealPath("/upload/employee");
		int result = employeeService.insertEmployee(employeeForm, path);
		return result == 1 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
	}

	// 직원 비활성화 기능 update(employee_Active : Y -> N)
	@GetMapping("delete")
	public String deleteEmployee(HttpSession session, Model model) {
		// 직원 비활성화 아이디정보 표기위한 세션 전달
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/login";
		}
		model.addAttribute("loginEmployee", loginEmployee);
		return "employee/delete";
	}

	// 직원 비활성화 액션
	@PostMapping("delete")
	public String deleteEmploye(String employeeId, String employeePw, int employeeNo, HttpSession session) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmployeePw(employeePw);
		employee.setEmployeeNo(employeeNo);
		int result = employeeService.deleteEmployee(employee);

		if (result == 1) { // 탈퇴 완 --> login 창으로 이동
			session.invalidate();
			return "employee/login";
		} else { // 예외발생
			return "employee/delete";
		}

	}

	// 직원 정보 수정 폼
	@GetMapping("updateOneForPw")
	public String employeeOneForCheckPw(HttpSession session) {
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/login";
		}

		return "employee/updateOneForPw";
	}

	@PostMapping("/updateOneForPw")
	public String updateEmployeeOne(HttpSession session, Model model, String employeePw) {
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		loginEmployee.setEmployeePw(employeePw);
		Employee checkEmployee = employeeService.loginEmployee(loginEmployee);
		if (checkEmployee == null) { // PW 확인 불일치 --> PW 확인 페이지로 return
			log.info("PW 불일치, 접속실패");
			return "employee/updateOneForPw";
		} else {
			log.info("PW 일치, 접속성공");

			Map<String, Object> resultMap = employeeService.employeeOne(loginEmployee);

			// Email 값 표기
			String employeeEmail = (String) resultMap.get("employeeEmail");
			String employeeEmailId = employeeEmail.substring(0, employeeEmail.lastIndexOf("@"));
			String employeeEmailJuso = employeeEmail.substring(employeeEmail.lastIndexOf("@") + 1);
			resultMap.put("emailId", employeeEmailId);
			resultMap.put("emailJuso", employeeEmailJuso);

			// 성별 option 값 표기
			String employeeGender = (String) resultMap.get("employeeGender");
			String employeeOtherGender = null;
			if (employeeGender.equals("남")) {
				employeeOtherGender = "여";
			} else {
				employeeOtherGender = "남";
			}
			resultMap.put("employeeOtherGender", employeeOtherGender);

			model.addAttribute("resultMap", resultMap);

			return "employee/updateOne";
		}
	}

	// 내정보 수정 Act
	@PostMapping("/updateOne")
	public String updateEmployeeOne(HttpSession session, EmployeeForm employeeForm, 
			String employeeEmailId,String employeeEmailJuso, String employeeEmailAutoJuso) {
		String path = session.getServletContext().getRealPath("/upload/employee");

		if (employeeEmailAutoJuso.equals("")) { // 선택한 이메일이 없다면 직접 입력한 이메일주소로 등록
			employeeForm.setEmployeeEmail(employeeEmailId + "@" + employeeEmailJuso);
		} else { // 선택한 이메일이 있다면 해당 이메일주소로 등록
			employeeForm.setEmployeeEmail(employeeEmailId + "@" + employeeEmailAutoJuso);
		}

		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		employeeService.updateEmployeeOne(path, employeeForm, loginEmployee.getEmployeeNo()); // 반환값 없음 (void)
		return "redirect:/employee/employeeOne";
	}

	// PW 수정 Form
	@GetMapping("/updatePw")
	public String updateEmployeePw(HttpSession session, Model model) {
		// ID값 표기 위한 세션 세팅
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/login";
		}

		model.addAttribute("loginEmployee", loginEmployee);
		return "employee/updatePw";
	}

	// PW 수정 Act
	@PostMapping("/updatePw")
	public String updateEmployeePw(HttpSession session, String employeePw, String employeeNewPw) {
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		loginEmployee.setEmployeePw(employeePw);

		int result = employeeService.updateEmployeePw(loginEmployee, employeeNewPw);
		if (result > 0) { // PW 수정 완 --> 재로그인
			session.invalidate();
			return "employee/login";
		} else { // 수정 실패 현재 페이지로 return
			return "employee/updatePw";
		}
	}


	// 마이페이지
	@GetMapping("/employeeOne")
	public String employeeOne(HttpSession session, Model model) {
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");

		Map<String, Object> resultMap = employeeService.employeeOne(loginEmployee);
		model.addAttribute("resultMap", resultMap);

		return "employee/employeeOne";
	}
	
	// 관리자 목록 리스트
		@GetMapping("/list")
		public String customerList(Model model) {
			Map<String, Object> customerList = customerService.selectAllCustomer();
			log.info(customerList.toString());

			model.addAttribute("customerList", customerList);
			return "employee/list";
		}
}