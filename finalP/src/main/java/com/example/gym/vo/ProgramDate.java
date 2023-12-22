package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProgramDate {
	private int programDateNo;
	private int programNo;
	private String programDate;
	private String createdate;
	private String updatedate;
}