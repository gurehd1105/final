package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Review;
import com.example.gym.vo.ReviewReply;

@Mapper
public interface ReviewMapper {
	
	//review
	List<Map<String, Object>> selectReviewList(Map<String, Object> paramMap);
	Integer selectCountOfReview(String programName);
	
	int insertReview(Review review);
	
	int deleteReview(Review review);
	
	int updateReview(Review review);
	
	Map<String, Object> selectReviewOne(Review review);
	
	//reply
	Map<String, Object> selectReviewReply(Review review);
	
	int insertReviewReply(ReviewReply reviewReply);
	
	int deleteReviewReply(ReviewReply reviewReply);
	
	int updateReviewReply(ReviewReply reviewReply);
}



