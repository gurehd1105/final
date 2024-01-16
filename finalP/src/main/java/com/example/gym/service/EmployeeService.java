package com.example.gym.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.mapper.EmployeeMapper;
import com.example.gym.vo.Employee;
import com.example.gym.vo.EmployeeDetail;
import com.example.gym.vo.EmployeeForm;
import com.example.gym.vo.EmployeeImg;
import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;

	// 직원 로그인
	public Employee loginEmployee(Employee employee) {
		Employee loginEmployee = employeeMapper.loginEmployee(employee);
		return loginEmployee;
	}

	// 직원 추가
	public int insertEmployee(EmployeeForm employeeForm) {
		int result = 0; // 최종 반환값 세팅
		boolean employeeSuccess = false;
		boolean employeeDetailSuccess = false;
		boolean employeeImgSuccess = false;

		// Employee 매개값 세팅
		Employee employee = new Employee();
		employee.setBranchNo(employeeForm.getBranchNo());
		employee.setEmployeeId(employeeForm.getEmployeeId());
		employee.setEmployeePw(employeeForm.getEmployeePw());

		employeeSuccess = employeeMapper.insertEmployee(employee) == 1;
		
		// EmployeeDetail 매개값 세팅
		EmployeeDetail employeeDetail = new EmployeeDetail();
		employeeDetail.setEmployeeNo(employee.getEmployeeNo());
		employeeDetail.setEmployeeName(employeeForm.getEmployeeName());
		employeeDetail.setEmployeePhone(employeeForm.getEmployeePhone());
		employeeDetail.setEmployeeEmail(employeeForm.getEmployeeEmail());
		employeeDetail.setEmployeeGender(employeeForm.getEmployeeGender());

		employeeDetailSuccess = employeeMapper.insertEmployeeDetail(employeeDetail) == 1;

		EmployeeImg employeeImg = new EmployeeImg();
		// fileName 이외 모든 값 세팅
		employeeImg.setEmployeeNo(employeeDetail.getEmployeeNo());
		employeeImg.setEmployeeImgOriginName(employeeForm.getEmployeeImg());
		
		employeeImgSuccess = employeeMapper.insertEmployeeImg(employeeImg) == 1;

		if (employeeSuccess && employeeDetailSuccess && employeeImgSuccess) { // Image 정보 없어도 가입가능
			result = 1;
		}

		return result;
	}
	// 직원 추가 프로세스 종료

	// 직원 퇴사 프로세스 시작
	public int deleteEmployee(Employee employee) {
		int result = 0; // 최종 반환값 세팅
		boolean employeeSuccess = false;
		boolean employeeDetailSuccess = false;
		boolean employeeImgSuccess = false;

		Employee check = employeeMapper.loginEmployee(employee);
		if (check != null) {
			log.info("PW 확인");
			employeeSuccess = employeeMapper.updateEmployeeActive(employee) == 1;
			employeeDetailSuccess = employeeMapper.deleteEmployeeDetail(employee) == 1;
			employeeImgSuccess = employeeMapper.deleteEmployeeImg(employee) == 1;

		}
		
		if (employeeSuccess && employeeDetailSuccess && employeeImgSuccess) { 
			result = 1;
		}
		return result;
	}

	// 직원 정보 수정
	// detail , img 수정
	public boolean updateEmployee(EmployeeForm form) {
		boolean employeeDetailSuccess = false;
		boolean employeeImgSuccess = false;
		
		// employeeDetail 수정
		EmployeeDetail employeeDetail = new EmployeeDetail();
		employeeDetail.setEmployeeNo(form.getEmployeeNo());
		employeeDetail.setEmployeeName(form.getEmployeeName());
		employeeDetail.setEmployeeGender(form.getEmployeeGender());
		employeeDetail.setEmployeePhone(form.getEmployeePhone());
		employeeDetail.setEmployeeEmail(form.getEmployeeEmail());
		employeeDetailSuccess = employeeMapper.updateEmployeeOne(employeeDetail) ==1;

		EmployeeImg employeeImg = new EmployeeImg();
		// fileName 이외 모든 값 세팅
		employeeImg.setEmployeeNo(form.getEmployeeNo());
		employeeImg.setEmployeeImgOriginName(form.getEmployeeImg());
		
		employeeImgSuccess = employeeMapper.updateEmployeeImg(employeeImg) == 1;
		
		return employeeDetailSuccess && employeeImgSuccess;
	}

	// 비밀번호 수정
	public int updateEmployeePw(Employee checkEmployee, String employeeNewPw) {
		int result = 0;
		Employee check = employeeMapper.loginEmployee(checkEmployee);
		if (check != null) {
			Employee updatePw = new Employee();
			updatePw.setEmployeeNo(check.getEmployeeNo());
			updatePw.setEmployeePw(employeeNewPw);
			result = employeeMapper.updateEmployeePw(updatePw);
		}
		return result;
	}

	// 직원 조회 (목록)
		public List<Employee> list(Page page) {
			return employeeMapper.list(page);
		}
	// 전체 직원수 체크	
		public int getEmployeeTotal() {
			return employeeMapper.selectEmployeeTotal();
		}

	// 직원 상세목록
	public Map<String, Object> getEmployee(Employee employee) {
		Map<String, Object> resultMap = employeeMapper.getEmployee(employee);
		return resultMap;
	}

}
