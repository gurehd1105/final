package com.example.finalP.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CustomerImg {
	public int customerImgNo;
	public int customerNo;
	public String customerImgOriginName;
	public String customerImgFileName;
	public String customerImgSize;
	public String customerImgType;
	public String createdate;
	public String updatedate;
}