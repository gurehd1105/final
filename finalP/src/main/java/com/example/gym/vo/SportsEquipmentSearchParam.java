package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class SportsEquipmentSearchParam extends SearchParam {
	@Default
	private String active = "";
	@Default
	private String query = "";
	@Default
	private String searchBranch = "";
	@Default
	private String beginDate = "";
	@Default
	private String endDate = "";
	@Default
	private String loginBranchNo = "";
	@Default
	private String loginBranchLevel = "";

}