package com.example.gym.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.QuestionMapper;
import com.example.gym.vo.Question;

@Service
@Transactional
public class QuestionService {
	@Autowired private QuestionMapper questionMapper;
	
	// question 등록 (insert)
	public int insertQuestion(Question paramQuestion) {
		int result = 0;
		result = questionMapper.insertQuestion(paramQuestion);
		return result;
	}
	// questionList 조회
	/*
	 
	 
	 
	*/
	
	
	
	// questionOne 조회
	public Map<String, Object> selectQuestionOne(Question paramQuestion) {
		Map<String, Object> resultMap = questionMapper.selectQuestionOne(paramQuestion);
		return resultMap;
	}
	
	// question 삭제 (delete)
	public int deleteQuestion(Question paramQuestion) {
		int result = 0;
		result = questionMapper.deleteQuestion(paramQuestion);
		return result;
	}
	
	
	
}
