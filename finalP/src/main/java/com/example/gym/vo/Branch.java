package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Branch {
	private int branchNo;
	private String branchName;
	private String branchTel;
	private String branchAddress;
	private String createdate;
	private String updatedate;
	private String branchLevel;
	private String branchActive;
}