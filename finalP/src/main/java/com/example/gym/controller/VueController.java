package com.example.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vue")
public class VueController {

    @GetMapping("{route}")
    public String route(@PathVariable String route) {
        return Path(route);
    }
    
    private String Path(String route) {
		return "vue-sample/" + route;
    }
}
