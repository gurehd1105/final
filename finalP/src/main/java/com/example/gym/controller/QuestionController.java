package com.example.gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.CustomerService;
import com.example.gym.service.QuestionService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Employee;
import com.example.gym.vo.Question;
import com.example.gym.vo.QuestionReply;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("question")
public class QuestionController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CustomerService customerService;
// Question
	
	// delete
	@PostMapping("/delete")
	public String deleteQuestion(Customer customer, Question question) {
		Customer checkCustomer = customerService.loginCustomer(customer);
		if (checkCustomer != null) {	// 입력한 계정PW 일치
			QuestionReply questionReply = new QuestionReply();
			questionReply.setQuestionNo(question.getQuestionNo());
			questionService.deleteQuestionReply(questionReply);
			questionService.deleteQuestion(question);
		} else {			
			log.info(customer.getCustomerId() + " / " + customer.getCustomerPw() + " --Pw 불일치");
		}
		return "redirect:list";
	}
	
	// insertForm
	@GetMapping("/insert")
	public String insertQuestion(HttpSession session, Model model) { // 작성자정보 표기위한 session 세팅
		// id 유효성검사
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		if (loginCustomer == null) {
			return "customer/login";
		}
		model.addAttribute("loginCustomer", loginCustomer);
		return "question/insert";
	}
	
	// insertAct
	@PostMapping("/insert")
	public String insertQuestion(Question question) {
		questionService.insertQuestion(question);
		return "redirect:list";
	}
	
	// selectQuestionList
	@GetMapping("/list")
	public String questionList(HttpSession session, @RequestParam(defaultValue = "1") int currentPage, Model model) throws JsonProcessingException {
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
		Object questionList = resultMap.get("questionList");
		model.addAttribute("questionList", mapper.writeValueAsString(questionList)); // questionList 출력 완

		
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

		return "question/list";
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
	@GetMapping("/update")
	public String updateQuestion(Question question, Model model) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		
		if(resultMap.get("questionReplyMap") != null) {	// 답변 있을 시 접속불가
			return "redirect:/question/questionOne?questionNo=" + question.getQuestionNo();
		}else {
			model.addAttribute("questionMap", resultMap.get("questionMap"));
			return "question/update";
		}
		
	}
	
	// updateAct
	@PostMapping("/update")
	public String updateQuestion(Question question) {
		questionService.updateQuestion(question);
		return "redirect:/question/questionOne?questionNo=" + question.getQuestionNo();
	}	
	
	
	
// Question	Reply
	
	// insertReply
	@PostMapping("/insertReply")
	public String insertReply(QuestionReply questionReply) {

		questionService.insertQuestionReply(questionReply);
		return "redirect:/question/questionOne?questionNo=" + questionReply.getQuestionNo();
	}
	
	// updateReply
	@GetMapping("/updateReply")
	public String updateReply(Question question, Model model) {
		
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		model.addAttribute("replyMap", resultMap.get("questionReplyMap"));
		return "question/updateReply";
	}
	
	@PostMapping("/updateReply")
	public String updateReply(QuestionReply questionReply) {
		System.out.println(questionReply);
		
		questionService.updateQuestionReply(questionReply);
		return "redirect:/question/questionOne?questionNo=" + questionReply.getQuestionNo();
	}

	// deleteReply
	@GetMapping("/deleteReply")
	public String deleteReply(QuestionReply questionReply) {

		questionService.deleteQuestionReply(questionReply);
		return "redirect:/question/questionOne?questionNo=" + questionReply.getQuestionNo();
	}
}
