package com.example.finalP.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SportsEquipmentImg {
	private int sportsEquipmentImgNo;
	private int sportsEquipmentNo;
	private String sportsEquipmentImgOriginName;
	private String sportsEquipmentImgFileName;
	private int sportsEquipmentImgSize;
	private String sportsEquipmentImgType;
	private String createdate;
	private String updatedate;
}