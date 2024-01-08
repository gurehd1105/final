package com.example.gym.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Review;

@Mapper
public interface ReviewMapper {
	int insertReview(Review review);
	
	int deleteReview(Review review);
	
	int updateReview(Review review);
	
	Map<String, Object> selectReviewOne(Review review);
	
	Map<String, Object> selectReviewList();
}
