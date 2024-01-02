package com.example.gym.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gym.vo.Branch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("branch")
public class BranchController {
	
	@GetMapping("list")
	public List<Branch> getBranchList() {
		return null;
	}
}
