package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Employee;
import com.example.gym.vo.EmployeeDetail;

@Mapper
public interface EmployeeMapper {
	// 직원 로그인
	Employee selectEmployeeByLogin(Employee employee);
	
	// 직원 조회 
	List<Employee> selectEmployeeList();
	
	Employee selectEmployeeOne(int EmployeeNo);
	// 총 직원수 확인을 위한 카운트
	int EmployeeCount();
	// 직원 추가
	int insertEmployee(Employee employee);
   
	int insertEmployeeDetail(EmployeeDetail employeeDetail);
   
	// 직원 비활성화
	int deleteEmployee(int employeeNo);
	// 직원 암호 수정
	int updateEmployee(Employee employee);
}
