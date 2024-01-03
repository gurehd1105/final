package com.example.gym.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @GetMapping("/emailCheck")
    @ResponseBody
    public ResponseEntity<Boolean> emailCheck() {
    	return ResponseEntity.ok(Math.random() >= 0.5);
    }
}
