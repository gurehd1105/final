package com.example.gym.service;

import java.io.File;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.gym.controller.SportsEquipmentController;
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
	
	public void insertSportsEquipmentService(HttpSession session,
												String path,
												String itemName, 
												int itemPrice, 
												MultipartFile[] sportsEquipmentImgList) {
		
		//sportsEquipment 추가
		SportsEquipment sportsEquipment = new SportsEquipment();
		sportsEquipment.setEmployeeNo(1); //employee session 구현 후 수정
		sportsEquipment.setItemName(itemName);
		sportsEquipment.setItemPrice(itemPrice);
		
		//mapper 호출
		int row1 = sportsEquipmentMapper.insertSportsEquipment(sportsEquipment);
		
		//sportsEquipment 정보 추가 실패했을 경우 -> 강제로 예외발생 트랜잭션 처리
		if(row1 != 1) {
			throw new RuntimeException();
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
				throw new RuntimeException();
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

}