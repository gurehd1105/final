package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.BranchService;
import com.example.gym.service.CalendarService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ProgramService;
import com.example.gym.service.ReservationService;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Page;
import com.example.gym.vo.ProgramDate;
import com.example.gym.vo.ProgramReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReservationController {	
	ObjectMapper mapper = new ObjectMapper();
	@Autowired CalendarService calendarService;
	@Autowired ReservationService reservationService;
	@Autowired CustomerService customerService;
	@Autowired BranchService branchService;
	@Autowired ProgramService programService;
	
	// 캘린더 출력
	@GetMapping("/calendar")
	public String calendar(Model model, HttpSession session,
							Page page,
			 			   @RequestParam(required = false) Integer targetYear ,
			 			   @RequestParam(required = false) Integer targetMonth			 			   		 			   
			 			   ) {
		// id 유효성검사
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
		if (loginCustomer == null) {
			return "customer/login";
		}
		
		System.out.println(loginCustomer);
		List<Branch> branchList = branchService.getBranchList(page);
		
		System.out.println("접속성공");					
		Map<String, Object> calendarMap = calendarService.getCalendar(targetYear, targetMonth, session);
		model.addAttribute("calendarMap", calendarMap);
		model.addAttribute("branchList", branchList);
			
		return "reservation/calendar";
	}
	
	
	// 예약 리스트
	@GetMapping("/reservationList")
	public String reservationList(HttpSession session, Model model, Integer targetDay, Integer targetYear, Integer targetMonth,	
								  ProgramReservation reservation,
								  @RequestParam(defaultValue = "1") int currentPage,
								  @RequestParam(defaultValue = "") String programActive,
								  @RequestParam(defaultValue = "") String searchWord,
								  Page page) throws JsonProcessingException {
		
		String targetYear2 = mapper.writeValueAsString(targetYear);
		String targetMonth2 = mapper.writeValueAsString(targetMonth);
		String targetDay2 = mapper.writeValueAsString(targetDay);
	
	
		String date = targetYear2+targetMonth2+targetDay2;
		if(targetMonth2.length()== 1){
			date = targetYear2+"0"+targetMonth2+targetDay2;
		}
		System.out.println(date +"날짜" );
		
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
		paramMap.put("paymentNo", loginCustomer.get("paymentNo"));
		paramMap.put("programDate", date );		
		System.out.println(paramMap + "<-- paramMap");
		
		
		Map<String, Object> list = reservationService.selectReservationList(paramMap);
		Object reservationList = list.get("reservationList");
		List<Branch> branchList = branchService.getBranchList(page);
		Map<String,Object> programList= programService.selectProgramListService(session, currentPage, programActive, searchWord);
		
		model.addAttribute("programList", mapper.writeValueAsString(programList));
		model.addAttribute("reservationList",mapper.writeValueAsString(reservationList));
		model.addAttribute("branchList", mapper.writeValueAsString(branchList));
		model.addAttribute("targetYear", targetYear);
		model.addAttribute("targetMonth", targetMonth);
		model.addAttribute("targetDay", targetDay);
		System.out.println(programList +"<---programList");
		System.out.println(reservationList +"<---reservationList");
		System.out.println(branchList +"<---branchList");
		System.out.println(targetYear + "<-- targetYear");
		System.out.println(targetMonth + "<-- targetMonth");
		System.out.println(targetDay + "<-- targetDay");
		
		
	
	    return "reservation/reservationList";
		
	}
	
	// 예약 추가
	@GetMapping("/insertReservation")
	public String insertReservation(HttpSession session, Model model, Page page,
									ProgramReservation reservation,
									@RequestParam(defaultValue = "1") int currentPage,
									@RequestParam(defaultValue = "Y") String programActive,
									@RequestParam(defaultValue = "") String searchWord,
									ProgramDate programDate) throws JsonProcessingException {
		
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
	    reservation.setPaymentNo((Integer) loginCustomer.get("paymentNo"));
		System.out.println(loginCustomer + "<--loginCustomer");

	    
		page.setRowPerPage(-1);
		List<Branch> branchList = branchService.getBranchList(page);
		Map<String,Object> programList= programService.selectProgramListService(session, currentPage, programActive, searchWord);
		model.addAttribute("programList", mapper.writeValueAsString(programList));
		model.addAttribute("branchList", mapper.writeValueAsString(branchList));

		System.out.println(branchList + "<--branchList");
		System.out.println(programList + "<--programList");

		return "reservation/insertReservation";
	}
	
	@GetMapping("/program/{program_no}/reservationInfo")
	@ResponseBody
	public ResponseEntity<List<ProgramDate>> reservationInfos(@PathVariable int program_no) {
		var result = reservationService.selectProgramDates(program_no);
		System.out.println(result + "<--result");
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/insertReservation")
	@ResponseBody
    public ResponseEntity<?> insertReservation2(@RequestBody ProgramReservation reservation, HttpSession session) {
		log.info(reservation.toString());
		
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
		reservation.setPaymentNo((Integer) loginCustomer.get("paymentNo"));
		reservationService.insertReservation(reservation);
        return ResponseEntity.ok("예약이 정상적으로 완료되었습니다.");
    }

	// 예약 삭제
	@GetMapping("/deleteReservationList")
	public String deleteReservation(ProgramReservation reservation,Integer targetDay, Integer targetYear, Integer targetMonth) {
		System.out.println(targetYear + " " + targetMonth + " " + targetDay + " " + "<--날짜");
		
		reservationService.deleteReservation(reservation);
		System.out.println(reservation.getProgramReservationNo()+"<-- 예약번호");
		return "redirect:/reservationList?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDay=" + targetDay;
	}
	
	// 프로그램 진행날짜 추가
	@GetMapping("/insertProgramDate")
	public String insertProgramDate(ProgramDate programDate,HttpSession session, Model model,
									@RequestParam(defaultValue = "1") int currentPage,
									@RequestParam(defaultValue = "Y") String programActive,
									@RequestParam(defaultValue = "") String searchWord) throws JsonProcessingException {
		Map<String,Object> programList= programService.selectProgramListService(session, currentPage, programActive, searchWord);
		model.addAttribute("programList", mapper.writeValueAsString(programList));
		System.out.println(programList + "<--programList");
		return "reservation/insertProgramDate";
		
	}
	
	@PostMapping("/insertProgramDate")
	@ResponseBody
	public int insertProgramDate2(@RequestBody Map<String, Object> paramMap) {
		System.out.println(paramMap + "<-- paramMap");
		ProgramDate programDate = new ProgramDate();
		programDate.setProgramNo(Integer.parseInt((String)paramMap.get("programNo")));
		programDate.setProgramDate((String)paramMap.get("programDate"));
		
		int result = reservationService.insertProgramDate(programDate);
		return result;
		
	}
	
	
	
	
	
	
}