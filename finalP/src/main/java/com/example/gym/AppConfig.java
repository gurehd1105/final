package com.example.gym;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.gym.filter.EmployeeLoginFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<EmployeeLoginFilter> sessionLoginFilter() {
        FilterRegistrationBean<EmployeeLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EmployeeLoginFilter());
        registrationBean.addUrlPatterns("/employee/*"); // 모든 URL 패턴에 필터 적용
        return registrationBean;
    }
}
