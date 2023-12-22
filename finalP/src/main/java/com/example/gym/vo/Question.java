package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Question {
	private int questionNo;
	private int customerNo;
	private String questionTitle;
	private String questionContent;
	private String createdate;
	private String updatedate;
}