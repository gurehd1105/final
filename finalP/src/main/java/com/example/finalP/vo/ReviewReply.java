package com.example.finalP.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReviewReply {
	private int reviewReplyNo;
	private int reviewNo;
	private int employeeNo;
	private String reviewReplyContent;
	private String createdate;
	private String updatedate;
}