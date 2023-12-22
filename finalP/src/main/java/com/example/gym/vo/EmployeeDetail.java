package com.example.gym.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDetail {
	private int employeeNo;
	private String employeeName;
	private String employeePhone;
	private String employeeEmail;
	private String employeeGender;
	private String createdate;
	private String updatedate;
}