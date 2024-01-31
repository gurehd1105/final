package com.example.schedule;

import com.example.gym.service.CustomerService;
import com.example.gym.service.EmployeeService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonSchedule {

   @Autowired
   CustomerService customerService;

   @Autowired
   EmployeeService employeeService;

   private final String DEFAULT_PATH = "src/main/webapp";
   private final String CUSTOMER_PATH = "src/main/webapp/upload/customer";
   private final String EMPLOYEE_PATH = "src/main/webapp/upload/employee";

   // 고정된 시간 간격으로 스케줄링
   @Scheduled(fixedRate = 1000 * 30) // 30초마다 실행
   public void ImageCleanup() {
         
      //현재 업로드 되어 있는 employee Img 리스트
      File employeeImg = new File(EMPLOYEE_PATH);
      String[] employeeImgList = employeeImg.list();
      
      List<String> employeeImages = new ArrayList<>();
      
        if (employeeImgList != null) {
            for (int i = 0; i < employeeImgList.length; i++) {
                employeeImgList[i] = EMPLOYEE_PATH + "/" + employeeImgList[i];
                employeeImages.add(employeeImgList[i]);
            }
        }
        
      //DB에 존재하는 Employee 이미지 
      List<String> DBEmployeeimage = new ArrayList<>();
      DBEmployeeimage.addAll(employeeService.selectAllEmployeeImage());
      
      List<String> DBEmployeeimages = new ArrayList<>();
      for (String img : DBEmployeeimage) {
         DBEmployeeimages.add(DEFAULT_PATH + img);
      }

      System.out.println("직원 이미지 확인");
      printAndCompareImages(employeeImages,DBEmployeeimages);
      //-------------------------------------------------------------------------------------------------------------//
      
      //현재 업로드 되어 있는 customer Img 리스트
      File customerImg = new File(CUSTOMER_PATH);
      String[] customerImgList = customerImg.list();
        
      List<String> customerImages = new ArrayList<>();
      
        if (customerImgList != null) {
            for (int i = 0; i < customerImgList.length; i++) {
               customerImgList[i] = CUSTOMER_PATH + "/" + customerImgList[i];
               customerImages.add(customerImgList[i]);
            }
        }
        
      //DB에 존재하는 Employee 이미지 
      List<String> DBCustomerimage = new ArrayList<>();
      DBCustomerimage.addAll(customerService.selectAllCustomerImage());;
      
      List<String> DBCustomerimages = new ArrayList<>();
      for (String img : DBCustomerimages) {
         DBCustomerimage.add(DEFAULT_PATH + img);
      }
      System.out.println("고객 이미지 확인");
      printAndCompareImages(customerImages,DBCustomerimages);
      
   }
   
    private void printAndCompareImages(List<String> fileList, List<String> images) {
        for (String imgName : fileList) {
            System.out.println(imgName);
            boolean existsInDB = images.contains(imgName);
            System.out.println("DB 존재 : " + (existsInDB ? "O" : "X"));
            
            if (!existsInDB) {
                deleteFile(imgName);
                System.out.println("파일 삭제: " + imgName);
                System.out.println();
            }
        }
    }
    
    private void deleteFile(String imgName) {
        File file = new File(imgName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("파일 삭제 성공");
            } else {
                System.out.println("파일 삭제 실패");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
    
    
}