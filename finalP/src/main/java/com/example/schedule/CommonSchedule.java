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

	// 고정된 시간 간격으로 스케줄링
	@Scheduled(fixedRate = 1000 * 30) // 30초마다 실행
	public void scheduleExample() {
		List<String> images = new ArrayList<>();
		images.addAll(customerService.selectAllCustomerImage());
		images.addAll(employeeService.selectAllEmployeeImage());
		for (String path : images) {
			File file = new File(DEFAULT_PATH + path);
			String message = file.exists() ? "은 존재하는 파일입니다." : "은 존재하지 않습니다. (삭제가능)";
			// System.out.println(path + " : " + message);
		}
	}
}
