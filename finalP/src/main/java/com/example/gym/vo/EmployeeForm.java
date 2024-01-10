package com.example.gym.vo;

import org.springframework.web.multipart.MultipartFile;

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
	private String employeeId;
	private String employeePw;
	private String employeeName;
	private String employeeGender;
	private String employeePhone;
	private String employeeEmail;
	private String employeeImg;
}