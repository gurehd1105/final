package com.example.finalP.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Membership {
	private int membershipNo;
	private int employeeNo;
	private String membershipName;
	private String membershipMonth;
	private String membershipPrice;
	private String createdate;
	private String updatedate;
}