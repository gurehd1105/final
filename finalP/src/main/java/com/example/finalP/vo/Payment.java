package com.example.finalP.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
	private int paymentNo;
	private int customerNo;
	private int paymentMembershipNo;
	private String paymentDate;
	private int paymentPrice;
	private String createdate;
	private String updatedate;
}