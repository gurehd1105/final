package com.example.gym.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
	private int customerNo;
	private String customerId;
	private String customerPw;
	private String customerActive;
	private String createdate;
	private String updatedate;
}