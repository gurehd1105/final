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
	
	public Customer loginCustomer(Customer customer){
		
		customer = customerMapper.loginCustomer(customer);
		
		return customer;
	}
	
	public int insertCustomer(Map<String, Object> paramMap) {
		int result = 0;
		int row0 = customerMapper.insertCustomer(paramMap);
		int row1 = customerMapper.insertCustomerDetail(paramMap);
		if(row0>1 && row1 >1) {
			result = 1;
		}
		return result;
	}
}
