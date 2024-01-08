package com.example.gym.mapper;

import java.util.List;
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

	// 이미지 파일
	int insertEmployeeImg(EmployeeImg employeeIng);

	// 직원 비활성화
	int updateEmployeeActive(Employee employee);

	int deleteEmployeeImg(Employee employee);

	int deleteEmployeeDetail(Employee employee);

	// 상세보기 = 마이페이지
	Map<String, Object> employeeOne(Employee employee);

	// 정보수정
	// 상세정보 변경
	int updateEmployeeOne(EmployeeDetail employeeDetail);

	int updateEmployeeImg(EmployeeImg employeeImg);

	// Img 변경위한 정보조회
	EmployeeImg checkEmployeeImg(Employee employee);

	// 비밀번호 변경
	int updateEmployeePw(Employee employee);

	// 직원 조회
	List<Employee> selectEmployeeList();

	Employee selectEmployeeOne(int EmployeeNo);

	// 총 직원수 확인을 위한 카운트
	int EmployeeCount();

}
