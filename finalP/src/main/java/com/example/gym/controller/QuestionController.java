package com.example.gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class QuestionController extends DefaultController{
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CustomerService customerService;
	
	// 리스트
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
		model.addAttribute("questionList", toJson(questionList));

		
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
	
	// delete
	@PostMapping("/delete")
	@ResponseBody
	public int deleteQuestion(@RequestBody Map<String, Object> paramMap) { // questionNo , customerId, customerPw 정보
																			// 필요하나 axios방식으로 두 vo값 받을 수 없어 Map형식 사용
		boolean checked = false;
		// 확인 후 customer 매개값 세팅
		Customer checkCustomer = new Customer();
		checkCustomer.setCustomerId((String) paramMap.get("customerId"));
		checkCustomer.setCustomerPw((String) paramMap.get("customerPw"));
		
		checked = customerService.loginCustomer(checkCustomer) != null;
		log.info("PW 일치여부 /" + checked);		 // 로그인 구조 이용해 입력한 작성자PW 일치여부 확인
		
		int result = 0;
		if (checked) { // 입력한 계정PW 일치
			QuestionReply questionReply = new QuestionReply();
			questionReply.setQuestionNo(Integer.parseInt((String)paramMap.get("questionNo")));
			questionService.deleteQuestionReply(questionReply); // 해당 글에 작성된 답변글 일괄 삭제

			Question question = new Question();
			question.setQuestionNo(Integer.parseInt((String)paramMap.get("questionNo")));
			result = questionService.deleteQuestion(question); // 최종 문의글 삭제
		}
		return result;
	}		
	
	// select - questionOne
	@GetMapping("/questionOne")
	public String selectQuestionOne(Question question, Model model, HttpSession session) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		model.addAttribute("questionMap", resultMap.get("questionMap"));
		model.addAttribute("replyMap", resultMap.get("questionReplyMap"));
		

		return "question/questionOne";
	}
	
	
	
// Question	Reply
	
	// insertReply
	@PostMapping("/insertReply")
	public String insertReply(QuestionReply questionReply) {

		questionService.insertQuestionReply(questionReply);
		return "redirect:/question/questionOne?questionNo=" + questionReply.getQuestionNo();
	}	
	
	// updateReply
	@PostMapping("/updateReply")
	@ResponseBody
	public int updateReply(@RequestBody QuestionReply questionReply) {
		int result = questionService.updateQuestionReply(questionReply);
		return result;
	}

	// deleteReply
	@PostMapping("/deleteReply")
	@ResponseBody
	public int deleteReply(@RequestBody QuestionReply questionReply) {		
		int result = questionService.deleteQuestionReply(questionReply);
		
		return result;
	}
}
