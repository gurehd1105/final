package com.example.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gym.mapper.PaymentMapper;
import com.example.gym.vo.Payment;

@Service
public class PaymentService {
	@Autowired
	private PaymentMapper paymentMapper;
	
	public int insert(Payment payment) {
		int result = 0;
		result = paymentMapper.insert(payment);
		return result;
	}
	
	public int delete(Payment payment) {
		int result = 0;
		result = paymentMapper.delete(payment);
		return result;
	}
	
	public List<Map<String, Object>> select(Map<String, Object> paramMap) {
		List<Map<String, Object>> paymentList = paymentMapper.select(paramMap);
		return paymentList;
	}
}
