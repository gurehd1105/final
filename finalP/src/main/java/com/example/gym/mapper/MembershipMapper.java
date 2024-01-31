package com.example.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Membership;

@Mapper
public interface MembershipMapper {
	List<Membership> list();
	int insert(Membership membership);
	int delete(Membership membership);
	int update(Membership membership);
	Membership membershipOne(Membership membership);
}
