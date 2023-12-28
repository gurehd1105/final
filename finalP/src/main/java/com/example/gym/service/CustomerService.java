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
		
		// customer 매개값 세팅
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(cf.getCustomerId());
		paramCustomer.setCustomerPw(cf.getCustomerPw());
		
		Customer checkId = customerMapper.loginCustomer(paramCustomer);	// 중복확인 --> checkId === null --> 회원가입 가능
		System.out.println(checkId + "  <-- 중복값 여부");
		
		if(checkId==null || checkId.getCustomerActive().equals("N")) { // 중복값이 없거나 , 있더라도 비활성화 계정일 때
		
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
				// fileName 이외 모든 값 세팅
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
			
			if(row>0 && row2>0 ) { // Image 정보 없어도 가입가능
				result = 1;
			}
		} else {		// ID 중복값이 있을 때
			result = 0;
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
	 public void updateCustomerOne(String path, CustomerForm paramCustomerForm, int customerNo) { 
		   // customerDetail 수정
		 	CustomerDetail customerDetail = new CustomerDetail();
		 	customerDetail.setCustomerNo(customerNo);
		 	customerDetail.setCustomerName(paramCustomerForm.getCustomerName());
		 	customerDetail.setCustomerGender(paramCustomerForm.getCustomerGender());
		 	customerDetail.setCustomerHeight(paramCustomerForm.getCustomerHeight());
		 	customerDetail.setCustomerWeight(paramCustomerForm.getCustomerWeight());
		 	customerDetail.setCustomerPhone(paramCustomerForm.getCustomerPhone());
		 	customerDetail.setCustomerAddress(paramCustomerForm.getCustomerAddress());
		 	customerDetail.setCustomerEmail(paramCustomerForm.getCustomerEmail());
		 	int row = customerMapper.updateCustomerOne(customerDetail);
		 	System.out.println(row + " <-- row / updateCustomerOne");
		 	if(row!=1) {
		 		throw new RuntimeException();
		 	}
		 			 	
		 	
		 	MultipartFile mf = paramCustomerForm.getCustomerImg();
		 	
		 	if(mf.getSize()!=0) {	// 사용자가 지정한 Image 정보가 있다면
		 	CustomerImg customerImg = new CustomerImg();
		 	customerImg.setCustomerNo(customerNo);
		 	customerImg.setCustomerImgOriginName(mf.getOriginalFilename());
		 	customerImg.setCustomerImgSize(mf.getSize());
		 	customerImg.setCustomerImgType(mf.getContentType());
		 	
		 	String fileName = UUID.randomUUID().toString();
		 	
		 	String oName = mf.getOriginalFilename();
		 	String fileName2 = oName.substring(oName.lastIndexOf("."));
		 	customerImg.setCustomerImgFileName(fileName + fileName2); // CustomerImg 매개값 세팅
		 	
		 // 변수 삽입 전 customerImg정보가 있는지 확인
		 	Customer checkImgCustomer = new Customer();
		 	checkImgCustomer.setCustomerNo(customerNo);		 	
		 	CustomerImg check = customerMapper.checkCustomerImg(checkImgCustomer);	
		 	System.out.println(check + " <-- check");
		 // 확인 후 조건에 따른 분기
		 	int row2 = 0;
		 	if(check == null) {	// 이전 Image 정보가 아예 없음 --> 가입 시 미등록이라면 또는 등록 이후 삭제 했다면
		 		row2 = customerMapper.insertCustomerImg(customerImg);
		 	} else {			// 원래 등록된 Image 정보가 있다면
		 		row2 = customerMapper.updateCustomerImg(customerImg);
		 	}	 	
		 	
		 	if(row2!=1) {
		 		throw new RuntimeException();
		 	}
		 	} else {	// 고객이 Image 정보를 지정하지 않았다면 --> 이미지정보 삭제
		 		Customer deleteImg = new Customer();
		 		deleteImg.setCustomerNo(customerNo);
		 		customerMapper.deleteCustomerImg(deleteImg);
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
