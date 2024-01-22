package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Branch;
import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;

@Mapper
public interface BranchMapper {
	// 지점 조회
	List<Branch> branchList(Page page);
	int totalBranch();
	// 관리자 추가를 위한 지점 조회
	List<Branch> branch();
	
	// 지점 추가
	int insertBranch(Map<String, Object> map);
	
	// 공지사항 상세보기 
	Map<String, Object> selectBranchOne(int branchNo);
	
	// 지점 수정
	int updateBranch(Branch branch);
	// 지점 비활성화.
	int deleteBranch(int branchNo);
}
