package com.example.gym.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Employee;
import com.example.gym.vo.EmployeeDetail;
import com.example.gym.vo.EmployeeImg;

@Mapper
public interface EmployeeMapper {
	// 직원 로그인
	Employee loginEmployee(Employee employee);
	
	
	// 직원 추가
	Employee checkId(Employee employee);
	int insertEmployee(Employee employee);
	int insertEmployeeDetail(EmployeeDetail employeeDetail);
	int insertEmployeeImg(EmployeeImg employeeIng);

	// 직원 퇴사시 정보 삭제 및 계정 비활성화 프로세스
	int updateEmployeeActive(Employee employee);
	int deleteEmployeeImg(Employee employee);
	int deleteEmployeeDetail(Employee employee);

	// 상세보기 = 마이페이지
	Map<String, Object> getEmployee(Employee employee);

	// 정보수정
	int updateEmployeePw(Employee employee);
	int updateEmployeeOne(EmployeeDetail employeeDetail);
	int updateEmployeeImg(EmployeeImg employeeImg);
	// Img 변경위한 정보조회
	EmployeeImg checkEmployeeImg(Employee employee);


	// 전체 직원 조회
	Map<String, Object> selectAllEmployee();

}
