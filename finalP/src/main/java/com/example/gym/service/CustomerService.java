package com.example.gym.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.CustomerMapper;
import com.example.gym.vo.Customer;

@Service
@Transactional
public class CustomerService {
	@Autowired private CustomerMapper customerMapper;
	
	// Image 등록
		public int insertCustomerImg(Map<String, Object> paramMap) {
			// 타 customer 관련 완료 후 fileUpload폼 생성 및 구현 예정
			
			return 0;		
		}
	
	// 로그인
	public Customer loginCustomer(Customer customer){
		
		customer = customerMapper.loginCustomer(customer);
		
		return customer;
	}
	
	// 회원가입
	public int insertCustomer(Map<String, Object> paramMap) {
		int result = 0;
		int row0 = customerMapper.insertCustomer(paramMap);
		int row1 = customerMapper.insertCustomerDetail(paramMap);
		if(row0>1 && row1 >1) {
			result = 1;
		}
		return result;
	}
	
	// 탈퇴
	public int deleteCustomer(Customer customer) {
		int result = 0;
		int row0 = customerMapper.updateCustomerActive(customer);
		int row1 = customerMapper.deleteCustomerImg(customer);
		int row2 = customerMapper.deleteCustomerDetail(customer);
		if(row0>0 && row1>0 && row2>0) {
			result = 1;
		}
		return result;
	}
}
