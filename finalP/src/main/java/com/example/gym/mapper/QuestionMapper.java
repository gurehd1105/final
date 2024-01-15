package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Page;
import com.example.gym.vo.Question;
import com.example.gym.vo.QuestionReply;

@Mapper
public interface QuestionMapper {
	// question 시작
	int insertQuestion(Question question);
	
	List<Map<String, Object>> selectQuestionList(Page page);
	Integer selectCountOfQuestion();
	
	Map<String, Object> selectQuestionOne(Question question);
	
	int updateQuestion(Question question);
	
	int deleteQuestion(Question question);
	// question 종료
	
	
	
	// question_reply 시작
	int insertQuestionReply(QuestionReply questionReply);
	
	Map<String, Object> selectQuestionReply(Question question);
	
	int updateQuestionReply(QuestionReply questionReply);
	
	int deleteQuestionReply(QuestionReply questionReply);
	// question_reply 종료
}
