package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.mapper.AttendanceMapper;
import com.example.gym.mapper.ProgramMapper;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ReviewService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Page;
import com.example.gym.vo.Review;
import com.example.gym.vo.ReviewReply;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("review")
public class ReviewController extends DefaultController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProgramMapper programMapper;
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	@GetMapping("/list")
	public String reviewList(Model model,Page page, HttpSession session,
								@RequestParam(defaultValue = "") String programName) {		
		Page page2 = new Page();
		page2.setRowPerPage(Integer.MAX_VALUE);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchWord", "");
		paramMap.put("rowPerPage", page2.getRowPerPage());
		paramMap.put("beginRow", page2.getBeginRow());
		paramMap.put("programActive", "");
		log.info(paramMap.toString());
		List<Map<String, Object>> programList = programMapper.selectProgramList(paramMap);
		log.info(programList.toString());
		model.addAttribute("programList", toJson(programList));		// 검색기능 위해 전체기능 필요 -> 페이징 변수 삽입 전 도출	
		
		paramMap.clear();			// 맵 초기화
		
		page.setRowPerPage(10);	
		
		paramMap.put("programName", programName);
		paramMap.put("rowPerPage", page.getRowPerPage());
		paramMap.put("beginRow", page.getBeginRow());
		
		List<Map<String, Object>> reviewList = reviewService.selectReviewList(paramMap);	// 페이징 변수 삽입 후 도출
		
		model.addAttribute("reviewList", toJson(reviewList));
		
		log.info((reviewList.size() == 0 || reviewList == null) ? "리스트 결과값 없음" : "출력 성공");
		
		// model.addAttribute("programList", toJson(programList));
		page.setTotalCount(reviewService.totalCount(programName));
		model.addAttribute("page", page);
		
		model.addAttribute("programName", programName);
		
		// 리스트 -> 리뷰작성 이동 시 attendance 정보 확인위한 전달
		if(session.getAttribute("loginCustomer") != null) {
		Map<String, Object> loginCustomer = (Map)session.getAttribute("loginCustomer");
		List<Map<String, Object>> attendanceList = attendanceMapper.selectAttendance((int)loginCustomer.get("customerNo"));
		boolean checkAttendance = attendanceList.size() > 0;
		model.addAttribute("checkAttendance", checkAttendance);
		}
		return ViewRoutes.후기_목록;
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session, Model model) {
		Map<String, Object> loginCustomer = (Map)session.getAttribute("loginCustomer");
		List<Map<String, Object>> attendanceList = attendanceMapper.selectAttendance((int)loginCustomer.get("customerNo"));
		model.addAttribute("attendanceList", toJson(attendanceList));
		log.info(attendanceList.toString());
		return ViewRoutes.후기_추가;
	}
	
	
	@PostMapping("insert")
	public String insert(Review review) {
		reviewService.insertReview(review);		
		return Redirect(ViewRoutes.후기_목록);
	}
	
	
	@GetMapping("/update")
	public String update(Review review, Model model) { 
		Map<String, Object> resultMap = reviewService.selectReviewOne(review);
		model.addAttribute("reviewMap", resultMap.get("reviewMap"));
		return ViewRoutes.후기_수정;
	}
	@PostMapping("/update")
	public String update(Review review) { 
		reviewService.updateReview(review);		
		return Redirect(ViewRoutes.후기_상세보기) +"?reviewNo=" + review.getReviewNo();
	}	
	
	@PostMapping("/delete")
	@ResponseBody
	public int delete(@RequestBody Map<String, Object> paramMap, HttpSession session) { // customer, review정보 axios 방식으로 둘 다 받을 수 없어 Map 사용
		
		int result = 0;
		System.out.println(paramMap);
		if(session.getAttribute("loginCustomer")!=null && session.getAttribute("loginEmployee")==null) {	// 고객 본인 리뷰 삭제
		boolean checked = false;
		Customer checkCustomer = new Customer();
		checkCustomer.setCustomerId((String) paramMap.get("customerId"));
		checkCustomer.setCustomerPw((String) paramMap.get("customerPw"));
		
		checked = customerService.loginCustomer(checkCustomer) != null;
		if(checked) {											// 입력한 계정PW 일치 -> 해당 리뷰에 작성된 리플부터 삭제 -> 리뷰 삭제
			ReviewReply reviewReply = new ReviewReply();
			reviewReply.setReviewNo(Integer.parseInt((String)paramMap.get("reviewNo")));
			reviewService.deleteReviewReply(reviewReply);
			
			Review review = new Review();
			review.setReviewNo(Integer.parseInt((String)paramMap.get("reviewNo")));
			result = reviewService.deleteReview(review);
			
			
		} 
		}	else if(session.getAttribute("loginCustomer")==null && session.getAttribute("loginEmployee")!=null) {	// 관리자 즉시삭제
			ReviewReply reviewReply = new ReviewReply();
			reviewReply.setReviewNo((int)paramMap.get("reviewNo"));
			reviewService.deleteReviewReply(reviewReply);
			
			Review review = new Review();
			review.setReviewNo((int)paramMap.get("reviewNo"));
			result = reviewService.deleteReview(review);			
		
	}	
		log.info("result : " + result);
		return result;
	}	
		
	@GetMapping("/reviewOne")
	public String reviewOne(Review review, Model model) { 
		Map<String, Object> resultMap = reviewService.selectReviewOne(review);
		model.addAttribute("reviewMap", resultMap.get("reviewMap"));
		model.addAttribute("replyMap", resultMap.get("replyMap"));
		
		return ViewRoutes.후기_상세보기;
	}
	
// review reply

	// insertReply
	@PostMapping("/insertReply")
	public String insertReply(ReviewReply reply) {		
		
		reviewService.insertReviewReply(reply);
		return Redirect(ViewRoutes.후기_상세보기) +"?reviewNo=" + reply.getReviewNo(); 
	}
	
	// updateReply
	@PostMapping("/deleteReply")
	@ResponseBody
	public int deleteReply(@RequestBody ReviewReply reply) {
		int result = reviewService.deleteReviewReply(reply);			
		return result;
	}
	
	// deleteReply
	@PostMapping("/updateReply")
	@ResponseBody
	public int updateReply(@RequestBody ReviewReply reply) {
		int result = reviewService.updateReviewReply(reply);
		
		return result;
	}
}
