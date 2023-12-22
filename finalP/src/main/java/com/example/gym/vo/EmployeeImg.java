package com.example.gym.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeImg {
	private int employeeImgNo;
	private int employeeNo;
	private String employeeImgOriginName;
	private String employeeImgFileName;
	private String employeeImgSize;
	private String employeeImgType;
	private String createdate;
	private String updatedate;
}