package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.NoticeService;
import com.example.gym.vo.Employee;
import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("notice")
public class NoticeController extends DefaultController{
	@Autowired
	NoticeService noticeService;

	// 공지사항 조회(목록)
	@GetMapping("/list")
	public String NoticeList(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum) 
			throws JsonProcessingException {
		// 매개변수 디버깅
		log.info("getNoticeList", "pageNum", pageNum);
		// 페이징 변수들
		int rowPerPage = 5; // 페이지당 보여주는 게시글 수는 5개로 고정 (추후 필요시 수정)
		Page page = new Page();
		page.setRowPerPage(rowPerPage);
		page.setBeginRow(pageNum * page.getRowPerPage());
		int totalCount = noticeService.getNoticeTotal(); // 게시글 총 갯수

		// 서비스 호출
		List<Notice> noticeList = noticeService.getNoticeList(page);
		model.addAttribute("noticeList", mapper.writeValueAsString(noticeList));
		// 결과물 디버깅
		log.debug(noticeList.toString());

		// 다음버튼 플래그 false이면 다음버튼 비활성화
		boolean nextFlag = true;
		if (totalCount <= (page.getBeginRow() + page.getRowPerPage())) { // 총갯수가 적으면 다음버튼 비활성화
			nextFlag = false;
		}

		// 모델객체에 담아서 뷰에 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("nextFlag", nextFlag);
		model.addAttribute("noticeList", noticeList);

		return "notice/list";
	}

	// 공지사항 상세보기
	@GetMapping("/one")
	public String getNoticeOne(Model model, @RequestParam(value = "noticeNo", required = true) int noticeNo,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {
		// 매개변수 디버깅
		log.debug("getNoticeOne", "noticeNo", noticeNo);
		log.debug("getNoticeOne", "pageNum", pageNum);
		// 서비스 호출
		Map<String, Object> noticeOne = noticeService.getNoticeOne(noticeNo);
		// 결과물 디버깅
		log.debug("getNoticeOne", "noticeOne", noticeOne.toString());

		model.addAttribute("noticeOne", noticeOne);
		model.addAttribute("pageNum", pageNum);

		return "notice/one";
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
	@GetMapping("/update")
	public String updateNotice(Model model, @RequestParam(value = "noticeNo", required = true) int noticeNo) {
		// 매개변수 디버깅
		log.debug("updateNotice", "noticeNo", noticeNo);
		// 공지사항 기존 내용 가져오기
		Map<String, Object> noticeOne = noticeService.getNoticeOne(noticeNo);
		// 결과값 디버깅
		log.debug("getNoticeOne", "noticeOne", noticeOne.toString());

		model.addAttribute("noticeOne", noticeOne);

		return "notice/update";
	}

	// 공지사항 수정 액션
	@PostMapping("/update")
	public String updateNotice(Notice notice, HttpSession session) {

		if (session.getAttribute("loginEmployee") == null) {
			return "redirect:/home";
		}
		// 매개변수 디버깅
		log.debug("modifyNotice", "notice", notice.toString());
		// 서비스 호출
		int updateRow = noticeService.updateNotice(notice);
		// 수정 확인 디버깅
		log.debug("updateNotice", "updateRow", updateRow);

		return "redirect:/getNoticeOne?noticeId=" + notice.getNoticeNo();
	}

	// 공지사항 삭제 액션
	@GetMapping("/delete")
	public String deleteNotice(@RequestParam(value = "noticeNo", required = true) int noticeNo, HttpSession session) {
		if (session.getAttribute("loginEmployee") == null) {
			return "redirect:/home";
		}
		// 매개변수 디버깅
		log.debug("deleteNotice", "noticeNo", noticeNo);
		// 서비스 호출
		int deleteRow = noticeService.deleteNotice(noticeNo);
		// 삭제 확인 디버깅
		log.debug("deleteNotice", "deleteRow", deleteRow);
		return "notice/list";
	}
}