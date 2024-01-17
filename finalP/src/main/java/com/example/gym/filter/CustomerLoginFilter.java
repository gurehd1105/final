package com.example.gym.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomerLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 작업
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        // 로그인 정보체크
        boolean isLogin = request.getSession().getAttribute("loginCustomer") != null;
        boolean isLoginPath = request.getServletPath().equals("/customer/login");
        boolean isInsertPath = request.getServletPath().equals("/customer/insert");
       
        if (!isLogin && !(isLoginPath || isInsertPath)) {	 // 로그인되어 있지 않은 경우 로그인 페이지로 리다이렉트 또는 예외 처리           
            response.sendRedirect("/customer/login"); 
            return;
        } else if(isLogin && (isLoginPath || isInsertPath)){	// 로그인 상태 & 회원가입 / 로그인페이지 접속 시 
        	response.sendRedirect("/home");		
        	return;
        }											 // 로그인이 되어 있으면 다음 필터로 진행하거나 요청 처리
        	filterChain.doFilter(request, response);      	
       
    }

    @Override
    public void destroy() {
        // 필터 종료 작업
    }
}
