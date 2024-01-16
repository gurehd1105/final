package com.example.gym.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.gym.vo.SearchParam;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultController {
	ObjectMapper mapper = new ObjectMapper();
	
	public String toJson(Object obj) {
		String rtn = "";
		try {
			rtn = mapper.writeValueAsString(obj);
		} catch (Exception ex) {
			rtn = "error";
		}
		return rtn;
	}
	
}
