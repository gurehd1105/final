package com.example.gym.service;

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
		log.debug(employee.toString() + "직원 로그인 Service");
		return employeeMapper.loginEmployee(employee);
	}

	// 직원 추가
	public int insertEmployee(EmployeeForm ef, String path) {
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
	public void updateEmployeeOne(String path, EmployeeForm ef, int employeeNo) {
		// employeeDetail 수정
		EmployeeDetail ed = new EmployeeDetail();
		ed.setEmployeeNo(employeeNo);
		ed.setEmployeeName(ef.getEmployeeName());
		ed.setEmployeeGender(ef.getEmployeeGender());
		ed.setEmployeePhone(ef.getEmployeePhone());
		ed.setEmployeeEmail(ef.getEmployeeEmail());
		int row = employeeMapper.updateEmployeeOne(ed);

		if (row != 1) {
			throw new RuntimeException();
		}

		MultipartFile mf = ef.getEmployeeImg();

		if (mf.getSize() != 0) { // 사용자가 지정한 Image 정보가 있다면
			EmployeeImg employeeImg = new EmployeeImg();
			employeeImg.setEmployeeNo(employeeNo);
			employeeImg.setEmployeeImgOriginName(mf.getOriginalFilename());
			employeeImg.setEmployeeImgSize(mf.getSize());
			employeeImg.setEmployeeImgType(mf.getContentType());

			String fileName = UUID.randomUUID().toString();

			String originName = mf.getOriginalFilename();
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

			/*
			 * // path 저장 -- 경로 확인 후 설정 예정 System.out.println(path +"/"+ fileName +
			 * fileName2); File file = new File(path +"/"+ fileName + fileName2); try {
			 * mf.transferTo(file); } catch (IllegalStateException | IOException e) { throw
			 * new RuntimeException(); }
			 */

		} else { // 고객이 Image 정보를 지정하지 않았다면 --> 이미지정보 삭제
			Employee deleteImg = new Employee();
			deleteImg.setEmployeeNo(employeeNo);
			employeeMapper.deleteEmployeeImg(deleteImg);
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

	// 직원 상세목록
	public Map<String, Object> employeeOne(Employee employee) {
		Map<String, Object> resultMap = employeeMapper.employeeOne(employee);
		return resultMap;
	}

	// 직원 목록
	public List<Employee> getEmployeeList() {
		return employeeMapper.selectEmployeeList();
	}
}
