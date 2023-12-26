package com.example.gym.service;


import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.mapper.CustomerMapper;
import com.example.gym.vo.Customer;
import com.example.gym.vo.CustomerDetail;
import com.example.gym.vo.CustomerForm;
import com.example.gym.vo.CustomerImg;

@Service
@Transactional
public class CustomerService {
	@Autowired private CustomerMapper customerMapper;
	
	// 로그인
	public Customer loginCustomer(Customer paramCustomer){		
		Customer loginCustomer = customerMapper.loginCustomer(paramCustomer);		
		return loginCustomer;
	}
	
	// 회원가입
	public int insertCustomer(CustomerForm cf, String path) {
		int result = 0; // 최종 반환값 세팅
		int row = 0;
		int row2 = 0;
		int row3 = 0;
		
		// customer vo 세팅 및 insert
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(cf.getCustomerId());
		paramCustomer.setCustomerPw(cf.getCustomerPw());
		
		row = customerMapper.insertCustomer(paramCustomer);
		System.out.println(row + " <-- row / insertCustomer");
		if(row != 1) {
			throw new RuntimeException();
		}
		
		
		CustomerDetail paramDetail = new CustomerDetail();
		paramDetail.setCustomerNo(paramCustomer.getCustomerNo());
		paramDetail.setCustomerName(cf.getCustomerName());
		paramDetail.setCustomerGender(cf.getCustomerGender());
		paramDetail.setCustomerHeight(cf.getCustomerHeight());
		paramDetail.setCustomerWeight(cf.getCustomerWeight());
		paramDetail.setCustomerPhone(cf.getCustomerPhone());
		paramDetail.setCustomerAddress(cf.getCustomerAddress());
		paramDetail.setCustomerEmail(cf.getCustomerEmail());
		row2 = customerMapper.insertCustomerDetail(paramDetail);
		System.out.println(row2 + " <-- row2 / insertCustomerDetail");
		if(row2 != 1) {
			throw new RuntimeException();
		}
		
		
		MultipartFile mf = cf.getCustomerImg();
		System.out.println(mf.getSize() + " <-- mf.getSize()");
		if(mf.getSize() != 0) { // 회원가입 시 선택된 사진이 있다면
			CustomerImg cImg = new CustomerImg();
				// fileName 이외 모든 값 미리세팅
			cImg.setCustomerNo(paramDetail.getCustomerNo());
			cImg.setCustomerImgOriginName(mf.getOriginalFilename());
			cImg.setCustomerImgSize(mf.getSize());
			cImg.setCustomerImgType(mf.getContentType());
			
				// fileName 값 세팅
			String fileName = UUID.randomUUID().toString();
			
			
			String oName = mf.getOriginalFilename();
			
			String fileName2 = oName.substring(oName.lastIndexOf("."));
			
			cImg.setCustomerImgFileName(fileName + fileName2);
			
			// 변수값 세팅 완 + 삽입
			row3 = customerMapper.insertCustomerImg(cImg);
			System.out.println(row3 + " <-- row3 / insertCustomerImg");
			if(row3 != 1) {
				throw new RuntimeException();
			}
			
			
			
	/*	// path 저장 -- 경로 확인 후 설정 예정
	  System.out.println(path +"/"+ fileName + fileName2);
		File file = new File(path +"/"+ fileName + fileName2);
		try {
			mf.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException();
		} */
	}
		
			
		if(row>0 && row2>0 ) {
			result = 1;
		}
		return result;
	}
														// 회원가입 프로세스 종료
	
	
	
	// 탈퇴
	public int deleteCustomer(Customer paramCustomer) {
		int result = 0;
		int row = 0;
		int row2 = 0;
		int row3 = 0;
		Customer check = customerMapper.loginCustomer(paramCustomer);
		System.out.println(paramCustomer + " <-- paramCustomer");
		System.out.println(check + " <-- check");
		if(check!=null) {
		row = customerMapper.updateCustomerActive(paramCustomer);
		System.out.println(row + " <-- row");
		
		row2 = customerMapper.deleteCustomerImg(paramCustomer);
		System.out.println(row2 + " <-- row2");
		
		row3 = customerMapper.deleteCustomerDetail(paramCustomer);
		System.out.println(row3 + " <-- row3");
		}
		
		if(row>0 && row2>0 && row3>0) {
			result = 1;
		}
		return result;
	}
	
	// 상세보기
	public Map<String, Object> customerOne(Customer paramCustomer) {
		Map<String, Object> resultMap = customerMapper.customerOne(paramCustomer);
		return resultMap;
	}
	
	
	
	
	 // 정보수정 
		// 마이페이지 수정 + Image 수정
	 public void updateCustomerOne(String path, CustomerForm customerForm, int customerNo) { 
		   // customerDetail 수정
		 	CustomerDetail customerDetail = new CustomerDetail();
		 	customerDetail.setCustomerNo(customerNo);
		 	customerDetail.setCustomerName(customerForm.getCustomerName());
		 	customerDetail.setCustomerGender(customerForm.getCustomerGender());
		 	customerDetail.setCustomerHeight(customerForm.getCustomerHeight());
		 	customerDetail.setCustomerWeight(customerForm.getCustomerWeight());
		 	customerDetail.setCustomerPhone(customerForm.getCustomerPhone());
		 	customerDetail.setCustomerAddress(customerForm.getCustomerAddress());
		 	customerDetail.setCustomerEmail(customerForm.getCustomerEmail());
		 	int row = customerMapper.updateCustomerOne(customerDetail);
		 	System.out.println(row + " <-- row / updateCustomerOne");
		 	if(row!=7) {
		 		throw new RuntimeException();
		 	}
		 	
		 	MultipartFile mf = customerForm.getCustomerImg();
		 	CustomerImg customerImg = new CustomerImg();
		 	customerImg.setCustomerNo(customerNo);
		 	customerImg.setCustomerImgOriginName(mf.getOriginalFilename());
		 	customerImg.setCustomerImgSize(mf.getSize());
		 	customerImg.setCustomerImgType(mf.getContentType());
		 	
		 	String fileName = UUID.randomUUID().toString();
		 	
		 	String oName = mf.getOriginalFilename();
		 	String fileName2 = oName.substring(oName.lastIndexOf("."));
		 	customerImg.setCustomerImgFileName(fileName + fileName2);
		 	
		 	int row2 = customerMapper.updateCustomerImg(customerImg);
		 	if(row2!=4) {
		 		throw new RuntimeException();
		 	}
		
	 } 
	 	
	 
	 
	
		// 비밀번호 수정
	public int updateCustomerPw(Customer checkCustomer, String customerNewPw) {
		int result = 0;
		Customer check = customerMapper.loginCustomer(checkCustomer);
		if(check != null) {
			Customer updatePw = new Customer();
			updatePw.setCustomerNo(check.getCustomerNo());
			updatePw.setCustomerPw(customerNewPw);
			result = customerMapper.updateCustomerPw(updatePw);
		}
		return result;
	}	
	
	
	
}
