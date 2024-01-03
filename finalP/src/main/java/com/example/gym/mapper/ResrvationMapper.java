package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResrvationMapper {
	// 캘린더 출력
	 List<Map<String,Object>> selectCalendarList(Map<String, Object> paramMap);
}
	