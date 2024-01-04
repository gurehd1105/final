package com.example.gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.EmployeeService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerForm;
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
	public String employeeLogin(HttpSession session) {
		// 로그인 되어 있을 경우 home으로
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee != null) {
			return "home";
		}
		return "employee/login";
	}

	// 로그인 엑션
	@PostMapping("/employeeLogin")
	public String employeeLogin(HttpSession session, Employee employee) {
		log.debug("getLoginEmployee", "Controller employee", employee.toString());
		Employee loginEmployee = employeeService.getLoginEmployee(employee);

		// 로그인성공시 세션에 정보담기
		if (loginEmployee != null) {
			session.setAttribute("loginEmployee", loginEmployee);
			return "home";

		} else { // 로그인 실패 시
			log.info(employee.getEmployeeId() + " / " + employee.getEmployeePw());
			return "redirect:/EmployeeLogin";
		}

	}

	// 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "employee/customerLogin";
	}

	// 직원 입력 폼
	@GetMapping("/insertEmployee")
	public String insertEmployee(HttpSession session) {
		// 로그인 되어 있는 경우에만 직원 추가 가능
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employeeLogin";
		}
		return "employee/insertEmployee";
	}

	// 직원 입력 엑션
	@PostMapping("/insertEmployee")
	public String insertEmployee(Employee employee, HttpSession session, EmployeeForm employeeForm,
			String employeeEmailId, String employeeEmailJuso, String employeeEmailAutoJuso, String address1,
			String address2, String address3) {
		String path = session.getServletContext().getRealPath("/upload/employee");

		if (employeeEmailAutoJuso.equals("")) { // 선택한 이메일이 없다면 직접 입력한 이메일주소로 등록
			employeeForm.setEmployeeEmail(employeeEmailId + "@" + employeeEmailJuso);
		} else { // 선택한 이메일이 있다면 해당 이메일주소로 등록
			employeeForm.setEmployeeEmail(employeeEmailId + "@" + employeeEmailAutoJuso);
		}
		employeeForm.setEmployeeAddress(address1 + " " + address2 + address3);

		int result = employeeService.insertEmployee(employeeForm, path);
		if (result == 1) { // 가입 완
			return "employee/employeeLogin";
		} else { // 예외발생
			return "employee/insertEmployee";
		}

	}

	// 직원 비활성화 기능 update(employee_Active : Y -> N)
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(HttpSession session, Model model) {
		// 직원 비활성화 아이디정보 표기위한 세션 전달
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/employeeLogin";
		}
		model.addAttribute("loginEmployee", loginEmployee);
		return "employee/deleteEmployee";
	}

	// 직원 비활성화 액션
	@PostMapping("/deleteEmployee")
	public String deleteEmploye(String employeeId, String employeePw, int employeeNo, HttpSession session) {
		Employee paramEmploye = new Employee();
		paramEmploye.setEmployeeId(employeeId);
		paramEmploye.setEmployeePw(employeePw);
		paramEmploye.setEmployeeNo(employeeNo);
		int result = employeeService.deleteEmployee(paramEmploye);

		if (result == 1) { // 탈퇴 완 --> login 창으로 이동
			session.invalidate();
			return "employee/employeeLogin";
		} else { // 예외발생
			return "employee/deleteEmploye";
		}

	}

	// 직원 정보 수정 폼
	@GetMapping("updateEmployeeOneForPw")
	public String employeeOneForCheckPw(HttpSession session) {
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/employeeLogin";
		}

		return "employee/updateEmployeeOneForPw";
	}

	@PostMapping("/updateEmployeeOneForm")
	public String updateEmployeeOne(HttpSession session, Model model, String employeePw) {
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		loginEmployee.setEmployeePw(employeePw);
		Employee checkEmployee = employeeService.loginEmployee(loginEmployee);
		if (checkEmployee == null) { // PW 확인 불일치 --> PW 확인 페이지로 return
			log.info("PW 불일치, 접속실패");
			return "employee/updateEmployeeOneForPw";
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

			return "employee/updateEmployeeOne";
		}
	}

	// 내정보 수정 Act
	@PostMapping("/updateEmployeeOne")
	public String updateEmployeeOne(HttpSession session, EmployeeForm employeeForm, String employeeEmailId,
			String employeeEmailJuso, String employeeEmailAutoJuso, String address1, String address2, String address3) {
		String path = session.getServletContext().getRealPath("/upload/employee");

		if (employeeEmailAutoJuso.equals("")) { // 선택한 이메일이 없다면 직접 입력한 이메일주소로 등록
			employeeForm.setEmployeeEmail(employeeEmailId + "@" + employeeEmailJuso);
		} else { // 선택한 이메일이 있다면 해당 이메일주소로 등록
			employeeForm.setEmployeeEmail(employeeEmailId + "@" + employeeEmailAutoJuso);
		}

		employeeForm.setEmployeeAddress(address1 + " " + address2 + address3);

		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		employeeService.updateEmployeeOne(path, employeeForm, loginEmployee.getEmployeeNo()); // 반환값 없음 (void)
		return "redirect:/employeeOne";
	}

	// PW 수정 Form
	@GetMapping("/updateEmployeePw")
	public String updateEmployeePw(HttpSession session, Model model) {
		// ID값 표기 위한 세션 세팅
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/employeeLogin";
		}

		model.addAttribute("loginEmployee", loginEmployee);
		return "employee/updateEmployeePw";
	}

	// PW 수정 Act
	@PostMapping("/updateEmployeePw")
	public String updateEmployeePw(HttpSession session, String employeePw, String employeeNewPw) {
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		loginEmployee.setEmployeePw(employeePw);

		int result = employeeService.updateEmployeePw(loginEmployee, employeeNewPw);
		if (result > 0) { // PW 수정 완 --> 재로그인
			session.invalidate();
			return "employee/employeeLogin";
		} else { // 수정 실패 현재 페이지로 return
			return "employee/updateEmployeePw";
		}
	}

	// 관리자 목록 리스트
	@GetMapping("/employee/employeeList")
	public String employeeList(Model model) {
		List<Employee> employeeList = employeeService.getEmployeeList();
		log.debug("getEmployeeList", "Controller employeeList", employeeList.toString());

		model.addAttribute("employeeList", employeeList);
		return "employee/employeeList";
	}

	// 마이페이지
	@GetMapping("/employeeOne")
	public String employeeOne(HttpSession session, Model model) {
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return "employee/employeeLogin";
		}

		Map<String, Object> resultMap = employeeService.employeeOne(loginEmployee);
		model.addAttribute("resultMap", resultMap);

		return "employee/employeeOne";
	}
}