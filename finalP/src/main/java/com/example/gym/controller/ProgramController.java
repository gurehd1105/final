package com.example.gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gym.service.ProgramService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Employee;
import com.example.gym.vo.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("employee/program")
public class ProgramController extends DefaultController{
	@Autowired ProgramService programService;
	
	//program 리스트 (전 직원 접근 가능)
	@GetMapping("/list")
	public String selectProgramList(HttpSession session,
								Model model,
								Page page,
								@RequestParam(defaultValue = "1") int pageNum,
								@RequestParam(defaultValue = "") String programActive,
								@RequestParam(defaultValue = "") String searchWord) throws JsonProcessingException {
		
		//service 호출
		Map<String,Object> map = programService.list(session, page, pageNum, programActive, searchWord);
		
		//직원레벨 확인
		Employee employee = (Employee)session.getAttribute("loginEmployee");
		//int loginBranchLevel = employee.getBranchLevel(); 
		int branchLevel = 1;
		
		//jsp에서 출력할 model
		model.addAttribute("branchLevel",branchLevel);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("programActive",programActive);
		model.addAttribute("page",map.get("page"));
		model.addAttribute("programList",toJson(map.get("programList")));
		
		return ViewRoutes.프로그램_목록;
	}
	
	//program 수정 폼 (본사 직원 접근 가능)
	@GetMapping("/update")
	public String updateProgram(HttpSession session,
									Model model,
									@RequestParam int programNo	){
		
		//service 호출
		Map<String,Object> map = programService.selectProgramOneService(session, programNo);
		

		//jsp에서 출력할 model
		model.addAttribute("programNo",map.get("programNo"));
		model.addAttribute("employeeId",map.get("employeeId"));
		model.addAttribute("programName",map.get("programName"));
		model.addAttribute("programContent",map.get("programContent"));
		model.addAttribute("maxCustomer",map.get("maxCustomer"));
		model.addAttribute("programActive",map.get("programActive"));
		model.addAttribute("createdate",map.get("createdate"));
		model.addAttribute("updatedate",map.get("updatedate"));
		
		return ViewRoutes.프로그램_수정;
	}	
	
	//program 수정 액션 (본사 직원 접근 가능)
	@PostMapping("/update")
	public String updateProgram(HttpSession session,
									Model model,
									@RequestParam int programNo,
									@RequestParam int maxCustomer,
									@RequestParam String programContent,
									@RequestParam String programActive){
		
		
		//service 호출
		programService.updateProgramOneService(session, programNo, maxCustomer, programContent, programActive);
		
		return String.format("redirect:%s?programNo=%s", ViewRoutes.프로그램_수정, programNo);
	}
	
	//program 추가 폼 (본사 직원 접근 가능)
	@GetMapping("/insert")
	public String insert(HttpSession session){
		
		//service 호출
		programService.insert(session);
		
		return ViewRoutes.프로그램_추가;
	}
	
	//program 추가 액션 (본사 직원 접근 가능)
	@PostMapping("/insert")
	public String insert(HttpSession session,
						 @RequestParam String programName,
						 @RequestParam String programContent,
						 @RequestParam int maxCustomer,
						 @RequestParam String programActive){
		
		//service 호출
		programService.insert(session, programContent, programName, maxCustomer, programActive);
		
		return Redirect(ViewRoutes.프로그램_목록);
	}
}
