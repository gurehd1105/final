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
	 public List<Question> selectQuestionList() {
	 
	 	return null;
	 }
	 
	 
	*/
	
	
	
	// questionOne 조회
	public Map<String, Object> selectQuestionOne(Question paramQuestion) {
		Map<String, Object> resultMap = questionMapper.selectQuestionOne(paramQuestion);
		return resultMap;
	}
	
	// question 수정 (update)
	public int updateQuestion(Question paramQuestion) {	// question_reply 없을 시에만 수정가능 -> 추후 코드변경 예정
		int result = 0;
		
		result = questionMapper.updateQuestion(paramQuestion);
		return result;
	}
	
	// question 삭제 (delete)
	public int deleteQuestion(Question paramQuestion) {
		int result = 0;
		result = questionMapper.deleteQuestion(paramQuestion);
		return result;
	}
	
	
	
}
