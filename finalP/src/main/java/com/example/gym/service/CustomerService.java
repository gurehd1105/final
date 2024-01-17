package com.example.gym.service;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	public Map<String, Object> loginCustomer(Customer paramCustomer) {
		Map<String, Object> loginCustomer = customerMapper.loginCustomer(paramCustomer);
		return loginCustomer;
	}
	// ID 중복확인 / 회원가입
	public Customer checkId(Customer customer) {
		Customer check = customerMapper.checkId(customer);
		
		return check;
	}
	
	// 회원가입
	public int insertCustomer(CustomerForm cf) {
		int result = 0; // 최종 반환값 세팅
		boolean insertCustomer = false;
		boolean insertCustomerDetail = false;
		boolean insertCustomerImg = false;

		// customer 매개값 세팅
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(cf.getCustomerId());
		paramCustomer.setCustomerPw(cf.getCustomerPw());
		insertCustomer = customerMapper.insertCustomer(paramCustomer) == 1;

		CustomerDetail paramDetail = new CustomerDetail();
		paramDetail.setCustomerNo(paramCustomer.getCustomerNo());
		paramDetail.setCustomerName(cf.getCustomerName());
		paramDetail.setCustomerGender(cf.getCustomerGender());
		paramDetail.setCustomerHeight(cf.getCustomerHeight());
		paramDetail.setCustomerWeight(cf.getCustomerWeight());
		paramDetail.setCustomerPhone(cf.getCustomerPhone());
		paramDetail.setCustomerAddress(cf.getCustomerAddress());
		paramDetail.setCustomerEmail(cf.getCustomerEmail());
		insertCustomerDetail = customerMapper.insertCustomerDetail(paramDetail) == 1;

		CustomerImg paramCustomerImg = new CustomerImg();
		paramCustomerImg.setCustomerNo(paramCustomer.getCustomerNo());
		paramCustomerImg.setCustomerImgOriginName(cf.getCustomerImg());
		insertCustomerImg = customerMapper.insertCustomerImg(paramCustomerImg) == 1;

	if (insertCustomer && insertCustomerDetail) { // Image 정보 없어도 가입가능
		result = 1;
	}
	return result;
}	
	
	// 탈퇴
	public int deleteCustomer(Customer paramCustomer) {
		int result = 0;
		boolean updateActive = false;
		boolean deleteDetail = false;
		boolean deleteImg = false;

			updateActive = customerMapper.updateCustomerActive(paramCustomer) == 1;

			deleteDetail = customerMapper.deleteCustomerDetail(paramCustomer) == 1;

			deleteImg = customerMapper.deleteCustomerImg(paramCustomer) == 1;


		if (updateActive && deleteDetail && deleteImg) {
			result = 1;
		}
		return result;
	}

	// 상세보기
	public Map<String, Object> customerOne(int customerNo) {
		Map<String, Object> resultMap = customerMapper.customerOne(customerNo);
		return resultMap;
	}
	
	// 정보수정
	// 마이페이지 수정 + Image 수정
	public int updateCustomerOne(CustomerForm paramCustomerForm, int customerNo) {
		int result = 0;
		boolean updateCustomerDetail = false;
		boolean updateCustomerImg = false;
		
		
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
		updateCustomerDetail = customerMapper.updateCustomerOne(customerDetail) == 1;
		
		if(!updateCustomerDetail) {	// 탈퇴 -> 원복 고객 있을 시 디테일정보 없을 수 있음
			updateCustomerDetail = customerMapper.insertCustomerDetail(customerDetail) == 1;
		}
		
		CustomerImg customerImg = new CustomerImg();
		customerImg.setCustomerNo(customerNo);
		customerImg.setCustomerImgOriginName(paramCustomerForm.getCustomerImg());
		
		
		updateCustomerImg = customerMapper.updateCustomerImg(customerImg) == 1;
		
		if(!updateCustomerImg) {
			updateCustomerImg = customerMapper.insertCustomerImg(customerImg) == 1;
		}
		
		if (updateCustomerDetail && updateCustomerImg) {
			result = 1;
		} 

			return result;
	}
	 
	
	// 비밀번호 수정
	public int updateCustomerPw(Customer checkCustomer) {
		int result = 0;
		result = customerMapper.updateCustomerPw(checkCustomer);		
		return result;
	}
	
	// 전체 회원정보 조회 - 관리자용
	public Map<String, Object> selectAllCustomer() {

		Map<String, Object> resultMap = customerMapper.selectAllCustomer();

		return resultMap;
	}
}
