package com.example.gym.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.mapper.CustomerMapper;
import com.example.gym.vo.Customer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class CustomerRestController {
	@Autowired private CustomerMapper customerMapper;
	
	@GetMapping("/idCheck")
	public int idCheck(String customerId) {
		int result = 0;
		Customer idCustomer = new Customer();
		idCustomer.setCustomerId(customerId);		
		if(customerMapper.checkId(idCustomer)!=null) {	// id 중복
			log.info(customerId + " / 중복 ID");
			result = 1;
		}
		
		return result;
	}
}
