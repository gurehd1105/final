package com.example.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.BranchService;
import com.example.gym.vo.Branch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("branch")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@GetMapping("list")
	@ResponseBody
	public List<Branch> BranchList() {
		return 	branchService.branchList();
		
	}
	
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
	@PostMapping("inset")
	public String insert(Branch branch){
		return null;
	}
}
