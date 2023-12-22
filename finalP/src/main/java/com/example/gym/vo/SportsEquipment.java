package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SportsEquipment {
	private int sportsEquipmentNo;
	private String itemName;
	private String createdate;
	private String updatedate;
	private int itemPrice;
}