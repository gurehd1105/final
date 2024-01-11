package com.example.gym.controller;

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
