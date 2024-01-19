package com.example.aspect;

import com.example.gym.service.QuestionService;
import com.example.gym.service.SportsEquipmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class CommonAspect {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SportsEquipmentService seService;

    @Around(
        "execution(String com.example.gym..*Controller.*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)"
    )
    public Object showNotifications(ProceedingJoinPoint joinPoint)
        throws Throwable {
        HttpServletRequest request =
            (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
            ).getRequest();
        HttpSession session = request.getSession();
        Object loginEmployee = session.getAttribute("loginEmployee");

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Model) {
                Model model = (Model) arg;
                if (loginEmployee != null) {
                    model.addAttribute(
                        "question_notyet",
                        questionService.countNotYet()
                    );
                    model.addAttribute(
                        "order_notyet",
                        seService.countNotYetOrder()
                    );
                }
            }
        }

        return joinPoint.proceed();
    }
}
