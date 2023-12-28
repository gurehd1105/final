package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.SportsEquipment;
import com.example.gym.vo.SportsEquipmentImg;

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
	
}
