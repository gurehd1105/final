package com.example.gym.controller;

import com.example.gym.mapper.ProgramMapper;
import com.example.gym.service.BranchService;
import com.example.gym.service.CalendarService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ProgramService;
import com.example.gym.service.ReservationService;
import com.example.gym.util.ViewRoutes;
import com.example.gym.vo.Branch;
import com.example.gym.vo.Customer;
import com.example.gym.vo.Page;
import com.example.gym.vo.Program;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Transactional
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
    @Autowired
    ProgramMapper programMapper;

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
								 @RequestParam(defaultValue = "Y") String programActive,
								 @RequestParam(defaultValue = "") String searchWord) {
								 
		Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
        reservation.setPaymentNo((Integer) loginCustomer.get("paymentNo"));
        System.out.println(loginCustomer + "<--loginCustomer");
 
        List<ProgramDate> list = reservationService.check((int) loginCustomer.get("customerNo"));
      

        List<Branch> branchList = branchService.getBranchList(page);
        Map<String, Object> map = new HashMap<>();
        map.put("programActive", programActive);
        map.put("searchWord", searchWord);
        List<Map<String, Object>> programList = programMapper.selectProgramList(map); 
		model.addAttribute("programList", toJson(programList));
        model.addAttribute("branchList", toJson(branchList));
              
       
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
    public ResponseEntity<Map<String, String>> insertReservation2(@RequestBody ProgramReservation reservation, HttpSession session) {
        log.info(reservation.toString());
        Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
        reservation.setPaymentNo((Integer) loginCustomer.get("paymentNo"));
        
        // 클라이언트로부터 전달받은 예약 날짜
        int programDate = reservation.getProgramDateNo(); 
        
        // 중복 체크를 위한 데이터 생성
        ProgramDate targetProgramDate = new ProgramDate();
        targetProgramDate.setProgramDateNo(programDate);
        
        // 중복 체크
        List<ProgramDate> check = reservationService.check((int) loginCustomer.get("customerNo"));
        System.out.println(check + "<--check");
        boolean duplicate = check.contains(targetProgramDate);
        System.out.println(programDate + "<--programdate");

        // 응답 메시지 생성
        Map<String, String> response = new HashMap<>();

        // 중복이 아닌 경우에만 예약 진행
        if (!duplicate) {
            reservationService.insertReservation(reservation);
            response.put("message", "예약이 정상적으로 완료되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            // 중복인 경우에는 예약을 진행하지 않고 클라이언트에게 알림
            response.put("message", "중복된 예약이 있습니다. 다른 날짜를 선택해주세요.");
            return ResponseEntity.ok(response);
        }
    }

    
    /**
    @PostMapping("/check")
    @ResponseBody
    public int check(@RequestBody Map<String, Object> programDate, HttpSession session) {
    	int result = 0;
    	Map<String, Object> loginCustomer = (Map) session.getAttribute("loginCustomer");
    	
    	List<ProgramDate> check = reservationService.check((int) loginCustomer.get("customerNo"));
    	System.out.println(check +"<--check");
    	boolean duplicate = check.stream()
    		    .anyMatch(pd -> pd.getProgramDate().equals(programDate.get("programDate")));
    	System.out.println(programDate +"<--programdate");
    	if(duplicate) {
    		result =1;
    	}
   	
    	return result;
    }
*/
    // 예약 삭제
    @PostMapping("/delete")
    public String deleteReservation(@RequestBody ProgramReservation reservation,
							        Integer targetDay,
							        Integer targetYear,
							        Integer targetMonth){
        System.out.println(targetYear + " " + targetMonth + " " + targetDay + " " + "<--날짜");
        System.out.println(reservation + "<-- reservation");

        int deletedRows = reservationService.deleteReservation(reservation);
		System.out.println(reservation.getProgramReservationNo() + "<-- 예약번호");
		System.out.println(reservation.getProgramReservationNo() + " deleted. " + deletedRows + " row(s) deleted");

		return Redirect(ViewRoutes.예약_조회 +"?targetYear=" + targetYear + "&targetMonth=" + targetMonth 
					                       + "&targetDay="+ targetDay);
    }
    
    
    
}