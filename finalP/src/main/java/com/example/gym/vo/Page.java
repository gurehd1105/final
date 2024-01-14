package com.example.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page {
	@Default
	private int pageNum = 1;
	
	@Default
	private int rowPerPage = 3;
	private int totalCount;
	
	private int beginRow;
	private int totalPage;
	
	public int getBeginRow() {
		return (pageNum - 1) * rowPerPage;
	}
	
	public int getTotalPage() {
		return (int)Math.ceil(1.0 * totalCount / rowPerPage);
	}
}
