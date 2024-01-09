package com.example.gym.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.ReviewMapper;
import com.example.gym.vo.Review;

@Service
@Transactional
public class ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;
	
	public Map<String,Object> selectReviewList() {
		Map<String,Object> resultMap = reviewMapper.selectReviewList();
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
		Map<String,Object> resultMap = reviewMapper.selectReviewOne(review);
		return resultMap;
	}
	
}

