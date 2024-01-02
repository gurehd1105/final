package com.example.gym.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.mapper.QuestionMapper;
import com.example.gym.vo.Question;

@RestController
public class QuestionRestController {
	@Autowired private QuestionMapper questionMapper;
	
	@GetMapping("/replyCheck")
	public int checkReply(String intQuestionNo) {
		int result = 0;
		int questionNo = Integer.parseInt(intQuestionNo);
		Question question = new Question();
		question.setQuestionNo(questionNo);
		Map<String, Object> replyMap = questionMapper.selectQuestionReply(question);
		if(replyMap != null) {
			result = 1;
		}
		return result;
	}
}
