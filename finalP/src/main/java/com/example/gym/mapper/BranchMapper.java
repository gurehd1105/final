package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Branch;

@Mapper
public interface BranchMapper {
	List<Branch> branchList();

	int insertBranch(Map<String, Object> map);

}
