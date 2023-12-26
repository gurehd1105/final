package com.example.gym.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CustomerImg {
	private int customerImgNo;
	private int customerNo;
	private String customerImgOriginName;
	private String customerImgFileName;
	private long customerImgSize;
	private String customerImgType;
	private String createdate;
	private String updatedate;
}