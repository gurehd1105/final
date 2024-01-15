package com.example.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.BranchMapper;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;

@Service
@Transactional
public class BranchService {
	@Autowired
	private BranchMapper branchMapper;

	// 지점 목록 조회
	public List<Branch> getBranchList(Page page) {
		return branchMapper.branchList(page);
	}
	
	public int getBranchTotal() {
		return branchMapper.totalBranch();
	}

	// 관리자 추가 위한 지점 목록 조회
	public List<Branch> branch() {
		return branchMapper.branch();
	}
	
	// 공지사항 상세보기
	public Map<String, Object> getBranchOne(int branchNo) {
		return branchMapper.selectBranchOne(branchNo);
	}
	// 지점 추가
	public int insertBranch(Map<String, Object> map) {
		return branchMapper.insertBranch(map);
	}
	// 지점 수정
	public int update(Branch branch) {
		
		return branchMapper.updateBranch(branch);
	}
		
	// 지점 비활성화
	public int delete(int branchNo) {
		
		return branchMapper.deleteBranch(branchNo);
	}
}
