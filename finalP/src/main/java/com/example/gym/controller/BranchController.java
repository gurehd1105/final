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

import com.example.gym.service.BranchService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("branch")
public class BranchController extends DefaultController {
	@Autowired
	private BranchService branchService;

	// 지점 목록 조회.
	@GetMapping("/list")
	public String branchList(Model model, Page page) throws JsonProcessingException {

		// 페이징 변수들
		int totalCount = branchService.getBranchTotal(); // 게시글 총 갯수
		page.setTotalCount(totalCount);
		// 서비스 호출
		List<Branch> branchList = branchService.getBranchList(page);
		model.addAttribute("branchList", toJson(branchList));

		// 결과물 디버깅
		log.info(branchList.toString());

		// 모델객체에 담아서 뷰에 전달
		model.addAttribute("page", page);

		return ViewRoutes.지점_목록;
	}

	@GetMapping("insert")
	public String insert() {
		return ViewRoutes.지점_추가;
	}

	@PostMapping("insert")
	public String insert(Branch branch, String address1, String address2, String address3) {
		Map<String, Object> map = new HashMap<>();
		map.put("branchName", branch.getBranchName());
		map.put("branchTel", branch.getBranchTel());

		// 주소 합치기
		String fullAddress = address1 + " " + address2 + " " + address3;
		map.put("branchAddress", fullAddress);
		log.info(fullAddress);
		int insertRow = branchService.insertBranch(map);
		if (insertRow == 1) {
			return Redirect(ViewRoutes.지점_목록); 
		} else {
			return ViewRoutes.지점_추가;
		}
	}

	// 지점 수정 폼
	@GetMapping("/update/{branchNo}")
	public String update(Model model, @PathVariable int branchNo) {
		// 공지사항 기존 내용 가져오기
		Map<String, Object> branch = branchService.getBranchOne(branchNo);
		model.addAttribute("branch", branch);

		return ViewRoutes.지점_정보_수정;
	}

	// 지점 수정 액션
	@PostMapping("/update")
	public String updateBranch(Branch branch, HttpSession session, String address1, String address2, String address3) {
		branch.setBranchAddress(address1 + " " + address2 + address3);
		// 매개변수 디버깅
		log.info(branch.toString());
		// 서비스 호출
		int updateRow = branchService.update(branch);

		return Redirect(ViewRoutes.지점_목록);
	}

	// 지점 삭제 액션
	@GetMapping("/delete/{branchNo}")
	public String delete(@PathVariable int branchNo, HttpSession session, String address1, String address2,
			String address3) {
		if (session.getAttribute("loginEmployee") == null) {
			return Redirect(ViewRoutes.홈);
		}

		// 매개변수 디버깅
		log.debug("delete", "branchNo", branchNo);
		// 서비스 호출
		int deleteRow = branchService.delete(branchNo);
		// 삭제 확인 디버깅
		log.debug("delete", "deleteRow", deleteRow);
		return Redirect(ViewRoutes.지점_목록);
	}

}
