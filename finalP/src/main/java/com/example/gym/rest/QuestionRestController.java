package com.example.gym.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.service.CustomerService;
import com.example.gym.service.QuestionService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Question;
import com.example.gym.vo.QuestionReply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QuestionRestController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CustomerService customerService;

// 회원 - question 관련
	@PostMapping("/deleteQuestion")
	public int deleteQuestion(Customer customer, Question question) {
		int result = 0;
		Customer checkCustomer = customerService.loginCustomer(customer);
		if (checkCustomer != null) {
			result = questionService.deleteQuestion(question);
		} else {
			
			log.info(customer.getCustomerId() + " / " + customer.getCustomerPw() + " --Pw 불일치");
		}
		return result;
	}

// 관리자 - reply 관련	
	@PostMapping("/updateQuestionReply")
	public int updateQuestionReply(String questionReplyContent, String questionReplyNo0, String employeeNo0) {
		int result = 0;
		int questionReplyNo = Integer.parseInt(questionReplyNo0);
		int employeeNo = Integer.parseInt(questionReplyNo0);

		QuestionReply questionReply = new QuestionReply();

		questionReply.setQuestionReplyNo(questionReplyNo);
		questionReply.setEmployeeNo(employeeNo);
		questionReply.setQuestionReplyContent(questionReplyContent);

		result = questionService.updateQuestionReply(questionReply);
		if (result == 0) {
			log.info("수정 실패 / restController error");
		}
		return result;
	}
}
