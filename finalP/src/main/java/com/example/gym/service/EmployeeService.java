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
		int result = 0;
		int row = 0;
		int row2 = 0;
		int row3 = 0;

		Employee check = employeeMapper.loginEmployee(employee);
		if (check != null) {
			log.info("PW 확인");
			row = employeeMapper.updateEmployeeActive(employee);
			row2 = employeeMapper.deleteEmployeeDetail(employee);
			row3 = employeeMapper.deleteEmployeeImg(employee);

		}

		if (row > 0 && row2 > 0) {
			log.info("직원 비활성화 완료");
			result = 1;
		}
		return result;
	}

	// 직원 정보 수정
	// detail , img 수정
	public void updateEmployeeOne(String path, EmployeeForm employeeForm, int employeeNo) {
		// employeeDetail 수정
		EmployeeDetail employeeDetail = new EmployeeDetail();
		employeeDetail.setEmployeeNo(employeeNo);
		employeeDetail.setEmployeeName(employeeForm.getEmployeeName());
		employeeDetail.setEmployeeGender(employeeForm.getEmployeeGender());
		employeeDetail.setEmployeePhone(employeeForm.getEmployeePhone());
		employeeDetail.setEmployeeEmail(employeeForm.getEmployeeEmail());
		int row = employeeMapper.updateEmployeeOne(employeeDetail);

		if (row != 1) {
			throw new RuntimeException();
		}

		MultipartFile multipartFile = employeeForm.getEmployeeImg();

		if (multipartFile.getSize() != 0) { // 사용자가 지정한 Image 정보가 있다면
			EmployeeImg employeeImg = new EmployeeImg();
			employeeImg.setEmployeeNo(employeeNo);
			employeeImg.setEmployeeImgOriginName(multipartFile.getOriginalFilename());
			employeeImg.setEmployeeImgSize(multipartFile.getSize());
			employeeImg.setEmployeeImgType(multipartFile.getContentType());
			String fileName = UUID.randomUUID().toString();

			String originName = multipartFile.getOriginalFilename();
			String fileName2 = originName.substring(originName.lastIndexOf("."));
			employeeImg.setEmployeeImgFileName(fileName + fileName2); // EmployeeImg 매개값 세팅

			// 변수 삽입 전 employeeImg정보가 있는지 확인
			Employee checkImgEmployee = new Employee();
			checkImgEmployee.setEmployeeNo(employeeNo);
			EmployeeImg check = employeeMapper.checkEmployeeImg(checkImgEmployee);

			// 확인 후 조건에 따른 분기
			int row2 = 0;
			if (check == null) { // 이전 Image 정보가 아예 없음 --> 가입 시 미등록이라면 또는 등록 이후 삭제 했다면
				row2 = employeeMapper.insertEmployeeImg(employeeImg);
			} else { // 원래 등록된 Image 정보가 있다면
				row2 = employeeMapper.updateEmployeeImg(employeeImg);
			}

			if (row2 != 1) {
				throw new RuntimeException();
			}

			// path 저장
			File file = new File(path +"/"+ fileName + fileName2);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException();
			} 

	} 
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

	// 직원 목록
	public Map<String, Object> selectAllEmployee() {
		Map<String, Object> resultMap =employeeMapper.selectAllEmployee();
		return resultMap;
	}

	// 직원 상세목록
	public Map<String, Object> employeeOne(Employee employee) {
		Map<String, Object> resultMap = employeeMapper.employeeOne(employee);
		return resultMap;
	}

}
