package com.example.gym.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.mapper.SportsEquipmentMapper;
import com.example.gym.vo.SportsEquipment;
import com.example.gym.vo.SportsEquipmentImg;
import com.example.gym.vo.SportsEquipmentOrder;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SportsEquipmentService {
	@Autowired private SportsEquipmentMapper sportsEquipmentMapper;
	
//----------------------------------------- SportsEquipment -------------------------------------
	
	//sportsEquipment 추가
	public void insertSportsEquipmentService(HttpSession session,
												String path,
												String itemName, 
												int itemPrice, 
												MultipartFile[] sportsEquipmentImgList) {
		
		//sportsEquipment 추가를 시도하는 employeeNo가 본사 소속인지 확인
		log.warn( "employee session 구현 후 수정");
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//sportsEquipment 추가
		SportsEquipment sportsEquipment = new SportsEquipment();
		log.warn("employee session 구현 후 수정 -> employee_no 컬럼은 세션에서 받아와서 설정 " );
		sportsEquipment.setEmployeeNo(1); 
		sportsEquipment.setItemName(itemName);
		sportsEquipment.setItemPrice(itemPrice);
			
		//mapper 호출
		int row1 = sportsEquipmentMapper.insertSportsEquipment(sportsEquipment);
		
		//sportsEquipment 정보 추가 실패했을 경우 -> 강제로 예외발생 트랜잭션 처리
		if(row1 != 1) {
			throw new RuntimeException("예외발생 : sportsEquipment 정보 추가 실패");
		}
		
		//추가 된 sportsEquipment 디버깅
		System.err.println(sportsEquipment + " <-- 추가 된 sportsEquipment");
		log.info(sportsEquipment + " <-- 추가 된 sportsEquipment");
		
		//sportsEquipmentImg 추가
		for (MultipartFile equipmentImg : sportsEquipmentImgList) {
			SportsEquipmentImg sportsEquipmentImg = new SportsEquipmentImg();
			
			//portsEquipmentImgFileName
			String fileName = UUID.randomUUID().toString();
			//확장자
			String originName = equipmentImg.getOriginalFilename();
			String extensionName = originName.substring(originName.lastIndexOf("."));
			
			sportsEquipmentImg.setSportsEquipmentNo(sportsEquipment.getSportsEquipmentNo());
			sportsEquipmentImg.setSportsEquipmentImgSize((int)equipmentImg.getSize());
			sportsEquipmentImg.setSportsEquipmentImgType(equipmentImg.getContentType());
			sportsEquipmentImg.setSportsEquipmentImgOriginName(originName);
			sportsEquipmentImg.setSportsEquipmentImgFileName(fileName+extensionName);
		
			//mapper 호출
			int row2 = sportsEquipmentMapper.insertSportsEquipmentImg(sportsEquipmentImg);
			//sportsEquipmentImg 추가 실패했을 경우 -> 강제로 예외발생 트랜잭션 처리
			if(row2 != 1) {
				throw new RuntimeException("예외발생 : sportsEquipmentImg 추가 실패");
			}
			File file = new File(path+"/"+fileName+extensionName);
			try {
				equipmentImg.transferTo(file);
			} catch (IllegalStateException e) {
				 e.printStackTrace();
				throw new RuntimeException();
			} catch (IOException e) {
				 e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	//sportsEquipmentList 출력
	public Map<String,Object> selectSportsEquipmentByPageService(HttpSession session,
																	int currentPage,
																	String equipmentActive,
																	String searchWord) {
		//디버깅
		log.info(searchWord);
		log.info("Current page: {}", currentPage);
		log.info("equipmentActive : {}", equipmentActive);
		
		//페이징
		int rowPerPage = 2; //한 페이지에 표시할 equipment 수 
		int beginRow = (currentPage - 1) * rowPerPage;
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap1 = new HashMap<>();
		paramMap1.put("searchWord", searchWord);
		paramMap1.put("equipmentActive", equipmentActive);
		
		//mapper 호출 
		int sportsEquipmentCnt = sportsEquipmentMapper.selectSportsEquipmentCnt(paramMap1);
		int lastPage = sportsEquipmentCnt/rowPerPage;
		
		if(sportsEquipmentCnt%rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		//디버깅
		log.info("lastPage : {}", lastPage);
		log.info("sportsEquipmentCnt : {}", sportsEquipmentCnt);
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap2 = new HashMap<>();
		paramMap2.put("searchWord", searchWord);
		paramMap2.put("equipmentActive", equipmentActive);
		paramMap2.put("beginRow", beginRow);
		paramMap2.put("rowPerPage", rowPerPage);
		
		//mapper 호출
		List<Map<String,Object>> sportsEquipmentList = sportsEquipmentMapper.selectSportsEquipmentByPage(paramMap2);
		
		//controller에 보내줄 resultMap 생성
		Map<String,Object> resultMap = new HashMap<>();
		
		resultMap.put("searchWord", searchWord);
		resultMap.put("equipmentActive", equipmentActive);
		resultMap.put("lastPage", lastPage);
		resultMap.put("sportsEquipmentList", sportsEquipmentList);
		
		return resultMap;
	}
	
	//sportsEquipment 수정 폼
	public Map<String,Object> sportsEquipmentOneService(HttpSession session,
																int sportsEquipmentNo) {
		//디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);

		
		//mapper 호출
		Map<String,Object> resultMap  = sportsEquipmentMapper.selectSportsEquipmentOne(sportsEquipmentNo);
		List<SportsEquipmentImg> sportsEquipmentImgList = sportsEquipmentMapper.selectSportsEquipmentImgList(sportsEquipmentNo);
		
		resultMap.put("sportsEquipmentImgList", sportsEquipmentImgList);
		
		return resultMap;
	}
	
	//sportsEquipment 수정 액션
	public int updateSportsEquipmentService(HttpSession session,
																int sportsEquipmentNo,
																String itemName,
																int itemPrice,
																String equipmentActive) {
		//디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);
		log.info("itemName : {}", itemName);
		log.info("itemPrice : {}", itemPrice);
		log.info("equipmentActive : {}", equipmentActive);
	
		//sportsEquipment 수정을 시도하는 employeeNo가 본사 소속인지 확인
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//mapper에 보내줄 sportsEquipment 객체 세팅
		SportsEquipment sportsEquipment = new SportsEquipment();
		sportsEquipment.setSportsEquipmentNo(sportsEquipmentNo);
		sportsEquipment.setItemName(itemName);
		sportsEquipment.setItemPrice(itemPrice);
		sportsEquipment.setEquipmentActive(equipmentActive);
		log.warn("employee session 구현 후 수정 -> employee_no 컬럼은 세션에서 받아와서 설정 " );
		sportsEquipment.setEmployeeNo(1);
		
		//mapper 호출
		int row = sportsEquipmentMapper.updateSportsEquipment(sportsEquipment);
		
		//mapper 호출 디버깅
		if (row != 1) {
		    log.info("sportsEquipment 수정실패 : row - {}", row);
		} else {
		    log.info("sportsEquipment 수정성공: row - {}", row);
		}
		return sportsEquipmentNo;
	}
	
	//sportsEquipmentImg 개별 삭제 액션
	public int deleteOneSportsEquipmentImgService(HttpSession session,
																int sportsEquipmentNo,
																int sportsEquipmentImgNo,
																String sportsEquipmentImgFileName) {
		//디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);
		log.info("sportsEquipmentImgNo : {}", sportsEquipmentImgNo);
		log.info("sportsEquipmentImgFileName : {}", sportsEquipmentImgFileName);
	
		//sportsEquipmentImg 삭제를 시도하는 employeeNo가 본사 소속인지 확인
		log.warn("employee session 구현 후 수정 " );
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//실제 img파일 먼저 삭제
	    String path = session.getServletContext().getRealPath("/upload/sportsEquipment");
	    File file = new File(path + "/" + sportsEquipmentImgFileName);
		
	    // 파일 삭제
	    if (file.exists()) {
	        if (file.delete()) {
	            log.info("sportsEquipmentImg 파일 개별 삭제 성공");
	        } else {
	            log.info("sportsEquipmentImg 파일 개별 삭제 실패");
	            throw new RuntimeException("sportsEquipmentImg 파일 개별 삭제 실패");
	        }
	    } else {
	        log.info("sportsEquipmentImg 파일이 이미 존재하지 않습니다.");
	    }

		
	    //데이터베이스 삭제
		//mapper 호출
		int row = sportsEquipmentMapper.deleteOneSportsEquipmentImg(sportsEquipmentImgNo);
		
		//mapper 호출 디버깅
		if (row != 1) {
		    log.info("sportsEquipmentImg 개별 데이터베이스 삭제실패 : row - {}", row);
		    throw new RuntimeException("데이터베이스 삭제 실패");
		} else {
		    log.info("sportsEquipmentImg 개별 삭제성공: row - {}", row);
		}
		return sportsEquipmentNo;
	}
	
	//sportsEquipment 개별 Img 추가
	public int insertOneSportsEquipmentImgService(HttpSession session,
												String path,
												int sportsEquipmentNo,
												MultipartFile[] sportsEquipmentImgList) {
		
		//sportsEquipment 추가를 시도하는 employeeNo가 본사 소속인지 확인
		log.warn("employee session 구현 후 수정 " );
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//sportsEquipmentImg 추가
		for (MultipartFile equipmentImg : sportsEquipmentImgList) {
			SportsEquipmentImg sportsEquipmentImg = new SportsEquipmentImg();
			
			//portsEquipmentImgFileName
			String fileName = UUID.randomUUID().toString();
			//확장자
			String originName = equipmentImg.getOriginalFilename();
			String extensionName = originName.substring(originName.lastIndexOf("."));
			
			sportsEquipmentImg.setSportsEquipmentNo(sportsEquipmentNo);
			sportsEquipmentImg.setSportsEquipmentImgSize((int)equipmentImg.getSize());
			sportsEquipmentImg.setSportsEquipmentImgType(equipmentImg.getContentType());
			sportsEquipmentImg.setSportsEquipmentImgOriginName(originName);
			sportsEquipmentImg.setSportsEquipmentImgFileName(fileName+extensionName);
		
			//mapper 호출
			int row = sportsEquipmentMapper.insertSportsEquipmentImg(sportsEquipmentImg);
			//sportsEquipmentImg 추가 실패했을 경우 -> 강제로 예외발생 트랜잭션 처리
			if(row != 1) {
				throw new RuntimeException("예외발생 : sportsEquipmentImg 추가 실패");
			}
			File file = new File(path+"/"+fileName+extensionName);
			try {
				equipmentImg.transferTo(file);
			} catch (IllegalStateException e) {
				 e.printStackTrace();
				throw new RuntimeException();
			} catch (IOException e) {
				 e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return sportsEquipmentNo;
	}
	
//----------------------------------------- SportsEquipmentOrder -------------------------------------
	
	//sportsEquipmentOrder 추가
	public void insertSportsEquipmentOrderService(HttpSession session,
													int sportsEquipmentNo,
													int quantity,
													int itemPrice) {
		
		//sportsEquipment 추가를 시도하는 employeeNo가 지점 소속인지 확인
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
		//mapper 호출
		log.warn("임시 데이터 부산점 직원NO" );
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(2);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 0) {	
			throw new RuntimeException("예외발생 : 지점직원이 아닙니다. ");
		}
		
		//mapper에 보내줄 sportsEquipmentOrder 객체 세팅
		SportsEquipmentOrder sportsEquipmentOrder = new SportsEquipmentOrder();
		sportsEquipmentOrder.setSportsEquipmentNo(sportsEquipmentNo);
		log.warn("employee session 구현 후 수정 -> branchNo 컬럼은 세션에서 받아와서 설정" );
		sportsEquipmentOrder.setBranchNo(2);
		sportsEquipmentOrder.setQuantity(quantity);
		if(quantity > 0) {
			sportsEquipmentOrder.setTotalPrice(itemPrice*quantity);
		}else {
			sportsEquipmentOrder.setTotalPrice(0);
		}
		//mapper 호출
		int row = sportsEquipmentMapper.insertSportsEquipmentOrder(sportsEquipmentOrder);
		
		//mapper 호출 디버깅
		if (row != 1) {
		    log.info("sportsEquipmentOrder 추가 실패 : row - {}", row);
		} else {
		    log.info("sportsEquipmentOrder 추가 성공 : row - {}", row);
		}
		
	}
	
	//sportsEquipmentOrderList 출력(본사)
	public Map<String,Object> selectSportsEquipmentOrderByHeadService(HttpSession session,
																	int currentPage,
																	String searchBranch,
																	String searchItem,
																	String beginDate,
																	String endDate) {
		//디버깅
		log.info("currentPage: {}", currentPage);
		log.info("searchBranch: {}", searchBranch);
		log.info("searchItem: {}", searchItem);
		log.info("beginDate: {}", beginDate);
		log.info("endDate: {}", endDate);
		
		//페이징
		int rowPerPage = 10; //한 페이지에 표시할 equipment 수 
		int beginRow = (currentPage - 1) * rowPerPage;
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("searchBranch", searchBranch);
		paramMap.put("searchItem", searchItem);
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		
		//본사직원은 모든 데이터 
		log.warn("employee session 구현 후 수정");
		int loginBranchLevel = 1;
		
		paramMap.put("loginBranchLevel", loginBranchLevel);
		
		//mapper 호출 
		int sportsEquipmentOrderCnt = sportsEquipmentMapper.selectSportsEquipmentOrderHeadCnt(paramMap);
		int lastPage = sportsEquipmentOrderCnt/rowPerPage;
		
		if(sportsEquipmentOrderCnt%rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		//디버깅
		log.info("lastPage : {}", lastPage);
		log.info("sportsEquipmentOrderCnt : {}", sportsEquipmentOrderCnt);
		
		//페이징 변수 Map에 put
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		//디버깅
		System.out.println(paramMap);
		
		//mapper 호출
		List<Map<String,Object>> sportsEquipmentOrderList = sportsEquipmentMapper.selectSportsEquipmentOrderByHead(paramMap);
				
		//controller에 보내줄 resultMap 생성
		Map<String,Object> resultMap = new HashMap<>();
		

		resultMap.put("endDate", endDate);
		resultMap.put("beginDate", beginDate);
		resultMap.put("searchItem", searchItem);
		resultMap.put("searchBranch", searchBranch);
		resultMap.put("lastPage", lastPage);
		resultMap.put("sportsEquipmentOrderList", sportsEquipmentOrderList);
		
		return resultMap;
	}

	//sportsEquipmentOrderList 출력(지사)
	public Map<String,Object> selectSportsEquipmentOrderByBranchService(HttpSession session,
																	int currentPage,
																	String searchItem,
																	String beginDate,
																	String endDate) {
		//디버깅
		log.info("currentPage: {}", currentPage);
		log.info("searchItem: {}", searchItem);
		log.info("beginDate: {}", beginDate);
		log.info("endDate: {}", endDate);
		
		//페이징
		int rowPerPage = 10; //한 페이지에 표시할 equipment 수 
		int beginRow = (currentPage - 1) * rowPerPage;
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("searchItem", searchItem);
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		
		//지점직원은 소속된 지점의 데이터 출력
		log.warn("employee session 구현 후 수정");
		int loginBranchNo = 2;
		log.warn("employee session 구현 후 수정");
		int loginBranchLevel = 0;
		
		paramMap.put("loginBranchNo", loginBranchNo);
		paramMap.put("loginBranchLevel", loginBranchLevel);
		
		//mapper 호출 
		int sportsEquipmentOrderCnt = sportsEquipmentMapper.selectSportsEquipmentOrderBranchCnt(paramMap);
		int lastPage = sportsEquipmentOrderCnt/rowPerPage;
		
		if(sportsEquipmentOrderCnt%rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		//디버깅
		log.info("lastPage : {}", lastPage);
		log.info("sportsEquipmentOrderCnt : {}", sportsEquipmentOrderCnt);
		
		//페이징 변수 Map에 put
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		//디버깅
		System.out.println(paramMap);
		
		//mapper 호출
		List<Map<String,Object>> sportsEquipmentOrderList = sportsEquipmentMapper.selectSportsEquipmentOrderByBranch(paramMap);
		
		
		//controller에 보내줄 resultMap 생성
		Map<String,Object> resultMap = new HashMap<>();
		

		resultMap.put("endDate", endDate);
		resultMap.put("beginDate", beginDate);
		resultMap.put("searchItem", searchItem);
		resultMap.put("lastPage", lastPage);
		resultMap.put("sportsEquipmentOrderList", sportsEquipmentOrderList);
		
		return resultMap;
	}

	//sportsEquipmentOrder 수정 액션
	public void updateSportsEquipmentOrderService(HttpSession session,
														int orderNo,
														String orderStatus) {
		//디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);
	
		//sportsEquipment 수정을 시도하는 employeeNo가 본사 소속인지 확인
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//mapper에 보내줄 sportsEquipmentOrder 객체 세팅
		SportsEquipmentOrder sportsEquipmentOrder = new SportsEquipmentOrder();
		sportsEquipmentOrder.setOrderNo(orderNo);
		sportsEquipmentOrder.setOrderStatus(orderStatus);

		//mapper 호출
		int row = sportsEquipmentMapper.updateSportsEquipmentOrder(sportsEquipmentOrder);
		
		//mapper 호출 디버깅
		if (row != 1) {
		    log.info("sportsEquipmentOrder 수정실패 : row - {}", row);
		} else {
		    log.info("sportsEquipmentOrder 수정성공: row - {}", row);
		}
	}

	//sportsEquipmentOrder 삭제 액션
	public void deleteSportsEquipmentOrderService(HttpSession session,
														int orderNo,
														String orderStatus) {
		//디버깅
		log.info("orderNo : {}", orderNo);
		log.info("orderStatus : {}", orderStatus);
	
		log.warn("employee session 구현 후 수정");
		//sportsEquipmentOrder 삭제을 시도하는 employeeNo가 지점 소속인지 확인
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
		//mapper 호출
		//int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		//log.info( branchLevel + " <-- 1:본사 0:지점");
		//if(branchLevel != 0) {	
			//throw new RuntimeException("예외발생 : 지점직원이 아닙니다. ");
		//}
		
		//mapper에 보내줄 sportsEquipmentOrder 객체 세팅
		SportsEquipmentOrder sportsEquipmentOrder = new SportsEquipmentOrder();
		sportsEquipmentOrder.setOrderNo(orderNo);
		sportsEquipmentOrder.setOrderStatus(orderStatus);

		//mapper 호출
		int row = sportsEquipmentMapper.deleteSportsEquipmentOrder(sportsEquipmentOrder);
		
		//mapper 호출 디버깅
		if (row != 1) {
		    log.info("sportsEquipmentOrder 삭제실패 : row - {}", row);
		} else {
		    log.info("sportsEquipmentOrder 삭제성공: row - {}", row);
		}
	}
	
//----------------------------------------- SportsEquipmentOrder -------------------------------------	
	
	//sportsEquipmentInventory 출력(본사)
	public Map<String,Object> selectSportsEquipmentInventoryHeadService(HttpSession session,
																	int currentPage,
																	String searchBranch,
																	String searchItem) {
		//디버깅
		log.info("currentPage: {}", currentPage);
		log.info("searchBranch: {}", searchBranch);
		log.info("searchItem: {}", searchItem);

		//페이징
		int rowPerPage = 2; //한 페이지에 표시할 equipment 수 
		int beginRow = (currentPage - 1) * rowPerPage;
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("searchBranch", searchBranch);
		paramMap.put("searchItem", searchItem);
		
		//본사직원은 모든 데이터 
		log.warn("employee session 구현 후 수정");
		int loginBranchLevel = 1;
		
		paramMap.put("loginBranchLevel", loginBranchLevel);
		
		//mapper 호출 
		int sportsEquipmentInventoryCnt = sportsEquipmentMapper.selectSportsEquipmentInventoryByHeadCnt(paramMap);
		int lastPage = sportsEquipmentInventoryCnt/rowPerPage;
		
		if(sportsEquipmentInventoryCnt%rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		//디버깅
		log.info("lastPage : {}", lastPage);
		log.info("sportsEquipmentInventoryCnt : {}", sportsEquipmentInventoryCnt);
		
		//페이징 변수 Map에 put
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		//디버깅
		System.out.println(paramMap);
		
		//mapper 호출
		List<Map<String,Object>> sportsEquipmentInventory = sportsEquipmentMapper.selectSportsEquipmentInventoryByHead(paramMap);
				
		//controller에 보내줄 resultMap 생성
		Map<String,Object> resultMap = new HashMap<>();
		

		resultMap.put("searchItem", searchItem);
		resultMap.put("searchBranch", searchBranch);
		resultMap.put("lastPage", lastPage);
		resultMap.put("sportsEquipmentInventory", sportsEquipmentInventory);
		
		return resultMap;
	}
	
	//sportsEquipmentInventory 출력(지점)
	public Map<String,Object> selectSportsEquipmentInventoryBranchService(HttpSession session,
																	int currentPage,
																	String searchItem) {
		//디버깅
		log.info("currentPage: {}", currentPage);
		log.info("searchItem: {}", searchItem);

		//페이징
		int rowPerPage = 2; //한 페이지에 표시할 equipment 수 
		int beginRow = (currentPage - 1) * rowPerPage;
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("searchItem", searchItem);
		
		//지점직원은 소속된 지점의 데이터 출력
		log.warn("employee session 구현 후 수정");
		int loginBranchNo = 2;
		log.warn("employee session 구현 후 수정");
		int loginBranchLevel = 0;
		
		paramMap.put("loginBranchNo", loginBranchNo);
		paramMap.put("loginBranchLevel", loginBranchLevel);
		
		
		//mapper 호출 
		int sportsEquipmentInventoryCnt = sportsEquipmentMapper.selectSportsEquipmentInventoryByBranchCnt(paramMap);
		int lastPage = sportsEquipmentInventoryCnt/rowPerPage;
		
		if(sportsEquipmentInventoryCnt%rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		//디버깅
		log.info("lastPage : {}", lastPage);
		log.info("sportsEquipmentInventoryCnt : {}", sportsEquipmentInventoryCnt);
		
		//페이징 변수 Map에 put
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		
		//디버깅
		System.out.println(paramMap);
		
		//mapper 호출
		List<Map<String,Object>> sportsEquipmentInventory = sportsEquipmentMapper.selectSportsEquipmentInventoryByBranch(paramMap);
				
		//controller에 보내줄 resultMap 생성
		Map<String,Object> resultMap = new HashMap<>();
		

		resultMap.put("searchItem", searchItem);
		resultMap.put("lastPage", lastPage);
		resultMap.put("sportsEquipmentInventory", sportsEquipmentInventory);
		
		return resultMap;
	}
}