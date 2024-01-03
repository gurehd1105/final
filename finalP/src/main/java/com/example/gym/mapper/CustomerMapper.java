package com.example.gym.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;
import com.example.gym.vo.CustomerImg;

@Mapper
public interface CustomerMapper {
	// login
	Customer loginCustomer(Customer customer);
	
	// insert (가입)
		// ID 중복체크
	Customer checkId(Customer customer);
	int insertCustomer(Customer customer);
	int insertCustomerDetail(CustomerDetail customerDetail);
	// 선택정보 (Image)
	int insertCustomerImg(CustomerImg customerImg);
	
	// 탈퇴 update(Active) + delete(Image, Detail)
	int updateCustomerActive(Customer customer);
	int deleteCustomerImg(Customer customer);
	int deleteCustomerDetail(Customer customer);
	
	// 상세보기 = 마이페이지
	Map<String, Object> customerOne(Customer customer);
	
	// 정보수정
		// 상세정보 변경
	int updateCustomerOne(CustomerDetail customerDetail);
	int updateCustomerImg(CustomerImg customerImg);
		// Img 변경위한 정보조회
	CustomerImg checkCustomerImg(Customer customer);
		// 비밀번호 변경
	int updateCustomerPw(Customer customer);
	
	
	// 전체 회원조회 - 관리자용
	Map<String, Object> selectAllCustomer();
}
