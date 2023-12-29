package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Page;
import com.example.gym.vo.Question;

@Mapper
public interface QuestionMapper {
	int insertQuestion(Question question);
	
	List<Question> selectQuestionList(Map<String, Integer> map);
	int selectCountOfQuestion();
	
	Map<String, Object> selectQuestionOne(Question question);
	
	int updateQuestion(Question question);
	
	int deleteQuestion(Question question);
}
