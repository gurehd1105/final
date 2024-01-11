package com.example.gym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.ReviewMapper;
import com.example.gym.vo.Review;
import com.example.gym.vo.ReviewReply;

@Service
@Transactional
public class ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;
	
	
	public Map<String,Object> selectReviewList(Map<String, Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<>();
		List<Map<String,Object>> reviewList = reviewMapper.selectReviewList(paramMap);
		int totalRow = reviewMapper.selectCountOfReview();
		
		resultMap.put("reviewList", reviewList);
		resultMap.put("totalRow", totalRow);		
		return resultMap;
	}
	
	public int insertReview(Review review) {
		int result = reviewMapper.insertReview(review);
		return result;
	}
	
	public int deleteReview(Review review) {
		int result = reviewMapper.deleteReview(review);
		return result;
	}
	
	public int updateReview(Review review) {
		int result = reviewMapper.updateReview(review);
		return result;
	}
	
	public Map<String,Object> selectReviewOne(Review review) {
		Map<String,Object> resultMap = new HashMap<>();
		Map<String,Object> reviewMap = reviewMapper.selectReviewOne(review);
		Map<String,Object> replyMap = reviewMapper.selectReviewReply(review);
		
		resultMap.put("reviewMap", reviewMap);
		resultMap.put("replyMap", replyMap);
		
		return resultMap;
	}
	
	// review 끝 reply 시작 //	


	public int insertReviewReply(ReviewReply reviewReply) {
	int result = reviewMapper.insertReviewReply(reviewReply);
		return result;
	}
	
	public int deleteReviewReply(ReviewReply reviewReply) {
		int result = reviewMapper.deleteReviewReply(reviewReply);
		return result;
	}
	
	public int updateReviewReply(ReviewReply reviewReply) {
		int result = reviewMapper.updateReviewReply(reviewReply);
		return result;
	}
	
}

