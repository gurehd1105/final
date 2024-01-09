package com.example.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Branch;

@Mapper
public interface BranchMapper {
	List<Branch> branchList();

	Branch insertBranch(Branch branch);

}
