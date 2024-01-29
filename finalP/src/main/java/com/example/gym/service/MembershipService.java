package com.example.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gym.mapper.MembershipMapper;
import com.example.gym.vo.Membership;
import com.example.gym.vo.Page;

@Service
public class MembershipService {
	@Autowired
	private MembershipMapper membershipMapper;
	
	public List<Membership> list(Page page) {
		List<Membership> membershipList = membershipMapper.list(page);
		return membershipList;
	}
	
	public int totalCount() {
		return membershipMapper.totalCount();		
	}
	
	public int insert(Membership paramMembership) {
		int insert = 0;
		insert = membershipMapper.insert(paramMembership);
		return insert;
	}
	
	public int delete(Membership paramMembership) {
		int delete = 0;
		delete = membershipMapper.delete(paramMembership);
		return delete;
	}
	
	public int update(Membership paramMembership) {
		int update = 0;
		update = membershipMapper.update(paramMembership);
		return update;
	}
	
	public Membership membershipOne(Membership paramMembership) {
		Membership membership = membershipMapper.membershipOne(paramMembership);		
		return membership;
	}
}
