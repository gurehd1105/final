package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gym.service.QuestionService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Question;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuestionController {
	@Autowired private QuestionService questionService;
	
	// insertForm
	@GetMapping("/insertQuestion")
	public String insertQuestion(HttpSession session, Model model) { // 작성자정보 표기위한 session 세팅
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		model.addAttribute("loginCustomer", loginCustomer);
		return "question/insertQuestion";
	}
	
	// insertAct
	@PostMapping("/insertQuestion")
	public String insertQuestion(Question question) {
		questionService.insertQuestion(question);
		return "question/questionList";
	}
	
	
	/*
	 	// selectQuestionList - Page.java 사용여부 고려 후 추후 진행
	 	 
	 
	*/
	
	// select - questionOne
	@GetMapping("/questionOne")
	public String selectQuestionOne(Question question, Model model) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		model.addAttribute("resultMap", resultMap);
		return "question/questionOne";
	}
	
	// delete -- jsp 내부 c:if 이용 / 본인 작성글 (or 관리자) 경우만 삭제버튼 조회 --> Act 미구분
	@GetMapping("/deleteQuestion")
	public String deleteQuestion(Question question) {
		questionService.deleteQuestion(question);
		return "question/questionList";
	}
	
	
	
	
	
	
	
}
