package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {
	private int branchNo;
	private String branchName;
	private int employeeNo;
	private String employeeId;
	private String employeePw;
	private String employeeName;
	private String employeeGender;
	private String employeePhone;
	private String employeeEmail;
	private String employeeImg;
}