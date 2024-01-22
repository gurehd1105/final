package com.example.gym.filter;

import java.io.IOException;
import java.util.Arrays;

import com.example.gym.vo.Employee;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 작업
    }

    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean isLogin =
            request.getSession().getAttribute("loginEmployee") != null;
        
        
        // 본사, 지점 직원 세션에 따른 권한검사
        boolean isLevel = false;
        if(isLogin) {
        	Employee loginEmployee = (Employee)request.getSession().getAttribute("loginEmployee");
        	isLevel = loginEmployee.getBranchLevel() == 1;        	
        }
        String[] allowPath = { "/employee/insert", "/sportsEquipment/insert", "/sportsEquipment/inventoryByHead", "/sportsEquipment/orderByHead"};
        boolean isAllowPath = Arrays.stream(allowPath).anyMatch(path -> path.equals(request.getServletPath()));
        
        boolean isLoginPath = request
            .getServletPath()
            .equals("/employee/login");
        // 세션에서 로그인 정보를 체크하는 로직
        if (!isLogin && !isLoginPath) {
            // 로그인되어 있지 않은 경우 로그인 페이지로 리다이렉트 또는 예외 처리
            response.sendRedirect(request.getContextPath() + "/employee/login"); // 로그인 페이지로 리다이렉트하는 예시
            return;
        }
        if(!isLevel && isAllowPath) {
        	response.sendRedirect(request.getContextPath() + "/employee/mypage");
        }

        // 로그인이 되어 있으면 다음 필터로 진행하거나 요청 처리
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 필터 종료 작업
    }
}
