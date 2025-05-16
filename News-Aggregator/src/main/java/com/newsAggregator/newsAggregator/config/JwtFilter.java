package com.newsAggregator.newsAggregator.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Header URI" +request.getRequestURI().toString());

        if(request.getRequestURI().contains("/register")
                || request.getRequestURI().contains("/login")
                || request.getRequestURI().contains("/verifyToken")
                || request.getRequestURI().contains("/h2-console")){
            filterChain.doFilter(request, response);
            return;
        }

        String authenticationHeader = request.getHeader("authorization");
        if(authenticationHeader == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing Authorization Header");
            return;
        }

        if(!JwtUtill.validateJwtToken(authenticationHeader)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("provided token is not valid");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
