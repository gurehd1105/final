package com.example.gym.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.CustomerMapper;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;

@Service
@Transactional
public class CustomerService {
	@Autowired private CustomerMapper customerMapper;
	
	// 로그인
	public Customer loginCustomer(Customer paramCustomer){
		
		Customer loginCustomer = customerMapper.loginCustomer(paramCustomer);
		
		return loginCustomer;
	}
	
	// 회원가입
	public int insertCustomer(Customer paramCustomer, CustomerDetail paramCustomerDetail) {
		int result = 0;
		int row = customerMapper.insertCustomer(paramCustomer);
		int row2 = customerMapper.insertCustomerDetail(paramCustomerDetail);
		
		if(row >0 && row2>0) {
			result = 1;
		}
		return result;
	}
	
	// Image 등록
	public void insertCustomerImg() {
		// 타 customer 관련 완료 후 fileUpload폼 생성 및 구현 예정
			
	}
	
	// 탈퇴
	public int deleteCustomer(Customer paramCustomer) {
		int result = 0;
		int row = customerMapper.updateCustomerActive(paramCustomer);
		int row2 = customerMapper.deleteCustomerImg(paramCustomer);
		int row3 = customerMapper.deleteCustomerDetail(paramCustomer);
		if(row>0 && row2>0 && row3>0) {
			result = 1;
		}
		return result;
	}
	
	// 상세보기
	public Map<String, Object> customerOne(Customer paramCustomer) {
		Map<String, Object> resultMap = customerMapper.customerOne(paramCustomer);
		return resultMap;
	}
	
	public int updateCustomerOne(CustomerDetail customerDetail) {
		int result = customerMapper.updateCustomerOne(customerDetail);
		return result;
	}
}
