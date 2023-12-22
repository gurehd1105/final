package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Review {
	private int reviewNo;
	private int customerAttendanceNo;
	private String reviewTitle;
	private String reviewContent;
	private String createdate;
	private String updatedate;
}