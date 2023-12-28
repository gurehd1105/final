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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.mapper.SportsEquipmentMapper;
import com.example.gym.vo.SportsEquipment;
import com.example.gym.vo.SportsEquipmentImg;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SportsEquipmentService {
	@Autowired private SportsEquipmentMapper sportsEquipmentMapper;
	
	//sportsEquipment 추가
	public void insertSportsEquipmentService(HttpSession session,
												String path,
												String itemName, 
												int itemPrice, 
												MultipartFile[] sportsEquipmentImgList) {
		
		//sportsEquipment 추가를 시도하는 employeeNo가 본사 소속인지 확인
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//sportsEquipment 추가
		SportsEquipment sportsEquipment = new SportsEquipment();
		sportsEquipment.setEmployeeNo(1); //employee session 구현 후 수정
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
	public Map<String,Object> updateSportsEquipmentService(HttpSession session,
																int sportsEquipmentNo) {
		//디버깅
		log.info("sportsEquipmentNo : {}", sportsEquipmentNo);

		//sportsEquipment 추가를 시도하는 employeeNo가 본사 소속인지 확인
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
		//mapper 호출
		int branchLevel = sportsEquipmentMapper.selectSearchEmployeeLevel(1);
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
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
		//employee session 구현 후 수정 -> employee_no 컬럼은 세션에서 받아와서 설정 
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
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
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
		//employee session 구현 후 수정 mapper -> employeeMapper로 이동해야함
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
}