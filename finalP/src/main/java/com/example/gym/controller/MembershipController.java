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
import com.example.gym.vo.Membership;

@Controller
@RequestMapping("membership")
public class MembershipController extends DefaultController{
	@ Autowired
	private MembershipService membershipService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Membership> membershipList = membershipService.list();
		model.addAttribute("membershipList", toJson(membershipList));
		return "membership/list";
	}
	
	@GetMapping("/insert")
	public String insert() {
		
		return "membership/insert";
	}
	
	@PostMapping("/insert")
	public String insert(Membership membership) {
		membershipService.insert(membership);
		
		return "redirect:list";
	}
	
	@GetMapping("/update")
	public String update(Membership membership, Model model) {
		System.out.println(membership.getMembershipNo());
		Membership membershipOne = membershipService.membershipOne(membership);
		model.addAttribute("membership" , membershipOne);		
		
		return "membership/update";
	}
	
	@PostMapping("/update")
	public String update(Membership membership) {
		membershipService.update(membership);
		
		return "";
	}
	
	@PostMapping("/delete")
	public String delete(Membership membership) {
		System.out.println(membership);
		/* int result = membershipService.delete(membership); */
		
		return "membership/list";
	}
	
	
}
