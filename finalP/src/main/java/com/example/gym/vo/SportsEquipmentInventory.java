package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SportsEquipmentInventory {
	private String branchName;
	private String itemName;
	private int discartdQuantity;
	private int itemPrice;
	private int inventoryQuantity;
	private int totalQuantity;
	private String sportsEquipmentImgFileName;
	private int sportsEquipmentNo;
}
