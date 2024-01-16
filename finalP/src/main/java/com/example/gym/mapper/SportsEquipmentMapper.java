package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.SportsEquipment;
import com.example.gym.vo.SportsEquipmentImg;
import com.example.gym.vo.SportsEquipmentOrder;
import com.example.gym.vo.SportsEquipmentSearchParam;

@Mapper
public interface SportsEquipmentMapper {
	
	//sportsEquipment 추가
	int insert(SportsEquipment sportsEuipment);
	
	//sportsEquipmentImg 추가
	int insertImg(SportsEquipmentImg sportsEquipmentImg);
	
	//sportsEquipment 목록 + 검색 + 페이징
	List<SportsEquipment> list(SportsEquipmentSearchParam param);
	
	//lastPage 구하기 위한 sportsEquipment 수
	int sportsEquipmentCnt(Map<String,Object> paramMap);
	int totalCnt(SportsEquipmentSearchParam param);
	
	//sportsEquipment 상세보기
	Map<String,Object> sportsEquipmentOne(int sportsEquipmentNo);
	List<SportsEquipmentImg> imgList(int sportsEquipmentNo);
	
	//sportsEquipment CRUD를 위해 본사소속 확인하기
	int selectSearchEmployeeLevel(int employeeNo); 
	//지점 확인하기
	int selectSearchEmployeeBranch(int employeeNo); 
	
	//sportsEquipment 수정
	int update(SportsEquipment sportsEuipment);
	
	//sportsEquipmentImg 개별 삭제
	int deleteImg(int sportsEquipmentImgNo);

	//sportsEquipmentOrder 추가
	int insertOrder(SportsEquipmentOrder sportsEuipmentOrder);
	
	//sportsEquipmentOrder 리스트 + 검색 + 페이징 (본사)
	List<Map<String,Object>> orderByHead(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentOrder 수 (본사)
	int orderHeadCnt(Map<String,Object> paramMap);
	
	//sportsEquipmentOrder 리스트 + 검색 + 페이징 (지점)
	List<Map<String,Object>> orderByBranch(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentOrder 수 (지점)
	int orderBranchCnt(Map<String,Object> paramMap);
	
	//sportsEquipmentOrder 상태 수정 (본사)
	int updateOrder(SportsEquipmentOrder sportsEuipmentOrder);
	
	//sportsEquipmentOrder 삭제 (지점)
	int deleteOrder(SportsEquipmentOrder sportsEuipmentOrder);
	
	//sportsEquipmentInventory 출력 (본사)
	List<Map<String,Object>> inventoryByHead(Map<String,Object> paramMap);
	
	//sportsEquipmentInventory 출력 (지점)
	List<Map<String,Object>> inventoryByBranch(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentInventory 수 (본사)
	int inventoryByHeadCnt(Map<String,Object> paramMap);
	
	//lastPage 구하기 위한 sportsEquipmentInventory 수 (지점)
	int inventoryByBranchCnt(Map<String,Object> paramMap);
	
	//장비 상세보기 창에서 지점 재고확인 (지점)
	Map<String,Object> inventoryOneByBranch(Map<String,Object> paramMap);
}

