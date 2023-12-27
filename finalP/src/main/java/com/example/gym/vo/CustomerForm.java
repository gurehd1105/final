package com.example.gym.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CustomerForm {
	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerGender;
	private int customerHeight;
	private int customerWeight;
	private String customerPhone;
	private String customerAddress;
	private String customerEmail;
	
	private MultipartFile customerImg;
}
