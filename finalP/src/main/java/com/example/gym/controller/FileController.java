package com.example.gym.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("file")
public class FileController {

	@PostMapping("upload/{folder}")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file, HttpSession session, @PathVariable String folder) {
		//String path = session.getServletContext().getRealPath("/upload/" + folder);
		String path2 = "C:\\Users\\GD\\git\\final\\finalP\\src\\main\\webapp\\upload\\"+folder;
		String path3 = "/home/ubuntu/upload/" + folder;
		log.info(path3 + " is path3");

	
		File f = new File(path3);
         
		if (!f.exists()) {
		    // 폴더가 존재하지 않으면 생성
		    log.info("path2 does not exist");
		    boolean success = f.mkdirs(); // 디렉토리 생성
		    if (success) {
		        log.info("Successfully created directory: " + path3);
		    } else {
		        log.error("Failed to create directory: " + path3);
		    }
		} else {
		    // 폴더가 이미 존재할 경우
		    log.info("path3 already exists 이게 떠야 돼 제발");
		}
		 
		 
		 
			
		if (file.getSize() != 0) {
			// fileName 값 세팅
			String fileName = UUID.randomUUID().toString();
			String oName = file.getOriginalFilename();
			String fileName2 = oName.substring(oName.lastIndexOf("."));

			// path 저장
			File copy = new File(path2 + "\\" + fileName + fileName2);
			log.info(copy.toString() + "	is copy.toString()");
			try {
				log.info("Start file.transfer");
				file.transferTo(copy);
				log.info(copy.getName() + " is copy.getName");
				return copy.getName();
			} catch (IllegalStateException | IOException e) {
				log.info("Fail file.transfer");
				e.printStackTrace();
			}
		}
		return "error";
	}
}
