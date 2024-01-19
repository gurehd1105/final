package com.example.gym.mapper;

import com.example.gym.vo.SportsEquipment;
import com.example.gym.vo.SportsEquipmentImg;
import com.example.gym.vo.SportsEquipmentInventory;
import com.example.gym.vo.SportsEquipmentOrder;
import com.example.gym.vo.SportsEquipmentSearchParam;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SportsEquipmentMapper {
    //sportsEquipment 추가
    int insert(SportsEquipment sportsEuipment);

    //sportsEquipmentImg 추가
    int insertImg(SportsEquipmentImg sportsEquipmentImg);

    //sportsEquipment 목록 + 검색 + 페이징
    List<SportsEquipment> list(SportsEquipmentSearchParam param);
    //lastPage 구하기 위한 sportsEquipment 수
    int totalCnt(SportsEquipmentSearchParam param);

    //sportsEquipment 상세보기
    SportsEquipment one(int sportsEquipmentNo);
    List<SportsEquipmentImg> imgList(int sportsEquipmentNo);

    //sportsEquipment 수정
    int update(SportsEquipment sportsEuipment);

    //sportsEquipmentImg 개별 삭제
    int deleteImg(int sportsEquipmentImgNo);

    //sportsEquipmentOrder 추가
    int insertOrder(SportsEquipmentOrder sportsEuipmentOrder);

    //sportsEquipmentOrder 리스트 + 검색 + 페이징 (본사)
    List<SportsEquipmentOrder> orderByHead(SportsEquipmentSearchParam param);
    //lastPage 구하기 위한 sportsEquipmentOrder 수 (본사)
    int orderHeadCnt(SportsEquipmentSearchParam param);

    //sportsEquipmentOrder 리스트 + 검색 + 페이징 (지점)
    List<SportsEquipmentOrder> orderByBranch(SportsEquipmentSearchParam param);
    //lastPage 구하기 위한 sportsEquipmentOrder 수 (지점)
    int orderBranchCnt(SportsEquipmentSearchParam param);

    //sportsEquipmentOrder 상태 수정 (본사)
    int updateOrder(SportsEquipmentOrder sportsEuipmentOrder);

    //sportsEquipmentOrder 삭제 (지점)
    int deleteOrder(SportsEquipmentOrder sportsEuipmentOrder);

    //sportsEquipmentInventory 출력 (본사)
    List<SportsEquipmentInventory> inventoryByHead(
        SportsEquipmentSearchParam param
    );

    //sportsEquipmentInventory 출력 (지점)
    List<SportsEquipmentInventory> inventoryByBranch(
        SportsEquipmentSearchParam param
    );

    //lastPage 구하기 위한 sportsEquipmentInventory 수 (본사)
    int inventoryByHeadCnt(SportsEquipmentSearchParam param);

    //lastPage 구하기 위한 sportsEquipmentInventory 수 (지점)
    int inventoryByBranchCnt(SportsEquipmentSearchParam param);

    //장비 상세보기 창에서 지점 재고확인 (지점)
    Map<String, Object> inventoryOneByBranch(Map<String, Object> paramMap);

    // 아직 처리 안 된 발주내역
    int countNotYetOrder();
}
