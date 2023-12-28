package com.example.gym.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Question;

@Mapper
public interface QuestionMapper {
	int insertQuestion(Question question);
	Question selectQuestionList();
	Map<String, Object> selectQuestionOne(Question question);
	int deleteQuestion(Question question);
}
