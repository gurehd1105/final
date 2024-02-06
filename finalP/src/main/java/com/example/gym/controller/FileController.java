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
		String path = session.getServletContext().getRealPath("/upload/" + folder);
		{
			File f = new File(path);
			if (!f.exists()) f.mkdir();
		}
		if (file.getSize() != 0) {
			// fileName 값 세팅
			String fileName = UUID.randomUUID().toString();
			String oName = file.getOriginalFilename();
			String fileName2 = oName.substring(oName.lastIndexOf("."));

			// path 저장
			File copy = new File(path + "/" + fileName + fileName2);
			log.info(copy.toString());
			try {
				file.transferTo(copy);
				return copy.getName();
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return "error";
	}
}
