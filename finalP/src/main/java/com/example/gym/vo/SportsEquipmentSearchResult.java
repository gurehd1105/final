package com.example.gym.vo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Component
public class SportsEquipmentSearchResult {
    private String searchItem;
    private String searchBranch;
    private String searchWord;
    private String equipmentActive;
    private String beginDate;
    private String endDate;
    private int lastPage;
    private List<Map<String, Object>> list;
    private String inventory;
    private String inventoryList;
    private String orderList;
    
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
