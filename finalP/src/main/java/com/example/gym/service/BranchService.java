package com.example.gym.service;

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
	@Autowired private BranchMapper branchMapper;
	
	// 지점 목록 조회
	public List<Branch> branchList() {
		return	branchMapper.branchList();
	}
	
	
	public int insertBranch(Map<String, Object> map) {
		return branchMapper.insertBranch(map);
	}
	
}
