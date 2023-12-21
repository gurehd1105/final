package com.example.finalP.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDetail {
	private int customerNo;
	private String customerName;
	private String customerGender;
	private int customerHeight;
	private int customerWeight;
	private String customerPhone;
	private String customerAddress;
	private String customerEmail;
	private String createdate;
	private String updatedate;
}