package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.NoticeService;
import com.example.gym.vo.Employee;
import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("notice")
public class NoticeController extends DefaultController{
	@Autowired
	NoticeService noticeService;

	// 공지사항 조회(목록)
	@GetMapping("/list")
	public String NoticeList(Model model, Page page) 
			throws JsonProcessingException {
		// 페이징 변수들
		int totalCount = noticeService.getNoticeTotal(); // 게시글 총 갯수
		page.setTotalCount(totalCount);

		// 서비스 호출
		List<Notice> noticeList = noticeService.getNoticeList(page);
		model.addAttribute("noticeList", mapper.writeValueAsString(noticeList));
		
		// 결과물 디버깅
		log.info(noticeList.toString());

		// 모델객체에 담아서 뷰에 전달
		model.addAttribute("page", page);
		
		return "notice/list";
	}

	// 공지사항 상세보기
	@GetMapping("/read/{noticeNo}")
	public String getNoticeOne(Model model, @PathVariable int noticeNo) {
		// 매개변수 디버깅
		log.info("getNoticeOne", "noticeNo", noticeNo);
		// 서비스 호출
		Map<String, Object> noticeOne = noticeService.getNoticeOne(noticeNo);
		// 결과물 디버깅
		log.info("getNoticeOne", "noticeOne", noticeOne.toString());

		model.addAttribute("noticeOne", noticeOne);

		return "notice/noticeOne";
	}

	// 공지사항 추가 폼
	@GetMapping("/insert")
	public String insertNotice() {
		return "notice/insert";
	}

	// 공지사항 추가 액션
	@PostMapping("/insert")
	public String insertNotice(Notice notice, HttpSession session) {
	    Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");

	    int employeeNo = loginEmployee.getEmployeeNo();

	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("noticeTitle", notice.getNoticeTitle());
	    paramMap.put("noticeContent", notice.getNoticeContent());
	    paramMap.put("employeeNo", employeeNo);

	    int insertRow = noticeService.insertNotice(paramMap);
	    if(insertRow == 1) {
	    return "redirect:/notice/list";
	    } else {
	    	return "notice/insert";
	    }
	}

	// 공지사항 수정 폼
	@GetMapping("/update/{noticeNo}")
	public String updateNotice(Model model, @PathVariable int noticeNo) {
		// 매개변수 디버깅
		log.debug("updateNotice", "noticeNo", noticeNo);
		// 공지사항 기존 내용 가져오기
		Map<String, Object> notice = noticeService.getNoticeOne(noticeNo);

		model.addAttribute("notice", notice);

		return "notice/update";
	}

	// 공지사항 수정 액션
	@PostMapping("/update")
	public String updateNotice(Notice notice, HttpSession session) {

		// 매개변수 디버깅
		log.info(notice.toString());
		// 서비스 호출
		int updateRow = noticeService.updateNotice(notice);
		
		return "redirect:/notice/list";
	}

	// 공지사항 삭제 액션
	@GetMapping("/delete/{noticeNo}")
	public String deleteNotice(@PathVariable int noticeNo, HttpSession session) {
		if (session.getAttribute("loginEmployee") == null) {
			return "redirect:/home";
		}
		// 매개변수 디버깅
		log.debug("deleteNotice", "noticeNo", noticeNo);
		// 서비스 호출
		int deleteRow = noticeService.deleteNotice(noticeNo);
		// 삭제 확인 디버깅
		log.debug("deleteNotice", "deleteRow", deleteRow);
		return "redirect:/notice/list";
	}
}