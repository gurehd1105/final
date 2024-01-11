package com.example.gym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.BranchMapper;
import com.example.gym.vo.Branch;

@Service
@Transactional
public class BranchService {
	@Autowired
	private BranchMapper branchMapper;

	// 지점 목록 조회
	public Map<String, Object> branchList(Map<String, Integer> paramMap) {
		Map<String, Object> resultMap = new HashMap<>();
		List<Branch> branchList = branchMapper.branchList(paramMap);
		int totalRow = branchMapper.totalBranch();

		resultMap.put("branchList", branchList);
		resultMap.put("totalRow", totalRow);
		return resultMap;
	}

	// 관리자 추가 위한 지점 목록 조회
	// 지점 목록 조회
	public List<Branch> branch() {
		return branchMapper.branch();
	}

	public int insertBranch(Map<String, Object> map) {
		return branchMapper.insertBranch(map);
	}

}
