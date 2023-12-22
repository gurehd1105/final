package com.example.gym.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Customer;

@Mapper
public interface CustomerMapper {
	// select
	Customer loginCustomer(Customer customer);
	
	// insert (가입)
	int insertCustomer(Map<String, Object> paramMap);
	int insertCustomerDetail(Map<String, Object> paramMap);
	// 선택정보 (Image)
	int insertCustomerImg(Map<String, Object> paramMap);
	
	// 탈퇴 update(Active) + delete(Image, Detail)
	int updateCustomerActive(Customer customer);
	int deleteCustomerImg(Customer customer);
	int deleteCustomerDetail(Customer customer);
	
}
