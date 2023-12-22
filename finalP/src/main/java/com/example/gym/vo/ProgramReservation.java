package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProgramReservation {
	private int programReservationNo;
	private int paymentNo;
	private int programDateNo;
	private int branchNo;
	private String createdate;
	private String updatedate;
}