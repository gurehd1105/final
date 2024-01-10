package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Branch;

@Mapper
public interface BranchMapper {
	// 지점 조회
	List<Branch> branchList(Map<String, Integer> map);
	int totalBranch();
	// 관리자 추가를 위한 지점 조회
	List<Branch> branch();
	
	// 지점 추가
	int insertBranch(Map<String, Object> map);

}
