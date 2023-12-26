package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Employee;
import com.example.gym.vo.EmployeeDetail;

@Mapper
public interface EmployeeMapper {
	// 직원 로그인
	Employee loginEmployee(Employee employee);
	
   // 직원 목록 조회 
   List<Employee> selectEmployeeList(Map<String, Object> paramMap);
   
   // 총 직원수 확인을 위한 카운트
   int EmployeeCount();
   // 직원 추가
   int insertEmployee(Map<String, Object> paramMap);
   
   int insertEmployeeDetail(Map<String, Object> paramMap);
   
   // 직원 비활성화
   int deleteEmployee(Employee employee);
   // 직원 정보 수정
   int updateEmployee(Employee employee);
}
