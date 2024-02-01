package com.example.gym.controller;

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
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Employee;
import com.example.gym.vo.EmployeeForm;
import com.example.gym.vo.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("employee")
public class EmployeeController extends DefaultController {
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
			return ViewRoutes.홈;
		}
		return ViewRoutes.직원_로그인;
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
		return Redirect(ViewRoutes.직원_로그인);
	}

	// 직원 입력 폼
	@GetMapping("insert")
	public String insertEmployee(HttpSession session, Model model) {
		List<Branch> branches = branchService.branch();
		model.addAttribute("branches", toJson(branches));
		return ViewRoutes.직원_추가;
	}

	// 직원 입력 엑션
	@PostMapping("insert")
	@ResponseBody
	public ResponseEntity<?> insertEmployee(HttpSession session, @RequestBody EmployeeForm employeeForm, Model model) {
		int result = employeeService.insertEmployee(employeeForm);
		return result == 1 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
	}

	// 직원 비활성화 기능 update(employee_Active : Y -> N)
	@GetMapping("delete")
	public String deleteEmployee(HttpSession session, Model model) {
		// 직원 비활성화 아이디정보 표기위한 세션 전달
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return ViewRoutes.직원_로그인;
		}
		Integer employeeNo = (Integer) session.getAttribute("employeeNo");
		model.addAttribute("employeeNo", employeeNo);
		model.addAttribute("loginEmployee", loginEmployee);
		return ViewRoutes.직원_삭제;
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
			return ViewRoutes.직원_로그인;
		} else { // 예외발생
			return ViewRoutes.직원_삭제;
		}

	}

	// 직원 정보 수정 폼
	@GetMapping("pwCheck")
	public String employeeOneForCheckPw(HttpSession session) {
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return ViewRoutes.직원_로그인;
		}

		return ViewRoutes.직원_암호_확인;
	}

	@PostMapping("updateOneForPw")
	public String updateEmployeeOne(HttpSession session, Model model, String employeePw) {
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		loginEmployee.setEmployeePw(employeePw);
		Employee checkEmployee = employeeService.loginEmployee(loginEmployee);
		if (checkEmployee == null) { // PW 확인 불일치 --> PW 확인 페이지로 return
			log.info("PW 불일치, 접속실패");
			return ViewRoutes.직원_암호_확인;
		} else {
			log.info("PW 일치, 접속성공");

			Map<String, Object> resultMap = employeeService.getEmployee(loginEmployee);

			// Email 값 표기
			String employeeEmail = (String) resultMap.get("employeeEmail");
			String employeeEmailId = employeeEmail != null ? employeeEmail.substring(0, employeeEmail.lastIndexOf("@"))
					: "";
			String employeeEmailJuso = employeeEmail != null
					? employeeEmail.substring(employeeEmail.lastIndexOf("@") + 1)
					: "";
			resultMap.put("emailId", employeeEmailId);
			resultMap.put("emailJuso", employeeEmailJuso);

			// 성별 option 값 표기
			String employeeGender = (String) resultMap.get("employeeGender");
			String employeeOtherGender = null;
			if (employeeGender != null) {
				employeeOtherGender = employeeGender.equals("남") ? "여" : "남";
			} else {
				employeeOtherGender = "";
			}
			resultMap.put("employeeOtherGender", employeeOtherGender);

			model.addAttribute("resultMap", resultMap);

			return ViewRoutes.직원__정보_수정;
		}
	}

	// 직원 수정 엑션
	@PostMapping("updateOne")
	@ResponseBody
	public ResponseEntity<?> updateEmployeeOne(HttpSession session, @RequestBody EmployeeForm employeeForm,
			Model model) {
		boolean result = employeeService.updateEmployee(employeeForm);
		return result ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
	}

	// PW 수정 Form
	@GetMapping("/pwUpdate")
	public String updateEmployeePw(HttpSession session, Model model) {
		// ID값 표기 위한 세션 세팅
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		if (loginEmployee == null) {
			return ViewRoutes.직원_로그인;
		}

		model.addAttribute("loginEmployee", loginEmployee);
		return ViewRoutes.직원_암호_변경;
	}

	// PW 수정 Act
	@PostMapping("/pwUpdate")
	public String updateEmployeePw(HttpSession session, String employeePw, String employeeNewPw) {
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		loginEmployee.setEmployeePw(employeePw);

		int result = employeeService.updateEmployeePw(loginEmployee, employeeNewPw);
		if (result > 0) { // PW 수정 완 --> 재로그인
			session.invalidate();
			return Redirect(ViewRoutes.직원_로그인);
		} else { // 수정 실패 현재 페이지로 return
			return ViewRoutes.직원_암호_변경;

		}
	}

	// 마이페이지
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		// 로그인 emp 값 받아오기
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");

		Map<String, Object> resultMap = employeeService.getEmployee(loginEmployee);
		model.addAttribute("resultMap", resultMap);

		return ViewRoutes.직원_정보_확인;
	}

	// 직원 조회(목록)
	@GetMapping("/list")
	public String EmployeeList(Model model, Page page) throws JsonProcessingException {
		// 페이징 변수들
		int totalCount = employeeService.getEmployeeTotal(); // 게시글 총 갯수
		page.setTotalCount(totalCount);

		// 서비스 호출
		List<Employee> employeeList = employeeService.list(page);
		model.addAttribute("employeeList", toJson(employeeList));

		// 결과물 디버깅
		log.info(employeeList.toString());

		// 모델객체에 담아서 뷰에 전달
		model.addAttribute("page", page);

		return ViewRoutes.직원_목록;
	}
}