package com.example.gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.CustomerService;
import com.example.gym.service.QuestionService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Employee;
import com.example.gym.vo.Page;
import com.example.gym.vo.Question;
import com.example.gym.vo.QuestionReply;
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
	public String questionList(HttpSession session, Page page, Model model) {
		page.setTotalCount(questionService.totalCount());
		page.setRowPerPage(10);	// 페이지당 조회량
		List<Map<String, Object>> list = questionService.selectQuestionList(page);
		log.info((list.size() == 0 || list == null) ? "리스트 결과값 없음" : "출력 성공");
		model.addAttribute("questionList", toJson(list));
		model.addAttribute("page", page);	// 페이징 위해 함께 전달		
		return ViewRoutes.문의사항_목록;
	}
		
	// insertForm
	@GetMapping("/insert")
	public String insertQuestion(HttpSession session, Model model) { // 작성자정보 표기위한 session 세팅
		return ViewRoutes.문의사항_추가;
	}	
	// insertAct
	@PostMapping("/insert")
	public String insertQuestion(Question question) {
		questionService.insertQuestion(question);
		return Redirect(ViewRoutes.문의사항_목록);
	}
	
	// updateForm
	@GetMapping("/update")
	public String updateQuestion(Question question, Model model) {
		Map<String, Object> resultMap = questionService.selectQuestionOne(question);
		
		if(resultMap.get("questionReplyMap") != null) {	// 답변 있을 시 접속불가
			return Redirect(ViewRoutes.문의사항_상세보기 + "?questionNo=" + question.getQuestionNo());
		} else {
			model.addAttribute("questionMap", resultMap.get("questionMap"));
			return ViewRoutes.문의사항_수정;
		}
		
	}	
	// updateAct
	@PostMapping("/update")
	public String updateQuestion(Question question) {
		questionService.updateQuestion(question);
		return Redirect(ViewRoutes.문의사항_상세보기 + "?questionNo=" + question.getQuestionNo());
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
		

		return ViewRoutes.문의사항_상세보기;
	}
	
	
	
// Question	Reply
	
	// insertReply
	@PostMapping("/insertReply")
	public String insertReply(QuestionReply questionReply) {

		questionService.insertQuestionReply(questionReply);
		return Redirect(ViewRoutes.문의사항_상세보기 + "?questionNo=" + questionReply.getQuestionNo());
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
