package com.example.gym.controller;

import com.example.gym.service.BranchService;
import com.example.gym.service.CalendarService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ProgramService;
import com.example.gym.service.ReservationService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Page;
import com.example.gym.vo.ProgramDate;
import com.example.gym.vo.ProgramReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/reservation")
public class ReservationController extends DefaultController {

    @Autowired
    CalendarService calendarService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BranchService branchService;

    @Autowired
    ProgramService programService;

    // 캘린더 출력
    @GetMapping("/calendar")
    public String calendar(
        Model model,
        HttpSession session,
        Page page,
        @RequestParam(required = false) Integer targetYear,
        @RequestParam(required = false) Integer targetMonth
    ) {
        // id 유효성검사
        Map<String, Object> loginCustomer = (Map) session.getAttribute(
            "loginCustomer"
        );
        if (loginCustomer == null) {
            return "customer/login";
        }

        System.out.println(loginCustomer);
        List<Branch> branchList = branchService.getBranchList(page);

        System.out.println("접속성공");
        Map<String, Object> calendarMap = calendarService.getCalendar(
            targetYear,
            targetMonth,
            session
        );
        model.addAttribute("calendarMap", calendarMap);
        model.addAttribute("branchList", branchList);

        return ViewRoutes.예약_달력;
    }

    // 예약 리스트
    @GetMapping("/list")
	public String reservationList(HttpSession session, Model model, Integer targetDay, Integer targetYear,
								  Integer targetMonth, ProgramReservation reservation, Page page,
								  @RequestParam(defaultValue = "1") int currentPage,
								  @RequestParam(defaultValue = "") String programActive, 
								  @RequestParam(defaultValue = "") String searchWord
								 ) {
        String targetYear2 = toJson(targetYear);
        String targetMonth2 = toJson(targetMonth);
        String targetDay2 = toJson(targetDay);

        String date = targetYear2 + targetMonth2 + targetDay2;
        if (targetMonth2.length() == 1) {
            date = targetYear2 + "0" + targetMonth2 + targetDay2;
        }
        System.out.println(date + "날짜");

        Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
        paramMap.put("paymentNo", loginCustomer.get("paymentNo"));
        paramMap.put("programDate", date);
        System.out.println(paramMap + "<-- paramMap");

		Map<String, Object> list = reservationService.selectReservationList(paramMap);
        Object reservationList = list.get("reservationList");
        List<Branch> branchList = branchService.getBranchList(page);
		Map<String, Object> programList = programService.selectProgramListService(session, currentPage, programActive,
																				  searchWord);

		model.addAttribute("programList", toJson(programList));
		model.addAttribute("reservationList", toJson(reservationList));
        model.addAttribute("branchList", toJson(branchList));
        model.addAttribute("targetYear", targetYear);
        model.addAttribute("targetMonth", targetMonth);
        model.addAttribute("targetDay", targetDay);
        System.out.println(programList + "<---programList");
        System.out.println(reservationList + "<---reservationList");
        System.out.println(branchList + "<---branchList");
        System.out.println(targetYear + "<-- targetYear");
        System.out.println(targetMonth + "<-- targetMonth");
        System.out.println(targetDay + "<-- targetDay");

        return ViewRoutes.예약_조회;
    }

    // 예약 추가
    @GetMapping("/insert")
    public String insertReservation(
			HttpSession session, Model model, Page page, ProgramReservation reservation, ProgramDate programDate,
								 @RequestParam(defaultValue = "1") int currentPage, 
								 @RequestParam(defaultValue = "Y") String programActive,
								 @RequestParam(defaultValue = "") String searchWord){
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
        reservation.setPaymentNo((Integer) loginCustomer.get("paymentNo"));
        System.out.println(loginCustomer + "<--loginCustomer");
        Map<String, Object> check = null;
        List<Map<String, Object>> list = reservationService.check((int) loginCustomer.get("customerNo"));
        if(!list.isEmpty()) {
        	 check = list.get(0);
        }      
        page.setRowPerPage(-1);
        List<Branch> branchList = branchService.getBranchList(page);
        Map<String, Object> programList = programService.selectProgramListService(
											session, currentPage, programActive, searchWord);
		model.addAttribute("programList", toJson(programList));
        model.addAttribute("branchList", toJson(branchList));
        if(check != null) {
        	model.addAttribute("check", check);
        }        
        System.out.println(check +"<--check");
        System.out.println(loginCustomer +"<-- loginCustomer");
        System.out.println(branchList + "<--branchList");
        System.out.println(programList + "<--programList");

        return ViewRoutes.예약_추가;
    }

    @GetMapping("/program/{program_no}/reservationInfo")
    @ResponseBody
	public ResponseEntity<List<ProgramDate>> reservationInfos(@PathVariable int program_no) {
        var result = reservationService.selectProgramDates(program_no);
        System.out.println(result + "<--result");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseEntity<?> insertReservation2(@RequestBody ProgramReservation reservation, HttpSession session) {
		log.info(reservation.toString());
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
        reservation.setPaymentNo((Integer) loginCustomer.get("paymentNo"));
        
        
        reservationService.insertReservation(reservation);
        return ResponseEntity.ok("예약이 정상적으로 완료되었습니다.");
    }

    // 예약 삭제
    @GetMapping("/delete")
    public String deleteReservation(ProgramReservation reservation,
							        Integer targetDay,
							        Integer targetYear,
							        Integer targetMonth){
        System.out.println(targetYear + " " + targetMonth + " " + targetDay + " " + "<--날짜");

        reservationService.deleteReservation(reservation);
		System.out.println(reservation.getProgramReservationNo() + "<-- 예약번호");
		return Redirect(ViewRoutes.예약_조회 +"?targetYear=" + targetYear + "&targetMonth=" + targetMonth + 
				"							&targetDay="+ targetDay);
    }
    
    
    
}