package com.example.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.MembershipService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Membership;
import com.example.gym.vo.Page;

@Controller
@RequestMapping("membership")
public class MembershipController extends DefaultController{
	@ Autowired
	private MembershipService membershipService;
	
	@GetMapping("/list")
	public String list(Model model, Page page) {
		page.setTotalCount(membershipService.totalCount());
		page.setRowPerPage(10);
		List<Membership> membershipList = membershipService.list(page);
		model.addAttribute("membershipList", toJson(membershipList));
		model.addAttribute("page", page);
		return ViewRoutes.회원권_조회;
	}
	
	@GetMapping("/insert")
	public String insert() {
		
		return ViewRoutes.회원권_추가;
	}
	
	@PostMapping("/insert")
	public String insert(Membership membership) {
		membershipService.insert(membership);
		
		return Redirect(ViewRoutes.회원권_조회);
	}
	
	@GetMapping("/update")
	public String update(Membership membership, Model model) {
		Membership membershipOne = membershipService.membershipOne(membership);
		model.addAttribute("membership" , toJson(membershipOne));	
		return ViewRoutes.회원권_수정;
	}
	
	@PostMapping("/update")
	public String update(Membership membership) {
		membershipService.update(membership);
		
		return Redirect(ViewRoutes.회원권_조회);
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public int delete(@RequestBody Membership membership) {
		int result = 0;
		result = membershipService.delete(membership);
		
		return result;
	}
	
	
}
