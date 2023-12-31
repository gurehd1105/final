package com.example.gym.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.mapper.CustomerMapper;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;
import com.example.gym.vo.CustomerForm;
import com.example.gym.vo.CustomerImg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	// 로그인
	public Customer loginCustomer(Customer paramCustomer) {
		Customer loginCustomer = customerMapper.loginCustomer(paramCustomer);
		return loginCustomer;
	}
	
	// 회원가입
	public int insertCustomer(CustomerForm cf, String path) {
		int result = 0; // 최종 반환값 세팅
		int row = 0;
		int row2 = 0;
		int row3 = 0;

		// customer 매개값 세팅
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(cf.getCustomerId());
		paramCustomer.setCustomerPw(cf.getCustomerPw());

		row = customerMapper.insertCustomer(paramCustomer);

		if (row != 1) {
			throw new RuntimeException();
		}

		CustomerDetail paramDetail = new CustomerDetail();
		paramDetail.setCustomerNo(paramCustomer.getCustomerNo());
		paramDetail.setCustomerName(cf.getCustomerName());
		paramDetail.setCustomerGender(cf.getCustomerGender());
		paramDetail.setCustomerHeight(cf.getCustomerHeight());
		paramDetail.setCustomerWeight(cf.getCustomerWeight());
		paramDetail.setCustomerPhone(cf.getCustomerPhone());
		paramDetail.setCustomerAddress(cf.getCustomerAddress());
		paramDetail.setCustomerEmail(cf.getCustomerEmail());
		row2 = customerMapper.insertCustomerDetail(paramDetail);

		if (row2 != 1) {
			throw new RuntimeException();
		}

		MultipartFile mf = cf.getCustomerImg();
		System.out.println(mf.getSize() + " <-- mf.getSize(), 0일 시 Image 미선택");
		if (mf.getSize() != 0) { // 회원가입 시 선택된 사진이 있다면
			CustomerImg cImg = new CustomerImg();
			// fileName 이외 모든 값 세팅
			cImg.setCustomerNo(paramDetail.getCustomerNo());
			cImg.setCustomerImgOriginName(mf.getOriginalFilename());
			cImg.setCustomerImgSize(mf.getSize());
			cImg.setCustomerImgType(mf.getContentType());

			// fileName 값 세팅
			String fileName = UUID.randomUUID().toString();

			String oName = mf.getOriginalFilename();

			String fileName2 = oName.substring(oName.lastIndexOf("."));

			cImg.setCustomerImgFileName(fileName + fileName2);

			// 변수값 세팅 완 + 삽입
			row3 = customerMapper.insertCustomerImg(cImg);

			if (row3 != 1) {
				throw new RuntimeException();
			}
			
		// path 저장
		File file = new File(path +"/"+ fileName + fileName2);
		try {
			mf.transferTo(file);			
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException();
		} 
	}		
			
	if (row > 0 && row2 > 0) { // Image 정보 없어도 가입가능
		result = 1;
	}

	return result;
}

	
	
	
	// 탈퇴
	public int deleteCustomer(Customer paramCustomer) {
		int result = 0;
		int row = 0;
		int row2 = 0;
		int row3 = 0;
		Customer check = customerMapper.loginCustomer(paramCustomer);

		if (check != null) {
			log.info("PW 정상확인");

			row = customerMapper.updateCustomerActive(paramCustomer);
			System.out.println(row + " <-- row");

			row2 = customerMapper.deleteCustomerDetail(paramCustomer);
			System.out.println(row2 + " <-- row2");

			row3 = customerMapper.deleteCustomerImg(paramCustomer);
			System.out.println(row3 + " <-- row3");
		}

		if (row > 0 && row2 > 0) {
			log.info("정상 탈퇴");
			result = 1;
		}
		return result;
	}

	// 상세보기
	public Map<String, Object> customerOne(Customer paramCustomer) {
		Map<String, Object> resultMap = customerMapper.customerOne(paramCustomer);
		return resultMap;
	}
	
	// 정보수정
	// 마이페이지 수정 + Image 수정
	public void updateCustomerOne(String path, CustomerForm paramCustomerForm, int customerNo) {
		// customerDetail 수정
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setCustomerNo(customerNo);
		customerDetail.setCustomerName(paramCustomerForm.getCustomerName());
		customerDetail.setCustomerGender(paramCustomerForm.getCustomerGender());
		customerDetail.setCustomerHeight(paramCustomerForm.getCustomerHeight());
		customerDetail.setCustomerWeight(paramCustomerForm.getCustomerWeight());
		customerDetail.setCustomerPhone(paramCustomerForm.getCustomerPhone());
		customerDetail.setCustomerAddress(paramCustomerForm.getCustomerAddress());
		customerDetail.setCustomerEmail(paramCustomerForm.getCustomerEmail());
		int row = customerMapper.updateCustomerOne(customerDetail);

		if (row != 1) {
			throw new RuntimeException();
		}

		MultipartFile mf = paramCustomerForm.getCustomerImg();

		if (mf.getSize() != 0) { // 사용자가 지정한 Image 정보가 있다면
			CustomerImg customerImg = new CustomerImg();
			customerImg.setCustomerNo(customerNo);
			customerImg.setCustomerImgOriginName(mf.getOriginalFilename());
			customerImg.setCustomerImgSize(mf.getSize());
			customerImg.setCustomerImgType(mf.getContentType());

			String fileName = UUID.randomUUID().toString();

			String oName = mf.getOriginalFilename();
			String fileName2 = oName.substring(oName.lastIndexOf("."));
			customerImg.setCustomerImgFileName(fileName + fileName2); // CustomerImg 매개값 세팅

			// 변수 삽입 전 customerImg정보가 있는지 확인
			Customer checkImgCustomer = new Customer();
			checkImgCustomer.setCustomerNo(customerNo);
			CustomerImg check = customerMapper.checkCustomerImg(checkImgCustomer);

			// 확인 후 조건에 따른 분기
			int row2 = 0;
			if (check == null) { // 이전 Image 정보가 아예 없음 --> 가입 시 미등록이라면 또는 등록 이후 삭제 했다면
				row2 = customerMapper.insertCustomerImg(customerImg);
			} else { // 원래 등록된 Image 정보가 있다면
				row2 = customerMapper.updateCustomerImg(customerImg);
			}

			if (row2 != 1) {
				throw new RuntimeException();
			}

		 		// path 저장
				File file = new File(path +"/"+ fileName + fileName2);
				try {
					mf.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					throw new RuntimeException();
				} 

		} 
	}
	 
	
	// 비밀번호 수정
	public int updateCustomerPw(Customer checkCustomer, String customerNewPw) {
		int result = 0;
		Customer check = customerMapper.loginCustomer(checkCustomer);
		if (check != null) {
			Customer updatePw = new Customer();
			updatePw.setCustomerNo(check.getCustomerNo());
			updatePw.setCustomerPw(customerNewPw);
			result = customerMapper.updateCustomerPw(updatePw);
		}
		return result;
	}
	
	// 전체 회원정보 조회 - 관리자용
	public Map<String, Object> selectAllCustomer() {

		Map<String, Object> resultMap = customerMapper.selectAllCustomer();

		return resultMap;
	}
}
