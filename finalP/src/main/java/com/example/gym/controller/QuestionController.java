package com.example.gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.QuestionService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Employee;
import com.example.gym.vo.Question;
import com.example.gym.vo.QuestionReply;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuestionController {
	@Autowired private QuestionService questionService;
	
// Question
	
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
		return "redirect:question/questionList";
	}
	
	// selectQuestionList
	@GetMapping("/questionList")
	public String questionList(@RequestParam(defaultValue = "1") int currentPage, Model model) {
		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		
		
		Map<String, Object> resultMap = questionService.selectQuestionList(paramMap);
		model.addAttribute("questionList", resultMap.get("questionList"));		// questionList 출력 완
		
		
		// 페이징 
		int totalRow = (int)resultMap.get("totalRow");
		int lastPage = totalRow / rowPerPage;
		if(totalRow % rowPerPage != 0) {
			lastPage += 1;
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rowPerPage", rowPerPage);
		
		return "question/questionList";
	}		
	
	// select - questionOne
	@GetMapping("/questionOne")
	public String selectQuestionOne(Question question, Model model, HttpSession session) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		model.addAttribute("resultMap", resultMap);
		
		if(session.getAttribute("loginEmployee") != null) {
			Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
			model.addAttribute("loginEmployee", loginEmployee);
		}
		return "question/questionOne";
	}
	
	// updateForm
	@GetMapping("/updateQuestion")
	public String updateQuestion(Question question, Model model) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		model.addAttribute("resultMap", resultMap);
		return "question/updateQuestion";
	}
	/*
	// updateAct
	@PostMapping("/updateQuestion")
	public String updateQuestion(Question question) {
		questionService.updateQuestion(question);
		return "redirect:question/questionOne";
	}
	*/
	
	// delete -- jsp 내부 c:if 이용 / 본인 작성글 (or 관리자) 경우만 삭제버튼 조회 --> Act 불필요
	// 추후 customer PW 등 확인여부 필요 시 변경 예정
	@GetMapping("/deleteQuestion")
	public String deleteQuestion(Question question) {
		questionService.deleteQuestion(question);
		return "question/questionList";
	}
	
	
// Question	Reply
	
	// insertReply
	@PostMapping("/insertQuestionReply")
	public String insertQuestionReply(QuestionReply questionReply) {
		questionService.insertQuestionReply(questionReply);
		return "redirect:question/questionOne";
	}
	
	// updateReply
	/*
		Form 배치문제로 추후 작성
	
	
	*/
	
	// deleteReply
	@GetMapping("/deleteQuestionReply")
	public String deleteQuestionReply(QuestionReply questionReply) {
		questionService.deleteQuestionReply(questionReply);
		return "redirect:question/questionOne";
	}
}
