package com.example.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.EmployeeMapper;
import com.example.gym.vo.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EmployeeService {
	@Autowired private EmployeeMapper employeeMapper;
	
	// 직원 목록
	public List<Employee> getEmployeeList(){
		return employeeMapper.selectEmployeeList();
	}
	
	// 직원 상세목록
	public Employee getEmployeeOne(int employeeNo) {
		return employeeMapper.selectEmployeeOne(employeeNo);
	}
	
	// 직원 로그인
	public Employee getLoginEmployee(Employee employee) {
		log.debug(employee.toString()+"직원 로그인 Service");
		return employeeMapper.selectEmployeeByLogin(employee);
	}
	
	
	// 직원 입력
	public int insetEmployee(Employee employee) {
		int insertRow = employeeMapper.insertEmployee(employee);
		log.debug("insetEmployee", "Service insertrow",insertRow);
		return insertRow;
	}
	
	// Image 등록
	public int insertEmployeeImg(Map<String, Object> paramMap) {
		// 타 employee 관련 완료 후 fileUpload폼 생성 및 구현 예정
		
		return 0;		
	}
	// 직원 삭제
	public int deleteEmployee(int employeeNo) {
		int deleteRow = employeeMapper.deleteEmployee(employeeNo);
		log.debug("deleteEmployee", "Service deleteRow",deleteRow);
		return deleteRow;
	}
	
	// 직원 수정
	public int updateEmployee(Employee employee) {
		int updateRow = employeeMapper.updateEmployee(employee);
		log.debug("updateEmployee", "Service updateRow",updateRow);
		return updateRow;
	}
	
}
