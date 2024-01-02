package com.example.gym.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.service.QuestionService;
import com.example.gym.vo.QuestionReply;

@RestController
public class QuestionRestController {
	private QuestionService questionService;
	
	@PostMapping("/updateQuestionReply")
	public int updateQuestionReply(String questionReplyContent, String questionReplyNo, String employeeNo) {
		int result = 0;
		System.out.println(questionReplyContent);
		System.out.println(questionReplyNo);
		System.out.println(employeeNo);
		//result = questionService.updateQuestionReply(questionReply);
		return result;
		
	}
}
