package com.example.gym;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.gym.filter.CustomerLoginFilter;
import com.example.gym.filter.EmployeeLoginFilter;
import com.example.gym.filter.EncodingFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<EmployeeLoginFilter> sessionEmployeeLoginFilter() {
        FilterRegistrationBean<EmployeeLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EmployeeLoginFilter());
        registrationBean.addUrlPatterns("/employee/*"); // 모든 URL 패턴에 필터 적용
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<CustomerLoginFilter> sessionCustomerLoginFilter() {
        FilterRegistrationBean<CustomerLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomerLoginFilter());
        registrationBean.addUrlPatterns("/customer/*"); // 모든 URL 패턴에 필터 적용
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<EncodingFilter> encodingFilter() {
        FilterRegistrationBean<EncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EncodingFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 URL 패턴에 필터 적용
        return registrationBean;
    }
}
