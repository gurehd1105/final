package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Payment;

@Mapper
public interface PaymentMapper {

	int insert(Payment payment);
	
	int delete(Payment payment);
	
	List<Map<String, Object>> select(Map<String, Object> paramMap);
	
	int countOfPayment();
}
