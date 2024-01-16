package com.example.gym.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam {
	private int pageNum = 1;
	private int rowPerPage = Integer.MAX_VALUE;
	private int totalCount;
	
	private List<SearchWhere> where;
	private List<SearchOrder> order;
	
	private int beginRow;
	private int totalPage;
	
	public int getBeginRow() {
		return (pageNum - 1) * rowPerPage;
	}
	
	public int getTotalPage() {
		return (int)Math.ceil(1.0 * totalCount / rowPerPage);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class SearchWhere {
	@NonNull
	private String key;
	
	@NonNull
	private String operator;
	
	@NonNull
	private Object value;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class SearchOrder {
	@NonNull
	private String key;
	@NonNull
	private String value;
}
