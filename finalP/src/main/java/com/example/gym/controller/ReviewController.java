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
import com.example.gym.service.ReviewService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Review;
import com.example.gym.vo.ReviewReply;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("review")
public class ReviewController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String reviewList(Model model, @RequestParam(defaultValue = "1") int currentPage, 
											@RequestParam(defaultValue = "") String branchName) throws JsonProcessingException {		
		
		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("branchName", branchName);
		
		Map<String, Object> resultMap = reviewService.selectReviewList(paramMap);
		Object reviewList = resultMap.get("reviewList");
		model.addAttribute("reviewList", mapper.writeValueAsString(reviewList));
		
		
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
		return "review/list";
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session, Model model) {
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		model.addAttribute("loginCustomer", loginCustomer);
		return "review/insert";
	}
	
	/*
	@PostMapping("insert")
	public String insert() { // customer_attendance_no 생성까지 보류
		
		return "redirect:list";
	}
	*/
	
	
	@PostMapping("/delete")
	public String delete(Review review, Customer customer) { 
		Customer checkCustomer = customerService.loginCustomer(customer);
		if(checkCustomer != null) {	// 입력한 계정PW 일치
			ReviewReply reviewReply = new ReviewReply();
			reviewReply.setReviewNo(review.getReviewNo());
			reviewService.deleteReviewReply(reviewReply);
			reviewService.deleteReview(review);
		} else {
			log.info(customer.getCustomerId() + " / " + customer.getCustomerPw() + " --Pw 불일치");
		}		
		return "redirect:review/list";
	}
	
	
	@GetMapping("/update")
	public String update(Review review, Model model) { 
		Map<String, Object> resultMap = reviewService.selectReviewOne(review);
		model.addAttribute("resultMap", resultMap);
		
		return "review/update";
	}
	
	
	@PostMapping("/update")
	public String update(Review review) { 
		reviewService.updateReview(review);		
		return "redirect:review/reviewOne?" + review.getReviewNo();
	}
		
	@GetMapping("/reviewOne")
	public String reviewOne(Review review, Model model) { 
		Map<String, Object> resultMap = reviewService.selectReviewOne(review);
		model.addAttribute("reviewMap", resultMap.get("reviewMap"));
		model.addAttribute("replyMap", resultMap.get("replyMap"));
		
		return "review/reviewOne";
	}
	
	
}
