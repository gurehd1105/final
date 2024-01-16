package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Notice {
	private int noticeNo;
	private int employeeNo;
	private	String employeeName;
	private String noticeTitle;
	private String noticeContent;
	private String createdate;
	private String updatedate;
}