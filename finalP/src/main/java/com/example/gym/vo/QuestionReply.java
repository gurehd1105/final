package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class QuestionReply {
	private int questionReplyNo;
	private int questionNo;
	private int employeeNo;
	private String questionReplyContent;
	private String createdate;
	private String updatedate;
}