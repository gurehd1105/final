package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return branchService.branchList();

	}

	@GetMapping("insert")
	public String insert() {
		return "branch/insert";
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
	        return "redirect:/branch/list"; // "Redirect:"에서 오타 수정
	    } else {
	        return "branch/insert"; 
	    }
	}

}
