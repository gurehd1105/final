package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.SportsEquipment;
import com.example.gym.vo.SportsEquipmentImg;
import com.example.gym.vo.SportsEquipmentOrder;

@Mapper
public interface SportsEquipmentMapper {
	
	//sportsEquipment 추가
	int insertSportsEquipment(SportsEquipment sportsEuipment);
	
	//sportsEquipmentImg 추가
	int insertSportsEquipmentImg(SportsEquipmentImg sportsEquipmentImg);
	
	//sportsEquipment 목록 + 검색 + 페이징
	List<Map<String,Object>> selectSportsEquipmentByPage(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipment 수
	int selectSportsEquipmentCnt(Map<String,Object> paramMap);
	
	//sportsEquipment 상세보기
	Map<String,Object> selectSportsEquipmentOne(int sportsEquipmentNo);
	List<SportsEquipmentImg> selectSportsEquipmentImgList(int sportsEquipmentNo);
	
	//sportsEquipment CRUD를 위해 본사소속 직원 확인하기
	int selectSearchEmployeeLevel(int employeeNo); 
	
	//sportsEquipment 수정
	int updateSportsEquipment(SportsEquipment sportsEuipment);
	
	//sportsEquipmentImg 개별 삭제
	int deleteOneSportsEquipmentImg(int sportsEquipmentImgNo);
	
	//sportsEquipment 삭제를 위해 sportsEquipmentImg 삭제
	//int deleteSportsEquipmentImg(int sportsEquipmentNo);
	
	//sportsEquipment 삭제
	//int deleteSportsEquipment(int sportsEquipmentNo);
	
	//sportsEquipmentOrder 추가
	int insertSportsEquipmentOrder(SportsEquipmentOrder sportsEuipmentOrder);
	
	//sportsEquipmentOrder 리스트 + 검색 + 페이징 (본사)
	List<Map<String,Object>> selectSportsEquipmentOrderByHead(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentOrder 수 (본사)
	int selectSportsEquipmentOrderHeadCnt(Map<String,Object> paramMap);
	
	//sportsEquipmentOrder 리스트 + 검색 + 페이징 (지점)
	List<Map<String,Object>> selectSportsEquipmentOrderByBranch(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentOrder 수 (지점)
	int selectSportsEquipmentOrderBranchCnt(Map<String,Object> paramMap);
	
	//sportsEquipmentOrder 상태 수정 (본사)
	int updateSportsEquipmentOrder(SportsEquipmentOrder sportsEuipmentOrder);
	
	//sportsEquipmentOrder 삭제 (지점)
	int deleteSportsEquipmentOrder(SportsEquipmentOrder sportsEuipmentOrder);
	
	//sportsEquipmentInventory 출력 (본사)
	List<Map<String,Object>> selectSportsEquipmentInventoryByHead(Map<String,Object> paramMap);
	
	//sportsEquipmentInventory 출력 (지점)
	List<Map<String,Object>> selectSportsEquipmentInventoryByBranch(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentInventory 수 (본사)
	int selectSportsEquipmentInventoryByHeadCnt(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentInventory 수 (지점)
	int selectSportsEquipmentInventoryByBranchCnt(Map<String,Object> paramMap);
}

