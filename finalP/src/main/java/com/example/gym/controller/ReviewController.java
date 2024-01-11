package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gym.service.ReviewService;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Review;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/list")
	public String reviewList(Model model) {
		Map<String, Object> resultMap = reviewService.selectReviewList();
		model.addAttribute("resultMap", resultMap);
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
	public String insert() { // customer_attendance_no 해결될 때까지 보류
		
	}
	*/
	
	
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model) { // 비동기 고려 중
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		model.addAttribute("loginCustomer", loginCustomer);
		return "review/delete";
	}
	
	@PostMapping("/delete")
	public String delete(Review review) { // 비동기 고려 중
		reviewService.deleteReview(review);
		
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
		model.addAttribute("resultMap", resultMap);
		
		return "review/reviewOne";
	}
	
}
