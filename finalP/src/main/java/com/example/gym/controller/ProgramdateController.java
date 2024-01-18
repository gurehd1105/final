package com.example.gym.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.ProgramDateService;
import com.example.gym.service.ProgramService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.ProgramDate;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/programDate")
public class ProgramdateController extends DefaultController{
	@Autowired
	ProgramDateService programDateService;
	@Autowired
    ProgramService programService;
	
	 //관리자 프로그램 예약 가능 정보 조회
    @GetMapping("/list")
    public String programDateList(ProgramDate programDate, Model model){
        List<Map<String, Object>> programDateList = programDateService.programDateList(programDate);
        model.addAttribute("programDateList",toJson(programDateList));
        System.out.println (programDateList + "<--programDateList");
        return ViewRoutes.예약_프로그램_조회;
    }
 
    // 관리자용 프로그램 진행일 추가
    @GetMapping("/insert")
    public String insertProgramDate(ProgramDate programDate, HttpSession session, Model model,
							        @RequestParam(defaultValue = "1") int currentPage,
							        @RequestParam(defaultValue = "Y") String programActive,
							        @RequestParam(defaultValue = "") String searchWord){
        Map<String, Object> programList = programService.selectProgramListService(session, currentPage, programActive,searchWord);
        model.addAttribute("programList",toJson(programList));
        System.out.println(programList + "<--programList");
        return ViewRoutes.예약_프로그램_추가;
    }

    @PostMapping("/insert")
    @ResponseBody
    public int insertProgramDate2(@RequestBody Map<String, Object> paramMap) {
        System.out.println(paramMap + "<-- paramMap");
        ProgramDate programDate = new ProgramDate();
        programDate.setProgramNo((Integer) paramMap.get("programNo"));
        String dateString = (String) paramMap.get("programDate");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
        programDate.setProgramDate(dateTime.toString());

        int result = programDateService.insertProgramDate(programDate);
        return result;
        
    }
    // 관리자용 프로그램 진행일 수정
    @GetMapping("/update")
    public String updateProgramDate(ProgramDate programDate, Model model){

    	List<Map<String, Object>> programDateList = programDateService.programDateList(programDate);
    	Map<String, Object> resultMap = programDateList.get(0);
    	model.addAttribute("resultMap", toJson(resultMap));
    	System.out.println(resultMap + "<--resultMap");
		return ViewRoutes.예약_프로그램_수정;
	}
    
    @PostMapping("/update")
    public String updateProgramDate(ProgramDate programDate ) {
    	programDateService.updateProgramDate(programDate);
    	
		return Redirect(ViewRoutes.예약_프로그램_조회);
    	
    }
    
    @PostMapping("/delete")
    @ResponseBody
    public int deleteProgramDate(@RequestBody ProgramDate programDate ) {    	
    	
    	int result = programDateService.deleteProgramDate(programDate);
		return result;
    	
    }
    
	
}
