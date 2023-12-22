package com.example.gym.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
	private int employeeNo;
	private int branchNo;
	private String employeeId;
	private String employeePw;
	private String employeeActive;
	private String createdate;
	private String updatedate;
}