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
	@Autowired
	private QuestionService questionService;

// Question
	
	// insertForm
	@GetMapping("/insertQuestion")
	public String insertQuestion(HttpSession session, Model model) { // 작성자정보 표기위한 session 세팅
		// id 유효성검사
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		if (loginCustomer == null) {
			return "customer/loginCustomer";
		}
		model.addAttribute("loginCustomer", loginCustomer);
		return "question/insertQuestion";
	}
	
	// insertAct
	@PostMapping("/insertQuestion")
	public String insertQuestion(Question question) {
		questionService.insertQuestion(question);
		return "redirect:/questionList";
	}
	
	// selectQuestionList
	@GetMapping("/questionList")
	public String questionList(HttpSession session, @RequestParam(defaultValue = "1") int currentPage, Model model) {
		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");

		if (loginEmployee != null) {
			model.addAttribute("loginEmployee", loginEmployee);
		}

		if (loginCustomer != null) {
			model.addAttribute("loginCustomer", loginCustomer);
		}

		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);

		Map<String, Object> resultMap = questionService.selectQuestionList(paramMap);
		model.addAttribute("questionList", resultMap.get("questionList")); // questionList 출력 완

		// 페이징
		int totalRow = (int) resultMap.get("totalRow");
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
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
		model.addAttribute("questionMap", resultMap.get("questionMap"));
		model.addAttribute("replyMap", resultMap.get("questionReplyMap"));

		// id 유효성검사
		Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		if (loginEmployee != null) {
			model.addAttribute("loginEmployee", loginEmployee);
		}

		if (loginCustomer != null) {
			model.addAttribute("loginCustomer", loginCustomer);
		}

		return "question/questionOne";
	}
	
	// updateForm
	@GetMapping("/updateQuestion")
	public String updateQuestion(Question question, Model model) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		
		if(resultMap.get("questionReplyMap") != null) {	// 답변 있을 시 접속불가
			return "redirect:/questionOne?questionNo=" + question.getQuestionNo();
		}
		model.addAttribute("questionMap", resultMap.get("questionMap"));
		return "question/updateQuestion";
	}
	
	// updateAct
	@PostMapping("/updateQuestion")
	public String updateQuestion(Question question) {
		
		questionService.updateQuestion(question);
		return "redirect:/questionOne?questionNo=" + question.getQuestionNo();
	}	
	
// Question	Reply
	
	// insertReply
	@PostMapping("/insertQuestionReply")
	public String insertQuestionReply(QuestionReply questionReply) {

		questionService.insertQuestionReply(questionReply);
		return "redirect:/questionOne?questionNo=" + questionReply.getQuestionNo();
	}
	

	// deleteReply
	@GetMapping("/deleteQuestionReply")
	public String deleteQuestionReply(QuestionReply questionReply) {

		questionService.deleteQuestionReply(questionReply);
		return "redirect:/questionOne?questionNo=" + questionReply.getQuestionNo();
	}
}
