package com.example.gym.vo;


import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CustomerAttendance {
	private int customerAttendanceNo;
	private int programReservationNo;
	private String customerAttendanceEnterTime;
}