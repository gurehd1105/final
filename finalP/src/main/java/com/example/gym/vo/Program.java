package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Program {
	private int programNo;
	private int employeeNo;
	private String programName;
	private String programContent;
	private int programMaxCustomer;
	private String active;
	private String createdate;
	private String updatedate;
}