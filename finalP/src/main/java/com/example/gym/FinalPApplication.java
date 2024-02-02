
package com.example.gym;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
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
        	model.addAttribute("errorMessage", ex.getMessage());
        	log.info(ex.getMessage());
            return "error/error";
        } else {
        	model.addAttribute("errorMessage", ex.getMessage());
        	log.info(ex.getMessage());
            return "error/error";
        }
    }
}