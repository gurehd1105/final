package com.example.finalP.vo;

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
	private String noticeTitle;
	private String noticeContent;
	private String createdate;
	private String updatedate;
}