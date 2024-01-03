package com.example.gym.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.mapper.CustomerMapper;
import com.example.gym.vo.Customer;

@RestController
public class CustomerRestController {
	@Autowired private CustomerMapper customerMapper;
	
	@GetMapping("/idCheck")
	public int idCheck(String customerId) {
		int result = 0;
		Customer idCustomer = new Customer();
		idCustomer.setCustomerId(customerId);
		System.out.println(customerId);
		if(customerMapper.checkId(idCustomer)!=null) {	// id 중복			
			result = 1;
		}
		
		return result;
	}
}
