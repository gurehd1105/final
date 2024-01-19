package com.example.gym;

import jakarta.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@SpringBootApplication
@ComponentScan(basePackages = { "com.example", "com.example.gym.controller" })
@EnableScheduling
@EnableAspectJAutoProxy
public class FinalPApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalPApplication.class, args);
    }

    @ExceptionHandler(Exception.class)
    public String handleExceptions(
        Exception ex,
        Model model,
        HttpServletRequest request
    ) {
        if (ex instanceof NoSuchElementException) {
            model.addAttribute("errorMessage", "올바르지 않은 접근입니다.");
            return "error/error";
        } else {
            model.addAttribute("errorMessage", "올바르지 않은 접근입니다.");
            return "error/error";
        }
    }
}
