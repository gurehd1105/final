package com.example.gym.service;

import java.util.List;
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
	public Employee getLoginEmployee(Employee employee) {
		log.debug(employee.toString() + "직원 로그인 Service");
		return employeeMapper.selectEmployeeByLogin(employee);
	}

	// 직원 추가
	public int insetEmployee(EmployeeForm ef, String path) {
		int result = 0; // 최종 반환값 세팅
		int row = 0;
		int row2 = 0;
		int row3 = 0;

		// Employee 매개값 세팅
		Employee paramEmployee = new Employee();
		paramEmployee.setEmployeeId(ef.getEmployeeId());
		paramEmployee.setEmployeePw(ef.getEmployeePw());

		row = employeeMapper.insertEmployee(paramEmployee);

		if (row != 1) {
			throw new RuntimeException();
		}
		// EmployeeDetail 매개값 세팅
		EmployeeDetail paramDetail = new EmployeeDetail();
		paramDetail.setEmployeeNo(paramEmployee.getEmployeeNo());
		paramDetail.setEmployeeName(ef.getEmployeeName());
		paramDetail.setEmployeePhone(ef.getEmployeePhone());
		paramDetail.setEmployeeEmail(ef.getEmployeeEmail());
		paramDetail.setEmployeeGender(ef.getEmployeeGender());
		row2 = employeeMapper.insertEmployeeDetail(paramDetail);

		if (row2 != 1) {
			throw new RuntimeException();
		}

		MultipartFile mf = ef.getEmployeeImg();
		System.out.println(mf.getSize() + " <-- mf.getSize(), 0일 시 Image 미선택");
		if (mf.getSize() != 0) { // 회원가입 시 선택된 사진이 있다면
			EmployeeImg eImg = new EmployeeImg();
			// fileName 이외 모든 값 세팅
			eImg.setEmployeeNo(paramDetail.getEmployeeNo());
			eImg.setEmployeeImgOriginName(mf.getOriginalFilename());
			eImg.setEmployeeImgSize(mf.getSize());
			eImg.setEmployeeImgType(mf.getContentType());

			// fileName 값 세팅
			String fileName = UUID.randomUUID().toString();

			String originName = mf.getOriginalFilename();

			String fileName2 = originName.substring(originName.lastIndexOf("."));

			eImg.setEmployeeImgFileName(fileName + fileName2);

			// 변수값 세팅 완 + 삽입
			row3 = employeeMapper.insertEmployeeImg(eImg);

			if (row3 != 1) {
				throw new RuntimeException();
			}

			/*
			 * // path 저장 -- 경로 확인 후 설정 예정 System.out.println(path +"/"+ fileName +
			 * fileName2); File file = new File(path +"/"+ fileName + fileName2); try {
			 * mf.transferTo(file); } catch (IllegalStateException | IOException e) { throw
			 * new RuntimeException(); }
			 */
		}

		if (row > 0 && row2 > 0) { // Image 정보 없어도 가입가능
			result = 1;
		}

		return result;
	}
	// 직원 추가 프로세스 종료

	// 직원 삭제
	public int deleteEmployee(int employeeNo) {
		int deleteRow = employeeMapper.deleteEmployee(employeeNo);
		log.debug("deleteEmployee", "Service deleteRow", deleteRow);
		return deleteRow;
	}

	// 직원 목록
	public List<Employee> getEmployeeList() {
		return employeeMapper.selectEmployeeList();
	}

	// 직원 상세목록
	public Employee getEmployeeOne(int employeeNo) {
		return employeeMapper.selectEmployeeOne(employeeNo);
	}

	// 직원 수정
	public int updateEmployee(Employee employee) {
		int updateRow = employeeMapper.updateEmployee(employee);
		log.debug("updateEmployee", "Service updateRow", updateRow);
		return updateRow;
	}

}
