package com.example.gym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.ProgramMapper;
import com.example.gym.vo.Program;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProgramService {
	@Autowired private ProgramMapper programMapper;
	
	//sportsEquipmentList 출력
	public Map<String,Object> selectProgramListService(HttpSession session,
																	int currentPage,
																	String programActive,
																	String searchWord) {
		//디버깅
		log.info(searchWord);
		log.info("Current page: {}", currentPage);
		log.info("programActive : {}", programActive);
		
		//페이징
		int rowPerPage = 6; //한 페이지에 표시할 equipment 수 
		int beginRow = (currentPage - 1) * rowPerPage;
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap1 = new HashMap<>();
		paramMap1.put("searchWord", searchWord);
		paramMap1.put("programActive", programActive);
		
		//mapper 호출 
		int programCnt = programMapper.programCnt(paramMap1);
		int lastPage = programCnt/rowPerPage;
		
		if(programCnt%rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		//디버깅
		log.info("lastPage : {}", lastPage);
		log.info("programCnt : {}", programCnt);
		
		//mapper의 매개변수로 들어갈 paramMap 생성
		Map<String,Object> paramMap2 = new HashMap<>();
		paramMap2.put("searchWord", searchWord);
		paramMap2.put("programActive", programActive);
		paramMap2.put("beginRow", beginRow);
		paramMap2.put("rowPerPage", rowPerPage);
		
		//mapper 호출
		List<Map<String, Object>> programList = programMapper.selectProgramList(paramMap2);
		
		//controller에 보내줄 resultMap 생성
		Map<String,Object> resultMap = new HashMap<>();
		
		resultMap.put("searchWord", searchWord);
		resultMap.put("programActive", programActive);
		resultMap.put("lastPage", lastPage);
		resultMap.put("programList", programList);
		
		return resultMap;
	}
	
	//program 수정 폼
	public Map<String,Object> selectProgramOneService(HttpSession session,
																int programNo) {
		//디버깅
		log.info("programNo : {}", programNo);
		
		//본사 직원 확인
//		Employee employee =(Employee)session.getAttribute("loginEmployee");
//		int branchLevel = employee.getBranchLevel();
		int branchLevel = 1;
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		//mapper 호출
		Map<String,Object> resultMap  = programMapper.selectProgramOne(programNo);

		return resultMap;
	}	
	
	//program 수정 액션
	public void updateProgramOneService(HttpSession session,
												int programNo,
												int maxCustomer,
												String programActive) {
		//디버깅
		log.info("programNo : {}", programNo);
		log.info("maxCustomer : {}", maxCustomer);
		log.info("programActive : {}", programActive);
		
		//본사 직원 확인
//		Employee employee =(Employee)session.getAttribute("loginEmployee");
//		int branchLevel = employee.getBranchLevel();
		int branchLevel = 1;
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//mapper에 보내줄 paramMap 생성
		Map<String,Object> paramMap = new HashMap<>();
		
		paramMap.put("programNo", programNo);
		paramMap.put("programActive", programActive);
		paramMap.put("maxCustomer", maxCustomer);
	
		
		//mapper 호출
		int row  = programMapper.updateProgram(paramMap);

		//mapper 호출 디버깅
		if (row != 1) {
		    log.info("program 수정실패 : row - {}", row);
		} else {
		    log.info("program 수정성공: row - {}", row);
		}
	}	
	
	//program 추가 폼
	public void insert(HttpSession session) {

		//본사 직원 확인
//		Employee employee =(Employee)session.getAttribute("loginEmployee");
//		int branchLevel = employee.getBranchLevel();
		int branchLevel = 1;
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
	}	

	//program 추가 액션
	public void insert(HttpSession session,
					   String programContent,
					   String programName,
					   int maxCustomer,
					   String programActive) {
		//디버깅
		log.info("programContent : {}", programContent);
		log.info("programName : {}", programName);
		log.info("maxCustomer : {}", maxCustomer);
		log.info("programActive : {}", programActive);
		
		//본사 직원 확인
		//Employee employee =(Employee)session.getAttribute("loginEmployee");
		//int branchLevel = employee.getBranchLevel();
		int branchLevel = 1;
		log.info( branchLevel + " <-- 1:본사 0:지점");
		if(branchLevel != 1) {	
			throw new RuntimeException("예외발생 : 본사직원이 아닙니다. ");
		}
		
		//controller에 보내줄 Program 객체 생성
		Program program = new Program();
		log.warn("수정예정");
		//program.setEmployeeNo(employee.getEmployeeNo());
		program.setEmployeeNo(1);
		program.setActive(programActive);
		program.setProgramMaxCustomer(maxCustomer);
		program.setProgramName(programName);
		program.setProgramContent(programContent);
		
		
		//mapper 호출
		boolean success  = programMapper.insert(program) != 1;

		//mapper 호출 디버깅
		if (success) {
		    log.info("program 추가실패 : success - {}", success);
		} else {
		    log.info("program 추가성공: success - {}", success);
		}
	}	
}
