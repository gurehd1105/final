package com.example.gym.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.EmployeeMapper;
import com.example.gym.vo.Employee;

@Service
@Transactional
public class EmployeeService {
	@Autowired private EmployeeMapper employeeMapper;
	
	// Image 등록
		public int insertEmployeeImg(Map<String, Object> paramMap) {
			// 타 employee 관련 완료 후 fileUpload폼 생성 및 구현 예정
			
			return 0;		
		}
	
	// 로그인
		public Employee loginEmployee(Employee employee){
			
			employee = employeeMapper.loginEmployee(employee);
			
			return employee;
		}
	
	// 회원가입
	public int insertEmployee(Map<String, Object> paramMap) {
		int result = 0;
		int row0 = employeeMapper.insertEmployee(paramMap);
		int row1 = employeeMapper.insertEmployeeDetail(paramMap);
		if(row0>1 && row1 >1) {
			result = 1;
		}
		return result;
	}	
}
